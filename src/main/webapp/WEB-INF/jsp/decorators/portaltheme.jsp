<%@page import="com.vnpt.media.rims.common.Message"%>

<%@ page import="com.vnpt.media.rims.bean.UserBO" %>
<%@ page import="com.vnpt.media.rims.common.Constants" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="UTF-8"%>

<jsp:include page="../parts/cachecontrol.jsp"></jsp:include>
    <html>
        <head>
            <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <link href="${pageContext.request.contextPath}/resources/vnpt/css/vnpt.css" rel="stylesheet" type="text/css"/>
        <title><sitemesh:write property='title' /></title>
    <sitemesh:write property='head' />
    <style>
        .no-print {
            z-index: 1;
        }
    </style>

</head>
<body>
    <div class="page-wrap">
        <div class="page_top" id="header">
            <div class="container header_div">
                <div class="header_info hidden-sm hidden-xs">
                    <span id="lblName">Cổng thông tin ứng dụng VNPT-OSS</span>
                    <br />
                    <span id="lblPhone"></span>
                </div>
            </div>
        </div>

        <div id="mainContainer">
            <!-- main navbar -->
            <div class="navbar navbar-inverse navbar-static-top navbar-sm" role="navigation" id="navbar-main">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="https://oss.vnpt.vn">
                            <span id="lblVersion"> </span>
                        </a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                                <li >
                                    <a href="https://oss.vnpt.vn" ><i class="fa fa-home"></i></a>
                                
                            </li>
                           
                            
                            
                              
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="divider-vertical"></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <span id="NavMenu_lblUsername">VNPT ID: <%=request.getSession().getAttribute("fullname")%></span>
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a id="NavMenu_aLogout" href="<c:url value="/logout" />"><i class="icon-signout"></i> Thoát</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>


                    </div>
                </div>
            </div>

            <!-- content wrapper -->
            <div class="container" id="wrapper">
                <div id="content" style="min-height: 400px">
                    <jsp:include page="../alert.jsp"/>
                    <sitemesh:write property='body' />
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
                        <span id="lblCopyright">© VNPT, xây dựng, triển khai bởi VNPT MEDIA SOFTWARE</span>
                        <br>
                        <span id="lblContact">57 Huỳnh Thúc Kháng - Đống Đa - Hà Nội.</span>
                    </div>
                </div>
            </div>
        </footer>        
    </div>
    <div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='${pageContext.request.contextPath}/resources/img/demo_wait.gif' width="64" height="64" /><br>Loading..</div>                
</body>
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