package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.DuAnTinhBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.TramDuAnBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDuAnTinh extends IGeneric {

        List<DuAnTinhBO> findDATinh(String tinhTpId, String maDA) throws DAOException;
        
        public void modifyDuAnTinh(DuAnTinhBO daTinhBO) throws DAOException ;
        
        public int deleteDuAnTinh(DuAnTinhBO daTinhBO) throws DAOException ;

}
