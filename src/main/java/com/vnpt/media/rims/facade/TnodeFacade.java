package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TnodeNodeBO;
import com.vnpt.media.rims.common.utils.DatabaseUtils;
import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.ITnode;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
//import com.vnpt.media.rims.formbean.Cell2GList;
import com.vnpt.media.rims.transaction.ITransaction;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TnodeFacade {

    private Logger logger = LogManager.getLogger(NodesFacade.class);
    private DAOFactory factory = null;
    private final String DB_RIMS_BB = "db_rims_bb";

    public TnodeFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public TnodeFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

    public List<TNodeBO> findAll(String tnodeid, String code, String name) throws ServiceException {
        ITransaction trans = null;
        try {
            trans = factory.getTransaction();
            ITnode i = factory.getTnodeDAO();
            trans.connectionType(DB_RIMS_BB);
            i.setTransaction(trans);
            trans.beginTransaction();
            List<TNodeBO> iRet = i.findAll(tnodeid, code, name);
            trans.commit();
            trans.endTransaction();
            return iRet;
        } catch (DAOException de) {
            logger.error("DAOException : ", de);
            DatabaseUtils.rollback(trans);
            throw new ServiceException(de);
        } finally {
            DatabaseUtils.close(trans);
        }
    }

    public List<NodeBO> findNode(String neTypeId, String tinhTpId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<NodeBO> result = null;
        try {
            trans = factory.getTransaction();
            trans.connectionType(DB_RIMS_BB);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_tnode.find_node(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, neTypeId);
            cs.setString(3, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                NodeBO item = new NodeBO();
                item.setId(rs.getLong("node_id"));
                item.setCode(rs.getString("ma_node"));
                result.add(item);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

    public List<TnodeNodeBO> findTNodeNode(String tnodeId, String neTypeId, String tinhTpId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TnodeNodeBO> result = null;
        try {
            trans = factory.getTransaction();
            trans.connectionType(DB_RIMS_BB);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_tnode.find_tnode_node(?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, tnodeId);
            cs.setString(3, neTypeId);
            cs.setString(4, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                TnodeNodeBO item = new TnodeNodeBO();
                item.setId(rs.getString("id"));
                item.setTnode_id(rs.getString("tnode_id"));
                item.setNode_id(rs.getString("node_id"));
                item.setTnode_code(rs.getString("tnode_code"));
                item.setMa_node(rs.getString("ma_node"));
                item.setPortIn(rs.getString("port_in"));
                item.setPortOut(rs.getString("port_out"));
                item.setTenTruyenDan(rs.getString("ten_loai_truyen_dan"));
                result.add(item);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

    public Integer addTNodeNode(String tnodeId, String nodes, String portIn, String portOut, String truyenDanId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            trans = factory.getTransaction();
            trans.connectionType(DB_RIMS_BB);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_tnode.add_tnode_node(?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, tnodeId);
            cs.setString(3, nodes);
            cs.setString(4, portIn);
            cs.setString(5, portOut);
            cs.setString(6, truyenDanId);
            cs.executeQuery();
            return cs.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return 0;
    }
    
    public Integer deleteTNodeNode(String id) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            trans = factory.getTransaction();
            trans.connectionType(DB_RIMS_BB);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_tnode.delete_tnode_node(?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, id);
            cs.executeQuery();
            return cs.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return 0;
    }
    
     public List<TNodeBO> findTnode( String neTypeId, String tinhTpId) {
        ITransaction trans = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;
        List<TNodeBO> result = null;
        try {
            trans = factory.getTransaction();
            trans.connectionType(DB_RIMS_BB);
            conn = trans.getConnection();
            String sql = "begin ?:=pkg_tnode.find_tnode(?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2, neTypeId);
            cs.setString(3, tinhTpId);
            cs.executeQuery();
            rs = (ResultSet) cs.getObject(1);
            result = new ArrayList<>();
            while (rs.next()) {
                TNodeBO item = new TNodeBO();
                item.setId(rs.getString("tnode_id"));
                item.setCode(rs.getString("tnode_code"));
                item.setName(rs.getString("tnode_name"));
                result.add(item);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

}
