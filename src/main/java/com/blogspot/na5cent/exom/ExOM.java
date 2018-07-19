/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.blogspot.na5cent.exom;

import com.blogspot.na5cent.exom.annotation.Column;
import com.blogspot.na5cent.exom.util.EachFieldCallback;
import com.blogspot.na5cent.exom.util.ReflectionUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author redcrow
 */
public class ExOM {

    private static final Logger logger = LogManager.getLogger(ExOM.class);

    private final File excelFile;
    private Class clazz;

    private ExOM(File excelFile) {
        this.excelFile = excelFile;
    }

    public static ExOM mapFromExcel(File excelFile) {
        return new ExOM(excelFile);
    }

    public ExOM to(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    private String getValueByName(String name, Row row, Map<String, Integer> cells) {
        if (cells.get(name) == null) {
            return null;
        }

        Cell cell = row.getCell(cells.get(name));
        return getCellValue(cell);
    }

    private String getValueByNo(String name, Row row, Map<String, Integer> cells, String no) {
//        if (cells.get(name) == null) {
//            return null;
//        }

//        Cell cell = row.getCell(cells.get(name));
        
        Cell cell = row.getCell(Integer.valueOf(no));
        
        return getCellValue(cell);
    }

    private void mapName2Index(String name, Row row, Map<String, Integer> cells) {
        int index = findIndexCellByName(name, row);
        if (index != -1) {
            
            cells.put(name, index);
        }
    }

//    private void readExcelHeader(final Row row, final Map<String, Integer> cells) throws Throwable {
//        ReflectionUtils.eachFields(clazz, new EachFieldCallback() {
//            @Override
//            public void each(Field field, String name) throws Throwable {
//                mapName2Index(name, row, cells);
//            }
//            @Override
//            public void each(Field field, String name) throws Throwable {
//                cells.put(name, Integer.SIZE)field.getAnnotation(Column.class).no()
//                mapName2Index(name, row, cells);
//            }
//        });
//    }
    private Object readExcelContent(final Row row, final Map<String, Integer> cells) throws Throwable {
        final Object instance = clazz.newInstance();
        ReflectionUtils.eachFields(clazz, new EachFieldCallback() {

            @Override
            public void each(Field field, String name, String no) throws Throwable {
                
//                ReflectionUtils.setValueOnField(instance, field, getValueByName(
//                        name,
//                        row,
//                        cells
//                ));
                if (!no.equals("")) {
                    ReflectionUtils.setValueOnField(instance, field, getValueByNo(
                            name,
                            row,
                            cells,
                            no
                    ));
                }
            }
        });

        return instance;
    }

    private boolean isVersion2003(File file) {
        return file.getName().endsWith(".xls");
    }

    private Workbook createWorkbook(InputStream inputStream) throws IOException {
        if (isVersion2003(excelFile)) {
            return new HSSFWorkbook(inputStream);
        } else { //2007+
            return new XSSFWorkbook(inputStream);
        }
    }

    public <T> List<T> map(int startRow) throws Throwable {
        //
        InputStream inputStream = null;
        List<T> items = new LinkedList<>();

        try {
            Iterator<Row> rowIterator;
            
//            File fileName = new File("C:\\Users\\HP ProDesk\\Desktop\\excel-object-mapping-master\\excel-object-mapping-master\\target\\classes\\excel.xlsx");
//            inputStream = new FileInputStream(fileName);
            inputStream = new FileInputStream(excelFile);
            Workbook workbook = createWorkbook(inputStream);
//              ByteArrayInputStream bis = new ByteArrayInputStream(excelFile.getBytes());
//        Workbook workbook = createWorkbook(bis);
//        Sheet sheet = workbook.getSheetAt(0);

            int numberOfSheets = workbook.getNumberOfSheets();
            
            for (int index = 0; index < numberOfSheets; index++) {
                Sheet sheet = workbook.getSheetAt(index);
                rowIterator = sheet.iterator();

                Map<String, Integer> nameIndexMap = new HashMap<>();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
//                    if (row.getRowNum() == 0) {
//                        readExcelHeader(row, nameIndexMap);
//                    } else {
//                        items.add((T) readExcelContent(row, nameIndexMap));
//                    }
                    if (row.getRowNum() >= startRow) {
                        items.add((T) readExcelContent(row, nameIndexMap));
                    }
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return items;
    }
    
    /* map excel theo sheet index */
    public <T> List<T> mapSheet(int sheetIndex, int startRow) throws Throwable {
        InputStream inputStream = null;
        List<T> items = new LinkedList<>();

        try {
            Iterator<Row> rowIterator;
//            File fileName = new File("C:\\Users\\HP ProDesk\\Desktop\\excel-object-mapping-master\\excel-object-mapping-master\\target\\classes\\excel.xlsx");
//            inputStream = new FileInputStream(fileName);
            inputStream = new FileInputStream(excelFile);
            Workbook workbook = createWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            rowIterator = sheet.iterator();

            Map<String, Integer> nameIndexMap = new HashMap<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
//                    if (row.getRowNum() == 0) {
//                        readExcelHeader(row, nameIndexMap);
//                    } else {
//                        items.add((T) readExcelContent(row, nameIndexMap));
//                    }
                if (row.getRowNum() >= startRow) {
                    items.add((T) readExcelContent(row, nameIndexMap));
                }
            }

        
        }
        catch(Exception e){
            logger.error(e.getMessage(), e);
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return items;
    }

    private int findIndexCellByName(String name, Row row) {
        Iterator<Cell> iterator = row.cellIterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            if (getCellValue(cell).trim().equalsIgnoreCase(name)) {
                return cell.getColumnIndex();
            }
        }

        return -1;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        String value = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                value += String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value += new BigDecimal(cell.getNumericCellValue()).toString();
                break;
            case Cell.CELL_TYPE_STRING:
                value += cell.getStringCellValue();
                break;
        }

        return value;
    }
}
