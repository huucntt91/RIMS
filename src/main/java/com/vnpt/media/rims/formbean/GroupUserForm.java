/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import java.util.List;
import com.vnpt.media.rims.bean.GroupBO;

/**
 *
 * @author VNP
 */
public class GroupUserForm {

    private String userId;
    private List<GroupBO> groupBOList;

    public GroupUserForm() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<GroupBO> getGroupBOList() {
        return groupBOList;
    }

    public void setGroupBOList(List<GroupBO> groupBOList) {
        this.groupBOList = groupBOList;
    }

   
}
