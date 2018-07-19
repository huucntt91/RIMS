/*
 * code https://github.com/jittagornp/excel-object-mapping
 */
package com.blogspot.na5cent.exom.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * @author redcrow
 */
public class StringTypeConverter implements TypeConverter<String> {

    @Override
    public String convert(Object value, String... pattern) {
        if (value instanceof String) {
            String output = ((String) value).trim();
            if(output.length() == 5 &&( pattern[0].toLowerCase().contains("ngay") || pattern[0].toLowerCase().contains("ngày"))){
                try{
                    Date javaDate= DateUtil.getJavaDate(Double.parseDouble(output));
                    output = new SimpleDateFormat("dd/MM/yyyy").format(javaDate);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                    output = ((String) value).trim();
                }
            }
         
            return output;
        }
        return null;
    }

}
