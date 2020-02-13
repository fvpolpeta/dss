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
package eu.europa.esig.dss.i18n;

/**
 * Contains a list of possible message tags.
 * NOTE: all message tags shall be listed in messages.properties file
 *
 */
public enum MessageTag {
	
	/* BBB -> FC */
	BBB_FC_IEFF,
	BBB_FC_IEFF_ANS,

	BBB_FC_ICFD,
	BBB_FC_ICFD_ANS,
	
	BBB_FC_ISD,
	BBB_FC_ISD_ANS,
	
	BBB_FC_IOSIP,
	BBB_FC_IOSIP_ANS,
	
	BBB_FC_IECTF,
	BBB_FC_IECTF_ANS,

	BBB_FC_ITZCP,
	BBB_FC_ITZCP_ANS,
	
	BBB_FC_ITEZCF,
	BBB_FC_ITEZCF_ANS,
	
	BBB_FC_ITMFP,
	BBB_FC_ITMFP_ANS,
	
	BBB_FC_IEMCF,
	BBB_FC_IEMCF_ANS,

	BBB_FC_IMFP_ASICE,
	BBB_FC_IMFP_ASICE_ANS,
	
	BBB_FC_IMFP_ASICS,
	BBB_FC_IMFP_ASICS_ANS,
	
	/* BBB -> CV */
	BBB_CV_IRDOF,
	BBB_CV_IRDOF_ANS,
	BBB_CV_IRDOI,
	BBB_CV_IRDOI_ANS,
	BBB_CV_ISMEC,
	BBB_CV_ISMEC_ANS,
	
	BBB_CV_ISI,
	BBB_CV_ISIC,
	BBB_CV_ISIR,
	BBB_CV_ISIT,
	BBB_CV_ISI_ANS,
	
	BBB_CV_IAFS,
	BBB_CV_IAFS_ANS,
	
	/* BBB -> ICS */
	BBB_ICS_ISCI,
	BBB_ICS_ISCI_ANS,
	BBB_ICS_ISASCP,
	BBB_ICS_ISASCP_ANS,
	BBB_ICS_ISACDP,
	BBB_ICS_ISACDP_ANS,
	BBB_ICS_ICDVV,
	BBB_ICS_ICDVV_ANS,
	BBB_ICS_AIDNASNE,
	BBB_ICS_AIDNASNE_ANS,

	/* BBB -> RFC */
	BBB_RFC_NUP,
	BBB_RFC_NUP_ANS,
	BBB_RFC_IRIF,
	BBB_RFC_IRIF_ANS,
	
	/* BBB -> SAV -> TSP*/
	BBB_SAV_TSP_IMIDF,
	BBB_SAV_TSP_IMIDF_ANS,
	BBB_SAV_TSP_IMSDAV,
	BBB_SAV_TSP_IMIVC,
	BBB_SAV_TSP_IMIVC_ANS,
	
	
	ADEST_ITVPC,
	ADEST_ITVPC_ANS_1,
	ADEST_ITVPC_INFO_1,
	ADEST_ITVPC_ANS_2,
	ADEST_ROBVPIIC,
	ADEST_ROBVPIIC_ANS,
	ADEST_ROTVPIIC,
	ADEST_ROTVPIIC_ANS,
	ADEST_RORPIIC,
	ADEST_RORPIIC_ANS,

	LTV_ABSV,
	LTV_ABSV_ANS,
	
	ARCH_LTVV,
	ARCH_LTVV_ANS,

	ASCCM,
	ACCCM,
	ARCCM,
	ATCCM,
	ASCCM_ANS_1,
	ASCCM_ANS_2,
	ASCCM_ANS_3,
	ASCCM_ANS_4,
	ASCCM_ANS_5,
	ASCCM_ANS_6,
	
	ACCCRM,
	ACCCRM_ANS,

	BBB_SAV_ISVA,
	BBB_SAV_ISVA_ANS,
	BBB_SAV_ISSV,
	BBB_SAV_ISSV_ANS,
	BBB_SAV_ICERRM,
	BBB_SAV_ICERRM_ANS,
	BBB_SAV_ICRM,
	BBB_SAV_ICRM_ANS,
	BBB_SAV_ISQPCTP,
	BBB_SAV_ISQPCTP_ANS,
	BBB_SAV_ISQPCHP,
	BBB_SAV_ISQPCHP_ANS,
	BBB_SAV_ISQPCIP,
	BBB_SAV_ISQPCIP_ANS,
	BBB_SAV_ISQPCTSIP,
	BBB_SAV_ISQPCTSIP_ANS,
	BBB_SAV_ISQPSLP,
	BBB_SAV_ISQPSLP_ANS,
	BBB_SAV_ISQPSTP,
	BBB_SAV_ISQPSTP_ANS,
	BBB_SAV_ISQPXTIP,
	BBB_SAV_ISQPXTIP_ANS,
	BBB_SAV_IUQPCSP,
	BBB_SAV_IUQPCSP_ANS,
	BBB_SAV_ISQPMDOSPP,
	BBB_SAV_ISQPMDOSPP_ANS,

	BBB_VCI_ISPK,
	BBB_VCI_ISPK_ANS_1,

	BBB_VCI_ISPA,
	BBB_VCI_ISPA_ANS,
	
	BBB_VCI_ISPM,
	BBB_VCI_ISPM_ANS,

	BBB_XCV_SUB,
	BBB_XCV_SUB_ANS,
	BBB_XCV_RFC,
	BBB_XCV_RFC_ANS,
	BBB_XCV_RAC,
	BBB_XCV_RAC_ANS,
	BBB_XCV_ACCCM,
	BBB_XCV_ACCM,
	BBB_XCV_ARDCCM,
	BBB_XCV_CCCBB,
	BBB_XCV_CCCBB_ANS,
	BBB_XCV_CCCBB_SIG_ANS,
	BBB_XCV_CCCBB_TSP_ANS,
	BBB_XCV_CCCBB_REV_ANS,
	BBB_XCV_CMDCIPI,
	BBB_XCV_CMDCIPI_ANS,
	BBB_XCV_CMDCIQCS,
	BBB_XCV_CMDCIQCS_ANS,
	BBB_XCV_CMDCIITLP,
	BBB_XCV_CMDCIITLP_ANS,
	BBB_XCV_CMDCIITNP,
	BBB_XCV_CMDCIITNP_ANS,
	BBB_XCV_CMDCIQC,
	BBB_XCV_CMDCIQC_ANS,
	BBB_XCV_CMDCIQSCD,
	BBB_XCV_CMDCIQSCD_ANS,
	BBB_XCV_ICTIVRSC,
	BBB_XCV_ICTIVRSC_ANS,
	BBB_XCV_IRDPFC,
	BBB_XCV_IRDPFC_ANS,
	BBB_XCV_IRDPFRC, 
	BBB_XCV_IRDPFRC_ANS,
	BBB_XCV_IARDPFC,
	BBB_XCV_IARDPFC_ANS,
	BBB_VTS_IRDPFC,
	BBB_VTS_IRDPFC_ANS,
	BBB_XCV_IRDTFC,
	BBB_XCV_IRDTFC_ANS,
	BBB_XCV_IRIF,
	BBB_XCV_IRIF_ANS,
	BBB_XCV_ISCOH,
	BBB_XCV_ISCOH_ANS,
	BBB_XCV_ISCR,
	BBB_XCV_ISCR_ANS,
	BBB_XCV_ISCGKU,
	BBB_XCV_ISCGKU_ANS,
	BBB_XCV_ISCGEKU,
	BBB_XCV_ISCGEKU_ANS,
	BBB_XCV_ICSI,
	BBB_XCV_ICSI_ANS,
	BBB_XCV_OCSP_NO_CHECK,
	BBB_XCV_OCSP_NO_CHECK_ANS,
	BBB_XCV_PSEUDO_USE,
	BBB_XCV_PSEUDO_USE_ANS,
	BBB_XCV_AIA_PRES,
	BBB_XCV_AIA_PRES_ANS,
	BBB_XCV_REVOC_PRES,
	BBB_XCV_REVOC_PRES_ANS,
	BBB_XCV_REVOC_CERT_HASH,
	BBB_XCV_REVOC_CERT_HASH_ANS,

	BBB_XCV_ISCGCOUN,
	BBB_XCV_ISCGCOUN_ANS,
	BBB_XCV_ISCGORGAN,
	BBB_XCV_ISCGORGAN_ANS,
	BBB_XCV_ISCGORGAU,
	BBB_XCV_ISCGORGAU_ANS,
	BBB_XCV_ISCGSURN,
	BBB_XCV_ISCGSURN_ANS,
	BBB_XCV_ISCGGIVEN,
	BBB_XCV_ISCGGIVEN_ANS,
	BBB_XCV_ISCGPSEUDO,
	BBB_XCV_ISCGPSEUDO_ANS,
	BBB_XCV_ISCGCOMMONN,
	BBB_XCV_ISCGCOMMONN_ANS,

	BBB_XCV_ISSSC,
	BBB_XCV_ISSSC_ANS,
	
	BBB_XCV_ISNSSC,
	BBB_XCV_ISNSSC_ANS,
	
	BBB_XCV_IRDC,
	BBB_XCV_IRDC_ANS,

	XCV_IFCCIIPC_ANS,

	XCV_TSL_ESP,
	XCV_TSL_ESP_ANS,
	XCV_TSL_ESP_SIG_ANS,
	XCV_TSL_ESP_TSP_ANS,
	XCV_TSL_ESP_REV_ANS,
	XCV_TSL_ETIP,
	XCV_TSL_ETIP_ANS,
	XCV_TSL_ETIP_SIG_ANS,
	XCV_TSL_ETIP_TSP_ANS,
	XCV_TSL_ETIP_REV_ANS,

	PCV_IVTSC,
	PCV_IVTSC_ANS,

	PSV_IPCVA,
	PSV_IPCVA_ANS,
	PSV_IPCVC,
	PSV_IPCVC_ANS,
	PSV_IPSVC,
	PSV_IPSVC_ANS,
	PSV_IPTVC,
	PSV_IPTVC_ANS,
	PSV_ITPOCOBCT,
	PSV_ITPOSVAOBCT,
	PSV_ITPORDAOBCT,
	PSV_ITPOOBCT_ANS,

	TSV_ASTPTCT,
	TSV_ASTPTCT_ANS,
	TSV_IBSTAIDOSC,
	TSV_IBSTAIDOSC_ANS,
	TSV_ISCNVABST,
	TSV_ISCNVABST_ANS,
	TSV_ICACNVABST,
	TSV_ICACNVABST_ANS,

	ADEST_IRTPTBST,
	ADEST_IRTPTBST_ANS,
	ADEST_VFDTAOCST_ANS,
	ADEST_ISTPTDABST,
	ADEST_ISTPTDABST_ANS,
	TSV_WACRABST,
	TSV_WACRABST_ANS,

	LABEL_TINTWS,
	LABEL_TINVTWS,

	VTS_IRC,
	VTS_IRC_ANS,
	VTS_ICTBRD,
	VTS_ICTBRD_ANS,

	QUAL_TL_EXP,
	QUAL_TL_EXP_ANS,
	QUAL_TL_FRESH,
	QUAL_TL_FRESH_ANS,
	QUAL_TL_VERSION,
	QUAL_TL_VERSION_ANS,
	QUAL_TL_WS,
	QUAL_TL_WS_ANS,
	
	QUAL_TL_SERV_CONS,
	QUAL_TL_SERV_CONS_ANS0,
	QUAL_TL_SERV_CONS_ANS1,
	QUAL_TL_SERV_CONS_ANS2,
	QUAL_TL_SERV_CONS_ANS3,
	QUAL_TL_SERV_CONS_ANS3A,
	QUAL_TL_SERV_CONS_ANS4,
	QUAL_TL_SERV_CONS_ANS5,
	QUAL_TL_SERV_CONS_ANS6,

	QUAL_CERT_TRUSTED_LIST_REACHED,
	QUAL_CERT_TRUSTED_LIST_REACHED_ANS,

	QUAL_TRUSTED_LIST_ACCEPT,
	QUAL_TRUSTED_LIST_ACCEPT_ANS,

	QUAL_FOR_SIGN_AT_ST,
	QUAL_FOR_SIGN_AT_ST_ANS,
	QUAL_FOR_SIGN_AT_CC,
	QUAL_FOR_SIGN_AT_CC_ANS,
	QUAL_FOR_SIGN_AT_VT,
	QUAL_FOR_SIGN_AT_VT_ANS,

	QUAL_QC_AT_ST,
	QUAL_QC_AT_ST_ANS,
	QUAL_QC_AT_CC,
	QUAL_QC_AT_CC_ANS,
	QUAL_QC_AT_VT,
	QUAL_QC_AT_VT_ANS,

	QUAL_QSCD_AT_ST,
	QUAL_QSCD_AT_ST_ANS,
	QUAL_QSCD_AT_CC,
	QUAL_QSCD_AT_CC_ANS,
	QUAL_QSCD_AT_VT,
	QUAL_QSCD_AT_VT_ANS,

	QUAL_UNIQUE_CERT,
	QUAL_UNIQUE_CERT_ANS,
	QUAL_IS_ADES,
	QUAL_IS_ADES_IND,
	QUAL_IS_ADES_INV,
	
	QUAL_HAS_CAQC,
	QUAL_HAS_CAQC_ANS,

	QUAL_HAS_QTST,
	QUAL_HAS_QTST_ANS,
	
	QUAL_IS_TRUST_CERT_MATCH_SERVICE,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS0,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS1,
	QUAL_IS_TRUST_CERT_MATCH_SERVICE_ANS2,

	QUAL_HAS_GRANTED,
	QUAL_HAS_GRANTED_ANS,
	
	QUAL_HAS_GRANTED_AT,
	QUAL_HAS_GRANTED_AT_ANS,

	QUAL_HAS_CONSISTENT,
	QUAL_HAS_CONSISTENT_ANS,

	QUAL_HAS_CERT_TYPE_COVERAGE,
	QUAL_HAS_CERT_TYPE_COVERAGE_ANS,

	QUAL_HAS_ONLY_ONE,
	QUAL_HAS_ONLY_ONE_ANS,
	
	BBB_ACCEPT,
	BBB_ACCEPT_ANS,
	
	EMPTY,
	
	/* Additional Info */
	
	BEST_SIGNATURE_TIME,
	
	CERTIFICATE_ID,

	CERTIFICATE_VALIDITY,

	CONTROL_TIME,
	
	CRYPTOGRAPHIC_CHECK_FAILURE,
	
	CRYPTOGRAPHIC_CHECK_FAILURE_WITH_ID,

	EXTENDED_KEY_USAGE,

	KEY_USAGE,
	
	OCSP_NO_CHECK,
	
	REFERENCE,

	REVOCATION,
	
	REVOCATION_ACCEPTANCE_CHECK,
	
	REVOCATION_CERT_HASH_OK,
	
	REVOCATION_CHECK,
	
	REVOCATION_CONSISTENT,
	
	REVOCATION_CRYPTOGRAPHIC_CHECK_FAILURE,
	
	REVOCATION_NO_THIS_UPDATE,
	
	REVOCATION_NOT_AFTER_AFTER,
	
	REVOCATION_THIS_UPDATE_BEFORE,

	PSEUDO,
	
	STRUCTURAL_VALIDATION_FAILURE,
	
	TOKEN_ID,

	TRUST_SERVICE_NAME,

	TRUSTED_SERVICE_STATUS,

	TRUSTED_SERVICE_TYPE,

	TRUSTED_LIST,

	VALIDATION_TIME,
	
	VALIDATION_TIME_WITH_ID,
	
	/* BasicBuildingBlocks titles */

	CRYPTOGRAPHIC_VERIFICATION,
	
	FORMAT_CHECKING,

	IDENTIFICATION_OF_THE_SIGNING_CERTIFICATE,

	PAST_SIGNATURE_VALIDATION,

	PAST_CERTIFICATE_VALIDATION,

	REVOCATION_FRESHNESS_CHECKER,

	SIGNATURE_ACCEPTANCE_VALIDATION,

	VALIDATION_CONTEXT_INITIALIZATION,

	VALIDATION_TIME_SLIDING,

	X509_CERTIFICATE_VALIDATION,
	
	/* Validation Process Definitions */

	CERT_QUALIFICATION,

	CERT_QUALIFICATION_AT_TIME,
	
	DAAV,
	
	RAV,

	SIG_QUALIFICATION,

	SUB_XCV,

	TL,

	LOTL,

	TST_QUALIFICATION,

	VPBS,

	VPFLTVD,

	VPFSWATSP,

	VPFTSP,
	
	/* Custom variables */

	/* Validation time */
	VT_BEST_SIGNATURE_TIME,
	VT_CERTIFICATE_ISSUANCE_TIME,
	VT_VALIDATION_TIME;
	
	private Object[] args;

	/**
	 * This method returns the id code of the referred message.
	 *
	 * @return {@code String} message.
	 */
	public String getId() {
		return name();
	}
	
	/**
	 * Allows setting of optional attributes for supported string patterns
	 * 
	 * @param args an array of {@link Object} arguments to set
	 * @return this {@link MessageTag}
	 */
	public MessageTag setArgs(Object... args) {
		this.args = args;
		return this;
	}
	
	/**
	 * This method returns an array of arguments to fill a message pattern
	 * 
	 * @return array of {@link Object} arguments
	 */
	public Object[] getArgs() {
		return args;
	}

}
