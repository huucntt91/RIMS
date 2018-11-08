/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.bean.ActionLogBO;
import com.vnpt.media.rims.bean.AntenInfoBO;
import com.vnpt.media.rims.bean.ObjectLog;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Cyano
 */
public class LogUtils {
    
    private static Logger logger = LogManager.getLogger(LogUtils.class);

    /*
    so sanh 2 object, lấy ra giá trị old và new
     */
    public static List compareObject(Object oldObject, Object newObject, String userId, String action, String objectName,String objectID) {
        Field[] fieldsOld = null;
        Field[] fieldsNew = null;
        ObjectLog objectLog = null;
        List objectLogs = new ArrayList<>();
        try {
            if (newObject == null) {
                return null;
            }
            if (!action.equalsIgnoreCase("INSERT") && oldObject == null) {
                return null;
            }
            
            if (action.equalsIgnoreCase("INSERT")) {
                fieldsNew = newObject.getClass().getDeclaredFields();
                if (fieldsNew == null) {
                    return null;
                }
                for (int i = 0; i < fieldsNew.length; i++) {
                    Field fNew = fieldsNew[i];
                    if (fNew.getType().isPrimitive()) {
                        continue;
                    }
                    fNew.setAccessible(true);
                    String valueNew = null;
                    if (fNew.getType().equals(String.class)) {
                        valueNew = (String) fNew.get(newObject) == null ? "" : (String) fNew.get(newObject);
                    } else if (fNew.getType().equals(Long.class)) {
                        valueNew = (Long) fNew.get(newObject) == null ? "" : ((Long) fNew.get(newObject)).toString();
                    } else if (fNew.getType().equals(Double.class)) {
                        valueNew = (Double) fNew.get(newObject) == null ? "" : ((Double) fNew.get(newObject)).toString();
                    } else if (fNew.getType().equals(Integer.class)) {
                        valueNew = (Integer) fNew.get(newObject) == null ? "" : ((Integer) fNew.get(newObject)).toString();
                    } else if (fNew.getType().equals(Date.class)) {
                        valueNew = DateTimeUtils.convertDateString((Date) fNew.get(newObject), "yyyy/MM/dd");
                    }
                    if (valueNew != null && !(valueNew.isEmpty())) {
                        logger.debug("userId: " + userId + ", action: " + action + ", valueNew: " + valueNew);
                        objectLog = new ObjectLog();
                        objectLog.setActionName(action);
                        objectLog.setObjectName(objectName);
                        objectLog.setAttributeName(fNew.getName());
                        objectLog.setNewValue(valueNew);
                        objectLog.setUserId(userId);
                        objectLog.setObjectId(objectID);
                        objectLogs.add(objectLog);
                    }
                }
            } else {
                fieldsOld = oldObject.getClass().getDeclaredFields();
                fieldsNew = newObject.getClass().getDeclaredFields();
                if (fieldsOld == null || fieldsNew == null) {
                    return null;
                }
                if (fieldsOld.length != fieldsNew.length) {
                    return null;
                }
                for (int i = 0; i < fieldsOld.length; i++) {
                    Field fOld = fieldsOld[i];
                    Field fNew = fieldsNew[i];
                    if (fOld.getType().isPrimitive() || fNew.getType().isPrimitive()) {
                        continue;
                    }
                    fOld.setAccessible(true);
                    fNew.setAccessible(true);
                    String valueOld = null;
                    String valueNew = null;
                    if (fOld.getType().equals(String.class) && fNew.getType().equals(String.class)) {
                        valueOld = (String) fOld.get(oldObject) == null ? "" : (String) fOld.get(oldObject);
                        valueNew = (String) fNew.get(newObject) == null ? "" : (String) fNew.get(newObject);
                    } else if (fOld.getType().equals(Long.class) && fNew.getType().equals(Long.class)) {
                        valueOld = (Long) fOld.get(oldObject) == null ? "" : ((Long) fOld.get(oldObject)).toString();
                        valueNew = (Long) fNew.get(newObject) == null ? "" : ((Long) fNew.get(newObject)).toString();
                    } else if (fOld.getType().equals(Double.class) && fNew.getType().equals(Double.class)) {
                        valueOld = (Double) fOld.get(oldObject) == null ? "" : ((Double) fOld.get(oldObject)).toString();
                        valueNew = (Double) fNew.get(newObject) == null ? "" : ((Double) fNew.get(newObject)).toString();
                    } else if (fOld.getType().equals(Integer.class) && fNew.getType().equals(Integer.class)) {
                        valueOld = (Integer) fOld.get(oldObject) == null ? "" : ((Integer) fOld.get(oldObject)).toString();
                        valueNew = (Integer) fNew.get(newObject) == null ? "" : ((Integer) fNew.get(newObject)).toString();
                    } else if (fOld.getType().equals(Date.class) && fNew.getType().equals(Date.class)) {
                        valueOld = DateTimeUtils.convertDateString((Date) fOld.get(oldObject), "yyyy/MM/dd");
                        valueNew = DateTimeUtils.convertDateString((Date) fNew.get(newObject), "yyyy/MM/dd");
                    }
                    /*
            neu giá trị mới khác giá trị cũ và 
            giá trị mới là null trong TH update thì bỏ quá
                     */
                    if (valueOld != null && valueNew != null && !valueOld.equals(valueNew) && !(valueNew.isEmpty())) {
                        logger.debug("userId: " + userId + ", action: " + action + ", valueOld: " + valueOld + ", valueNew: " + valueNew);
                        objectLog = new ObjectLog();
                        objectLog.setActionName(action);
                        objectLog.setObjectName(objectName);
                        objectLog.setAttributeName(fOld.getName());
                        objectLog.setOldValue(valueOld);
                        objectLog.setNewValue(valueNew);
                        objectLog.setUserId(userId);
                        objectLog.setObjectId(objectID);
                        objectLogs.add(objectLog);
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return objectLogs;
    }
    
    public static void main(String[] args) {
        try {
            ActionLogBO cellOld = new ActionLogBO();
            cellOld.setUser_name("2G_TBI037M11_VLG");
            cellOld.setActionlog_name("2G_TBI037M12_VLg");
            cellOld.setActionlog_time(new Date());
            ActionLogBO cellNew = new ActionLogBO();
            cellNew.setUser_name("12G_TBI037M11_VLG");
            cellNew.setActionlog_name("12G_TBI037M12_VLG");
//            cellNew.setActionlog_time(new Date(2017 - 1900, 10, 10));
            AntenInfoBO bts = new AntenInfoBO();
            
            System.out.println(compareObject(null, cellNew, "1", "INSERT", "",""));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
