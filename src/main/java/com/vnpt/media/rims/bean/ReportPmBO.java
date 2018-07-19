/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.bean;

/**
 *
 * @author VNP
 */
//

public class ReportPmBO{
    private String nodeId;
    private String maNode;
    private String tenNode;
    private String btsName;
    private String bscRncName;
    private String provinceId;
    private String provinceCode;
    private String provinceName;
    private String vendor;
    private String date;
    private String khuVuc;
    private String quanHuyen;
//    kpi cell 2g
    private String cALVOL;
    private String cSSRV1;
    private String cSSRV2;
    private String dCR;
    private String dCRV2;
    private String dL_TBF_DROP_RATE;
    private String hOSR;
    private String hOSRV2;
    private String pS_TBF_SR_2G;
    private String pS_TRAFFIC_2G;
    private String pS_UL_TBF_SR_2G;
    private String sDCCHBLKR;
    private String tCHBLR;
    private String tRAFFIC;
    private String uL_TBF_DROP_RATE;
//    kpi cell 3g
    private String cALLVOLUME;
    private String cSCONGES;
    private String cSINTERFREQHOSR;
    private String cSIRATHOSRWEIGHT;
    private String cSSR;
    private String cSSRVIDEOPHONE;
    private String cSVIDEODROPCALLRATE;
    private String cSVIDEOTRAFFIC;
    private String cSVOICECSSR;
    private String cSVOICEDROPCALLRATE;
//    private String DCR;
    private String dLTRAFFICPS;
    private String hSDPATHROUGHPUT;
    private String iRATHOSR;
    private String pSCONGES;
    private String pSCSSR;
    private String pSDCR;
    private String pSHSDPATPKBPS;
    private String pSHSDPATRAFFICGB;
    private String pSHSPACALLDROPRATE;
    private String pSHSPACSSR;
    private String pSHSPATRAFFICGB;
    private String pSHSUPATPKBPS;
    private String pSHSUPATRAFFICGB;
    private String pSIRATHOSR;
    private String pSR99CALLDROPRATE;
    private String pSR99CALLSETUPSR;
    private String pSR99DLTRAFFICGB;
    private String pSR99TRAFFICGB;
    private String pSR99UPLINKTRAFFICGB;
    private String pSTRAFFIC;
    private String r99DLTHROUGHPUT;
    private String r99ULTHROUGHPUT;
    private String sOFTHOSR;
    private String sOFTHOSRPS;
//    private String TRAFFIC;
    private String tRAFFICACTIVESETCS64;
    private String uLTRAFFICPS;
    private String v2INTERFREQHOSRPS;
     
//    kpi cell 4g
    private String aVAILABLE;
    private String cELL_DL_AVG_THPUTs;
    private String cELL_DL_MAX_THPUT;
    private String cELL_UL_AVG_THPUT;
    private String cELL_UL_MAX_THPUT;
    private String cSFB_SSR;
//    private String CSSR;
    private String dL_LATENCY;
    private String eRAB_SSRATE_ALL;
    private String hOSRX2;
    private String hOSR_IRAT_EXE;
    private String hOSR_IRAT_LTE_GSM;
    private String hOSR_IRAT_LTE_WCDMA;
    private String hOSR_IRAT_PRE;
    private String hOSR_IRAT_PRE_EXE;
    private String hOSR_S1;
    private String iNTER_FREQUENCY_HO;
    private String iNTRA_ENODEB_HOSR;
    private String iNTRA_FREQUENCY_HO;
    private String rES_BLK_DL;
    private String rES_BLK_UL;
    private String rRC_SSRATE;
    private String sERVICE_DROP_ALL;
//    private String TRAFFIC;
    private String tRAFFIC_VOL_DL;
    private String tRAFFIC_VOL_UL;
    private String uL_LATENCY;
    private String uNVAILABLE;
    private String uSER_DL_AVG_THPUT;
    private String uSER_UL_AVG_THPUT;

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }
    
    
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMaNode() {
        return maNode;
    }

    public void setMaNode(String maNode) {
        this.maNode = maNode;
    }

    public String getTenNode() {
        return tenNode;
    }

    public void setTenNode(String tenNode) {
        this.tenNode = tenNode;
    }

    public String getBscRncName() {
        return bscRncName;
    }

    public void setBscRncName(String bscRncName) {
        this.bscRncName = bscRncName;
    }


    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBtsName() {
        return btsName;
    }

    public void setBtsName(String btsName) {
        this.btsName = btsName;
    }

    public String getcALVOL() {
        return cALVOL;
    }

    public void setcALVOL(String cALVOL) {
        this.cALVOL = cALVOL;
    }

    public String getcSSRV1() {
        return cSSRV1;
    }

    public void setcSSRV1(String cSSRV1) {
        this.cSSRV1 = cSSRV1;
    }

    public String getcSSRV2() {
        return cSSRV2;
    }

    public void setcSSRV2(String cSSRV2) {
        this.cSSRV2 = cSSRV2;
    }

    public String getdCR() {
        return dCR;
    }

    public void setdCR(String dCR) {
        this.dCR = dCR;
    }

    public String getdCRV2() {
        return dCRV2;
    }

    public void setdCRV2(String dCRV2) {
        this.dCRV2 = dCRV2;
    }

    public String getdL_TBF_DROP_RATE() {
        return dL_TBF_DROP_RATE;
    }

    public void setdL_TBF_DROP_RATE(String dL_TBF_DROP_RATE) {
        this.dL_TBF_DROP_RATE = dL_TBF_DROP_RATE;
    }

    public String gethOSR() {
        return hOSR;
    }

    public void sethOSR(String hOSR) {
        this.hOSR = hOSR;
    }

    public String gethOSRV2() {
        return hOSRV2;
    }

    public void sethOSRV2(String hOSRV2) {
        this.hOSRV2 = hOSRV2;
    }

    public String getpS_TBF_SR_2G() {
        return pS_TBF_SR_2G;
    }

    public void setpS_TBF_SR_2G(String pS_TBF_SR_2G) {
        this.pS_TBF_SR_2G = pS_TBF_SR_2G;
    }

    public String getpS_TRAFFIC_2G() {
        return pS_TRAFFIC_2G;
    }

    public void setpS_TRAFFIC_2G(String pS_TRAFFIC_2G) {
        this.pS_TRAFFIC_2G = pS_TRAFFIC_2G;
    }

    public String getpS_UL_TBF_SR_2G() {
        return pS_UL_TBF_SR_2G;
    }

    public void setpS_UL_TBF_SR_2G(String pS_UL_TBF_SR_2G) {
        this.pS_UL_TBF_SR_2G = pS_UL_TBF_SR_2G;
    }

    public String getsDCCHBLKR() {
        return sDCCHBLKR;
    }

    public void setsDCCHBLKR(String sDCCHBLKR) {
        this.sDCCHBLKR = sDCCHBLKR;
    }

    public String gettCHBLR() {
        return tCHBLR;
    }

    public void settCHBLR(String tCHBLR) {
        this.tCHBLR = tCHBLR;
    }

    public String gettRAFFIC() {
        return tRAFFIC;
    }

    public void settRAFFIC(String tRAFFIC) {
        this.tRAFFIC = tRAFFIC;
    }

    public String getuL_TBF_DROP_RATE() {
        return uL_TBF_DROP_RATE;
    }

    public void setuL_TBF_DROP_RATE(String uL_TBF_DROP_RATE) {
        this.uL_TBF_DROP_RATE = uL_TBF_DROP_RATE;
    }

    public String getcALLVOLUME() {
        return cALLVOLUME;
    }

    public void setcALLVOLUME(String cALLVOLUME) {
        this.cALLVOLUME = cALLVOLUME;
    }

    public String getcSCONGES() {
        return cSCONGES;
    }

    public void setcSCONGES(String cSCONGES) {
        this.cSCONGES = cSCONGES;
    }

    public String getcSINTERFREQHOSR() {
        return cSINTERFREQHOSR;
    }

    public void setcSINTERFREQHOSR(String cSINTERFREQHOSR) {
        this.cSINTERFREQHOSR = cSINTERFREQHOSR;
    }

    public String getcSIRATHOSRWEIGHT() {
        return cSIRATHOSRWEIGHT;
    }

    public void setcSIRATHOSRWEIGHT(String cSIRATHOSRWEIGHT) {
        this.cSIRATHOSRWEIGHT = cSIRATHOSRWEIGHT;
    }

    public String getcSSR() {
        return cSSR;
    }

    public void setcSSR(String cSSR) {
        this.cSSR = cSSR;
    }

    public String getcSSRVIDEOPHONE() {
        return cSSRVIDEOPHONE;
    }

    public void setcSSRVIDEOPHONE(String cSSRVIDEOPHONE) {
        this.cSSRVIDEOPHONE = cSSRVIDEOPHONE;
    }

    public String getcSVIDEODROPCALLRATE() {
        return cSVIDEODROPCALLRATE;
    }

    public void setcSVIDEODROPCALLRATE(String cSVIDEODROPCALLRATE) {
        this.cSVIDEODROPCALLRATE = cSVIDEODROPCALLRATE;
    }

    public String getcSVIDEOTRAFFIC() {
        return cSVIDEOTRAFFIC;
    }

    public void setcSVIDEOTRAFFIC(String cSVIDEOTRAFFIC) {
        this.cSVIDEOTRAFFIC = cSVIDEOTRAFFIC;
    }

    public String getcSVOICECSSR() {
        return cSVOICECSSR;
    }

    public void setcSVOICECSSR(String cSVOICECSSR) {
        this.cSVOICECSSR = cSVOICECSSR;
    }

    public String getcSVOICEDROPCALLRATE() {
        return cSVOICEDROPCALLRATE;
    }

    public void setcSVOICEDROPCALLRATE(String cSVOICEDROPCALLRATE) {
        this.cSVOICEDROPCALLRATE = cSVOICEDROPCALLRATE;
    }

    public String getdLTRAFFICPS() {
        return dLTRAFFICPS;
    }

    public void setdLTRAFFICPS(String dLTRAFFICPS) {
        this.dLTRAFFICPS = dLTRAFFICPS;
    }

    public String gethSDPATHROUGHPUT() {
        return hSDPATHROUGHPUT;
    }

    public void sethSDPATHROUGHPUT(String hSDPATHROUGHPUT) {
        this.hSDPATHROUGHPUT = hSDPATHROUGHPUT;
    }

    public String getiRATHOSR() {
        return iRATHOSR;
    }

    public void setiRATHOSR(String iRATHOSR) {
        this.iRATHOSR = iRATHOSR;
    }

    public String getpSCONGES() {
        return pSCONGES;
    }

    public void setpSCONGES(String pSCONGES) {
        this.pSCONGES = pSCONGES;
    }

    public String getpSCSSR() {
        return pSCSSR;
    }

    public void setpSCSSR(String pSCSSR) {
        this.pSCSSR = pSCSSR;
    }

    public String getpSDCR() {
        return pSDCR;
    }

    public void setpSDCR(String pSDCR) {
        this.pSDCR = pSDCR;
    }

    public String getpSHSDPATPKBPS() {
        return pSHSDPATPKBPS;
    }

    public void setpSHSDPATPKBPS(String pSHSDPATPKBPS) {
        this.pSHSDPATPKBPS = pSHSDPATPKBPS;
    }

    public String getpSHSDPATRAFFICGB() {
        return pSHSDPATRAFFICGB;
    }

    public void setpSHSDPATRAFFICGB(String pSHSDPATRAFFICGB) {
        this.pSHSDPATRAFFICGB = pSHSDPATRAFFICGB;
    }

    public String getpSHSPACALLDROPRATE() {
        return pSHSPACALLDROPRATE;
    }

    public void setpSHSPACALLDROPRATE(String pSHSPACALLDROPRATE) {
        this.pSHSPACALLDROPRATE = pSHSPACALLDROPRATE;
    }

    public String getpSHSPACSSR() {
        return pSHSPACSSR;
    }

    public void setpSHSPACSSR(String pSHSPACSSR) {
        this.pSHSPACSSR = pSHSPACSSR;
    }

    public String getpSHSPATRAFFICGB() {
        return pSHSPATRAFFICGB;
    }

    public void setpSHSPATRAFFICGB(String pSHSPATRAFFICGB) {
        this.pSHSPATRAFFICGB = pSHSPATRAFFICGB;
    }

    public String getpSHSUPATPKBPS() {
        return pSHSUPATPKBPS;
    }

    public void setpSHSUPATPKBPS(String pSHSUPATPKBPS) {
        this.pSHSUPATPKBPS = pSHSUPATPKBPS;
    }

    public String getpSHSUPATRAFFICGB() {
        return pSHSUPATRAFFICGB;
    }

    public void setpSHSUPATRAFFICGB(String pSHSUPATRAFFICGB) {
        this.pSHSUPATRAFFICGB = pSHSUPATRAFFICGB;
    }

    public String getpSIRATHOSR() {
        return pSIRATHOSR;
    }

    public void setpSIRATHOSR(String pSIRATHOSR) {
        this.pSIRATHOSR = pSIRATHOSR;
    }

    public String getpSR99CALLDROPRATE() {
        return pSR99CALLDROPRATE;
    }

    public void setpSR99CALLDROPRATE(String pSR99CALLDROPRATE) {
        this.pSR99CALLDROPRATE = pSR99CALLDROPRATE;
    }

    public String getpSR99CALLSETUPSR() {
        return pSR99CALLSETUPSR;
    }

    public void setpSR99CALLSETUPSR(String pSR99CALLSETUPSR) {
        this.pSR99CALLSETUPSR = pSR99CALLSETUPSR;
    }

    public String getpSR99DLTRAFFICGB() {
        return pSR99DLTRAFFICGB;
    }

    public void setpSR99DLTRAFFICGB(String pSR99DLTRAFFICGB) {
        this.pSR99DLTRAFFICGB = pSR99DLTRAFFICGB;
    }

    public String getpSR99TRAFFICGB() {
        return pSR99TRAFFICGB;
    }

    public void setpSR99TRAFFICGB(String pSR99TRAFFICGB) {
        this.pSR99TRAFFICGB = pSR99TRAFFICGB;
    }

    public String getpSR99UPLINKTRAFFICGB() {
        return pSR99UPLINKTRAFFICGB;
    }

    public void setpSR99UPLINKTRAFFICGB(String pSR99UPLINKTRAFFICGB) {
        this.pSR99UPLINKTRAFFICGB = pSR99UPLINKTRAFFICGB;
    }

    public String getpSTRAFFIC() {
        return pSTRAFFIC;
    }

    public void setpSTRAFFIC(String pSTRAFFIC) {
        this.pSTRAFFIC = pSTRAFFIC;
    }

    public String getR99DLTHROUGHPUT() {
        return r99DLTHROUGHPUT;
    }

    public void setR99DLTHROUGHPUT(String r99DLTHROUGHPUT) {
        this.r99DLTHROUGHPUT = r99DLTHROUGHPUT;
    }

    public String getR99ULTHROUGHPUT() {
        return r99ULTHROUGHPUT;
    }

    public void setR99ULTHROUGHPUT(String r99ULTHROUGHPUT) {
        this.r99ULTHROUGHPUT = r99ULTHROUGHPUT;
    }

    public String getsOFTHOSR() {
        return sOFTHOSR;
    }

    public void setsOFTHOSR(String sOFTHOSR) {
        this.sOFTHOSR = sOFTHOSR;
    }

    public String getsOFTHOSRPS() {
        return sOFTHOSRPS;
    }

    public void setsOFTHOSRPS(String sOFTHOSRPS) {
        this.sOFTHOSRPS = sOFTHOSRPS;
    }

    public String gettRAFFICACTIVESETCS64() {
        return tRAFFICACTIVESETCS64;
    }

    public void settRAFFICACTIVESETCS64(String tRAFFICACTIVESETCS64) {
        this.tRAFFICACTIVESETCS64 = tRAFFICACTIVESETCS64;
    }

    public String getuLTRAFFICPS() {
        return uLTRAFFICPS;
    }

    public void setuLTRAFFICPS(String uLTRAFFICPS) {
        this.uLTRAFFICPS = uLTRAFFICPS;
    }

    public String getV2INTERFREQHOSRPS() {
        return v2INTERFREQHOSRPS;
    }

    public void setV2INTERFREQHOSRPS(String v2INTERFREQHOSRPS) {
        this.v2INTERFREQHOSRPS = v2INTERFREQHOSRPS;
    }

    public String getaVAILABLE() {
        return aVAILABLE;
    }

    public void setaVAILABLE(String aVAILABLE) {
        this.aVAILABLE = aVAILABLE;
    }

    public String getcELL_DL_AVG_THPUTs() {
        return cELL_DL_AVG_THPUTs;
    }

    public void setcELL_DL_AVG_THPUTs(String cELL_DL_AVG_THPUTs) {
        this.cELL_DL_AVG_THPUTs = cELL_DL_AVG_THPUTs;
    }

    public String getcELL_DL_MAX_THPUT() {
        return cELL_DL_MAX_THPUT;
    }

    public void setcELL_DL_MAX_THPUT(String cELL_DL_MAX_THPUT) {
        this.cELL_DL_MAX_THPUT = cELL_DL_MAX_THPUT;
    }

    public String getcELL_UL_AVG_THPUT() {
        return cELL_UL_AVG_THPUT;
    }

    public void setcELL_UL_AVG_THPUT(String cELL_UL_AVG_THPUT) {
        this.cELL_UL_AVG_THPUT = cELL_UL_AVG_THPUT;
    }

    public String getcELL_UL_MAX_THPUT() {
        return cELL_UL_MAX_THPUT;
    }

    public void setcELL_UL_MAX_THPUT(String cELL_UL_MAX_THPUT) {
        this.cELL_UL_MAX_THPUT = cELL_UL_MAX_THPUT;
    }

    public String getcSFB_SSR() {
        return cSFB_SSR;
    }

    public void setcSFB_SSR(String cSFB_SSR) {
        this.cSFB_SSR = cSFB_SSR;
    }

    public String getdL_LATENCY() {
        return dL_LATENCY;
    }

    public void setdL_LATENCY(String dL_LATENCY) {
        this.dL_LATENCY = dL_LATENCY;
    }

    public String geteRAB_SSRATE_ALL() {
        return eRAB_SSRATE_ALL;
    }

    public void seteRAB_SSRATE_ALL(String eRAB_SSRATE_ALL) {
        this.eRAB_SSRATE_ALL = eRAB_SSRATE_ALL;
    }

    public String gethOSRX2() {
        return hOSRX2;
    }

    public void sethOSRX2(String hOSRX2) {
        this.hOSRX2 = hOSRX2;
    }

    public String gethOSR_IRAT_EXE() {
        return hOSR_IRAT_EXE;
    }

    public void sethOSR_IRAT_EXE(String hOSR_IRAT_EXE) {
        this.hOSR_IRAT_EXE = hOSR_IRAT_EXE;
    }

    public String gethOSR_IRAT_LTE_GSM() {
        return hOSR_IRAT_LTE_GSM;
    }

    public void sethOSR_IRAT_LTE_GSM(String hOSR_IRAT_LTE_GSM) {
        this.hOSR_IRAT_LTE_GSM = hOSR_IRAT_LTE_GSM;
    }

    public String gethOSR_IRAT_LTE_WCDMA() {
        return hOSR_IRAT_LTE_WCDMA;
    }

    public void sethOSR_IRAT_LTE_WCDMA(String hOSR_IRAT_LTE_WCDMA) {
        this.hOSR_IRAT_LTE_WCDMA = hOSR_IRAT_LTE_WCDMA;
    }

    public String gethOSR_IRAT_PRE() {
        return hOSR_IRAT_PRE;
    }

    public void sethOSR_IRAT_PRE(String hOSR_IRAT_PRE) {
        this.hOSR_IRAT_PRE = hOSR_IRAT_PRE;
    }

    public String gethOSR_IRAT_PRE_EXE() {
        return hOSR_IRAT_PRE_EXE;
    }

    public void sethOSR_IRAT_PRE_EXE(String hOSR_IRAT_PRE_EXE) {
        this.hOSR_IRAT_PRE_EXE = hOSR_IRAT_PRE_EXE;
    }

    public String gethOSR_S1() {
        return hOSR_S1;
    }

    public void sethOSR_S1(String hOSR_S1) {
        this.hOSR_S1 = hOSR_S1;
    }

    public String getiNTER_FREQUENCY_HO() {
        return iNTER_FREQUENCY_HO;
    }

    public void setiNTER_FREQUENCY_HO(String iNTER_FREQUENCY_HO) {
        this.iNTER_FREQUENCY_HO = iNTER_FREQUENCY_HO;
    }

    public String getiNTRA_ENODEB_HOSR() {
        return iNTRA_ENODEB_HOSR;
    }

    public void setiNTRA_ENODEB_HOSR(String iNTRA_ENODEB_HOSR) {
        this.iNTRA_ENODEB_HOSR = iNTRA_ENODEB_HOSR;
    }

    public String getiNTRA_FREQUENCY_HO() {
        return iNTRA_FREQUENCY_HO;
    }

    public void setiNTRA_FREQUENCY_HO(String iNTRA_FREQUENCY_HO) {
        this.iNTRA_FREQUENCY_HO = iNTRA_FREQUENCY_HO;
    }

    public String getrES_BLK_DL() {
        return rES_BLK_DL;
    }

    public void setrES_BLK_DL(String rES_BLK_DL) {
        this.rES_BLK_DL = rES_BLK_DL;
    }

    public String getrES_BLK_UL() {
        return rES_BLK_UL;
    }

    public void setrES_BLK_UL(String rES_BLK_UL) {
        this.rES_BLK_UL = rES_BLK_UL;
    }

    public String getrRC_SSRATE() {
        return rRC_SSRATE;
    }

    public void setrRC_SSRATE(String rRC_SSRATE) {
        this.rRC_SSRATE = rRC_SSRATE;
    }

    public String getsERVICE_DROP_ALL() {
        return sERVICE_DROP_ALL;
    }

    public void setsERVICE_DROP_ALL(String sERVICE_DROP_ALL) {
        this.sERVICE_DROP_ALL = sERVICE_DROP_ALL;
    }

    public String gettRAFFIC_VOL_DL() {
        return tRAFFIC_VOL_DL;
    }

    public void settRAFFIC_VOL_DL(String tRAFFIC_VOL_DL) {
        this.tRAFFIC_VOL_DL = tRAFFIC_VOL_DL;
    }

    public String gettRAFFIC_VOL_UL() {
        return tRAFFIC_VOL_UL;
    }

    public void settRAFFIC_VOL_UL(String tRAFFIC_VOL_UL) {
        this.tRAFFIC_VOL_UL = tRAFFIC_VOL_UL;
    }

    public String getuL_LATENCY() {
        return uL_LATENCY;
    }

    public void setuL_LATENCY(String uL_LATENCY) {
        this.uL_LATENCY = uL_LATENCY;
    }

    public String getuNVAILABLE() {
        return uNVAILABLE;
    }

    public void setuNVAILABLE(String uNVAILABLE) {
        this.uNVAILABLE = uNVAILABLE;
    }

    public String getuSER_DL_AVG_THPUT() {
        return uSER_DL_AVG_THPUT;
    }

    public void setuSER_DL_AVG_THPUT(String uSER_DL_AVG_THPUT) {
        this.uSER_DL_AVG_THPUT = uSER_DL_AVG_THPUT;
    }

    public String getuSER_UL_AVG_THPUT() {
        return uSER_UL_AVG_THPUT;
    }

    public void setuSER_UL_AVG_THPUT(String uSER_UL_AVG_THPUT) {
        this.uSER_UL_AVG_THPUT = uSER_UL_AVG_THPUT;
    }
    
    
}
