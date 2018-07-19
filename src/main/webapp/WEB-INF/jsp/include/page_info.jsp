<%--
    Document   : page_info
    Created on : Nov 18, 2015, 7:03:36 PM
    Author     : Le
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">

    <div class="form-inline">
        <div class="col-lg-6">
            <div class="dataTables_info">
                <div class="col-lg-4"> ${pageInfo.totalRowsTitle} ${pageInfo.totalRows}</div>
                <div class="col-lg-4">${pageInfo.totalPagesTitle} ${pageInfo.totalPages}</div>
                <div class="col-lg-4">
                    <div class="form-inline">
                        <label for="page">${pageInfo.goToPageTitle}</label>
                        <input type="number" class="" name="page" id="page" value="${pageInfo.destPage}"  onchange="javascript:getTextPage();"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-6" style="text-align: right">
            <div class="dataTables_paginate paging_simple_numbers" id="example1_paginate"></div>
        </div>
    </div>

</div>
<script type="text/javascript" language="javascript">

    function getCurrentPage(curPage)
    {
        var path = document.location.href;

        path = path.indexOf('?') !== -1 ? path + '&' : path + '?';
        path = path.replace("?page=" + curPage + "&", "?");
        path = path.replace("&page=" + curPage + "&", "&");
        //path = path.replace("&page=".curPage , "");

        return path;
    }

    function showPageLink(curPage, numShowedPage, totalPage) {
        var path = getCurrentPage(curPage);

        var strHtml = '<ul class="pagination">';
        if (totalPage <= numShowedPage) {
            for (var x = 1; x <= totalPage; x++) {
                var url = path + "page=" + x;

                if (x == curPage) {
                    strHtml += '<li class="active"><a href="#">' + x + '</a></li>'
                } else
                    strHtml += '<li><a href="' + url + '">' + x + '</a></li>';
//                if (x == curPage) {
//                    if (x == totalPage) {
//                        strHtml += x;
//                    } else {
//                        strHtml += x + " | ";
//                    }
//
//                } else {
//                    if (x == totalPage) {
//                        strHtml += '<li><a href="' + url + '">' + x + '</a></li>';
//                    } else {
//                        strHtml += '<li><a href="' + url + '">' + x + '</a>' + ' | ';
//                    }
//                }
            }
        } else {
            strHtml += '<li><a href="' + path + "page=1" + '">Đầu</a></li>';
            var startPage = 1;
            var endPage = totalPage;

            var numBefore = Math.ceil(numShowedPage / 2);
            var numAfter = Math.ceil(numShowedPage / 2) - 1;
            if (curPage > numBefore) {
                startPage = curPage - numAfter;
            }

            if (curPage > (totalPage - numAfter)) {
                startPage = totalPage - numShowedPage + 1;
            }
            endPage = startPage + (numShowedPage - 1);

            if (endPage >= totalPage) {
                endPage = totalPage;
            }
            for (var x = startPage; x <= endPage; x++) {
                var url = path + "page=" + x;
                if (x == curPage) {
                    strHtml += '<li class="active"><a href="#">' + x + '</a></li>';
                } else
                    strHtml += '<li><a href="' + url + '">' + x + '</a></li>';
//                if (x == curPage) {
//                    strHtml += x;
//                } else {
//                    strHtml += '<li><a href="' + url + '">' + x + '</a></li>';
//                }
//                if (x != endPage) {
//                    strHtml += " | ";
//                }
            }
            strHtml += '<li><a href="' + path + 'page=' + totalPage + '">Cuối</a></li> ';
        }
        strHtml += '</ul>';
        document.getElementById("example1_paginate").innerHTML = strHtml;


    }

    function getTextPage() {
        var page = document.getElementById("page").value;
        if (isNaN(page)) {
            document.getElementById("page").value = ${pageInfo.destPage};
        } else {
            if (page > ${pageInfo.totalPages}) {
                page = ${pageInfo.totalPages};
            } else if (page < 1) {
                page = 1;
            }

            document.getElementById("page").value = page;

            if (page == ${pageInfo.destPage}) {
                return;
            } else {
                window.location.href = getCurrentPage(${pageInfo.destPage}) + "page=" + page;
                //goToPage(url, page);
            }
        }
    }

    showPageLink(${pageInfo.destPage}, 7, ${pageInfo.totalPages});


</script>
