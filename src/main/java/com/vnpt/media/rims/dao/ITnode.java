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
import com.vnpt.media.rims.bean.TNodeBO;
import com.vnpt.media.rims.bean.TramDuAnBO;
import com.vnpt.media.rims.bean.eNodeBInfoBO;
import com.vnpt.media.rims.exception.DAOException;
import com.vnpt.media.rims.exception.ServiceException;
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
public interface ITnode extends IGeneric {


    public List<TNodeBO> findAll(String tnodeid, String code,String name) throws DAOException;

}
