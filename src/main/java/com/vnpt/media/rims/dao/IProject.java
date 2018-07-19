/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ProjectBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;

/**
 *
 * @author Cyano
 */
public interface IProject extends IGeneric{
    public List<ProjectBO> searchProject( String quy_hoach_id) throws DAOException;
    public Integer addProject(ProjectBO project, Long userId) throws DAOException;
    public boolean deleteProject(Integer  quyhoachId) throws DAOException;
    public Integer updateProject(ProjectBO project, Long userId) throws DAOException;
    public List<ProjectBO> searchProjectInfor( String quy_hoach_tinh_id, String province) throws DAOException;
    public boolean addProjectInfor(ProjectBO project, Long userId) throws DAOException;
    public boolean deleteProjectInfor(Integer quyhoachInforId) throws DAOException;
    public boolean updateProjectInfor(ProjectBO project, Long userId) throws DAOException;
}
