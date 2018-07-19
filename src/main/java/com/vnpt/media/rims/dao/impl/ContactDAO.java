package com.vnpt.media.rims.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.common.utils.StringUtils;
import com.vnpt.media.rims.dao.IContact;
import com.vnpt.media.rims.exception.ConnectionException;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.JdbcException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ContactForm;
import com.vnpt.media.rims.formbean.ImportNodeForm;
import com.vnpt.media.rims.jdbc.RowMapper;
import com.vnpt.media.rims.jdbc.SQLTemplate;

public class ContactDAO extends GenericDAO implements IContact {

    private static Logger logger = LogManager.getLogger(ContactDAO.class);

    @Override

    public List<ContactForm> findContactByGroupId(String groupContactId, String contactId, String name) throws ServiceException {
        Connection conn = null;
        try {

            conn = this.getConnection();

            String querySql = "select g.* ,gc.name gcname from tbl_contact g inner join tbl_group_contact gc on g.group_id=gc.id where 1=1 ";

            if (StringUtils.hasText(groupContactId)) {
                querySql = querySql + " and g.group_id = ?  ";
            }
            if (StringUtils.hasText(contactId)) {
                querySql = querySql + " and g.id = ?  ";
            }
            if (StringUtils.hasText(name)) {
                querySql = querySql + " and g.phone = ?  ";
            }
querySql+=" order by g.id desc ";


            List<Object> vars = new Vector<Object>();
//            vars.add(level);
            if (StringUtils.hasText(groupContactId)) {
                vars.add(groupContactId.trim());
            }
            if (StringUtils.hasText(contactId)) {
                vars.add(contactId.trim());
            }
            if (StringUtils.hasText(name)) {
                vars.add(name.trim());
            }
            SQLTemplate sqlTemplate = new SQLTemplate(conn);
            List<?> list = sqlTemplate.query(querySql, new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ContactForm temp = new ContactForm();
                    temp.setPhone(rs.getString("phone"));
                    temp.setGroupId(rs.getLong("group_id"));
                    temp.setGroupName(rs.getString("gcname"));                    
                    temp.setId(rs.getLong("id"));
                    return temp;
                }
            }, vars);

            return (List<ContactForm>) list;
        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
        
        
    }
    @Override
    public void addContact(ContactForm contactForm) throws DAOException {
         Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "insert into tbl_contact(id, phone,group_id)";
            querySql += " values(SEQ_CONTACT.nextval, "
                    + "?,?)";
            if (contactForm == null) {
                return;
            }
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(contactForm.getPhone().trim());

            vars.add(contactForm.getGroupId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdate(querySql, vars);

            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
    
    @Override
    public void updateContact(ContactForm contactForm) throws DAOException {
         Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "update tbl_contact set phone=? where id=?";
            if (contactForm == null) {
                return;
            }
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(contactForm.getPhone().trim());

            vars.add(contactForm.getId());

            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdate(querySql, vars);

            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
    
    @Override
    public void deleteContact(String contactId) throws DAOException {
         Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "delete from  tbl_contact where id=?";
           
            //logger.info("SQL : " + querySql);
            List<Object> vars = new Vector<Object>();
            vars.add(contactId.trim());


            SQLTemplate sqlTemplate = new SQLTemplate(conn);

            int count = sqlTemplate.executeUpdate(querySql, vars);

            if (count > 0) {
                logger.debug("Records update : " + count);
            } else {
                throw new DAOException();
            }

        } catch (ConnectionException e) {
            logger.error("ConnectionException :", e);
            throw new DAOException(e);
        } catch (JdbcException e) {
            logger.error("JdbcException :", e);
            throw new DAOException(e);
        } catch (Exception e) {
            logger.error("Exception :", e);
            throw new DAOException(e);
        }
    }
}
