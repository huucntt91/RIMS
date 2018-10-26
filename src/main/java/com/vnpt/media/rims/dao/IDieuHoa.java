package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.LoaiDieuHoaBO;

/**
 * Created with IntelliJ IDEA. User: cuongvn Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDieuHoa extends IGeneric {

    public List<LoaiDieuHoaBO> findAll(String id) throws DAOException;

    public List<LoaiDieuHoaBO> findDieuHoaByName(String name) throws DAOException;

    public int modify(String action, LoaiDieuHoaBO dieuHoaBO) throws DAOException;
}
