package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.DanhMucBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.TrangThaiHDBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDanhMuc extends IGeneric {

    public List<DanhMucBO> findDmOkNok() throws DAOException;
    
    public List<DanhMucBO> findDmNguonThietBi() throws DAOException;
    
    public List<DanhMucBO> findDmLoaiCongNghe() throws DAOException;
    
    public List<DanhMucBO> findDmHienTrangTram() throws DAOException;
    
    public List<DanhMucBO> findDmDungLuongACCU() throws DAOException;

}
