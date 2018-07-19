package com.vnpt.media.rims.common.utils;

import com.vnpt.media.rims.common.Constants;
import org.apache.logging.log4j.LogManager;

public class PagingUtils {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PagingUtils.class);

    private static final int MAXDISPLAYPAGES = 8;
    private static final String PAGERSTYLE = "pagelinks";
    private static final String[] STRPAGER = {"< Tr&#432;&#7899;c", "...", "Sau >"};
    private static final String[] STRPREFIX = {"&#272;&#7847;u", "Tr&#432;&#7899;c", "Sau", "Cu&#7889;i", "Trang", "T&#7899;i trang "};
    private static final String SEPARATOR = ",";

    public static String printPaging(String urlWeb, int totalRecords, int totalRecordPerPage, int currentPage) throws Exception {
        return printPaging(urlWeb, totalRecords, totalRecordPerPage, currentPage, null);
    }

    public static String printPaging(String urlWeb, int totalRecords, int totalRecordPerPage, int currentPage, String methodAjax) throws Exception {
        try {
            int totalPageCount = (totalRecords % totalRecordPerPage > 0) ? totalRecords / totalRecordPerPage + 1 : totalRecords / totalRecordPerPage;
            if (currentPage > totalPageCount) {
                currentPage = totalPageCount;
            }
            if (!StringUtils.hasText(urlWeb)) {
                return null;
            }
            int vCount;
            vCount = currentPage * totalRecordPerPage;
            if (vCount > totalRecords) {
                vCount = totalRecords;
            }
            String paging = "<b>&nbsp;T&#7893;ng s&#7889; b&#7843;n ghi : " + vCount + "/" + totalRecords + " </b>" + getPagingDisplayTable(totalRecords, currentPage, urlWeb, methodAjax);
            return paging;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private static int getPageCountAndPageSize(int pageSize, int rowCount) {
        int pageCount = 1;
        if (pageSize > 0 && rowCount > 0) {
            pageCount = (int) Math.ceil((rowCount - 1) / pageSize) + 1;
        }
        return pageCount;
    }

    private static int getPageCount(int rowCount) {
        int pageCount = getPageCountAndPageSize(Constants.NUMBER_FOR_PAGING, rowCount);
        return pageCount;
    }

    private static String getPageStrDisplayTable(boolean selected, int pageNo, String pageLink, String prefixPaging, String methodAjax) {

        if (selected) {
            String tempCurrentPage = "<strong>" + STRPREFIX[4] + "&nbsp;" + pageNo + "</strong>";
            return tempCurrentPage;
        } else {
            String url = pageLink;
            String showPage, alt = "";
            if (prefixPaging != null && prefixPaging.trim().length() > 0) {
                showPage = prefixPaging;
            } else {
                alt = "title='" + STRPREFIX[5] + pageNo + "'";
                showPage = String.valueOf(pageNo);
            }
            String tempPaging;
            if (StringUtils.hasText(methodAjax)) {
                tempPaging = "<a style='cursor:pointer' href=\"javascript:" + methodAjax + "(\'" + url + "',\'" + pageNo + "')\"" + alt + ">" + showPage + "</a>";
            } else {
                tempPaging = "<a style='cursor:pointer' href=\"javascript:ajaxPaging(\'" + url + "',\'" + pageNo + "')\"" + alt + ">" + showPage + "</a>";
            }
            return tempPaging;
        }
    }

    private static String getPagingDisplayTable(int rowCount, int pageCurrent, String pageLink, String methodAjax) {
        try {
            int groupSize = MAXDISPLAYPAGES, startPage, endPage;
            StringBuilder stringBuffer = new StringBuilder();
            int pageCount = getPageCount(rowCount);
            if (pageCount == 1) {
                stringBuffer.append("<span id=\"pager_span\" class='").append(PAGERSTYLE).append("'>");
                stringBuffer.append("<strong>");
                stringBuffer.append(STRPREFIX[4]);
                stringBuffer.append("&nbsp;");
                stringBuffer.append(pageCount);
                stringBuffer.append("</strong>");
                stringBuffer.append("</span>");
                return stringBuffer.toString();
            }
            if (pageCount > 0) {
                stringBuffer.append("<span id=\"pager_span\" class='").append(PAGERSTYLE).append("'>");
                startPage = Math.max(Math.min(pageCurrent - groupSize / 2, pageCount - (groupSize - 1)), 1);
                endPage = Math.min(startPage + groupSize - 1, pageCount);
                if (pageCurrent != 1) {
                    stringBuffer.append("[");
                    stringBuffer.append(getPageStrDisplayTable(false, 1, pageLink, STRPREFIX[0], methodAjax));
                    stringBuffer.append("/");
                    stringBuffer.append(getPageStrDisplayTable(false, (pageCurrent - 1), pageLink, STRPREFIX[1], methodAjax));
                    stringBuffer.append("]");
                    stringBuffer.append("&nbsp;");
                } else {
                    stringBuffer.append("[");
                    stringBuffer.append(STRPREFIX[0]);
                    stringBuffer.append("/");
                    stringBuffer.append(STRPREFIX[1]);
                    stringBuffer.append("]");
                }
                for (int j = startPage; j <= endPage; j++) {
                    stringBuffer.append(getPageStrDisplayTable((j == pageCurrent), j, pageLink, null, methodAjax));
                    stringBuffer.append("&nbsp;");
                    if (j != endPage) {
                        stringBuffer.append(SEPARATOR);
                        stringBuffer.append("&nbsp;");
                    }
                }
                if (pageCurrent != pageCount) {
                    stringBuffer.append("[");
                    stringBuffer.append(getPageStrDisplayTable(false, (pageCurrent + 1), pageLink, STRPREFIX[2], methodAjax));
                    stringBuffer.append("/");
                    stringBuffer.append(getPageStrDisplayTable(false, (pageCount), pageLink, STRPREFIX[3], methodAjax));
                    stringBuffer.append("]");
                } else {
                    stringBuffer.append("[");
                    stringBuffer.append(STRPREFIX[2]);
                    stringBuffer.append("/");
                    stringBuffer.append(STRPREFIX[3]);
                    stringBuffer.append("]");
                }
                stringBuffer.append("</span>");
                return stringBuffer.toString();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
