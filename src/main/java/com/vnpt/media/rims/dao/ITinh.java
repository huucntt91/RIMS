package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.HuyenBO;
import com.vnpt.media.rims.bean.KhuvucBO;
import com.vnpt.media.rims.bean.PhuongXaBO;
import com.vnpt.media.rims.bean.TinhBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ITinh extends IGeneric {

    public List<TinhBO> findAllTinh(String id) throws DAOException;

    public List<KhuvucBO> findAllKhuVuc(String id) throws DAOException;

    public List<HuyenBO> findAllHuyen(String id, String tinhId) throws DAOException;

    public List<PhuongXaBO> findAllPhuongXa(String id, String huyenId) throws DAOException;

    public List<HuyenBO> findAllHuyen(String listTinhId) throws DAOException;

    public List<PhuongXaBO> findAllPhuongXa(String listHuyenId) throws DAOException;

    public int modifyTinh(String action, TinhBO tinhBO) throws DAOException;

    public int modifyQuan(String action, HuyenBO huyenBO) throws DAOException;

    public int modifyPhuong(String action, PhuongXaBO phuongBO) throws DAOException;

    public List<TinhBO> findListTinhByUserId(String id) throws DAOException;
}
