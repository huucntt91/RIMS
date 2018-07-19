package com.vnpt.media.rims.dao;

import com.vnpt.media.rims.bean.CardBO;
import com.vnpt.media.rims.exception.DAOException;
import java.util.List;
import com.vnpt.media.rims.bean.DataViewBO;
import com.vnpt.media.rims.bean.PortBO;
import com.vnpt.media.rims.bean.RackBO;
import com.vnpt.media.rims.bean.SlotBO;
import com.vnpt.media.rims.bean.SubRackBO;
import com.vnpt.media.rims.exception.ServiceException;

/**
 * Created with IntelliJ IDEA. User: haidd Date: 6/26/13 Time: 4:21 PM To change
 * this template use File | Settings | File Templates.
 */
public interface IEquipment extends IGeneric {

    public List<DataViewBO> getDataView(Long nodeId,int startRow, int endRow) throws DAOException;

    public RackBO viewRack(String id) throws ServiceException;

    public SubRackBO viewSubRack(String id) throws ServiceException;

    public SlotBO viewSlot(String id) throws ServiceException;

    public CardBO viewCard(String id) throws ServiceException;

    public PortBO viewPort(String id) throws ServiceException;
}
