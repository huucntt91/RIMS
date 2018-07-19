package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.transaction.ITransaction;
import com.vnpt.media.rims.dao.impl.MBDAOFactory;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 5/13/13 Time: 10:33 AM To
 * change this template use File | Settings | File Templates.
 */
public abstract class DAOFactory {

    public static final int ORACLE = 1;
    public static final int SQLSERVER = 2;

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case DAOFactory.ORACLE:
                return new MBDAOFactory();
            case DAOFactory.SQLSERVER:
                return new MBDAOFactory();
            default:
                return new MBDAOFactory();
        }
    }

    public abstract IUsers getUsersDAO();

    public abstract IMenuAction getMenuActionDAO();

//    public abstract ICps getCpsDAO();
    public abstract IDonVi getDonViDAO();

    public abstract IGroups getGroupsDAO();

    public abstract ITransaction getTransaction();

    public abstract IContact getContactDAO();

    public abstract ITinh getTinhDAO();

    public abstract IThietBi getThietBiDAO();

    public abstract INode getNodeDAO();

    public abstract IHlr getHlrDAO();

    public abstract IHss getHssDAO();

    public abstract IIms getImsDAO();

    public abstract IMgw getMgwDAO();

    public abstract IMsc getMscDAO();

    public abstract IMss getMssDAO();

    public abstract INgn getNgnDAO();

    public abstract IStp getStpDAO();

    public abstract ITss getTssDAO();

    // PS core
    public abstract ISgsn getSgsnDAO();

    public abstract IGgsn getGgsnDAO();

    public abstract IMme getMmeDAO();

    public abstract IPgw getPgwDAO();
//    trunglk_start

    public abstract ISmsc getSmscDAO();

    public abstract ISmpp getSmppDAO();

    public abstract IDsr getDsrDAO();

    public abstract IMca getMcaDAO();

    public abstract IUssd getUssdDAO();

    public abstract IDns getDnsDAO();

    public abstract ICrbt getCrbtDAO();

    public abstract IRc getRcDAO();

    public abstract ISdp getSdpDAO();

    public abstract IFda getFdaDAO();

    public abstract ISapc getSapcDAO();
//    trunglk_end

    public abstract ILoaiAnTen getLoaiAnTenDAO();

    public abstract IBuilding getBuildingDAO();

    public abstract ILoaiMayNo getLoaiMayNoDAO();

    public abstract ILoaiTuNguon getLoaiTuNguonDAO();

    public abstract ILoaiTruyenDan getLoaiTruyenDanDAO();

    public abstract ILoaiAcQuy getLoaiAcQuyDAO();

    public abstract ILoaiTram getLoaiTramDAO();

//    trunglk_start
    public abstract IGoogleMap getGoogleMapDAO();
//    trunglk_end

    public abstract IPhancap getPhancapDAO();

    public abstract INetworkType getNetworkTypeDAO();

    public abstract IFamilyType getFamilyTypeDAO();

    public abstract IReport getReportDAO();

    public abstract IEquipment getEquipmentDAO();

    public abstract IClassGroup getClassGroupDAO();

    // huunv project dao start
    public abstract IProject getProjectDAO();

    // huunv project dao end
    public abstract IProjectStation getProjectStationDAO();

    public abstract ITrangThaiHD getTrangThaiHDDAO();

    public abstract ITrangThaiQL getTrangThaiQLDAO();

//    trunglk_start
    public abstract IPlanStation getStationPlansDAO();

    public abstract IDanhMuc getDanhMucDAO();

    public abstract IDataAudit getDataAuditDAO();

    public abstract IDuAnTinh getDuAnTinhDAO();

    public abstract IBscRnc getBscRncDAO();
//    trunglk_end

    public abstract IPhuTro getPhuTroDAO();

    public abstract IDAudit getDAuditDAO();

    public abstract IHistory getHistoryDAO();

    public abstract ICells getCellsDAO();

//    broadBand
    public abstract ILogicalLink getLogicalLinkDAO();

    public abstract INetworkSpace getNetworkSpaceDAO();

    public abstract ITConnectionGroup getTConnectionGroupDAO();

    public abstract ITConnection getTConnectionDAO();

    public abstract ITPath getTPathDAO();

    public abstract ITnode getTnodeDAO();

    public abstract ITlink getTlinkDAO();

    public abstract ITEq getTEqDAO();

    public abstract IDsLam getDsLamDAO();

    public abstract IAccess getAccessDAO();

    public abstract ITVendor getTVendorDAO();

    public abstract IDongThietBi getDongThietBiDAO();

    public abstract ITNodeStyle getTNodeStyleDAO();

    public abstract ITNodeType getTNodeTypeDAO();

    public abstract ICpe getCpeDAO();
    
    public abstract ITPort getTPortDAO();
    
    public abstract IViewCard getViewCardDAO();

    public abstract IJasper getJasperDAO();
    
    public abstract IServerDirMsc getServerDirDAO();
    
    public abstract IFirewallDir getFirewallDirDAO();
}
