<%-- 
    Document   : header
    Created on : Aug 31, 2016, 5:46:18 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%@page  pageEncoding="UTF-8"%>

</div>
</div>
</div>
<footer class="site-footer">
    <div class="page_footer" id="footer">
        <div class="container footer_div" style="text-align: right;">
            <div class="pull-left footer_info hidden-sm hidden-xs">
                <!--                        <a href="http://forum.vnpt.vn/forumdisplay.php?374&amp;h=fAQGV1aP4" target="_blank">
                                            <span id="lblReq">Ý kiến phản hồi</span>
                                        </a>-->
                <br>
                <a href="https://oss.vnpt.vn/media/HDSD_RIMS.pdf">
                    <span id="lblHelp">Hướng dẫn sử dụng</span>
                </a>
                <br>
                <span id="lblCopyright">© VNPT, xây dựng, triển khai bởi Trung tâm ERP - VNPT IT</span>
                <br>
                <span id="lblContact">57 Huỳnh Thúc Kháng - Đống Đa - Hà Nội.</span>
            </div>
        </div>
    </div>
</footer>        
</div>
<div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='${pageContext.request.contextPath}/resources/img/demo_wait.gif' width="64" height="64" /><br>Loading..</div>
</body>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/fastclick/fastclick.js"></script>


<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>


<!--call ajax-->
<script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).ajaxStart(function () {
            $("#wait").css("display", "block");
        });
        $(document).ajaxComplete(function () {
            $("#wait").css("display", "none");
        });
        
        $('.navbar a.dropdown-toggle').on('click', function (e) {
            var $el = $(this);
            var $parent = $(this).offsetParent(".dropdown-menu");
            $(this).parent("li").toggleClass('open');

            if (!$parent.parent().hasClass('nav')) {
                $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
            }
            $('.nav li.open').not($(this).parents("li")).removeClass("open");
            return false;
        });
    });

</script>
</html>