
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
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
        <link href="${pageContext.request.contextPath}/resources/css/vis-network.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/resources/css/topo.css" rel="stylesheet" type="text/css"/>
        <!-- jQuery 2.0.2 -->
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.1.min.js"></script> 
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script> 
       
        <!-- jquery ui -->
        
<style>
    #mynetworkPrint{
        width: 2200px;
        height: 2200px;
    }
    div.vis-network {
        background-color: white;
    }
</style>

</head>
<body class="skin-blue">
<section class="content">            
    <div class="row">
        <div class="col-xs-12">
            
            <div class="box" style="min-height: 400px">
                <a href="#" id="btn-download" download="${tinhBO.tenTinhTp}.png" style="cursor: pointer" href="#" title="Export PNG">
                                                            <img width="32px" src="${pageContext.request.contextPath}/resources/img/download-icon.png">
                                </a>
                                <h3 style="text-align: center">Mạng Topo ${tinhBO.tenTinhTp}</h3>
                <input style="display: none" type="checkbox"  name="neType" class="form-control"
                                       id="topo_access" ${neType == 1 ? 'checked' : ''} value="1">
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
                    <div id="mynetworkPrint"></div>
                  
                </c:if>
            </div>
            <!-- /.box -->
        </div>
    </div>
</section>
</body>
</html>

<!-- ADD Footer PAGE -->
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/topo/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/vis.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/topo/mane.js" type="text/javascript"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.min.js"></script>

<c:if test="${content != null}">
    <div id="jsonContent" style="display: none">
         ${content[0]}  
    </div>
    <div id="jsonEdges" style="display: none">
         ${content[1]}  
    </div>     
</c:if>

<script>
    
    
    <c:if test="${issetTopo == true}">
    // create an array with nodes
        <c:if test="${nodeList != null}">
            var DIR = '${pageContext.request.contextPath}/resources/img/topo/';
            var nodesData = [
                <c:forEach var="item" items="${nodeList}">
                     {id: ${item.id}, ip: '${item.ip}', shape: 'image', label: '${item.name}', image : DIR + '${item.imageUrl}' , hidden: ${item.typeId == '1' || item.typeId == '2' || item.typeId == '3' ? 'false' : 'true'}, topo: ${item.typeId}, chaId: '${item.chaId}', size: ${item.typeId == '1' ? '80' : (item.typeId == '3' ? '60' : (item.typeId == '2' ? '40' : '20'))} },   
                </c:forEach>
            ];
        
            var edgesData = [
                <c:forEach var="item" items="${tlinkList}">
                     {id:${item.rownum}, tid: ${item.id}, loai: ${item.loai}, from: ${item.nodeId1}, to: ${item.nodeId2}, label: '${item.distanceM} ; ${item.bandWidth} ${item.bandWidthDv}' , smooth: { roundness: 0 , type: 'curvedCW'} , hidden: ${item.loai == 1 ? ( item.type == '1' ? 'false' : 'true') : "true" } , type: ${item.type}, ${item.loai == 2 ? "color:'#7BE141'" : ""} },
                </c:forEach>
                ];
            
            
       
        
            <c:if test="${content != null}">
                var inputData = JSON.parse($('#jsonContent').text());
                var inputEdges = JSON.parse($('#jsonEdges').text());
                nodesData = getNodeData(inputData);
                edgesData =  getEdgeData(inputEdges);
            </c:if>
                
            var nodesDataset = new vis.DataSet(nodesData);
            var edgesDataset = new vis.DataSet(edgesData);
            
    </c:if>
    function redrawAll() {
        // create a network
        container = document.getElementById('mynetworkPrint');
       
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
                    centralGravity: 0.5,
                    // các kết nối thể hiện bằng các dây có độ co giãn, gọi là lò xo
                    // tham số này thể hiện độ dài của lò xo, càng cao thì các kết nối càng dài, các node cách xa nhau
                    springLength: 10,
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
            },
        };
        
        network = new vis.Network(container, data, options);

    };
    </c:if>

    var firtIdSearch = 0;
    
    $(document).ready(function () {
        <c:if test="${issetTopo == true}">
            redrawAll();
            var canvas = $("#mynetworkPrint canvas")[0];
            
            var button = document.getElementById('btn-download');
                button.addEventListener('click', function (e) {
                    
                var dataURL = canvas.toDataURL();
                button.href = dataURL;
                button.download = "${tinhBO.tenTinhTp}.png";
            });
        </c:if>
     
    });

</script>

