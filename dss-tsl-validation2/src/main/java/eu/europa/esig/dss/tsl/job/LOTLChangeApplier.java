package eu.europa.esig.dss.tsl.job;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.tsl.cache.CacheKey;
import eu.europa.esig.dss.tsl.cache.TLChangesCacheAccess;
import eu.europa.esig.dss.tsl.dto.OtherTSLPointerDTO;
import eu.europa.esig.dss.tsl.parsing.AbstractParsingResult;
import eu.europa.esig.dss.tsl.parsing.LOTLParsingResult;

public class LOTLChangeApplier {

	private static final Logger LOG = LoggerFactory.getLogger(LOTLChangeApplier.class);

	private final TLChangesCacheAccess cacheAccess;

	private final Map<CacheKey, AbstractParsingResult> oldValues;
	private final Map<CacheKey, AbstractParsingResult> newValues;

	public LOTLChangeApplier(final TLChangesCacheAccess cacheAccess, 
			final Map<CacheKey, AbstractParsingResult> oldValues, final Map<CacheKey, AbstractParsingResult> newValues) {
		this.cacheAccess = cacheAccess;
		this.oldValues = oldValues;
		this.newValues = newValues;
	}

	public void analyzeAndApply() {
		for (CacheKey lotlCacheKey : oldValues.keySet()) {
			Map<String, List<CertificateToken>> oldUrlCerts = getTLPointers(oldValues.get(lotlCacheKey));
			Map<String, List<CertificateToken>> newUrlCerts = getTLPointers(newValues.get(lotlCacheKey));

			detectUrlChanges(oldUrlCerts, newUrlCerts);
			detectSigCertsChanges(oldUrlCerts, newUrlCerts);
		}
	}

	private Map<String, List<CertificateToken>> getTLPointers(AbstractParsingResult parsingResult) {
		if (parsingResult instanceof LOTLParsingResult) {
			LOTLParsingResult lotlParsingResult = (LOTLParsingResult) parsingResult;
			List<OtherTSLPointerDTO> tlPointers = lotlParsingResult.getTlPointers();
			return tlPointers.stream().collect(Collectors.toMap(OtherTSLPointerDTO::getLocation, s -> s.getCertificates()));
		}
		return Collections.emptyMap();
	}

	private void detectUrlChanges(Map<String, List<CertificateToken>> oldUrlCerts, Map<String, List<CertificateToken>> newUrlCerts) {
		for (String oldUrl : oldUrlCerts.keySet()) {
			if (!newUrlCerts.containsKey(oldUrl)) {
				LOG.info("Expired TL URL : {}", oldUrl);
				cacheAccess.toBeDeleted(new CacheKey(oldUrl));
			}
		}
	}

	private void detectSigCertsChanges(Map<String, List<CertificateToken>> oldUrlCerts, Map<String, List<CertificateToken>> newUrlCerts) {
		for (String newUrl : newUrlCerts.keySet()) {
			List<CertificateToken> oldCerts = oldUrlCerts.get(newUrl);
			List<CertificateToken> newCerts = newUrlCerts.get(newUrl);
			if (oldCerts == null || !oldCerts.equals(newCerts)) {
				LOG.info("Signing certificates change detected for TL URL : {}", newUrl);
				cacheAccess.expireSignatureValidation(new CacheKey(newUrl));
			}
		}
	}

}
