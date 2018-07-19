/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vnpt.media.rims.common;


public class Message {
    public static final String TYPE_SUCCESS = "alert-success";
    public static final String TYPE_INFO = "alert-default";
    public static final String TYPE_WARNING = "alert-warning";
    public static final String TYPE_DANGER = "alert-danger";
    public static final String HEAD_SUCCESS = "Thành công";
    public static final String HEAD_INFO = "Thông tin";
    public static final String HEAD_WARNING = "Cảnh báo";
    public static final String HEAD_DANGER = "Lỗi";
    
    
    public static final String MESSAGE_INPUT_DEFAULT = "Thông tin nhập vào không chính xác";
    public static final String MESSAGE_SYSTEM_DEFAULT_ERROR = "Có lỗi trong quá trình xử lý. Vui lòng thử lại";
    public static final String MESSAGE_ADD_SUCCESS = "Thêm dữ liệu thành công";
    public static final String MESSAGE_DELETE_SUCCESS = "Xóa dữ liệu thành công";
    public static final String MESSAGE_UPDATE_SUCCESS = "Cập nhật dữ liệu thành công";
    public static final String MESSAGE_ADD_ERROR = "Thêm dữ liệu không thành công";
    public static final String MESSAGE_DELETE_ERROR = "Xóa dữ liệu không thành công";
    public static final String MESSAGE_UPDATE_ERROR = "Cập nhật dữ liệu không thành công";
    public String type;
    public String content;
    public String head;
    public Message(String type, String head,String content) {
        this.type = type;
        this.head=head;
        this.content = content;
    }

    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
    
}
