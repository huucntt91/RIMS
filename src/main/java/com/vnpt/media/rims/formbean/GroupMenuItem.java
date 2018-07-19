/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.List;

/**
 *
 * @author VNP
 */
public class GroupMenuItem {

    private String stt;
    private long menuId;
    private String menuName;
    private int level;
    private List<GroupBeanBO> groupBeanBO;

    public GroupMenuItem() {
    }

    public GroupMenuItem(String menuName) {
        this.menuName = menuName;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

   

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<GroupBeanBO> getGroupBeanBO() {
        return groupBeanBO;
    }

    public void setGroupBeanBO(List<GroupBeanBO> groupBeanBO) {
        this.groupBeanBO = groupBeanBO;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

  

}
