/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Cyano
 */
public class AntenaBO {

    Integer DVI_TRACH_NHIEM_ANTEN;
    Integer TRAM_QH_ID;
    String MA_QUY_HOACH;
    String TEN_QUY_HOACH;
    Integer LOAI_ANTEN_ID1;
    String TEN_ANTENA1;
    String HANG_SX_ANTENA1;
    Integer SO_LUONG_ANTENA1;
    Integer BANG_TANG_ANTENA_ID1;
    Integer CAU_HINH_PORT_ID1;
    String CAU_HINH_GAIN1;
    String CAU_HINH_BEAM_WIDTH1;
    Integer TRONG_LUONG1;
    Integer KICH_CO_CAO1;
    Integer KICH_CO_RONG1;
    Integer KICH_CO_SAU1;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    Integer LOAI_ANTEN_ID2;
    String TEN_ANTENA2;
    String HANG_SX_ANTENA2;
    Integer SO_LUONG_ANTENA2;
    Integer BANG_TANG_ANTENA_ID2;
    Integer CAU_HINH_PORT_ID2;
    String CAU_HINH_GAIN2;
    String CAU_HINH_BEAM_WIDTH2;
    Integer TRONG_LUONG2;
    Integer KICH_CO_CAO2;
    Integer KICH_CO_RONG2;
    Integer KICH_CO_SAU2;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    Integer LOAI_ANTEN_ID3;
    String TEN_ANTENA3;
    String HANG_SX_ANTENA3;
    Integer SO_LUONG_ANTENA3;
    Integer BANG_TANG_ANTENA_ID3;
    Integer CAU_HINH_PORT_ID3;
    String CAU_HINH_GAIN3;
    String CAU_HINH_BEAM_WIDTH3;
    Integer TRONG_LUONG3;
    Integer KICH_CO_CAO3;
    Integer KICH_CO_RONG3;
    Integer KICH_CO_SAU3;
    Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_ANTENA_DU_KIEN;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date NGAY_DAP_UNG_ANTENA_THUC_TE;

    public AntenaBO() {
    }
    
    public Integer getDVI_TRACH_NHIEM_ANTEN() {
        return DVI_TRACH_NHIEM_ANTEN;
    }

    public void setDVI_TRACH_NHIEM_ANTEN(Integer DVI_TRACH_NHIEM_ANTEN) {
        this.DVI_TRACH_NHIEM_ANTEN = DVI_TRACH_NHIEM_ANTEN;
    }

    public Integer getLOAI_ANTEN_ID1() {
        return LOAI_ANTEN_ID1;
    }

    public void setLOAI_ANTEN_ID1(Integer LOAI_ANTEN_ID1) {
        this.LOAI_ANTEN_ID1 = LOAI_ANTEN_ID1;
    }

    public String getTEN_ANTENA1() {
        return TEN_ANTENA1;
    }

    public void setTEN_ANTENA1(String TEN_ANTENA1) {
        this.TEN_ANTENA1 = TEN_ANTENA1;
    }

    public String getHANG_SX_ANTENA1() {
        return HANG_SX_ANTENA1;
    }

    public void setHANG_SX_ANTENA1(String HANG_SX_ANTENA1) {
        this.HANG_SX_ANTENA1 = HANG_SX_ANTENA1;
    }

    public Integer getSO_LUONG_ANTENA1() {
        return SO_LUONG_ANTENA1;
    }

    public void setSO_LUONG_ANTENA1(Integer SO_LUONG_ANTENA1) {
        this.SO_LUONG_ANTENA1 = SO_LUONG_ANTENA1;
    }

    public Integer getBANG_TANG_ANTENA_ID1() {
        return BANG_TANG_ANTENA_ID1;
    }

    public void setBANG_TANG_ANTENA_ID1(Integer BANG_TANG_ANTENA_ID1) {
        this.BANG_TANG_ANTENA_ID1 = BANG_TANG_ANTENA_ID1;
    }

    public Integer getCAU_HINH_PORT_ID1() {
        return CAU_HINH_PORT_ID1;
    }

    public void setCAU_HINH_PORT_ID1(Integer CAU_HINH_PORT_ID1) {
        this.CAU_HINH_PORT_ID1 = CAU_HINH_PORT_ID1;
    }

    public String getCAU_HINH_GAIN1() {
        return CAU_HINH_GAIN1;
    }

    public void setCAU_HINH_GAIN1(String CAU_HINH_GAIN1) {
        this.CAU_HINH_GAIN1 = CAU_HINH_GAIN1;
    }

    public String getCAU_HINH_BEAM_WIDTH1() {
        return CAU_HINH_BEAM_WIDTH1;
    }

    public void setCAU_HINH_BEAM_WIDTH1(String CAU_HINH_BEAM_WIDTH1) {
        this.CAU_HINH_BEAM_WIDTH1 = CAU_HINH_BEAM_WIDTH1;
    }

    public Integer getTRONG_LUONG1() {
        return TRONG_LUONG1;
    }

    public void setTRONG_LUONG1(Integer TRONG_LUONG1) {
        this.TRONG_LUONG1 = TRONG_LUONG1;
    }

    public Integer getKICH_CO_CAO1() {
        return KICH_CO_CAO1;
    }

    public void setKICH_CO_CAO1(Integer KICH_CO_CAO1) {
        this.KICH_CO_CAO1 = KICH_CO_CAO1;
    }

    public Integer getKICH_CO_RONG1() {
        return KICH_CO_RONG1;
    }

    public void setKICH_CO_RONG1(Integer KICH_CO_RONG1) {
        this.KICH_CO_RONG1 = KICH_CO_RONG1;
    }

    public Integer getKICH_CO_SAU1() {
        return KICH_CO_SAU1;
    }

    public void setKICH_CO_SAU1(Integer KICH_CO_SAU1) {
        this.KICH_CO_SAU1 = KICH_CO_SAU1;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT1() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT1(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT1) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT1 = DO_CAO_ANTENA_SO_VOI_MAT_DAT1;
    }

    public Integer getLOAI_ANTEN_ID2() {
        return LOAI_ANTEN_ID2;
    }

    public void setLOAI_ANTEN_ID2(Integer LOAI_ANTEN_ID2) {
        this.LOAI_ANTEN_ID2 = LOAI_ANTEN_ID2;
    }

    public String getTEN_ANTENA2() {
        return TEN_ANTENA2;
    }

    public void setTEN_ANTENA2(String TEN_ANTENA2) {
        this.TEN_ANTENA2 = TEN_ANTENA2;
    }

    public String getHANG_SX_ANTENA2() {
        return HANG_SX_ANTENA2;
    }

    public void setHANG_SX_ANTENA2(String HANG_SX_ANTENA2) {
        this.HANG_SX_ANTENA2 = HANG_SX_ANTENA2;
    }

    public Integer getSO_LUONG_ANTENA2() {
        return SO_LUONG_ANTENA2;
    }

    public void setSO_LUONG_ANTENA2(Integer SO_LUONG_ANTENA2) {
        this.SO_LUONG_ANTENA2 = SO_LUONG_ANTENA2;
    }

    public Integer getBANG_TANG_ANTENA_ID2() {
        return BANG_TANG_ANTENA_ID2;
    }

    public void setBANG_TANG_ANTENA_ID2(Integer BANG_TANG_ANTENA_ID2) {
        this.BANG_TANG_ANTENA_ID2 = BANG_TANG_ANTENA_ID2;
    }

    public Integer getCAU_HINH_PORT_ID2() {
        return CAU_HINH_PORT_ID2;
    }

    public void setCAU_HINH_PORT_ID2(Integer CAU_HINH_PORT_ID2) {
        this.CAU_HINH_PORT_ID2 = CAU_HINH_PORT_ID2;
    }

    public String getCAU_HINH_GAIN2() {
        return CAU_HINH_GAIN2;
    }

    public void setCAU_HINH_GAIN2(String CAU_HINH_GAIN2) {
        this.CAU_HINH_GAIN2 = CAU_HINH_GAIN2;
    }

    public String getCAU_HINH_BEAM_WIDTH2() {
        return CAU_HINH_BEAM_WIDTH2;
    }

    public void setCAU_HINH_BEAM_WIDTH2(String CAU_HINH_BEAM_WIDTH2) {
        this.CAU_HINH_BEAM_WIDTH2 = CAU_HINH_BEAM_WIDTH2;
    }

    public Integer getTRONG_LUONG2() {
        return TRONG_LUONG2;
    }

    public void setTRONG_LUONG2(Integer TRONG_LUONG2) {
        this.TRONG_LUONG2 = TRONG_LUONG2;
    }

    public Integer getKICH_CO_CAO2() {
        return KICH_CO_CAO2;
    }

    public void setKICH_CO_CAO2(Integer KICH_CO_CAO2) {
        this.KICH_CO_CAO2 = KICH_CO_CAO2;
    }

    public Integer getKICH_CO_RONG2() {
        return KICH_CO_RONG2;
    }

    public void setKICH_CO_RONG2(Integer KICH_CO_RONG2) {
        this.KICH_CO_RONG2 = KICH_CO_RONG2;
    }

    public Integer getKICH_CO_SAU2() {
        return KICH_CO_SAU2;
    }

    public void setKICH_CO_SAU2(Integer KICH_CO_SAU2) {
        this.KICH_CO_SAU2 = KICH_CO_SAU2;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT2() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT2(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT2) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT2 = DO_CAO_ANTENA_SO_VOI_MAT_DAT2;
    }

    public Integer getLOAI_ANTEN_ID3() {
        return LOAI_ANTEN_ID3;
    }

    public void setLOAI_ANTEN_ID3(Integer LOAI_ANTEN_ID3) {
        this.LOAI_ANTEN_ID3 = LOAI_ANTEN_ID3;
    }

    public String getTEN_ANTENA3() {
        return TEN_ANTENA3;
    }

    public void setTEN_ANTENA3(String TEN_ANTENA3) {
        this.TEN_ANTENA3 = TEN_ANTENA3;
    }

    public String getHANG_SX_ANTENA3() {
        return HANG_SX_ANTENA3;
    }

    public void setHANG_SX_ANTENA3(String HANG_SX_ANTENA3) {
        this.HANG_SX_ANTENA3 = HANG_SX_ANTENA3;
    }

    public Integer getSO_LUONG_ANTENA3() {
        return SO_LUONG_ANTENA3;
    }

    public void setSO_LUONG_ANTENA3(Integer SO_LUONG_ANTENA3) {
        this.SO_LUONG_ANTENA3 = SO_LUONG_ANTENA3;
    }

    public Integer getBANG_TANG_ANTENA_ID3() {
        return BANG_TANG_ANTENA_ID3;
    }

    public void setBANG_TANG_ANTENA_ID3(Integer BANG_TANG_ANTENA_ID3) {
        this.BANG_TANG_ANTENA_ID3 = BANG_TANG_ANTENA_ID3;
    }

    public Integer getCAU_HINH_PORT_ID3() {
        return CAU_HINH_PORT_ID3;
    }

    public void setCAU_HINH_PORT_ID3(Integer CAU_HINH_PORT_ID3) {
        this.CAU_HINH_PORT_ID3 = CAU_HINH_PORT_ID3;
    }

    public String getCAU_HINH_GAIN3() {
        return CAU_HINH_GAIN3;
    }

    public void setCAU_HINH_GAIN3(String CAU_HINH_GAIN3) {
        this.CAU_HINH_GAIN3 = CAU_HINH_GAIN3;
    }

    public String getCAU_HINH_BEAM_WIDTH3() {
        return CAU_HINH_BEAM_WIDTH3;
    }

    public void setCAU_HINH_BEAM_WIDTH3(String CAU_HINH_BEAM_WIDTH3) {
        this.CAU_HINH_BEAM_WIDTH3 = CAU_HINH_BEAM_WIDTH3;
    }

    public Integer getTRONG_LUONG3() {
        return TRONG_LUONG3;
    }

    public void setTRONG_LUONG3(Integer TRONG_LUONG3) {
        this.TRONG_LUONG3 = TRONG_LUONG3;
    }

    public Integer getKICH_CO_CAO3() {
        return KICH_CO_CAO3;
    }

    public void setKICH_CO_CAO3(Integer KICH_CO_CAO3) {
        this.KICH_CO_CAO3 = KICH_CO_CAO3;
    }

    public Integer getKICH_CO_RONG3() {
        return KICH_CO_RONG3;
    }

    public void setKICH_CO_RONG3(Integer KICH_CO_RONG3) {
        this.KICH_CO_RONG3 = KICH_CO_RONG3;
    }

    public Integer getKICH_CO_SAU3() {
        return KICH_CO_SAU3;
    }

    public void setKICH_CO_SAU3(Integer KICH_CO_SAU3) {
        this.KICH_CO_SAU3 = KICH_CO_SAU3;
    }

    public Integer getDO_CAO_ANTENA_SO_VOI_MAT_DAT3() {
        return DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    }

    public void setDO_CAO_ANTENA_SO_VOI_MAT_DAT3(Integer DO_CAO_ANTENA_SO_VOI_MAT_DAT3) {
        this.DO_CAO_ANTENA_SO_VOI_MAT_DAT3 = DO_CAO_ANTENA_SO_VOI_MAT_DAT3;
    }

    public Date getNGAY_DAP_UNG_ANTENA_DU_KIEN() {
        return NGAY_DAP_UNG_ANTENA_DU_KIEN;
    }

    public void setNGAY_DAP_UNG_ANTENA_DU_KIEN(Date NGAY_DAP_UNG_ANTENA_DU_KIEN) {
        this.NGAY_DAP_UNG_ANTENA_DU_KIEN = NGAY_DAP_UNG_ANTENA_DU_KIEN;
    }

    public Date getNGAY_DAP_UNG_ANTENA_THUC_TE() {
        return NGAY_DAP_UNG_ANTENA_THUC_TE;
    }

    public void setNGAY_DAP_UNG_ANTENA_THUC_TE(Date NGAY_DAP_UNG_ANTENA_THUC_TE) {
        this.NGAY_DAP_UNG_ANTENA_THUC_TE = NGAY_DAP_UNG_ANTENA_THUC_TE;
    }

    public Integer getTRAM_QH_ID() {
        return TRAM_QH_ID;
    }

    public void setTRAM_QH_ID(Integer TRAM_QH_ID) {
        this.TRAM_QH_ID = TRAM_QH_ID;
    }

    public String getMA_QUY_HOACH() {
        return MA_QUY_HOACH;
    }

    public void setMA_QUY_HOACH(String MA_QUY_HOACH) {
        this.MA_QUY_HOACH = MA_QUY_HOACH;
    }

    public String getTEN_QUY_HOACH() {
        return TEN_QUY_HOACH;
    }

    public void setTEN_QUY_HOACH(String TEN_QUY_HOACH) {
        this.TEN_QUY_HOACH = TEN_QUY_HOACH;
    }
    
    

}
