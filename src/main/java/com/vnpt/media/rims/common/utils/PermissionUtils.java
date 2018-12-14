package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.bean.UserAttrBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PermissionUtils {
    private static HttpSession ss;
    protected static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PermissionUtils.class);

    public static Boolean checkUserAttr(String attrCode, String attrClassCode, String objectCode, String acctionName) {
        LOGGER.debug("PermissionUtils.checkUserAttr");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        try {
            List<UserAttrBO> listUserAttrBo = (List<UserAttrBO>)session.getAttribute(Constants.USER_ATTR_KEY);
            //if(listUserAttrBo == null){
                UserBO user = (UserBO)session.getAttribute(Constants.USER_KEY);
                if(user == null)
                    return  false;
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                listUserAttrBo = adminFacade.findUserAttrByUserId(String.valueOf(user.getId()));
                session.setAttribute(Constants.USER_ATTR_KEY, listUserAttrBo);
            //}

            if(listUserAttrBo == null){
                return  false;
            }
            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAttrCode().equalsIgnoreCase(attrCode) && x.getAttClass().getCode().equalsIgnoreCase(attrClassCode) && x.getObject().getCode().equalsIgnoreCase(objectCode) && x.getAction().equalsIgnoreCase("NOT" + acctionName)))
            {
                return  false;
            }
            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAttrCode().equalsIgnoreCase(attrCode) && x.getAttClass().getCode().equalsIgnoreCase(attrClassCode) && x.getObject().getCode().equalsIgnoreCase(objectCode) && x.getAction().equalsIgnoreCase(acctionName)))
            {
                return  true;
            }
            return true;
        }catch (Exception ex){
            LOGGER.error("Exception :", ex);
            return  false;
        }
    }

    public static Boolean checkUserExcelAttr(String attrCode, String attrClassCode, String acctionName) {
        LOGGER.debug("PermissionUtils.checkUserAttr");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        try {
            List<UserAttrBO> listUserAttrBo = (List<UserAttrBO>)session.getAttribute(Constants.USER_ATTR_KEY);
            if(listUserAttrBo == null){
                UserBO user = (UserBO)session.getAttribute(Constants.USER_KEY);
                if(user == null)
                    return  false;
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                listUserAttrBo = adminFacade.findUserAttrByUserId(String.valueOf(user.getId()));
                session.setAttribute(Constants.USER_ATTR_KEY, listUserAttrBo);
            }

            if(listUserAttrBo == null){
                return  false;
            }

            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAliasExcelAttr().toUpperCase().equalsIgnoreCase(attrCode) && x.getAttClass().getCode().equalsIgnoreCase(attrClassCode) && x.getAction().equalsIgnoreCase(acctionName)))
            {
                return  true;
            }

            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAliasExcelAttr().toUpperCase().equalsIgnoreCase(attrCode.toUpperCase()) && x.getAttClass().getCode().equalsIgnoreCase(attrClassCode) && x.getAction().equalsIgnoreCase("NOT" + acctionName)))
            {
                return  false;
            }
            return true;
        }catch (Exception ex){
            LOGGER.error("Exception :", ex);
            return  false;
        }
    }

    public static <T> void filterUserExcelAttr(List<T> items, List<String> listAttrClassCode) {
        LOGGER.debug("PermissionUtils.checkUserAttr");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        try {
            for (Iterator<String> iAttrClass = listAttrClassCode.iterator(); iAttrClass.hasNext();) {
                String attrClassCode = iAttrClass.next();
                for(int i = 0; i< items.size(); i++){
                    Object item = items.get(i);
                    Field[] fields = items.get(0).getClass().getDeclaredFields();
                    for(int j = 0; j < fields.length; j++){
                        Field field = fields[j];
                        field.setAccessible(true);
                        //nếu không có quyền update thì set là giá trị là rỗng
                        if(field.getType().getName().equals("java.lang.String") && !PermissionUtils.checkUserExcelAttr(field.getName(), attrClassCode, Constants.USER_PERMISSION_UPDATE)){
                            field.set(item, null);
                        }
                    }
                }
            }
        }catch (Exception ex){
            LOGGER.error("Exception :", ex);
        }
    }
}
