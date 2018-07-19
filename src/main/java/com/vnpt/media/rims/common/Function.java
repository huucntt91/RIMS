/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.common;

/**
 *
 * @author VNP
 */
public class Function {
//    public static 

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static String getInfoMemory() {
        StringBuilder str = new StringBuilder();
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        // lấy bộ nhớ còn trống trong phần bộ nhớ đã cấp phát
        long lFreeMemoryBytes = runtime.freeMemory();
        long lFreeMemoryMB = lFreeMemoryBytes / (1024 * 1024);
        // lấy bộ nhớ tối đa max heap size có thể cấp phát
        long lMaxMemoryBytes = runtime.maxMemory();
        long lMaxMemoryMB = lMaxMemoryBytes / (1024 * 1024);
        // lấy tổng bộ nhớ hiện tại đang được cấp phát trong java heap
        long lTotalMemoryBytes = runtime.totalMemory();
        long lTotalMemoryMB = lTotalMemoryBytes / (1024 * 1024);
        // lấy bộ nhớ đã sử dụng trong tổng bộ nhớ được cấp phát
        long lUsedMemoryMB = (lTotalMemoryBytes - lFreeMemoryBytes) / mb;
        // return
        str.append("MaxHeap=").append(lMaxMemoryMB);
        str.append(",TotalAllocated=").append(lTotalMemoryMB);
        str.append(",Used=").append(lUsedMemoryMB);
        str.append(",Free=").append(lFreeMemoryMB);
        return str.toString();
    }
}
