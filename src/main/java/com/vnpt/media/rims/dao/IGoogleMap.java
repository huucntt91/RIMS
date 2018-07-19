package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.GoogleMapBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.exception.ServiceException;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IGoogleMap extends IGeneric {

    List<GoogleMapBO> findAll(String nodeCode, String[] classType, Long tinhTpId,Long quanHuyenId,Long phuongXaId) throws DAOException;
    
    List<GoogleMapBO> findLinkNode(String nodeCode, String[] classType, Long tinhTpId,Long quanHuyenId,Long phuongXaId) throws DAOException;

}
