/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;


/**
 *
 * @author Administrator
 */
public class Role  {

    private String name;

    public Role(String name) {
        this.name = name;
    }
    

    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
