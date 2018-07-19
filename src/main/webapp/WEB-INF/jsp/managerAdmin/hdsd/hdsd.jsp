<%-- 
    Document   : hdsd
    Created on : Dec 27, 2017, 10:45:41 AM
    Author     : tunv1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hướng dẫn sử dụng</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
    </head>
    <body>
        <video width="100%" height="100%" controls>
            <source class="srcvideo" src="" type="video/mp4">
            <source class="srcvideo" src="" type="video/ogg">
            Trình duyệt của bạn không hỗ trợ xem trực tiếp, hãy <a class="avideo"  href="${pageContext.request.contextPath}/media/${media}">TẢI VỀ</a> để xem.
        </video>
    </body>
    <script>
          $(document).ready(function () {
              var url = location.protocol + "//" + location.host + "/media/" + '${media}';
              $('.srcvideo').attr('src',url);
              $('.avideo').attr('href',url);
          });
    </script>
</html>
