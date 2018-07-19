package com.vnpt.media.rims.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: thanhnd.ho Date: 6/17/13 Time: 6:12 PM To
 * change this template use File | Settings | File Templates.
 */
public class MenuBO implements Serializable {

    private long id;
    private String name;
    private long parentId;
    private String mappingUrl;
    private String icon;
    private boolean selected;

    public MenuBO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
