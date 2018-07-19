package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.AttributeBO;
import com.vnpt.media.rims.bean.BTSInfoBO;
import com.vnpt.media.rims.bean.BuildingBO;
import com.vnpt.media.rims.bean.CellInfoBO;
import com.vnpt.media.rims.bean.ClassAttributeBO;
import com.vnpt.media.rims.bean.ExcelBtsUpdateBO;
import com.vnpt.media.rims.bean.ExcelCellUpdateBO;
import com.vnpt.media.rims.bean.ExcelDeleteNodeBO;
import com.vnpt.media.rims.bean.ImportBtsModel;
import com.vnpt.media.rims.bean.ImportCellModel;
import com.vnpt.media.rims.bean.NodeBO;
import com.vnpt.media.rims.bean.OmcCellInfoBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.bean.eNodeBInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import com.vnpt.media.rims.formbean.ApproveAllForm;
import com.vnpt.media.rims.formbean.ApproveForm;
import java.util.List;
import com.vnpt.media.rims.formbean.CellNewForm;
import com.vnpt.media.rims.formbean.NeLinkForm;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface INode extends IGeneric {

    public List<NodeBO> findAll(String startRow, String endRow, String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException;

    public List<?> findAllDetail(String nodeId, String startRow, String endRow, String code,
            String khuvucId, String tinhTpId, String quanHuyenId, String phuongXaId,
            String neTypeId, String thietBiId, String status) throws DAOException;

    public int modify(String action, BuildingBO item) throws DAOException;

    public int getTotalAll(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws DAOException;

    public int getTotalDetail(String name, String khuvucId, String tinhId, String quanId, String phuongId, String neTypeId, String venderId, String statusList) throws ServiceException;

    public List<NeLinkForm> findNodeLink(String id) throws DAOException;
    
    public List<NeLinkForm> findNode1Link(String id) throws DAOException;
    
    public List<NeLinkForm> findNode2Link(String id) throws DAOException; 

    public List<?> findDetail(String id, String neTypeId) throws DAOException;

    public void updateNode(NodeBO nodeBO) throws DAOException;

    public Long addNode(NodeBO nodeBO) throws DAOException;

    public void updateTramInfo(BTSInfoBO item) throws DAOException;

    public void updateCellInfo(CellInfoBO cellInfoBO) throws DAOException;

    public void updateOmcCellInfo(OmcCellInfoBO omcCellInfoBO) throws DAOException;

    public void addCellInfo(CellInfoBO cellInfoBO) throws DAOException;

    public void addOmcCellInfo(OmcCellInfoBO omcCellInfoBO) throws DAOException;

    public int addTram(BTSInfoBO item) throws DAOException;

    public List<ClassAttributeBO> getAttributeFormName(String id) throws ServiceException;

    public List<AttributeBO> getAttributeByClass(String id) throws ServiceException;

    public List<TramDuAnBO> findAllTramDuAn(String startRow, String endRow, String name, String tinhId) throws DAOException;

    public int getTotalAllTramDuAn(String name, String tinhId) throws DAOException;

    public List<CellNewForm> findCell(Long nodeId, String statusList, String tinhTpId) throws ServiceException;

    public OmcCellInfoBO findOmcCellInfo(String id) throws ServiceException;

    public String getCodeVNPT(String tinhCode, String ne, String status) throws DAOException;

//    trunglk_start
    int getTotal(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach) throws DAOException;

    List<TramDuAnBO> findTramDAAll(String maDuAn,
            String tenDuAn,
            String msHopDong,
            String maTramBTS,
            String maTramNodeB,
            String maTramQuyHoach,
            int startRow,
            int endRow) throws DAOException;

    public void addTramDA(TramDuAnBO tramDABO) throws DAOException;
//    trunglk_end

    public List<NodeBO> findByNodeChaId(Long nodeChaId, String statusList) throws DAOException;

    public List<NodeBO> findIdByMaNode(String maNode) throws DAOException;

    public int findSpecialCell(Long buildingId) throws DAOException;

    public void approveCell(ApproveForm approveForm, String maNode, Long userUpdate) throws ServiceException;

    public List<BTSInfoBO> findDuyetTram(Long nodeId, String statusList, String tinhTpId) throws ServiceException;

    public int addNeLink(NeLinkForm bean) throws DAOException;

    public int removeNeLink(String id) throws DAOException;

    public String importCell(String permission, String maNode, long userInsertId, ImportCellModel importcellModel) throws DAOException;

    public String importDkBts(String codeVNPT, long userInsertId, ImportBtsModel importBtsModel,
            RedirectAttributes attr, MessageSource messageSource, Locale locale) throws DAOException;

    public String excelCellUpdate(String permission, long userInsertId, ExcelCellUpdateBO excelCellUpdateBO) throws DAOException;

    public String excelBtsUpdate(String permission, long userInsertId, ExcelBtsUpdateBO excelBtsUpdateBO) throws DAOException;

    public String excelDeleteNode(String permission, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO) throws DAOException;

    public String excelDestroyNode(String permission, long userInsertId, ExcelDeleteNodeBO excelDeleteNodeBO) throws DAOException;
    
    public int updateOmcBtsInfo(BTSInfoBO bo, Long userId) throws DAOException;

    public int updateOmcNodeBInfo(Long nodeId, String tenTrenHeThong,
            String tenBSCRNC, String tenBSCRNCQly, String dcHsdpa42M, String fileterUser,
            String bangTanId, Long userId) throws DAOException;

    public int updateOmcEnodeBInfo(Long nodeId, String tenTrenHeThong,
            String tenBSCRNC, String tenBSCRNCQly,
            String MscMss, String sgsn, String dcHsdpa42M, String fileterUser,
            Long bangTanId, Long userId) throws DAOException;

    public int swapNodeB(Long rNodebId, Long vendorId, long userId, String iBscRncName);

    public int swapENodeB(Long reNodebId, Long vendorId, long userId, String iBscRncName);

    public int swapBts( Long btsId, long userId, String iBscRncName,Long vendorId);

    public List<?> findAllNodeDetailMap(String nodeId, String neTypeId) throws DAOException;
    
    public List<NodeBO> findSiteByBuilding(String buildingId, String neTypeId) throws DAOException;
    
    public int approveNodeAll(ApproveAllForm approveForm, Long userUpdate) throws ServiceException;
    
    public int approveAllBuilding(ApproveAllForm approveForm, Long userUpdate) throws ServiceException;
    
    public int fn_check_name_system(Integer prn_node_id, String prn_name_system, Integer prn_ne_type_id) throws ServiceException;
}
