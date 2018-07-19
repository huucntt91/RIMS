package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.CpBO;
import com.vnpt.media.rims.bean.DonViBO;
import com.vnpt.media.rims.bean.GroupBO;
import com.vnpt.media.rims.bean.UserBO;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IDonVi extends IGeneric {

 public List<DonViBO> findAll(String id, String name, String tinhs) throws DAOException;
 public void addDonVi(DonViBO cpBO) throws DAOException ;
 public void updateDonVi(DonViBO cpBO) throws DAOException;
 public void delete(String Id) throws DAOException;
}
