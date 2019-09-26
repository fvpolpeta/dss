package eu.europa.esig.dss.tsl.job;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.service.http.commons.FileCacheDataLoader;
import eu.europa.esig.dss.spi.DSSUtils;
import eu.europa.esig.dss.spi.x509.CommonTrustedCertificateSource;
import eu.europa.esig.dss.tsl.cache.CacheCleaner;
import eu.europa.esig.dss.tsl.function.TrustServicePredicate;
import eu.europa.esig.dss.tsl.function.TrustServiceProviderPredicate;
import eu.europa.esig.dss.tsl.source.TLSource;
import eu.europa.esig.dss.tsl.summary.TLInfo;
import eu.europa.esig.dss.tsl.summary.ValidationJobSummary;
import eu.europa.esig.trustedlist.jaxb.tsl.TSPServiceType;
import eu.europa.esig.trustedlist.jaxb.tsl.TSPType;

public class TLValidationJobTest {

	private static TLValidationJob tlValidationJob;
	private static CacheCleaner cacheCleaner;
	private static FileCacheDataLoader offlineFileLoader;
	private static FileCacheDataLoader onlineFileLoader;
	
	private static Map<String, byte[]> urlMap;
	
	private static File cacheDirectory;
	
	private static final String CZ_URL = "https://tsl.gov.cz/publ/TSL_CZ.xtsl";
	private TLSource czSource;
	private CertificateToken czSigningCertificate;
	
	@BeforeAll
	public static void initBeforeAll() throws IOException {
		urlMap = new HashMap<String, byte[]>();
		urlMap.put("https://www.signatur.rtr.at/currenttl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/AT.xml")));
		urlMap.put("https://tsl.belgium.be/tsl-be.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/BE.xml")));
		urlMap.put("https://crc.bg/files/_en/TSL_BG.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/BG.xml")));
		urlMap.put("http://www.mcw.gov.cy/mcw/dec/dec.nsf/all/B28C11BBFDBAC045C2257E0D002937E9/$file/TSL-CY-sign.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/CY.xml")));
		urlMap.put(CZ_URL, Files.readAllBytes(Paths.get("src/test/resources/lotlCache/CZ.xml")));
		urlMap.put("https://www.nrca-ds.de/st/TSL-XML.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/DE.xml")));
		urlMap.put("https://www.digst.dk/TSLDKxml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/DK.xml")));
		urlMap.put("https://sr.riik.ee/tsl/estonian-tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/ES.xml"))); // wrong country code
		urlMap.put("https://www.eett.gr/tsl/EL-TSL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/EL.xml")));
		urlMap.put("https://sede.minetur.gob.es/Prestadores/TSL/TSL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/ES.xml")));
		urlMap.put("https://dp.trustedlist.fi/fi-tl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/FI.xml")));
		urlMap.put("http://www.ssi.gouv.fr/eidas/TL-FR.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/FR.xml")));
		urlMap.put("https://www.mingo.hr/TLS/TSL-HR.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/HR.xml")));
		urlMap.put("http://www.nmhh.hu/tl/pub/HU_TL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/HU.xml")));
		urlMap.put("http://files.dcenr.gov.ie/rh/Irelandtslsigned.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/IE.xml")));
		urlMap.put("http://www.neytendastofa.is/library/Files/TSl/tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/IS.xml")));
		urlMap.put("https://eidas.agid.gov.it/TL/TSL-IT.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/IT.xml")));
		urlMap.put("https://www.llv.li/files/ak/xml-llv-ak-tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/LI.xml")));
		urlMap.put("https://elektroninisparasas.lt/LT-TSL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/LT.xml")));
		urlMap.put("https://portail-qualite.public.lu/content/dam/qualite/fr/publications/confiance-numerique/liste-confiance-nationale/tsl-xml/tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/LU.xml")));
		urlMap.put("https://trustlist.gov.lv/tsl/latvian-tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/LV.xml")));
		urlMap.put("https://www.mca.org.mt/tsl/MT_TSL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/MT.xml")));
		urlMap.put("https://www.agentschaptelecom.nl/binaries/agentschap-telecom/documenten/publicaties/2018/januari/01/digitale-statuslijst-van-vertrouwensdiensten/current-tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/NL.xml")));
		urlMap.put("https://tl-norway.no/TSL/NO_TSL.XML", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/NO.xml")));
		urlMap.put("https://www.nccert.pl/tsl/PL_TSL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/PL.xml")));
		urlMap.put("https://www.gns.gov.pt/media/1894/TSLPT.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/PT.xml")));
		// RO is missed
		urlMap.put("https://trustedlist.pts.se/SE-TL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/SE.xml")));
		urlMap.put("http://www.mju.gov.si/fileadmin/mju.gov.si/pageuploads/DID/Informacijska_druzba/eIDAS/SI_TL.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/SI.xml")));
		urlMap.put("http://tl.nbu.gov.sk/kca/tsl/tsl.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/SK.xml")));
		urlMap.put("https://www.tscheme.org/UK_TSL/TSL-UKsigned.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/UK.xml")));
		urlMap.put("https://www.tscheme.org/UK_TSL/TSL-UKsigned.xml", Files.readAllBytes(Paths.get("src/test/resources/lotlCache/UK.xml")));
		
		cacheDirectory = new File("target/cache");
		
		offlineFileLoader = new FileCacheDataLoader();
		offlineFileLoader.setCacheExpirationTime(Long.MAX_VALUE);
		offlineFileLoader.setDataLoader(new MockDataLoader(urlMap));
		offlineFileLoader.setFileCacheDirectory(cacheDirectory);
		
		Map<String, byte[]> onlineMap = new HashMap<String, byte[]>();
		onlineMap.putAll(urlMap);
		
		onlineFileLoader = new FileCacheDataLoader();
		onlineFileLoader.setCacheExpirationTime(0);
		onlineFileLoader.setDataLoader(new MockDataLoader(onlineMap));
		onlineFileLoader.setFileCacheDirectory(cacheDirectory);
		
		cacheCleaner = new CacheCleaner();
		cacheCleaner.setDSSFileLoader(offlineFileLoader);
		cacheCleaner.setCleanFileSystem(true);
		
		tlValidationJob = new TLValidationJob();
		tlValidationJob.setOfflineDataLoader(offlineFileLoader);
		tlValidationJob.setOnlineDataLoader(onlineFileLoader);
		tlValidationJob.setCacheCleaner(cacheCleaner);
	}
	
	@BeforeEach
	public void init() {
		czSource = new TLSource();
		czSource.setUrl(CZ_URL);
		CommonTrustedCertificateSource commonTrustedCertificateSource = new CommonTrustedCertificateSource();
		czSigningCertificate = DSSUtils.loadCertificateFromBase64EncodedString("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");
		commonTrustedCertificateSource.addCertificate(czSigningCertificate);
		czSource.setCertificateSource(commonTrustedCertificateSource);
		czSource.setTrustServicePredicate(new TrustServicePredicate() {
			@Override
			public boolean test(TSPServiceType t) {
				return true;
			}
		});
		czSource.setTrustServiceProviderPredicate(new TrustServiceProviderPredicate() {
			@Override
			public boolean test(TSPType t) {
				return true;
			}
		});
		tlValidationJob.setTrustedListSources(czSource);
	}
	
	@Test
	public void test() {
		tlValidationJob.offlineRefresh();
		ValidationJobSummary summary = tlValidationJob.getSummary();
		
		assertEquals(0, summary.getNumberOfProcessedLOTLs());
		assertEquals(1, summary.getNumberOfProcessedTLs());
		
		List<TLInfo> tlInfos = summary.getTLInfos();
		assertEquals(1, tlInfos.size());
		
		TLInfo czTL = tlInfos.get(0);
		assertNotNull(czTL.getLastLoadingDate());
		assertFalse(czTL.getLastFileSynchronizationDate().after(czTL.getLastLoadingDate()));
		
		// TODO: synchronization is not implemented yet
//		assertEquals(CacheStateEnum.SYNCHRONIZED, czTL.getDownloadJobState());
//		assertEquals(CacheStateEnum.SYNCHRONIZED, czTL.getParsingJobState());
//		assertEquals(CacheStateEnum.SYNCHRONIZED, czTL.getValidationJobState());
		
		assertNull(czTL.getDownloadJobExceptionMessage());
		assertNull(czTL.getDownloadJobExceptionStackTrace());
		assertNull(czTL.getParsingJobExceptionMessage());
		assertNull(czTL.getParsingJobExceptionStackTrace());
		assertNull(czTL.getValidationJobExceptionMessage());
		assertNull(czTL.getValidationJobExceptionStackTrace());
		
		assertNotNull(czTL.getUrl());
		
		assertNotNull(czTL.getSequenceNumber());
		assertNotNull(czTL.getTLVersion());
		assertEquals("CZ", czTL.getTerritory());
		assertNotNull(czTL.getIssueDate());
		assertNotNull(czTL.getNextUpdateDate());
		assertTrue(czTL.getIssueDate().before(czTL.getNextUpdateDate()));
		assertNotNull(czTL.getDistributionPoints());
		assertNotNull(czTL.getTrustServiceProviders());
		
		assertEquals(Indication.TOTAL_PASSED, czTL.getValidationIndication());
		assertNull(czTL.getValidationSubIndication());
		assertNotNull(czTL.getTLSigningTime());
		assertNotNull(czTL.getTLSigningCertificate());
		assertEquals(czSigningCertificate, czTL.getTLSigningCertificate());
		
	}
	
	@AfterEach
	public void clean() throws IOException {
		File cacheDirectory = new File("target/cache");
		cacheDirectory.mkdirs();
		Files.walk(cacheDirectory.toPath()).map(Path::toFile).forEach(File::delete);
	}

}
