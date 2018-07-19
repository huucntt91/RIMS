package com.vnpt.media.rims.bean;

import java.io.Serializable;

public class ConfLayerBO implements Serializable {

    public String layerId;
    public String layerName;
    public String styleOpacity;
    public String styleColor;
    public String styleBorderColor;
    public String styleSize;
    public String styleWhere;
    public String userId;
    public String objectId;
    
    //lst_event
    public String eventId;
    public String eventName;
    public String techType;
    public String startDate;
    public String endDate;
    
    public String nodeId;
    public String maNode;
    public String latitude;
    public String longitude;
    //lst_event

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getStyleOpacity() {
        return styleOpacity;
    }

    public void setStyleOpacity(String styleOpacity) {
        this.styleOpacity = styleOpacity;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleBorderColor() {
        return styleBorderColor;
    }

    public void setStyleBorderColor(String styleBorderColor) {
        this.styleBorderColor = styleBorderColor;
    }

    public String getStyleSize() {
        return styleSize;
    }

    public void setStyleSize(String styleSize) {
        this.styleSize = styleSize;
    }

    public String getStyleWhere() {
        return styleWhere;
    }

    public void setStyleWhere(String styleWhere) {
        this.styleWhere = styleWhere;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
