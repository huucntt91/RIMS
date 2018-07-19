/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.formbean;

import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TNodeBO;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class SearchTopoForm {

    private String tnode1;

    private String tnode2;

    private List<TLinkBO> listTLink;

    private List<TNodeBO> listTNode;

    public SearchTopoForm() {
    }

    public String getTnode1() {
        return tnode1;
    }

    public void setTnode1(String tnode1) {
        this.tnode1 = tnode1;
    }

    public String getTnode2() {
        return tnode2;
    }

    public void setTnode2(String tnode2) {
        this.tnode2 = tnode2;
    }

    
    public List<TLinkBO> getListTLink() {
        return listTLink;
    }

    public void setListTLink(List<TLinkBO> listTLink) {
        this.listTLink = listTLink;
    }

    public List<TNodeBO> getListTNode() {
        return listTNode;
    }

    public void setListTNode(List<TNodeBO> listTNode) {
        this.listTNode = listTNode;
    }

}
