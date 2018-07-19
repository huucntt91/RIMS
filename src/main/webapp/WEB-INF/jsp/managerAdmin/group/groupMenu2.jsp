<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />



        <link href="${pageContext.request.contextPath}/resources/js/js2/style.min.css" rel="stylesheet" type="text/css" />

        <!--        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
                <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
                <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
                <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
                <script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js" type="text/javascript"></script>
                <script src="${pageContext.request.contextPath}/resources/js/js2/jstree.min.js"></script>-->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/themes/default/style.min.css" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/jstree.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"/>


    </head>
    <body>
        <script type="text/javascript">
            $(function() {
                //document.getElementById("plugins1").innerHTML = $('#data').val();
                $("#plugins1")
                        .on("changed.jstree", function(e, data) {
                            console.log(data.selected.length); // newly selected
                            //console.log(data.changed.deselected); // newly deselected
                            var i, j, r = [];
                            var selected = [];
                            for (i = 0, j = data.selected.length; i < j; i++) {
                                r.push(data.instance.get_node(data.selected[i]).id);
//                                r.push(data.selected[i].parent);
//                                selected.push(data.instance.get_node(data.selected[i]).parents);
                                selected = selected.concat(data.instance.get_node(data.selected[i]).parents);
                            }
//                           var selected = $.vakata.array_unique(r);

                            //


                            selected = $.vakata.array_unique(selected);
//                            var loMainSelected = data;



                            $('#resultText').html('Selected: ' + r.join(', '));

//                            $("#plugins1").find(".jstree-undetermined").each(function(i, element) {
//                                var checked_ids = [];
//
//                                alert($(element).closest('.jstree-node').attr("id"));
//                                checked_ids.push($(element).attr("id"));
                            $('#resultText2222').html('undetermined: ' + selected.join(', '));
//                            });

                        });

//                        .on('select_node.jstree', function(e, data) {
//                            var loMainSelected = data;
//                            uiGetParents(loMainSelected);
//                        })
                $("#plugins1").jstree({
                    "plugins": ["checkbox"]
                });

            });
        </script>

        <section class="content-header">
            <h1>Quản lý Phân quyền<small></small></h1>
            <ol class="breadcrumb">
            </ol>
        </section>
        <section class="content">    
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Danh mục nhóm quyền</h3>
                        </div>
                        <input type="hidden" value="" id="data"/>
                        <label>Result - <span id="resultText"></span></label><br />
                        <label>Result2222 - <span id="resultText2222"></span></label>


                        <form:form method="POST" action="updateGroupMenu">
                            <div class="box-body">
                                <div class="form-group">
                                    <div id="plugins1" class="demo plugin-demo"> 
                                        ${data}
                                    </div>
                                </div>                                                                                     
                            </div>
                            <div class="box-footer">
                                <button id="btnUpdateMenu" type="button" class="btn btn-primary"><spring:message code="admin.common" /></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div id="container">
                <ul>
                    <li data-jstree='{ "opened" : true }'>Root node
                        <ul>
                            <li id ='${id}' data-jstree='{ "selected" : true}'>Child node 1</li>
                            <li>

                                Child node 2
                                <ul><li>fdafdas</li></ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <script>
                $(function() {
                    $('#container').jstree({
                        "plugins": ["checkbox"]
                    });
                });
            </script>
        </section>

        <!-- jQuery 2.0.2 -->

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        <!--call ajax-->
        <script src="${pageContext.request.contextPath}/resources/js/common.js" type="text/javascript"></script>
        <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.6/css/jquery.dataTables.css"/>-->

        <script src="https://cdn.datatables.net/1.10.6/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/process-form.js" type="text/javascript"></script>        
        <script type="text/javascript">
                $('#btnUpdateMenu').click(function() {
                    alert('1');
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8084/RIMS/group/updateGroupMenu/1",
                        data: 'loginData',
                        success: function(result) {
                            // do something.
                            alert(result);
                            //document.getElementById("plugins1").innerHTML = result;
                            window.location.reload(true);
                        },
                        error: function(result) {
                            // do something.
                            alert('error');
                        }
                    });
                });
        </script>
    </body>
</html>