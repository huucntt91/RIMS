/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.bean.ProjectBO;
import com.vnpt.media.rims.dao.IProject;
import com.vnpt.media.rims.exception.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyano
 */
public class ProjectDAO extends GenericDAO implements IProject {

    @Override
    public List<ProjectBO> searchProject(String quy_hoach_id) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_project.searchProject(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, quy_hoach_id);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ProjectBO record = new ProjectBO();
                record.setQhId(rs.getInt("quyhoach_id"));
                record.setProjectName(rs.getString("ten_quy_hoach"));
                record.setProjectCode(rs.getString("ma_quy_hoach"));
                record.setParentId(rs.getInt("quyhoach_cha_id"));
                record.setParentName(rs.getString("quy_hoach_cha"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

    @Override
    public Integer addProject(ProjectBO project, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.insertProject(?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, project.getProjectCode());
            cs.setString(3, project.getProjectName());
            cs.setString(4, project.getParentId() == null ? "" : project.getParentId().toString());
            cs.setLong(5, userId);
            cs.executeQuery();
            int projectId = cs.getInt(1);
            return projectId;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return -1;
    }

    @Override
    public boolean deleteProject(Integer quyhoachId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.deleteProject(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setInt(2, quyhoachId.intValue());
            cstmt.executeQuery();
            int projectId = cstmt.getInt(1);
            if (projectId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public Integer updateProject(ProjectBO project, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.updateProject(?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, project.getQhId() == null ? "" : project.getQhId().toString());
            cs.setString(3, project.getProjectCode());
            cs.setString(4, project.getProjectName());
            cs.setString(5, project.getParentId() == null ? "" : project.getParentId().toString());
            cs.setLong(6, userId);
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            return projectInforId;
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return -1;
    }

    @Override
    public boolean addProjectInfor(ProjectBO project, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.insertProjectTinh(?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, project.getQhId() == null ? "" : project.getQhId().toString());
            cs.setString(3, project.getTinhTpId() == null ? "" : project.getTinhTpId().toString());
            cs.setString(4, project.getSoLuongChi2G() == null ? "" : project.getSoLuongChi2G().toString());
            cs.setString(5, project.getSoLuong2G3G() == null ? "" : project.getSoLuong2G3G().toString());
            cs.setString(6, project.getTongThietBi2G3G() == null ? "" : project.getTongThietBi2G3G().toString());
            cs.setString(7, project.getChstActive() == null ? "" : project.getChstActive().toString());
            cs.setString(8, project.getCshtNew() == null ? "" : project.getCshtNew().toString());
            cs.setString(9, project.getTongThieBi() == null ? "" : project.getTongThieBi().toString());
            cs.setString(10, project.getTongCshtXaymoi() == null ? "" : project.getTongCshtXaymoi().toString());
            cs.setString(11, project.getTongThietBi3G() == null ? "" : project.getTongThietBi3G().toString());
            cs.setString(12, project.getLte() == null ? "" : project.getLte().toString());
            cs.setLong(13, userId);
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteProjectInfor(Integer quyhoachInforId) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.deleteProjectTinh(?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cstmt.setInt(2, quyhoachInforId);
            cstmt.executeQuery();
            int projectId = cstmt.getInt(1);
            if (projectId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateProjectInfor(ProjectBO project, Long userId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String sql = "begin ? := pkg_project.updateProjectTinh(?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
            cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.INTEGER);
            cs.setString(2, project.getQhInforId() == null ? "" : project.getQhInforId().toString());
            cs.setString(3, project.getQhId() == null ? "" : project.getQhId().toString());
            cs.setString(4, project.getTinhTpId() == null ? "" : project.getTinhTpId().toString());
            cs.setString(5, project.getSoLuongChi2G() == null ? "" : project.getSoLuongChi2G().toString());
            cs.setString(6, project.getSoLuong2G3G() == null ? "" : project.getSoLuong2G3G().toString());
            cs.setString(7, project.getTongThietBi2G3G() == null ? "" : project.getTongThietBi2G3G().toString());
            cs.setString(8, project.getChstActive() == null ? "" : project.getChstActive().toString());
            cs.setString(9, project.getCshtNew() == null ? "" : project.getCshtNew().toString());
            cs.setString(10, project.getTongThieBi() == null ? "" : project.getTongThieBi().toString());
            cs.setString(11, project.getTongCshtXaymoi() == null ? "" : project.getTongCshtXaymoi().toString());
            cs.setString(12, project.getTongThietBi3G() == null ? "" : project.getTongThietBi3G().toString());
            cs.setString(13, project.getLte() == null ? "" : project.getLte().toString());
            cs.setLong(14, userId);
            cs.executeQuery();
            int projectInforId = cs.getInt(1);
            if (projectInforId > 0) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException ex) {
                }
            }
        }
        return false;
    }

    @Override
    public List<ProjectBO> searchProjectInfor(String quy_hoach_tinh_id, String province) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List ar = new ArrayList<>();
        try {
            conn = this.getConnection();
            String sql = "begin ?:=pkg_project.get_qh_tinh(?,?); end;";
            cstmt = conn.prepareCall(sql);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, quy_hoach_tinh_id == null ? "" : quy_hoach_tinh_id);
            cstmt.setString(3, province);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                ProjectBO record = new ProjectBO();
                record.setQhInforId(rs.getInt("qh_tinh_id"));
                record.setQhId(rs.getInt("quyhoach_id"));
                record.setProjectName(rs.getString("ten_quy_hoach"));
                record.setProjectCode(rs.getString("ma_quy_hoach"));
                record.setProvince(rs.getString("ten_tinh_tp"));
                record.setTinhTpId(rs.getInt("TINHTP_ID"));
                record.setSoLuongChi2G(rs.getInt("so_luong_chi_2g"));
                record.setSoLuong2G3G(rs.getInt("so_luong_2G_3G"));
                record.setTongThietBi2G3G(rs.getInt("tong_thiet_bi_2g_3g"));
                record.setCshtNew(rs.getInt("so_luong_csht_3G2100_moi"));
                record.setChstActive(rs.getInt("so_luong_csht_3G2100_co_san"));
                record.setTongThieBi(rs.getInt("TONG_THIET_BI_3G2100"));
                record.setTongCshtXaymoi(rs.getInt("tong_xay_moi_csht_2g_3g"));
                record.setTongThietBi3G(rs.getInt("TONG_THIET_BI_3G900"));
                record.setMaQhTinh(rs.getString("ma_qh_tinh"));
                record.setLte(rs.getInt("lte"));
                ar.add(record);
            }
            return ar;
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return null;
    }

}
