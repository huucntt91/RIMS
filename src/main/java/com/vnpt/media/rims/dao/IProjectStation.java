/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ProjectStationBO;
import com.vnpt.media.rims.bean.TechnologyTypeBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cyano
 */
public interface IProjectStation extends IGeneric {

    public List<TechnologyTypeBO> searchTechType(String techType) throws DAOException;

    public List<ProjectStationBO> searchProjectStation(Integer tramQhId, Integer qhTinhId, String status, String tinhIds, String maTramQh, String tenTramQh, String tinhTpId, String loaiCongNgheId) throws DAOException;

    public boolean insertProjectStation(ProjectStationBO tramQh, Long userId) throws DAOException;

    public boolean updateThongTinChung(ProjectStationBO tramQh, Long userId) throws DAOException;

    public boolean updateCamKet(ProjectStationBO tramQh, Long userId) throws DAOException;

//    trunglk_start
    public boolean updateTuNguon(ProjectStationBO tramQh, Long userId) throws DAOException;
//    trunglk_end

    public boolean updateCSHT(ProjectStationBO tramQh, Long userId) throws DAOException;

    public boolean updateAntena(ProjectStationBO tramQh, Long userId) throws DAOException;

    public boolean updateStatus(ProjectStationBO tramQh, Long userId) throws DAOException;
    public boolean deleteTramQh(String tram_qh_id) throws DAOException;

    public ArrayList<ProjectStationBO> fn_search(String prs_start_record, String prs_length_page,
            String prs_global_search, String prs_list_column_name, String prs_list_column_search, String prs_column_to_sort,
            String prs_sort_direction, String[] recordsTotal, String[] recordsFiltered, String tinhTpIds, String status) throws DAOException;
    
}
