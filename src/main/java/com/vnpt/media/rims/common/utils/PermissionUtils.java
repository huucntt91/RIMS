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

    public static Boolean checkUserAttr(String attrCode, String attrClassCode, String acctionName) {
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

            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAttrCode().equals(attrCode) && x.getAttClass().getCode() == attrClassCode && x.getAction().equals(acctionName)))
            {
                return  true;
            }

            if(listUserAttrBo.stream().anyMatch(x->x.getAttr().getAttrCode().equals(attrCode) && x.getAttClass().getCode().equals(attrClassCode) && x.getAction().equals("NOT" + acctionName)))
            {
                return  false;
            }
            return true;
        }catch (Exception ex){
            LOGGER.error("Exception :", ex);
            return  false;
        }
    }
}
