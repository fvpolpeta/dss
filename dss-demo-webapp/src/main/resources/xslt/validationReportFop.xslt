<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:dss="http://dss.esig.europa.eu/validation/diagnostic">
	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/dss:ValidationData">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master>
					<xsl:attribute name="master-name">A4-portrait</xsl:attribute>
					<xsl:attribute name="page-height">11in</xsl:attribute>
					<xsl:attribute name="page-width">8.5in</xsl:attribute>
					<xsl:attribute name="margin">.6in</xsl:attribute>
					<xsl:attribute name="font-size">small</xsl:attribute>
			
					<fo:region-body>
						<xsl:attribute name="margin-bottom">.6in</xsl:attribute>
					</fo:region-body>

					<fo:region-after>
						<xsl:attribute name="region-name">page-footer</xsl:attribute>
						<xsl:attribute name="extent">.6in</xsl:attribute>
					</fo:region-after>
			
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:bookmark-tree>
				<fo:bookmark>
					<xsl:attribute name="internal-destination">policy</xsl:attribute>
					<fo:bookmark-title>Validation Policy</fo:bookmark-title>
				</fo:bookmark>
								
				<fo:bookmark>
					<xsl:attribute name="internal-destination">docInfo</xsl:attribute>
					<fo:bookmark-title>Document Information</fo:bookmark-title>
				</fo:bookmark>
			</fo:bookmark-tree>

			<fo:page-sequence>
				<xsl:attribute name="master-reference">A4-portrait</xsl:attribute>
	
				<fo:static-content>
					<xsl:attribute name="flow-name">page-footer</xsl:attribute>
					<fo:block>
						<xsl:attribute name="color">grey</xsl:attribute>
						<xsl:attribute name="border-top-style">solid</xsl:attribute>
						<xsl:attribute name="border-top-color">grey</xsl:attribute>
						<xsl:attribute name="text-align-last">justify</xsl:attribute>
					
						<fo:inline>
							 <fo:basic-link>
							 	<xsl:attribute name="external-destination">url('https://joinup.ec.europa.eu/asset/sd-dss/description')</xsl:attribute>
							 	Generated by DSS
							 </fo:basic-link>
						</fo:inline>
						
						<fo:leader/>

						<fo:inline>
							<fo:page-number />
							/
							<fo:page-number-citation>
								<xsl:attribute name="ref-id">theEnd</xsl:attribute>
							</fo:page-number-citation> 
						</fo:inline>
					</fo:block>
				</fo:static-content>

				<fo:flow>
					<xsl:attribute name="flow-name">xsl-region-body</xsl:attribute>
					<xsl:apply-templates />
					
					<fo:block>
						<xsl:attribute name="id">theEnd</xsl:attribute>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

</xsl:stylesheet>

