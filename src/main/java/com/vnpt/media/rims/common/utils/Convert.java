/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.bean.NodeBO;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author VNP
 */
public class Convert {

    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));

    public static String convertMsisdn(String temp) {

        if (temp.startsWith("0")) {
            return temp = "84" + temp.substring(1).trim();
        }
        if (temp.startsWith("84")) {
            return temp.trim();
        }
        return temp = "84" + temp.trim();
    }

    public static String convertNeTypeToObjectId(String neTypeId) {
        if (neTypeId.equals("2")) {
            return "1";
        } else if (neTypeId.equals("3")) {
            return "2";
        } else if (neTypeId.equals("8")) {
            return "3";
        } else if (neTypeId.equals("5")) {
            return "4";
        } else if (neTypeId.equals("6")) {
            return "5";
        } else if (neTypeId.equals("7")) {
            return "6";
        } else if (neTypeId.equals("8")) {
            return "6";
        } else {
            return "0";
        }

    }

    public static String convertNeTypeNameToObjectId(String neTypeName) {
        if (neTypeName.equalsIgnoreCase("BTS")) {
            return "1";
        } else if (neTypeName.equalsIgnoreCase("NODEB")) {
            return "2";
        } else if (neTypeName.equalsIgnoreCase("ENODEB")) {
            return "3";
        } else if (neTypeName.equalsIgnoreCase("CELL2G")) {
            return "4";
        } else if (neTypeName.equalsIgnoreCase("CELL3G")) {
            return "5";
        } else if (neTypeName.equalsIgnoreCase("CELL4G")) {
            return "6";
        }
        return "0";
    }

    public static String convertNeTypeIdToNeTypeParentId(String neTypeId) {
        if (neTypeId.equals("5")) {
            return "2";
        } else if (neTypeId.equals("6")) {
            return "3";
        } else if (neTypeId.equals("7")) {
            return "8";
        }
        return "0";
    }

    public static String convertNeTypeToTechnology(long neTypeId) {
        if (neTypeId == 2) {
            return "2G";
        } else if (neTypeId == 3) {
            return "3G";
        } else if (neTypeId == 8) {
            return "4G";
        }
        return "";
    }

    public static String convertNeTypeToTechnology(String neTypeName) {
        if (neTypeName.equalsIgnoreCase("BTS")) {
            return "2G";
        } else if (neTypeName.equalsIgnoreCase("NODEB")) {
            return "3G";
        } else if (neTypeName.equalsIgnoreCase("ENODEB")) {
            return "4G";
        }
        return "";
    }

    public static String convertFilter(String column, String filterType, String value) {
        String ret = "";
        if (filterType.equalsIgnoreCase("Contains")) {
            ret = column + " like '%" + value + "%'";
        } else if (filterType.equalsIgnoreCase("Not contains")) {
            ret = column + " not like '%" + value + "%'";
        } else if (filterType.equalsIgnoreCase("startWith")) {
            ret = column + " like '" + value + "%'";
        } else if (filterType.equalsIgnoreCase("endWith")) {
            ret = column + " like '%" + value + "'";
        } else if (filterType.equalsIgnoreCase("Exact")) {
            ret = column + " = '" + value + "'";
        } else if (filterType.equalsIgnoreCase("NULL")) {
            ret = column + " is null";
        } else if (filterType.equalsIgnoreCase("NOT NULL")) {
            ret = column + " is not null";
        } else if (filterType.equalsIgnoreCase("IN")) {
            ret = column + " " + filterType + " " + value;
        } else {

        }
        return ret;
    }

    public static int getCode(List<NodeBO> listTemp, int index) {
        int[] listInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Map<Integer, Integer> tempHashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < listTemp.size(); i++) {
            int temp = Integer.valueOf(listTemp.get(i).getCode().substring(9, 10));
            tempHashMap.put(temp, temp);
        }
        for (int i = 0; i < listInt.length; i++) {
            int j = listInt[i];
            if (!tempHashMap.containsKey(j)) {
                return j + index;
            }
        }
        return 1 + index;
    }

    public static String convertErrorCode(String errorCode) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
        /*StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '5') {
                sb.append("'Kiểu NE' không tồn tại. ");
            }
            if (current == '1') {
                sb.append("Mã BTS/NODEB/ENodeB không tồn tại. ");
            }
            if (current == '2') {
                sb.append("Thiết bị không tồn tại. ");
            }
            if (current == '3') {
                sb.append("Loại trạm không tồn tại. ");
            }
            if (current == '4') {
                sb.append("Frequency band không tồn tại. ");
            }
            if (current == '6') {
                sb.append("Ngày hoạt động không đúng định dạng");
            }

            if (current == '10') {
                sb.append(resourceBundle.getString("tram.systemname.exist"));
            }
        }
         */
        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "Mã BTS/NODEB/ENodeB không tồn tại. ";
                        break;
                    case "2":
                        output += "Thiết bị không tồn tại. ";
                        break;
                    case "3":
                        output += "Loại trạm không tồn tại. ";
                        break;
                    case "4":
                        output += "Frequency band không tồn tại. ";
                        break;
                    case "5":
                        output += "'Kiểu NE' không tồn tại. ";
                        break;
                    case "6":
                        output += "Ngày hoạt động không đúng định dạng";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                    //validate.ngay.hoat.dong
                    case "active.date.validate":
                        output += resourceBundle.getString("active.date.validate");
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorCodeBTS(String errorCode, String type) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale/messages", new Locale("en"));
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < errorCode.length(); i++) {
//            char current = errorCode.charAt(i);
//            if (current == '1') {
//                sb.append("'Kiểu NE' không tồn tại. ");
//            }
//            if (!type.toUpperCase().equals("ENODEB")) {
//                if (current == '2') {
//                    sb.append("Mã BSC/RNC không tồn tại. ");
//                }
//            } else {
//                if (current == '2') {
//                    sb.append("eNodeB Id đã tồn tại hoặc để trổng. ");
//                }
//            }
//            if (current == '3') {
//                sb.append("Mã trạm dự án không tồn tại. ");
//            }
//            if (current == '4') {
//                sb.append("Tên đơn vị quản lý không tồn tại. ");
//            }
//            if (current == '5') {
//                sb.append("Mã CSHT không tồn tại. ");
//            }
//            if (current == '6') {
//                sb.append("Tên thiết bị không tồn tại. ");
//            }
//            if (current == '7') {
//                sb.append("Loại trạm không tồn tại. ");
//            }
//            if (current == '8') {
//                sb.append("Cấu hình không tồn tại.");
//            }
//            if (current == '9') {
//                sb.append("Ngày hoạt động không đúng định dạng dd/MM/yyyy");
//            }
//            if (current == '0') {
//                sb.append("Mã đăng ký đã tồn tại");
//            }
//           
//        }
        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "0":
                        output += "'Mã đăng ký đã tồn tại. ";
                        break;
                    case "1":
                        output += "'Kiểu NE' không tồn tại.  ";
                        break;
                    case "2":
                        if (!type.toUpperCase().equals("ENODEB")) {
                            output += "Mã BSC/RNC không tồn tại. ";
                        } else {
                            output += "eNodeB Id đã tồn tại hoặc để trổng. ";
                        }
                        break;
                    case "3":
                        output += "Mã trạm dự án không tồn tại. ";
                        break;
                    case "4":
                        output += "Tên đơn vị quản lý không tồn tại. ";
                        break;
                    case "5":
                        output += "Mã CSHT không tồn tại. ";
                        break;
                    case "6":
                        output += "Tên thiết bị không tồn tại. ";
                        break;
                    case "7":
                        output += "Loại trạm không tồn tại. ";
                        break;
                    case "8":
                        output += "Cấu hình không tồn tại.";
                        break;
                    case "9":
                        output += "Ngày hoạt động không đúng định dạng dd/MM/yyyy";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                    case "11":
                        output += "Ngày hoạt động không được lớn hơn ngày hiện tại";
                        break;    
                    case "cell.new.import.0001":
                        output += resourceBundle.getString("cell.new.import.0001") + ", ";
                        break;
                    case "cell.new.import.0002":
                        output += resourceBundle.getString("cell.new.import.0002") + ", ";
                        break;
                    case "system.error.process":
                        output += resourceBundle.getString("system.error.process") + ", ";
                        break;
                    case "node.code.validate":
                        output += resourceBundle.getString("node.code.validate") + ", ";
                        break;
                    //validate.ngay.hoat.dong
                    case "active.date.validate":
                        output += resourceBundle.getString("active.date.validate")+ ", ";
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorImportDkCell4G(String errorCode) {
        /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '1') {
                sb.append("'eNodeB Id không tồn tại! ");
            }
            if (current == '2') {
                sb.append("LCI không đúng kiểu số!");
            }
            if (current == '3') {
                sb.append("PCI không đúng kiểu số! ");
            }
            if (current == '4') {
                sb.append("Không tồn tại băng tần! ");
            }
            if (current == '5') {
                sb.append("Không tồn tại thiêt bị! ");
            }
            if (current == '6') {
                sb.append("Không tồn tại loại trạm. ");
            }
            if (current == '7') {
                sb.append("no_of_carrier không đúng kiểu số! ");
            }
            if (current == '8') {
                sb.append("Ngày hoạt động không đúng định dạng dd/MM/yyyy! ");
            }
            if (current == '9') {
                sb.append("TAC không đúng kiểu số! ");
            }
        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
         */
        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "'eNodeB Id không tồn tại!, ";
                        break;
                    case "2":
                        output += "LAC không đúng kiểu số!, ";
                        break;
                    case "3":
                        output += "CI không đúng kiểu số!, ";
                        break;
                    case "4":
                        output += "Không tồn tại băng tần!, ";
                        break;
                    case "5":
                        output += "Không tồn tại thiêt bị!, ";
                        break;
                    case "6":
                        output += "Không tồn tại loại trạm, ";
                        break;
                    case "7":
                        output += "no_of_carrier không đúng kiểu số!, ";
                        break;
                    case "8":
                        output += "Ngày hoạt động không đúng định dạng dd/MM/yyyy!, ";
                        break;
                    case "81":
                        output += "Ngày hoạt động không được lớn hơn ngày hiện tại ";
                        break;    
                    case "9":
                        output += "TAC không đúng kiểu số!, ";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist") + ", ";
                        break;
                    case "cell.new.import.0001":
                        output += resourceBundle.getString("cell.new.import.0001") + ", ";
                        break;
                    case "cell.new.import.0002":
                        output += resourceBundle.getString("cell.new.import.0002") + ", ";
                        break;
                    case "system.error.process":
                        output += resourceBundle.getString("system.error.process") + ", ";
                        break;
                    //validate.ngay.hoat.dong
                    case "active.date.validate":
                        output += resourceBundle.getString("active.date.validate")+ ", ";
                        break;
                }
            }
        }

        return output;

    }

    public static String convertErrorImportDkCell2G(String errorCode) {
        /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '1') {
                sb.append("'mã BTS không tồn tại! ");
            }
            if (current == '2') {
                sb.append("LAC không đúng kiểu số!");
            }
            if (current == '3') {
                sb.append("CI không đúng kiểu số! ");
            }
            if (current == '4') {
                sb.append("Không tồn tại băng tần! ");
            }
            if (current == '5') {
                sb.append("Không tồn tại thiêt bị! ");
            }
            if (current == '6') {
                sb.append("Không tồn tại loại trạm. ");
            }
            if (current == '7') {
                sb.append("no_of_carrier không đúng kiểu số! ");
            }
            if (current == '8') {
                sb.append("Ngày hoạt động không đúng định dạng dd/MM/yyyy! ");
            }
            if (current == '9') {
                sb.append("Cặp LAC và CI đã tồn tại! ");
            }

        }
        return sb.toString().length() == 0 ? "OK" : sb.toString();
         */
        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "'mã BTS không tồn tại! ";
                        break;
                    case "2":
                        output += "LAC không đúng kiểu số!";
                        break;
                    case "3":
                        output += "CI không đúng kiểu số! ";
                        break;
                    case "4":
                        output += "Không tồn tại băng tần! ";
                        break;
                    case "5":
                        output += "Không tồn tại thiêt bị! ";
                        break;
                    case "6":
                        output += "Không tồn tại loại trạm. ";
                        break;
                    case "7":
                        output += "no_of_carrier không đúng kiểu số! ";
                        break;
                    case "8":
                        output += "Ngày hoạt động không đúng định dạng dd/MM/yyyy! ";
                        break;
                    case "81":
                        output += "Ngày hoạt động không được lớn hơn ngày hiện tại ";
                        break;    
                    case "9":
                        output += "Cặp LAC và CI đã tồn tại! ";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                    case "cell.new.import.0001":
                        output += resourceBundle.getString("cell.new.import.0001") + ", ";
                        break;
                    case "cell.new.import.0002":
                        output += resourceBundle.getString("cell.new.import.0002") + ", ";
                        break;
                    case "system.error.process":
                        output += resourceBundle.getString("system.error.process") + ", ";
                        break;
                    //validate.ngay.hoat.dong
                    case "active.date.validate":
                        output += resourceBundle.getString("active.date.validate")+ ", ";
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorImportDkCell3G(String errorCode) {
        /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '1') {
                sb.append("'mã NODEB không tồn tại! ");
            }
            if (current == '2') {
                sb.append("LAC không đúng kiểu số!");
            }
            if (current == '3') {
                sb.append("CI không đúng kiểu số! ");
            }
            if (current == '4') {
                sb.append("Không tồn tại băng tần! ");
            }
            if (current == '5') {
                sb.append("Không tồn tại thiêt bị! ");
            }
            if (current == '6') {
                sb.append("Không tồn tại loại trạm. ");
            }
            if (current == '7') {
                sb.append("no_of_carrier không đúng kiểu số! ");
            }
            if (current == '8') {
                sb.append("Ngày hoạt động không đúng định dạng dd/MM/yyyy! ");
            }
            if (current == '9') {
                sb.append("Cặp LAC và CI đã tồn tại! ");
            }

        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
         */
        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "'mã BTS/NodeB không tồn tại! ";
                        break;
                    case "2":
                        output += "LAC không đúng kiểu số!";
                        break;
                    case "3":
                        output += "CI không đúng kiểu số! ";
                        break;
                    case "4":
                        output += "Không tồn tại băng tần! ";
                        break;
                    case "5":
                        output += "Không tồn tại thiêt bị! ";
                        break;
                    case "6":
                        output += "Không tồn tại loại trạm. ";
                        break;
                    case "7":
                        output += "no_of_carrier không đúng kiểu số! ";
                        break;
                    case "8":
                        output += "Ngày hoạt động không đúng định dạng dd/MM/yyyy! ";
                        break;
                    case "81":
                        output += "Ngày hoạt động không được lớn hơn ngày hiện tại ";
                        break;    
                    case "9":
                        output += "Cặp LAC và CI đã tồn tại! ";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                    case "cell.new.import.0001":
                        output += resourceBundle.getString("cell.new.import.0001") + ", ";
                        break;
                    case "cell.new.import.0002":
                        output += resourceBundle.getString("cell.new.import.0002") + ", ";
                        break;
                    case "system.error.process":
                        output += resourceBundle.getString("system.error.process") + ", ";
                        break;
                    //validate.ngay.hoat.dong
                    case "active.date.validate":
                        output += resourceBundle.getString("active.date.validate")+ ", ";
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorCodeUpdateCell(String errorCode) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < errorCode.length(); i++) {
//            char current = errorCode.charAt(i);
//            if (current == '1') {
//                sb.append("'Mã cell không tồn tại. ");
//            }
//            if (current == '2') {
//                sb.append("Kiểu NE không tồn tại. ");
//            }
//            if (current == '3') {
//                sb.append("Băng tần không tồn tại. ");
//            }
//            if (current == '4') {
//                sb.append("Thiết bị không tồn tại. ");
//            }
//            if (current == '5') {
//                sb.append("Loại trạm không tồn tại. ");
//            }
//            if (current == '6') {
//                sb.append("Tên thiết bị không tồn tại. ");
//            }
//            if (current == '7') {
//                sb.append("Ngày hoạt động không đúng định dạng. ");
//            }
//            if (current == '8') {
//                sb.append("Ngày phê duyệt không tồn tại.");
//            }
//        }
//
//        return sb.toString().length() == 0 ? "OK" : sb.toString();

        String output = "";
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "'Mã cell không tồn tại. ";
                        break;
                    case "2":
                        output += "Kiểu NE không tồn tại. ";
                        break;
                    case "3":
                        output += "Băng tần không tồn tại. ";
                        break;
                    case "4":
                        output += "Thiết bị không tồn tại. ";
                        break;
                    case "5":
                        output += "Loại trạm không tồn tại. ";
                        break;
                    case "6":
                        output += "Tên thiết bị không tồn tại.  ";
                        break;
                    case "7":
                        output += "Ngày hoạt động không đúng định dạng. ";
                        break;
                    case "8":
                        output += "Ngày phê duyệt không tồn tại.";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorCodeUpdateBTS(String errorCode) {
        /*
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '1') {
                sb.append("'Kiểu NE' không tồn tại. ");
            }
            if (current == '2') {
                sb.append("Mã BTS/NodeB/eNodeB không tồn tại. ");
            }
            if (current == '3') {
                sb.append("Mã BSC/RNC không tồn tại. ");
            }
            if (current == '4') {
                sb.append("Tên đơn vị quản lý không tồn tại. ");
            }
            if (current == '5') {
                sb.append("Mã CSHT không tồn tại. ");
            }
            if (current == '6') {
                sb.append("Băng tần không tồn tại. ");
            }
            if (current == '7') {
                sb.append("Thiết bị không tồn tại. ");
            }
            if (current == '8') {
                sb.append("Loại trạm không tồn tại. ");
            }
            if (current == '9') {
                sb.append("Cấu hình không tồn tại.");
            }

        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
         */

        String output = "";
        if (errorCode == null || errorCode.isEmpty() || errorCode.equalsIgnoreCase("null")) {
            return "OK";
        } else {
            errorCode = errorCode.substring(0, errorCode.length() - 1);
            for (String item : errorCode.split(",")) {
                switch (item.trim()) {
                    case "1":
                        output += "'Kiểu NE' không tồn tại. ";
                        break;
                    case "2":
                        output += "Mã BTS/NodeB/eNodeB không tồn tại. ";
                        break;
                    case "3":
                        output += "Mã BSC/RNC không tồn tại. ";
                        break;
                    case "4":
                        output += "Tên đơn vị quản lý không tồn tại. ";
                        break;
                    case "5":
                        output += "Mã CSHT không tồn tại. ";
                        break;
                    case "6":
                        output += "Băng tần không tồn tại. ";
                        break;
                    case "7":
                        output += "Thiết bị không tồn tại. ";
                        break;
                    case "8":
                        output += "Loại trạm không tồn tại. ";
                        break;
                    case "9":
                        output += "Cấu hình không tồn tại.";
                        break;
                    case "10":
                        output += resourceBundle.getString("tram.systemname.exist");
                        break;
                }
            }
        }

        return output;
    }

    public static String convertErrorCodeDeleteNode(String errorCode) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorCode.length(); i++) {
            char current = errorCode.charAt(i);
            if (current == '1') {
                sb.append("Mã node không tồn tại. ");
            }
        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
    }

    public static String errorUpdateCell2G(String errorCode) {
        StringBuilder sb = new StringBuilder();
        if (errorCode == null || errorCode.isEmpty()) {
            return "OK";
        }
        for (int i = 0; i < errorCode.split(",").length; i++) {
            String temp = errorCode.split(",")[i];
            if (temp.equals("1") || temp.equals("2")) {
                sb.append("Mã cell không tồn tại. ");
            } else if (temp.equals("3")) {
                sb.append("Mã BTS/NODEB/ENODEB không tồn tại hoặc trống. ");
            } else if (temp.equals("4")) {
                sb.append("Ngày hoạt động không đúng định dạng. ");
            } else if (temp.equals("5")) {
                sb.append("Giá trị LAC không đúng. ");
            } else if (temp.equals("6")) {
                sb.append("Giá trị CI không đúng. ");
            } else if (temp.equals("7")) {
                sb.append("Băng tần không tồn tại. ");
            } else if (temp.equals("8")) {
                sb.append("Thiết bị không tồn tại. ");
            } else if (temp.equals("9")) {
                sb.append("Trạm không tồn tại. ");
            } else if (temp.equals("10")) {
                sb.append("Ngày phê duyệt sai định dạng. ");
            } else if (temp.equals("11")) {
                sb.append("Giá trị azimuth không đúng. ");
            } else if (temp.equals("12")) {
                sb.append("Giá trị mechanicalTilt không đúng. ");
            } else if (temp.equals("13")) {
                sb.append("Giá trị total_tilt không đúng. ");
            } else if (temp.equals("14")) {
                sb.append("Giá trị antenna_high không đúng. ");
            } else if (temp.equals("15")) {
                sb.append("Giá trị antenna_type không đúng. ");
            } else if (temp.equals("16")) {
                sb.append("Giá trị cell_type không đúng. ");
            } else if (temp.equals("17")) {
                sb.append("ngày đăng ký sai định dạng. ");
            } else if (temp.equals("-18")) {
                sb.append("Ngày cấp phép sai định dạng. ");
            } else if (temp.equals("19")) {
                sb.append("Giá trị ELECTRICAL_TILT không đúng. ");
            } else if (temp.equals("20")) {
                sb.append("Giá trị BCCH không đúng. ");
            } else if (temp.equals("21")) {
                sb.append(resourceBundle.getString("tram.systemname.exist"));
            }
        }
        return sb.toString()
                .length() == 0 ? "OK" : sb.toString();
    }

    public static String errorDKCell2G(String errorCode) {
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            if (errorCode.equals("-99")) {
                return "Lỗi";
            }
            if (errorCode.equals("0,")) {
                return "Thành công";
            }
            for (int i = 0; i < errorCode.split(",").length; i++) {
                String temp = errorCode.split(",")[i];
                if (temp.equals("-1")) {
                    sb.append("Ngày hoạt động không đúng định dạng. ");
                } else if (temp.equals("-2")) {
                    sb.append("Giá trị LAC không đúng. ");
                } else if (temp.equals("-3")) {
                    sb.append("Giá trị CI không đúng. ");
                } else if (temp.equals("-4")) {
                    sb.append("Băng tần không tồn tại. ");
                } else if (temp.equals("-5")) {
                    sb.append("Thiết bị không tồn tại. ");
                } else if (temp.equals("-6")) {
                    sb.append("Loại trạm không tồn tại. ");
                } else if (temp.equals("-7")) {
                    sb.append("Mã bts không tồn tại. ");
                }

            }

        }
        return sb.toString().length() == 0 ? "OK" : sb.toString();
    }

    public static String errorDKCell3G(String errorCode) {
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            if (errorCode.equals("-99")) {
                return "Lỗi";
            }
            if (errorCode.equals("0,")) {
                return "Thành công";
            }
            for (int i = 0; i < errorCode.split(",").length; i++) {
                String temp = errorCode.split(",")[i];
                if (temp.equals("-1")) {
                    sb.append("Ngày hoạt động không đúng định dạng. ");
                } else if (temp.equals("-2")) {
                    sb.append("Giá trị LAC không đúng. ");
                } else if (temp.equals("-3")) {
                    sb.append("Giá trị CI không đúng. ");
                } else if (temp.equals("-4")) {
                    sb.append("Băng tần không tồn tại. ");
                } else if (temp.equals("-5")) {
                    sb.append("Thiết bị không tồn tại. ");
                } else if (temp.equals("-6")) {
                    sb.append("Loại trạm không tồn tại. ");
                } else if (temp.equals("-7")) {
                    sb.append("Giá trị no_of_carrier không đúng. ");
                } else if (temp.equals("-8")) {
                    sb.append("Mã nodeb không tồn tại. ");
                }
            }
        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
    }

    public static String errorDKCell4G(String errorCode) {
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            if (errorCode.equals("-99")) {
                return "Lỗi";
            }
            if (errorCode.equals("0,")) {
                return "Thành công";
            }
            for (int i = 0; i < errorCode.split(",").length; i++) {
                String temp = errorCode.split(",")[i];
                if (temp.equals("-1")) {
                    sb.append("Ngày hoạt động không đúng định dạng. ");
                } else if (temp.equals("-2")) {
                    sb.append("Giá trị LAC không đúng. ");
                } else if (temp.equals("-3")) {
                    sb.append("Giá trị CI không đúng. ");
                } else if (temp.equals("-4")) {
                    sb.append("Băng tần không tồn tại. ");
                } else if (temp.equals("-5")) {
                    sb.append("Thiết bị không tồn tại. ");
                } else if (temp.equals("-6")) {
                    sb.append("Loại trạm không tồn tại. ");
                } else if (temp.equals("-7")) {
                    sb.append("Giá trị no_of_carrier không đúng. ");
                } else if (temp.equals("-8")) {
                    sb.append("Mã enodeb không tồn tại. ");
                }
            }
        }

        return sb.toString().length() == 0 ? "OK" : sb.toString();
    }

    public static String errorDeleteCell(String errorCode) {
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            if (errorCode.equals("-99")) {
                return "Lỗi";
            }
            if (errorCode.equals("-1")) {
                return "Mã node không tồn tại";
            }
            return "Thành công";
        }
        return sb.toString().length() == 0 ? "OK" : sb.toString();
    }
}
