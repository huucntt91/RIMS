package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.ExportExcelL2Switch;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTb;
import com.vnpt.media.rims.bean.ExportExcelLKNAccessTheoTinh;
import com.vnpt.media.rims.bean.TEq1BO;
import com.vnpt.media.rims.bean.TEq2BO;
import com.vnpt.media.rims.bean.TEq3BO;
import com.vnpt.media.rims.bean.TLinkBO;
import com.vnpt.media.rims.bean.TModuleQuangBO;
import com.vnpt.media.rims.bean.TPortBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IJasper extends IGeneric {

    public List<ExportExcelL2Switch> exportExcelL2Switch(Long tnodeTypeId);

    public List<ExportExcelLKNAccessTheoTinh> exportExcelKetNoiAccessTheoTinh(Long tinhTPId);

    public List<ExportExcelLKNAccessTheoTb> exportExcelKetNoiAccessTheoTb();

    public List<ExportExcelLKNAccessTheoTinh> vn2ExportExcelKetNoiAccessTheoTinh(Long tinhTPId);

    public List<ExportExcelLKNAccessTheoTb> vn2ExportExcelKetNoiAccessTheoTb();
}
