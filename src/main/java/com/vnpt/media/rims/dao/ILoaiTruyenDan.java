package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.LoaiMayNoBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.*;



/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface ILoaiTruyenDan extends IGeneric {

    public List<LoaiTruyenDanBO> findAll(String id) throws DAOException;
    public int modify(String action, LoaiTruyenDanBO loaiTruyenDanBO) throws DAOException;
}
