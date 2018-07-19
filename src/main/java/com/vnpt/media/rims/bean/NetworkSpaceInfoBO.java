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
 * @author VNP
 */
public class NetworkSpaceInfoBO{
    private String networkSpaceId;
    private String networkSpaceName;
    private String note;

    public String getNetworkSpaceId() {
        return networkSpaceId;
    }

    public void setNetworkSpaceId(String networkSpaceId) {
        this.networkSpaceId = networkSpaceId;
    }

    public String getNetworkSpaceName() {
        return networkSpaceName;
    }

    public void setNetworkSpaceName(String networkSpaceName) {
        this.networkSpaceName = networkSpaceName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
}
