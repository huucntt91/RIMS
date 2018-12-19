package com.vnpt.media.rims.controller.security;

import com.vnpt.media.rims.bean.MenuBO;
import com.vnpt.media.rims.bean.TinhTpGiapRanhBO;
import com.vnpt.media.rims.bean.UserBO;
import com.vnpt.media.rims.common.Constants;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.facade.ManagerAdminFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "SetSessionServlet", urlPatterns = {"/SetSessionServlet"})
public class SetSessionServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(SetSessionServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean result = initSession(request, response);
        if (!result) {
            LOGGER.debug("Redirect:" + request.getContextPath() + "/noreg");
            response.sendRedirect(request.getContextPath() + "/noreg");
        } else {
            LOGGER.debug("Redirect:" + request.getContextPath() + "");
            if (request.getContextPath() == null || request.getContextPath().isEmpty()) {
                response.sendRedirect("https://oss.vnpt.vn/");
            } else {
                response.sendRedirect(request.getContextPath() + "");
            }
        }
    }

    public boolean initSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext ct = request.getServletContext();
        ManagerAdminFacade adminFacade = new ManagerAdminFacade();
        LOGGER.debug("RemoteUser:{}", request.getRemoteUser());
        try {
            UserBO t = adminFacade.findByUserName(request.getRemoteUser());
            if (t == null) {
                return false;
            }
            adminFacade.UserLogin(String.valueOf(t.getId()), request.getRemoteAddr());
            LOGGER.debug("Insert action login:ID={},IP={}", t.getId(), request.getRemoteAddr());
            String data = getTreeMenu(ct, request, request.getRemoteUser());
            request.getSession().setAttribute(Constants.MENU_KEY, data);
            List<MenuBO> function = adminFacade.getAllFunctionByUser(request.getRemoteUser());
            StringBuilder tempFunction = new StringBuilder();
            if (function != null && function.size() > 0) {
                for (int i = 0; i < function.size(); i++) {
                    tempFunction.append(function.get(i).getMappingUrl());
                    tempFunction.append(";");
                }
            }
            request.getSession().setAttribute(Constants.FUNCTION_KEY, tempFunction);
            request.getSession().setAttribute(Constants.USER_KEY, t);

            List<String> provinces = new ArrayList<>();
            String[] provincePermission = null;
            try {
                provincePermission = adminFacade.findListTinhByUserId(String.valueOf(t.getId()));
                provinces.addAll(Arrays.stream(provincePermission).collect(Collectors.toList()));
                //lấy danh sách tính giáp ranh được phân quyền
                List<TinhTpGiapRanhBO> listByUser = adminFacade.findTinhTpGiapRanh(t.getId(), Constants.DOI_TUONG_USER);
                List<TinhTpGiapRanhBO> listByGroup = adminFacade.findTinhTpGiapRanh(t.getId(), Constants.DOI_TUONG_GROUP);
                if(listByUser == null) listByUser = new ArrayList<>();
                if(listByGroup == null) listByGroup = new ArrayList<>();

                provinces.addAll(listByUser.stream().map(x->x.getMaTinhTp()).collect(Collectors.toList()));
                provinces.addAll(listByGroup.stream().map(x->x.getMaTinhTp()).collect(Collectors.toList()));
                listByUser.addAll(listByGroup);
                request.getSession().setAttribute(Constants.PROVINCE_GIAP_RANH, listByUser);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            request.getSession().setAttribute(Constants.PROVINCE_KEY, provinces.toArray(new String[0]));
            return true;
        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return false;
        }
    }

    private String getTreeMenu(ServletContext ct, HttpServletRequest request, String username) {
        List<MenuBO> menuList;
        StringBuffer sb = new StringBuffer();
        try {
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            UserBO userBO = adminFacade.findByUserName(username);
            request.getSession().setAttribute("fullname", userBO.getFullname());
            request.getSession().setAttribute("email", userBO.getEmail());
            request.getSession().setAttribute("userBO", userBO);
            menuList = adminFacade.getChildrenMenuByUserId("0", null, userBO, null);
            for (MenuBO menu : menuList) {
                sb.append("<li data='").append(menu.getName()).append("' class=\"dropdown\">");
                sb.append(" <a class='dropdown-toggle' data-toggle='dropdown' href=\"#\">\n").append(menu.getName()).append("<b class='caret'></b></a>");
                createChildMenu(ct, request, menu, sb, userBO);
                sb.append("</li>");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    private void createChildMenu(ServletContext ct, HttpServletRequest request, MenuBO menu, StringBuffer sb, UserBO userBO) throws IOException {
        try {
            // tim cac menu con cua menu cha truyen vao
            ManagerAdminFacade adminFacade = new ManagerAdminFacade();
            List<MenuBO> childMenu = adminFacade.getChildrenMenuByUserId(menu.getId() + "", null, userBO, null);
            if (childMenu != null && childMenu.size() > 0) {
                sb.append("<ul class=\"dropdown-menu\">\n");
                int dem = 0;
                for (MenuBO child : childMenu) {
                    sb.append(child.getMappingUrl().equals("#") ? " <li data='" + child.getName() + "'>" : " <li data='" + child.getName() + "'>");
                    if (child.getMappingUrl().equals("#")) {
                        sb.append("<a href='#' class='dropdown-toggle' data-toggle='dropdown' >").append(child.getName()).append("<b class='caret'></b></a>");
                    } else {
                        sb.append("<a  href='").append(request.getContextPath()).append("/").append(child.getMappingUrl()).append("' >").append(child.getName()).append("</a>");
                    }
                    request.getSession().setAttribute("menuMapping",
                            request.getSession().getAttribute("menuMapping") + ";" + child.getMappingUrl());
                    createChildMenu(ct, request, child, sb, userBO);
                    dem += 1;
                }
                for (int i = 0; i < dem; i++) {
                    sb.append("</li>");
                }
                sb.append("</ul>");
            }
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
