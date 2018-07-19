package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.transaction.ITransaction;



/**
 * Created with IntelliJ IDEA.
 * User: haidd
 * Date: 6/20/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGeneric {

    void setTransaction(ITransaction trans) throws DAOException;
}
