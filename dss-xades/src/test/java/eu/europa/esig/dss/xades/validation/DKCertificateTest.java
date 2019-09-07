/**
 * DSS - Digital Signature Services
 * Copyright (C) 2015 European Commission, provided under the CEF programme
 * 
 * This file is part of the "DSS - Digital Signature Services" project.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package eu.europa.esig.dss.xades.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import eu.europa.esig.dss.diagnostic.CertificateWrapper;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.enumerations.SubIndication;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.policy.ValidationPolicyFacade;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.spi.DSSUtils;
import eu.europa.esig.dss.spi.client.http.DataLoader;
import eu.europa.esig.dss.spi.client.http.IgnoreDataLoader;
import eu.europa.esig.dss.spi.client.http.MemoryDataLoader;
import eu.europa.esig.dss.spi.x509.CommonTrustedCertificateSource;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.AdvancedSignature;
import eu.europa.esig.dss.validation.CertificateVerifier;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.validation.SignatureCertificateSource;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.executor.DefaultSignatureProcessExecutor;
import eu.europa.esig.dss.validation.reports.Reports;

public class DKCertificateTest {

	private static final DSSDocument DOC = new FileDocument(new File("src/test/resources/validation/dk_tl-sn21.xml"));

	private static final String DATE_STRING = "2019-08-05";
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String AIA_URI = "http://m.aia.ica02.trust2408.com/oces-issuing02-ca.cer";
	private static final String AIA_BASE64_CONTENT = "MIIFFzCCAv+gAwIBAgIES47SGDANBgkqhkiG9w0BAQsFADBFMQswCQYDVQQGEwJESzESMBAGA1UEChMJVFJVU1QyNDA4MSIwIAYDVQQDExlUUlVTVDI0MDggT0NFUyBQcmltYXJ5IENBMB4XDTE0MDUxNDA4NDU1M1oXDTI5MDUxNDA5MTU1M1owQDELMAkGA1UEBhMCREsxEjAQBgNVBAoMCVRSVVNUMjQwODEdMBsGA1UEAwwUVFJVU1QyNDA4IE9DRVMgQ0EgSUkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQClA0ndSIgIFIRsUZTjOLKSg1a/gz90oNF7IagTM6GYotUXezx1rR+XUAxJ5T7ap60sTE9MUhGCNxxq+XwzAS/3qUXsUSSkBNLrBD9rW9mpiygd/m0vA5hsRhqlCLNcYAf/AW9H1ip4ym2NZ/1Vt6hG7dxZylPahCPhDEQEv+GDC8W8Q0FCOg3sz9IpckPn5+FyR5gE69k8He2N1JpW1uUTtpusl27lUta1SJVO1Q0h8DpRnDb5zHvy78NJxcg2/1nB2t6LZKpjSAybAuaHos//1zQjqbgg+m0IpMjyqfEbfVt7Lbth0PmCxg7lIcJAq4vk/DDiKMxK1A6ZSgLUoEm9AgMBAAGjggESMIIBDjAPBgNVHRMBAf8EBTADAQH/MA4GA1UdDwEB/wQEAwIBBjARBgNVHSAECjAIMAYGBFUdIAAwgZcGA1UdHwSBjzCBjDAsoCqgKIYmaHR0cDovL2NybC5vY2VzLnRydXN0MjQwOC5jb20vb2Nlcy5jcmwwXKBaoFikVjBUMQswCQYDVQQGEwJESzESMBAGA1UEChMJVFJVU1QyNDA4MSIwIAYDVQQDExlUUlVTVDI0MDggT0NFUyBQcmltYXJ5IENBMQ0wCwYDVQQDEwRDUkwxMB8GA1UdIwQYMBaAFPZt+LFIs0FDAduGROUYBbdezAY3MB0GA1UdDgQWBBSZj7oNia4hGkJ6Cq4aTE4i/xDrjDANBgkqhkiG9w0BAQsFAAOCAgEACUJn4jCHiEEmTxm8Tbp0XPWq3uKUktxAbsvqcw4ZICsVdFFkSiPQiIjeGff1aQlDFVVoRxZmJSJZZUTvg4z3p+6RHqyxJ1sWpM0ScxVd0rdl4i4C8jisIESxY8vxfu5yKw0ThCFliB3wJ7rcxnJn4hzf1qZmQF/mzOnHsNMW0J+VETvheShqPw5IVC3c9p5zxxf8LJ+0fs9csUJMcuksU25zWNpEyMSOfDA9zmTVHrANr2+SzrUkNroW34OiJKlBLWKcUJRWEb5T0tKvSTxWJXPJztcjrm03423XmxwgRBubuNKQlJbcDE/N5t0euZRaYPymLv+o4hjXCNQcKT7rkDIgDg7Pwi20ZNlSUOxb7EX4FCkXhG0HPbnqg8oKcRIz64bfjf0WlVyBZVKYJL8rO1zj6XBiOXK2f81o4fsYoQTbd1Uou+nWEkssnwr+a9d9+7Sjw8tJwgCEnsNzJCGebcJSrVDvoO4p/NBzp0yKDae8wignYTo30KnJeG4nSGdoUOh3ddxpP5awqpBR4ufDGPyzHKmBo8EEfHDp5JpHVJrvC2IkXWo/2frnSlEHytHRACLmJg6Povha52Y9DZalIvXsGSL+6gwwVWUMlgAHyiLLBNxOqXsHzbX7xVRDkV5FpHeDeSvzF+GgUQKs81D+yeAjMlJ00TkB+Ugmai3RBJU=";

	private static final CertificateToken PREVIOUS_SIG_CERT = DSSUtils.loadCertificateFromBase64EncodedString(
			"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");
	private static final CertificateToken WRONG = DSSUtils.loadCertificateFromBase64EncodedString(
			"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");

	private static final CertificateToken EXPECTED_SIG_CERT = DSSUtils.loadCertificateFromBase64EncodedString(
			"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");

	private static final CertificateToken AIA_CERT = DSSUtils.loadCertificateFromBase64EncodedString(AIA_BASE64_CONTENT);

	@Test
	public void dkPreviousSigCert() throws Exception {
		SignedDocumentValidator val = SignedDocumentValidator.fromDocument(DOC);
		CertificateVerifier certificateVerifier = new CommonCertificateVerifier();
		CommonTrustedCertificateSource certSource = new CommonTrustedCertificateSource();
		certSource.addCertificate(PREVIOUS_SIG_CERT);
		certificateVerifier.setTrustedCertSource(certSource);
		certificateVerifier.setDataLoader(getMemoryDataLoader());
		val.setCertificateVerifier(certificateVerifier);
		val.setProcessExecutor(fixedTime());
		Reports reports = val.validateDocument(ValidationPolicyFacade.newFacade().getTrustedListValidationPolicy());

		SimpleReport simpleReport = reports.getSimpleReport();
		assertEquals(Indication.TOTAL_PASSED, simpleReport.getIndication(simpleReport.getFirstSignatureId()));
	}

	@Test
	public void dkTestOther() throws Exception {
		SignedDocumentValidator val = SignedDocumentValidator.fromDocument(DOC);
		CertificateVerifier certificateVerifier = new CommonCertificateVerifier();
		CommonTrustedCertificateSource certSource = new CommonTrustedCertificateSource();
		certSource.addCertificate(WRONG);
		certificateVerifier.setTrustedCertSource(certSource);
		certificateVerifier.setDataLoader(getMemoryDataLoader());
		val.setCertificateVerifier(certificateVerifier);
		val.setProcessExecutor(fixedTime());
		Reports reports = val.validateDocument(ValidationPolicyFacade.newFacade().getTrustedListValidationPolicy());
		SimpleReport simpleReport = reports.getSimpleReport();
		assertEquals(Indication.INDETERMINATE, simpleReport.getIndication(simpleReport.getFirstSignatureId()));
		assertEquals(SubIndication.NO_CERTIFICATE_CHAIN_FOUND, simpleReport.getSubIndication(simpleReport.getFirstSignatureId()));
	}

	@Test
	public void dkExpectedSigCert() throws Exception {
		SignedDocumentValidator val = SignedDocumentValidator.fromDocument(DOC);
		CertificateVerifier certificateVerifier = new CommonCertificateVerifier();
		CommonTrustedCertificateSource certSource = new CommonTrustedCertificateSource();
		certSource.addCertificate(EXPECTED_SIG_CERT);
		certificateVerifier.setTrustedCertSource(certSource);
		certificateVerifier.setDataLoader(getMemoryDataLoader());
		val.setCertificateVerifier(certificateVerifier);
		val.setProcessExecutor(fixedTime());
		Reports reports = val.validateDocument(ValidationPolicyFacade.newFacade().getTrustedListValidationPolicy());
		SimpleReport simpleReport = reports.getSimpleReport();
		assertEquals(Indication.INDETERMINATE, simpleReport.getIndication(simpleReport.getFirstSignatureId()));
		assertEquals(SubIndication.NO_CERTIFICATE_CHAIN_FOUND, simpleReport.getSubIndication(simpleReport.getFirstSignatureId()));

		DiagnosticData diagnosticData = reports.getDiagnosticData();
		List<CertificateWrapper> usedCertificates = diagnosticData.getUsedCertificates();
		for (CertificateWrapper certificateWrapper : usedCertificates) {
			assertFalse(certificateWrapper.isTrusted());
		}
	}

	private DefaultSignatureProcessExecutor fixedTime() throws ParseException {
		DefaultSignatureProcessExecutor processExecutor = new DefaultSignatureProcessExecutor();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Date currentTime = sdf.parse(DATE_STRING);
		processExecutor.setCurrentTime(currentTime);
		return processExecutor;
	}

	private DataLoader getMemoryDataLoader() {
		Map<String, byte[]> dataMap = new HashMap<String, byte[]>();
		dataMap.put(AIA_URI, Utils.fromBase64(AIA_BASE64_CONTENT));
		return new MemoryDataLoader(dataMap);
	}

	@Test
	public void xml() {
		XMLDocumentValidator val = new XMLDocumentValidator(DOC);
		CommonCertificateVerifier certificateVerifier = new CommonCertificateVerifier();
		certificateVerifier.setDataLoader(new IgnoreDataLoader());
		val.setCertificateVerifier(certificateVerifier);
		List<AdvancedSignature> signatures = val.getSignatures();
		AdvancedSignature advancedSignature = signatures.get(0);
		SignatureCertificateSource certificateSource = advancedSignature.getCertificateSource();
		List<CertificateToken> certificates = certificateSource.getCertificates();
		assertEquals(1, certificates.size());
		assertEquals(PREVIOUS_SIG_CERT, certificates.get(0));
	}

	@Test
	public void certs() {
		System.out.println(PREVIOUS_SIG_CERT);
		System.out.println(EXPECTED_SIG_CERT);

		assertFalse(PREVIOUS_SIG_CERT.isEquivalent(EXPECTED_SIG_CERT));
		assertFalse(PREVIOUS_SIG_CERT.isEquivalent(AIA_CERT));
		assertFalse(EXPECTED_SIG_CERT.isEquivalent(AIA_CERT));
		assertFalse(PREVIOUS_SIG_CERT.getPublicKey().equals(EXPECTED_SIG_CERT.getPublicKey()));
		assertFalse(PREVIOUS_SIG_CERT.getPublicKey().equals(AIA_CERT.getPublicKey()));
		assertFalse(EXPECTED_SIG_CERT.getPublicKey().equals(AIA_CERT.getPublicKey()));
		assertFalse(PREVIOUS_SIG_CERT.getEntityKey().equals(EXPECTED_SIG_CERT.getEntityKey()));
		assertFalse(PREVIOUS_SIG_CERT.getEntityKey().equals(AIA_CERT.getEntityKey()));
		assertFalse(EXPECTED_SIG_CERT.getEntityKey().equals(AIA_CERT.getEntityKey()));
		assertFalse(PREVIOUS_SIG_CERT.getDSSId().equals(EXPECTED_SIG_CERT.getDSSId()));
		assertFalse(PREVIOUS_SIG_CERT.getDSSId().equals(AIA_CERT.getDSSId()));
		assertFalse(EXPECTED_SIG_CERT.getDSSId().equals(AIA_CERT.getDSSId()));
		assertTrue(PREVIOUS_SIG_CERT.getSubjectX500Principal().equals(EXPECTED_SIG_CERT.getSubjectX500Principal()));
	}

}
