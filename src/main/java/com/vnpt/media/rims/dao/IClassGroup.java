package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.AttClassGroupBO;
import com.vnpt.media.rims.bean.AttClassListBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.ObjectListBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TinhBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IClassGroup extends IGeneric {

    public List<ObjectListBO> findAllObjectList(String id) throws DAOException;

    public List<AttClassListBO> findAttrClassListByObjectId(String objectId) throws DAOException;

    public List<AttClassGroupBO> findAttClassGroupByGroupId(String groupId) throws DAOException;

    public int modifyTinh(String action, TinhBO tinhBO) throws DAOException;

    public int modifyQuan(String action, HuyenBO huyenBO) throws DAOException;

    public int modifyPhuong(String action, PhuongXaBO phuongBO) throws DAOException;

    public List<TinhBO> findListTinhByUserId(String id) throws DAOException;

    public List<String> findClassAttrByUserId(String userId, String action, String objectId) throws DAOException;
}
