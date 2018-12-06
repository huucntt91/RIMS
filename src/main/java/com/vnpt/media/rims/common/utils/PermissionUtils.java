package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.bean.UserAttrBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PermissionUtils {
    private static HttpSession ss;
    protected static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PermissionUtils.class);

    private static List<UserAttrBO> listUserAttrBo;
    public static Boolean checkUserAttr(String attrCode, String attrClassCode, String acctionName) {
        LOGGER.debug("PermissionUtils.checkUserAttr");
        Boolean result = false;
        try {
            if(listUserAttrBo == null){
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                UserBO user = (UserBO)attr.getRequest().getSession().getAttribute(Constants.USER_KEY);
                if(user == null)
                    return  false;
                ManagerAdminFacade adminFacade = new ManagerAdminFacade();
                listUserAttrBo = adminFacade.findUserAttrByUserId(String.valueOf(user.getId()));
            }

            if(listUserAttrBo == null){
                return  false;
            }
            result = listUserAttrBo.stream().anyMatch(x->x.getAttr().getAttrCode().equals(attrCode) && x.getAttClass().getCode().equals(attrClassCode) && (x.getAction() + ",").contains(acctionName + ","));
            return result;
        }catch (Exception ex){
            listUserAttrBo = null;
            LOGGER.error("Exception :", ex);
        }
        finally {
            return result;
        }
    }

    public static void clear(){
        listUserAttrBo = null;
    }
}
