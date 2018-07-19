
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ADD HEADER PAGE -->
<html>
    <head>
        <title>RIMS</title>
        <link rel="icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- bootstrap 3.0.2 -->
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css">
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!-- jQuery 2.0.2 -->
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 
       
       <link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/jquery.contextMenu.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/vis-network.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/topo.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/opentip.css" rel="stylesheet" type="text/css"/>
        
</head>
<body class="skin-blue">


<style>
    div.vis-network {
        background-color: white;
    }
</style>
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            
            <div class="box" style="min-height: 400px">
                <div class="alert alert-danger" id="success-alert">
                    <button type="button" class="close" data-dismiss="alert">x</button>
                    <strong id="lbAlert"></strong>
                </div>
               
                <div class="buttonAction">
                                <a onclick="location.reload();" style="cursor: pointer" href="#" title="Load lại Topo">
                                                            <img width="32px" src="${pageContext.request.contextPath}/resources/img/reload_icon.png">
                                </a>
                                <a onclick="downloadPNG();" style="cursor: pointer" href="#" title="Export PNG">
                                                            <img width="32px" src="${pageContext.request.contextPath}/resources/img/download-icon.png">
                                </a>
                                <a onclick="info();" style="cursor: pointer" href="#" title="Chú thích">
                                    <img width="32px" src="${pageContext.request.contextPath}/resources/img/info_icon.png" />
                                </a>
                </div>
                <c:if test="${issetTopo == false}">
                    <h2 style="text-align: center; color: blue; margin-top: 50px;">Chưa có dữ liệu</h2>
                </c:if>
                <c:if test="${issetTopo == true}">
                    <div id="borough">
                            <a href="#"  class="ol-popup-closer"><img draggable="false" class="emoji" alt="✖" src="https://s0.wp.com/wp-content/mu-plugins/wpcom-smileys/twemoji/2/svg/2716.svg"></a>
                            <input type="hidden" id="txtEdges"/>
                            <input type="hidden" id="txtNodes"/>
                            <input type="hidden" id="flagAccess" value="0"/>
                            <div><label>Hướng cong: </label><input type="radio" name="curved" id="curvedCCW" value="curvedCCW" /> <label>CCW</label><input type="radio" name="curved" id="curvedCW" checked="true"  value="curvedCW" /> <label >CW</label>  </div>
                            <div><label for="slider">Độ cong: </label><div id="slider" style="width:200px; margin-top: 3px; float: right"></div></div>
                    </div>
                    <div id="mynetwork" class="context-menu-one"></div>
                    
                    <div id="noteTopo">
                        <div class="col-xs-12">Chú thích</div>
                        <c:forEach var="item" items="${styleList}">
                            <c:if test='${item.status == "NORMAL"}'>
                                <div class="col-xs-6"><img style=" width: 20px; margin: 2px 0;" src="${pageContext.request.contextPath}/resources/img/topo/${item.url}" /> </div>
                                <div class="col-xs-6">${item.typeName}</div>
                            </c:if>
                        <div class="clearfix"></div>
                        </c:forEach>
                        <div class="col-xs-12">Chú thích Link</div>
                        <div class="col-xs-12">
                             <input type="checkbox"  name="chk_khoangcach" class="form-control"
                                    id="chk_khoangcach" checked value="1">
                             <label for="chk_khoangcach">Khoảng cách</label>
                             <input type="checkbox" checked  name="chk_bangthong" class="form-control"
                                       id="chk_bangthong" value="2">
                             <label for="chk_bangthong">Băng thông</label> 
                        </div>
                    </div>
                    <div id="tooltip">
                        
                    </div>
                </c:if>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>


<!-- ADD Footer PAGE -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.9.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/topo/jquery.contextMenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/topo/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/vis.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/topo/mane.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js" type="text/javascript"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>




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

</body>
</html>



<script>
    
    
    <c:if test="${issetTopo == true}">
    // create an array with nodes
        <c:if test="${nodeList != null}">
            var DIR = '${pageContext.request.contextPath}/resources/img/topo/';
            var nodesData = [
                <c:forEach var="item" items="${nodeList}">
                     {id: ${item.id}, ip: '${item.ip}',tinhTpId: '${item.tinhTpId}',maTinh: '${item.maTinh}', shape: 'image', label: '${item.name}', image : DIR + '${item.imageUrl}' , topo: ${item.typeId}, chaId: '${item.chaId}' },   
                </c:forEach>
                    {id: 8888888, ip: '',tinhTpId: '${tnode1.tinhTpId}',maTinh: '${tnode1.maTinh}', shape: 'image', label: '${node1.code}', image : DIR +  '${node1.neTypeId == 11 ? 'P_Normal.png' : (node1.neTypeId == 22 ? 'server.png' : 'bts_topo.png' )}' , topo: ${tnode1.typeId} },
                    {id: 9999999, ip: '',tinhTpId: '${tnode2.tinhTpId}',maTinh: '${tnode2.maTinh}', shape: 'image', label: '${node2.code}', image : DIR + '${node2.neTypeId == 11 ? 'P_Normal.png' : (node2.neTypeId == 22 ? 'server.png' : 'bts_topo.png' )}' , topo: ${tnode2.typeId} }
            ];
        
            var edgesData = [
                <c:forEach var="item" items="${tlinkList}">
                     {id:${item.rownum}, tid: ${item.id}, loai: ${item.loai}, from: ${item.nodeId1}, to: ${item.nodeId2}, label: '${item.distanceM} ; ${item.bandWidth} ${item.bandWidthDv}' , smooth: { roundness: 0 , type: 'curvedCW'} , hidden: false, type: ${item.type} ${item.type == '3' ? ', width: 30' : '' } },
                </c:forEach>
                    {id: 8888888, loai: 2, from: 8888888, to: ${tnode1.id}, label: '' , smooth: { roundness: 0 , type: 'curvedCW'} , hidden: false },
                    {id: 9999999, loai: 2, from: 9999999, to: ${tnode2.id}, label: '' , smooth: { roundness: 0 , type: 'curvedCW'} , hidden: false }        
                ];
            nodesData = getPotionNodeDataVPN(${tnode1.id}, ${tnode2.id}, '${pageContext.request.contextPath}/resources/img/topo/');
            var nodesDataset = new vis.DataSet(nodesData);
            var edgesDataset = new vis.DataSet(edgesData);
            
    </c:if>
    function redrawAll() {
        // create a network
        container = document.getElementById('mynetwork');
       
        data = {
            nodes: nodesDataset,
            edges: edgesDataset
        };
        options = {
            // tùy chọn hiệu ứng di chuyển vật lý, di chuyển 1 node các node khác sẽ di chuyển theo
            // hiệu ứng vật lý được thể hiện như trọng lực, lực hút, đẩy, độ co giãn của các liên kết
            physics: {
                enabled: false,
                // cấu hình thuật toán cho các hành động để các node phản ứng lại khi sắp xếp
                barnesHut: {
                    // lực đẩy, số âm càng to các node đẩy nhau càng mạnh
                    gravitationalConstant: -10000,
                    // hệ số trọng lực trung tâm, dùng để kéo toàn bộ các node vào giữa, để co toàn bộ mạng lại
                    // hệ số càng cao thì các node càng xít gần nhau và gần trung tâm màn hình hơn
                    centralGravity: 0.1,
                    // các kết nối thể hiện bằng các dây có độ co giãn, gọi là lò xo
                    // tham số này thể hiện độ dài của lò xo, càng cao thì các kết nối càng dài, các node cách xa nhau
                    springLength: 200,
                    // springConstant thể hiện độ cứng của lò xo liên kết, càng cao thì càng cứng
                    springConstant: 0.04,
                    // hệ số damping thể hiện vận tốc của các đối tượng khi di chuyển, giá trị số thực trong khoảng từ 0->1
                    damping: 0.5,
                    // giá trị 0 hoặc 1, thể hiện các node sẽ tránh đè lên nhau, 1 là tránh
                    // chồng lên nhau, nhưng có khả năng network sẽ di chuyển mãi ko ngừng để tránh chồng lên nhau
                    avoidOverlap: 0
                },
                // lựa chọn thuật toán sắp xếp tự động, có 4 loại, mặc định là barnesHut
                // 'barnesHut', 'repulsion', 'hierarchicalRepulsion', 'forceAtlas2Based'
                solver: 'barnesHut',
                // ổn định hóa
                stabilization: {
                    // tắt/bật ổn định hóa
                    enabled: true,
                    // số bước lặp tối đa để thực hiện ổn định hóa
                    iterations: 1000,
                    // sử dụng khi muốn hiện thanh loading bar để đợi trong khi DOM HTML ổn định các đối tượng
                    updateInterval: 100,
                    // khi muốn set vị trí của các node và ko muốn di chuyển các node khi ổn định thì set true
                    onlyDynamicEdges: false,
                    // khi ổn định các đối tượng xong, nếu true thì network sẽ nằm trong màn hình
                    fit: true
                }
            },
            // cấu hình layout sắp xếp layout mặc định
            layout: {
                // randomSeed=undefined sẽ hiển thị ngẫu nhiên layout
                // randomSeed=1 số nào đó thì khi load sẽ ra layout tương ứng với số đó, reload lại cũng ko thay đổi
                randomSeed: 1
            },
            edges: {
//                shadow: true,
                smooth: {
                    type: 'curvedCW',
                    //type: 'curvedCW', roundness: 0.5
                    //forceDirection: 'none', //duong lien kien cong hay thang
                    //roundness: 0 // duong lien kien cong hay thang
                },
                font: {
                    align: 'middle'
                }
            },
            interaction: {
                hover:true, 
                tooltipDelay: 300
            }
       
        };
        
        network = new vis.Network(container, data, options);
        network.on("click", function (params) {
            params.event = "[original event]";
            //document.getElementById('eventSpan').innerHTML = '<h2>Click event:</h2>' + JSON.stringify(params, null, 4);
            //console.log('click event, getNodeAt returns: ' + this.getNodeAt(params.pointer.DOM));
            //console.log('click event, getEdgeAt returns: ' + this.getEdgeAt(params.pointer.DOM));
            var idEdgeAt = this.getEdgeAt(params.pointer.DOM)
            if(undefined != idEdgeAt){
                var edges = objectToArray(edgesDataset._data);
                edges.forEach(function (elem, index, array) {
                    if(elem.id == idEdgeAt){
                        if(elem.smooth !== undefined)
                            $("#slider").slider('value', elem.smooth.roundness);
                        else
                            $("#slider").slider('value', 0);
                         //$("#info_opacity").html("roundness: " + elem.smooth.roundness + "");
                    }
                });
                $("#txtEdges").val(idEdgeAt);
                $('#borough').show();
                
                //alert(idEdgeAt);
            }
            var idNodes = this.getNodeAt(params.pointer.DOM);
//            if(undefined != idNodes){
//                $("#txtNodes").val(idNodes);
//                $('.context-menu-one').contextMenu(true);
//            }
//            else{
//                $("#txtNodes").val("");
//                $('.context-menu-one').contextMenu(false);
//            }
            
        });
        network.on("hoverNode", function (params) {
            $("#txtNodes").val(params.node);
            $('.context-menu-one').contextMenu(true);
            loadDetailTnode(params.node);
        });
        network.on("blurNode", function (params) {
             $('.context-menu-one').contextMenu(false);
             $('#tooltip').hide();
        });
        
        network.on("hoverEdge", function (params) {
            //$('.context-menu-one').contextMenu(true);
            loadDetailEdge(params.edge);
            console.log(params.edge)
        });
        network.on("blurEdge", function (params) {
             //$('.context-menu-one').contextMenu(false);
             $('#tooltip').hide();
        });
        
        window.onmousemove = function (e) {
            var x = e.clientX,
            y = e.clientY;
            var heightTooltip = $('#tooltip').height();
            //console.log(heightTooltip);
            $('#tooltip').css("top",(y - heightTooltip - 20) + 'px');
            $('#tooltip').css("left",(x + 20) + 'px');
        };
    };
    </c:if>

    var firtIdSearch = 0;
    
    $(document).ready(function () {
        $("#success-alert").hide();
        $("#mynetwork").
                css("width", '100%').
                css("height", $(window).height() - 115);
        $('#tinhTpId').change(function ()
        {
            $('#frm_search').submit();
        });
        $('.ol-popup-closer').click(function () {
            $('#borough').hide(1000);
        });
        <c:if test="${issetTopo == true}">
            redrawAll();
            //console.log(nodesDataset);
            //edgesDataset.update([{id: 6, smooth: {roundness:0}}]);
            $("#slider").slider({
                range: "min",
                value: 0,
                min: 0,
                max: 5,
                step: 0.01,
                slide: function (event, ui) {
                    //$("#info_opacity").html("roundness: " + ui.value + "");
                    var idEdges = $("#txtEdges").val();
                    edgesDataset.update([{id: idEdges, smooth: {roundness: ui.value}}]);
                    for(var i=0;i<edgesData.length;i++){
                        if(edgesData[i].id==idEdges){
                            edgesData[i].smooth.roundness = ui.value; 
                            break;
                        }
                    }    
                }
                
            });
            $('.iCheck-helper').click(function () {
                    var parent = $(this).parent().get(0);
                    $(parent).find('input').click();
            });
            $('input[type=radio][name=curved]').click(function() {
                var idEdges = $("#txtEdges").val();
                if (this.value == 'curvedCCW') {
                    //alert("curvedCCW");
                    edgesDataset.update([{id: idEdges, smooth: {type: 'curvedCCW'}}]);
                    for(var i=0;i<edgesData.length;i++){
                        if(edgesData[i].id==idEdges){
                            edgesData[i].smooth.type='curvedCCW'; 
                            break;
                        }
                    }
                }
                else if (this.value == 'curvedCW') {
                    //alert("curvedCW");
                    edgesDataset.update([{id: idEdges, smooth: {type: 'curvedCW'}}]);
                    for(var i=0;i<edgesData.length;i++){
                        if(edgesData[i].id==idEdges){
                            edgesData[i].smooth.type='curvedCW'; 
                            break;
                        }
                    }
                }
            });
            
            $.contextMenu({
                selector: '.context-menu-one',
                build: function($trigger, e) {
                    return {
                        callback: function(key, options) {
                            var m = "clicked: " + key;
                            //window.console && console.log(m) || alert(m);
                            if(key == 'device1'){
                                showDevice($('#txtNodes').val(),1);
                            }
                            if(key == 'device2'){
                                showDevice($('#txtNodes').val(),2);
                            }

                        },
                        items: loadme()
                        }
                    }
                });
           
            $('.context-menu-one').contextMenu(false);
            $('#topo_access').click(function() {
                if($('#topo_access').is(":checked")){
                    // an access
                    updateVisiAcess(false,"");
                    $('#flagAccess').val(0);
                }
                else{
                    // show all access
                    updateVisiAcess(true,"")
                    $('#flagAccess').val(1);
                }
            });
            // action khi click chk_khoangcanh
            $('#chk_khoangcach').click(function() {
                var tempEdges = $.map(edgesData, function (obj) {
                      return $.extend({}, obj);
                  });
                //console.log(edgesData);
                if($('#chk_khoangcach').is(":checked")){
                    // an chu thich khoang canh
                    if($('#chk_bangthong').is(":checked")){
                        tempEdges.forEach(function (item, i) {
                            var arrLabel = tempEdges[i].label.split(";");
                            tempEdges[i].label = arrLabel[1].trim();
                        });
            
                    } else {
                        tempEdges.forEach(function (item, i) {
                            tempEdges[i].label = "";
                        });
                    }
                }
                else{
                    // show chu thich khoang canh
                    if($('#chk_bangthong').is(":checked")){
                        // 2 cai cung hien thi giu nguyen 
                    } else {
                        tempEdges.forEach(function (item, i) {
                            var arrLabel = tempEdges[i].label.split(";");
                            tempEdges[i].label = arrLabel[0].trim();
                        });
                    }
                }
                edgesDataset.update(tempEdges);
            });
            // action khi click chk_bangthong
            $('#chk_bangthong').click(function() {
                var tempEdges = $.map(edgesData, function (obj) {
                      return $.extend({}, obj);
                  });
                //console.log(edgesData);
                if($('#chk_bangthong').is(":checked")){
                    // an chu thich bang thong
                    if($('#chk_khoangcach').is(":checked")){
                        tempEdges.forEach(function (item, i) {
                            var arrLabel = tempEdges[i].label.split(";");
                            tempEdges[i].label = arrLabel[0].trim();
                        });
            
                    } else {
                        tempEdges.forEach(function (item, i) {
                            tempEdges[i].label = "";
                        });
                    }
                }
                else{
                    // show chu thich bang thong
                    if($('#chk_khoangcach').is(":checked")){
                        // 2 cai cung hien thi giu nguyen 
                    } else {
                        tempEdges.forEach(function (item, i) {
                            var arrLabel = tempEdges[i].label.split(";");
                            tempEdges[i].label = arrLabel[1].trim();
                        });
                    }
                }
                edgesDataset.update(tempEdges);
            });
            
            
            $("#txtLabel").on('keyup', function (e) {
                if (e.keyCode == 13) {
                    // Do something
                    btSearch();
                }
            });
        </c:if>
            
            
    });
    
    function loadme() {
            return {
                    "device": {name: "Thiết bị", icon: "copy", items: {
                            "device1": {"name": "Danh sách"},
                            "device2": {"name": "Đồ họa"},
                        }
                    },
                    "quit": {name: "Quit", icon: function(){
                            return 'context-menu-icon context-menu-icon-quit';
                    }}
                }
    };
    
    function saveTopo(){
        if(network != null){
            var tempNodes = [];
            var edges = objectToArray(edgesDataset._data);
            
            var nodesPosition = objectToArray(network.getPositions());
         
            nodesData.forEach(function (item, i) {
                var flag = false;
                nodesPosition.forEach(function (elem, index, array) {
                    if (elem.id == item.id) {
                        tempNodes.push({id: item.id, x: elem.x, y: elem.y});
                        item.x = elem.x;
                        item.y = elem.y;
                        flag = true;
                    }
                });
                if(!flag){
                    tempNodes.push({id: item.id, x: item.x, y: item.y});
                }
            });
            
            //nodes.forEach(addConnections);
         
            var exportValue = JSON.stringify(tempNodes, undefined, 2);
            var exportEdges = JSON.stringify(edgesData, undefined, 2); // lay luon array hien tai
            
            console.log(edgesData);
           
            
            $.post("${pageContext.request.contextPath}/broadband/topo/addToPoManE", {tinhTpId: $('#tinhTpId').val(), content: exportValue, edges: exportEdges }, function(data) {
                if (parseInt(data) < 1){
                    showAlert('Lưu Topo thất bại! Mời bạn thử lại',false);
                }
                else{
                    showAlert('Lưu Mane Topo thành công',true);
                }
            });
        }else{
             alert('Chưa có Topo để lưu');
        }
    }
    
    var arraySearch = [];
    var indexSearch = 0;
    var txtSearchOld = '';
    function btSearch(){
        var flagResult = false;
        var txtSeach = $('#txtLabel').val().trim();
        if(txtSeach == ''){
            alert('Bạn phải nhập label của node');
            return;
        }
        txtSeach = boDau(txtSeach.toLowerCase());
        var options = {
                            scale: 1.5,
                            offset: {x:0,y:0},
                            animation: {
                                duration: 1500,
                                easingFunction: 'easeInOutQuad'
                            }   
                       };
        if(firtIdSearch != 0)
            nodesDataset.update([{id: firtIdSearch, font: {color: 'black'}}]);
        if(txtSearchOld != txtSeach){
            txtSearchOld = txtSeach;
            indexSearch = 0;
            // reset all color to black 
            
            arraySearch = [];
            for(var i=0;i<nodesData.length;i++){
                var keyResult = boDau(nodesData[i].label.toLowerCase().trim());
                if(keyResult.indexOf(txtSeach) > -1 && !nodesData[i].hidden){ 
                    //network.focus(nodesData[i].id, options);
                    //nodesDataset.update([{id: nodesData[i].id, font: {color: 'red'}}]);
                    //firtIdSearch = nodesData[i].id;
                    arraySearch.push(nodesData[i].id);
                    //flagResult = true;
                    //break;
                }
            }
        }
        
        if(arraySearch.length == 0){
            alert('Không tìm thấy kết quả!');
        }
        else{
            if(indexSearch == arraySearch.length)
                indexSearch = 0;
            network.focus(arraySearch[indexSearch], options);
            nodesDataset.update([{id: arraySearch[indexSearch], font: {color: 'red'}}]);
            firtIdSearch = arraySearch[indexSearch];
            indexSearch++;        
        }
        
    }
    
    function downloadPNG() {
        var accessFull = 0;
        if ($('#topo_access').is(":checked"))
            accessFull = 1;
        var urlPrint = '${pageContext.request.contextPath}/broadband/topo/print?tinhTpId=' + $('#tinhTpId').val() + '&neType=' + accessFull;
        window.open(urlPrint,'_blank');
    }
    
    function loadDetailTnode(id){
        for(var i=0;i<nodesData.length;i++){
            if(nodesData[i].id == id){
                var html = '<table>';
                html += '<tr><td>Tên: </td><td>' + nodesData[i].label +'</td></tr>';
                html += '<tr><td>Loại: </td><td>' + configTypeId(nodesData[i].topo) +'</td></tr>';
                html += '<tr><td>IP: </td><td>' + nodesData[i].ip +'</td></tr>';
                html += '</table>';
                $('#tooltip').html(html);
                $('#tooltip').show();
            }
        }
    }
    function loadDetailEdge(id){
        $.get("${pageContext.request.contextPath}/broadband/topo/getEdges/" + edgesData[id-1].tid, function(data) {
            //console.log(data);
            if(data != null)
            {
                var html = '<table>';
                html += '<tr><td>Tên thiết bị </td><td><font color="red">' + data.nodeName1 + '</font> - <font color="green">' + data.nodeName2 +'</font></td></tr>';
                html += '<tr><td>Cổng: </td><td><font color="red">' + data.port1 + '</font> - <font color="green">' + data.port2 +'</font></td></tr>';
                html += '<tr><td>Địa chỉ IP: </td><td><font color="red">' + data.ip1 + ' </font> - <font color="green"> ' + data.ip2 + '</font></td></tr>';
                html += '<tr><td>Mức thu: </td><td><font color="red">' + data.mucThu1 + ' </font> - <font color="green"> ' + data.mucThu2 + '</font></td></tr>';
                html += '<tr><td>Khoảng cách: </td><td>' + data.distanceM + ' (m)</td></tr>';
                html += '<tr><td>Băng thông: </td><td>' + data.bandWidth + ' ' + data.bandWidthDv + '</td></tr>';
                html += '</table>';
                $('#tooltip').html(html);
                $('#tooltip').show();
            }
        });
    }
    
    function showDevice(id,type){
        if(type == 1)
            window.open('${pageContext.request.contextPath}/eq/init?tnodeId=' + id,'_blank','width=800,height=500');
        else if (type == 2)
            window.open('${pageContext.request.contextPath}/viewCard/view/' + id,'_blank','width=800,height=500');
    }
                

</script>

