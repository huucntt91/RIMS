package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ContactForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IContact extends IGeneric {

    public List<ContactForm> findContactByGroupId(String groupContactId, String contactId, String name) throws ServiceException ;
public void addContact(ContactForm contactForm) throws DAOException;
    public void updateContact(ContactForm contactForm) throws DAOException ;
        public void deleteContact(String contactId) throws DAOException ;

//    public ImportNodeForm findGroupContactById(String id) throws ServiceException;
//
//    public void updateGroupContact(ImportNodeForm groupContactForm) throws DAOException;
//
//    public void deleteGroupContact(String groupContactId) throws DAOException;
//
//    public void addGroupContact(ImportNodeForm cpBO, long cpId) throws DAOException;
//
//    public long getSeqGroupContact() throws DAOException;
}
