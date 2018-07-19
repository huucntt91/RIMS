package com.vnpt.media.rims.facade;

import com.vnpt.media.rims.exception.ServiceException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vnpt.media.rims.dao.DAOFactory;
//import com.vnpt.media.rims.dao.ICps;
import oracle.net.aso.i;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author VNP
 */
public class ImportFacade {

    private Logger logger = LogManager.getLogger(ImportFacade.class);
    private DAOFactory factory = null;
    private final String DB_ADMIN = "rims_web";

    public ImportFacade() {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        }
    }

    public ImportFacade(int type) {
        if (factory == null) {
            factory = DAOFactory.getDAOFactory(type);
        }
    }

  

    public List<?> findAll(MultipartFile file) throws ServiceException {
       
//            ReadExcel r=new ReadExcel();
//            return r.findAll(file);   
        return null;
    } 
}
