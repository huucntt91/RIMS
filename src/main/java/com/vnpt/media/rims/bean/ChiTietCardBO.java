/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

/**
 *
 * @author Cyano
 */
public class ChiTietCardBO extends  TNodeBO{
    String tvendorName;
    int frame;
    int slot;
    String cardName;
    String serialNumber;

    public String getTvendorName() {
        return tvendorName;
    }

    public void setTvendorName(String tvendorName) {
        this.tvendorName = tvendorName;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    
}
