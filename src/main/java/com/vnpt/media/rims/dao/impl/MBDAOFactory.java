package com.vnpt.media.rims.dao.impl;

import com.vnpt.media.rims.dao.DAOFactory;
import com.vnpt.media.rims.dao.IContact;
//import com.vnpt.media.rims.dao.ICps;
import com.vnpt.media.rims.dao.IDonVi;
import com.vnpt.media.rims.dao.IGoogleMap;
import com.vnpt.media.rims.dao.IFamilyType;
import com.vnpt.media.rims.dao.IGroups;
import com.vnpt.media.rims.dao.ILoaiTram;
import com.vnpt.media.rims.dao.IMenuAction;
import com.vnpt.media.rims.dao.INetworkType;
import com.vnpt.media.rims.dao.IPhancap;
import com.vnpt.media.rims.dao.IThietBi;
import com.vnpt.media.rims.dao.IDieuHoa;
import com.vnpt.media.rims.dao.ITinh;
import com.vnpt.media.rims.dao.ILoaiAnTen;
import com.vnpt.media.rims.dao.IUsers;
import com.vnpt.media.rims.dao.*;
import com.vnpt.media.rims.transaction.ITransaction;
import com.vnpt.media.rims.transaction.impl.JDBCTransaction;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 5/13/13 Time: 10:33 AM To
 * change this template use File | Settings | File Templates.
 */
public class MBDAOFactory extends DAOFactory {

    public MBDAOFactory() {
    }

    @Override
    public IUsers getUsersDAO() {
        // TODO Auto-generated method stub
        return new UsersDAO();
    }

    @Override
    public ITransaction getTransaction() {
        // TODO Auto-generated method stub
        return new JDBCTransaction();
    }

    @Override
    public IMenuAction getMenuActionDAO() { 
        return new MenuActionDAO();
    }

//    @Override
//    public ICps getCpsDAO() {
//        return new CpsDAO();
//    }
    @Override
    public IDonVi getDonViDAO() {
        return new DonViDAO();
    }

    @Override
    public ITinh getTinhDAO() {
        return new TinhDAO();
    }

    @Override
    public IThietBi getThietBiDAO() {
        return new ThietBiDAO();
    }
    @Override
    public IDieuHoa getDieuHoaDAO() {
        return new DieuHoaDAO();
    }
    @Override
    public ILoaiAnTen getLoaiAnTenDAO() {
        return new LoaiAnTenDAO();
    }

    @Override
    public ILoaiMayNo getLoaiMayNoDAO() {
        return new LoaiMayNoDAO();
    }

    @Override
    public INode getNodeDAO() {
        return new NodeDAO();
    }

    @Override
    public IHlr getHlrDAO() {
        return new HlrDAO();
    }

    @Override
    public IHss getHssDAO() {
        return new HssDAO();
    }

    @Override
    public IIms getImsDAO() {
        return new ImsDAO();
    }

    @Override
    public IMgw getMgwDAO() {
        return new MgwDAO();
    }

    @Override
    public IMsc getMscDAO() {
        return new MscDAO();
    }

    @Override
    public IMss getMssDAO() {
        return new MssDAO();
    }

    @Override
    public INgn getNgnDAO() {
        return new NgnDAO();
    }

    @Override
    public IStp getStpDAO() {
        return new StpDAO();
    }

    @Override
    public ITss getTssDAO() {
        return new TssDAO();
    }

    public ILoaiTuNguon getLoaiTuNguonDAO() {
        return new LoaiTuNguonDAO();
    }

    @Override
    public ILoaiAcQuy getLoaiAcQuyDAO() {
        return new LoaiAcQuyDAO();
    }

    public ILoaiTruyenDan getLoaiTruyenDanDAO() {
        return new LoaiTruyenDanDAO();
    }

    public IBuilding getBuildingDAO() {
        return new BuildingDAO();
    }

    @Override
    public ILoaiTram getLoaiTramDAO() {
        return new LoaiTramDAO();
    }

    @Override
    public IGroups getGroupsDAO() {
        return new GroupsDAO();
    }

    @Override
    public IContact getContactDAO() {
        return new ContactDAO();
    }

//    trunglk_start
    @Override
    public IGoogleMap getGoogleMapDAO() {
        return new GoogleMapDAO();
    }

    @Override
    public IDanhMuc getDanhMucDAO() {
        return new DanhMucDAO();
    }

    @Override
    public IDataAudit getDataAuditDAO() {
        return new AuditDAO();
    }

    @Override
    public IDuAnTinh getDuAnTinhDAO() {
        return new DuAnTinhDAO();
    }

    @Override
    public IBscRnc getBscRncDAO() {
        return new BscRncDAO();
    }
//    trunglk_end

    @Override
    public IPhancap getPhancapDAO() {
        return new PhancapDAO();
    }

    @Override
    public INetworkType getNetworkTypeDAO() {
        return new NetworkTypeDAO();
    }

    @Override
    public IFamilyType getFamilyTypeDAO() {
        return new FamilyTypeDAO();
    }

    @Override
    public IReport getReportDAO() {
        return new ReportDao();
    }

    @Override
    public IEquipment getEquipmentDAO() {
        return new EquipmentDAO();
    }

    @Override
    public IClassGroup getClassGroupDAO() {
        return new ClassGroupDAO();
    }

    @Override
    public IProject getProjectDAO() {
        return new ProjectDAO();
    }

    @Override
    public ITrangThaiHD getTrangThaiHDDAO() {
        return new TrangThaiHDDAO();
    }

    @Override
    public ITrangThaiQL getTrangThaiQLDAO() {
        return new TrangThaiQLDAO();
    }

    @Override
    public IPlanStation getStationPlansDAO() {
        return new StationPlansDAO();
    }

    @Override
    public IProjectStation getProjectStationDAO() {
        return new ProjectStationDAO();
    }

    @Override
    public IPhuTro getPhuTroDAO() {
        return new PhuTroDAO();
    }

    @Override
    public IDAudit getDAuditDAO() {
        return new DAuditDAO();
    }

    @Override
    public IHistory getHistoryDAO() {
        return new HistoryDao();
    }

    @Override
    public ICells getCellsDAO() {
        return new CellsDAO();
    }

    // PS Core
    @Override
    public ISgsn getSgsnDAO() {
        return new SgsnDAO();
    }

    @Override
    public IGgsn getGgsnDAO() {
        return new GgsnDAO();
    }

    @Override
    public IMme getMmeDAO() {
        return new MmeDAO();
    }

    @Override
    public IPgw getPgwDAO() {
        return new PgwDAO();
    }
//    trunglk_start

    @Override
    public ISmsc getSmscDAO() {
        return new SmscDAO();
    }

    @Override
    public ISmpp getSmppDAO() {
        return new SmppDAO();
    }

    @Override
    public IDsr getDsrDAO() {
        return new DsrDAO();
    }

    @Override
    public IMca getMcaDAO() {
        return new McaDAO();
    }

    @Override
    public IUssd getUssdDAO() {
        return new UssdDAO();
    }

    @Override
    public IDns getDnsDAO() {
        return new DnsDAO();
    }

    @Override
    public ICrbt getCrbtDAO() {
        return new CrbtDAO();
    }

    @Override
    public IRc getRcDAO() {
        return new RcDAO();
    }

    @Override
    public ISdp getSdpDAO() {
        return new SdpDAO();
    }

    @Override
    public IFda getFdaDAO() {
        return new FdaDAO();
    }

    @Override
    public ISapc getSapcDAO() {
        return new SapcDAO();
    }

    @Override
    public ILogicalLink getLogicalLinkDAO() {
        return new LogicalLinkDAO();
    }

    @Override
    public INetworkSpace getNetworkSpaceDAO() {
        return new NetworkSpaceDAO();
    }

    @Override
    public ITConnectionGroup getTConnectionGroupDAO() {
        return new TConnectionGroupDAO();
    }

    @Override
    public ITConnection getTConnectionDAO() {
        return new TConnectionDAO();
    }

    @Override
    public ITPath getTPathDAO() {
        return new TPathDAO();
    }
//    trunglk_end

    @Override
    public ITnode getTnodeDAO() {
        return new TnodeDAO();
    }

    @Override
    public ITlink getTlinkDAO() {
        return new TlinkDAO();
    }

    @Override
    public ITEq getTEqDAO() {
        return new TEqDAO();
    }

    @Override
    public IDsLam getDsLamDAO() {
        return new DsLamDAO();
    }

    @Override
    public IAccess getAccessDAO() {
        return new AccessDAO();
    }

    @Override
    public ITVendor getTVendorDAO() {
        return new TVendorDAO();
    }

    @Override
    public IDongThietBi getDongThietBiDAO() {
        return new DongThietBiDAO();
    }

    @Override
    public ITNodeStyle getTNodeStyleDAO() {
        return new TNodeStyleDAO();
    }

    @Override
    public ITNodeType getTNodeTypeDAO() {
        return new TNodeTypeDAO();
    }

    @Override
    public ICpe getCpeDAO() {
        return new CpeDAO();
    }
    
    @Override
    public ITPort getTPortDAO() {
        return new TPortDAO();
    }
    
    @Override
    public IViewCard getViewCardDAO() {
        return new ViewCardDAO();
    }
    
    @Override
    public IJasper getJasperDAO() {
        return new JasperDAO();
    }
    
    @Override
    public IServerDirMsc getServerDirDAO() {
        return new ServerDirDAO();
    } 
    
    @Override
    public IFirewallDir getFirewallDirDAO() {
        return new FirewallDirDAO();
    } 
}
