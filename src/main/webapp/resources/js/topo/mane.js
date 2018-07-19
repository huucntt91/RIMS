
'use strict';

var network;
var allNodes;
var highlightActive = false;
var options;
var container;
var data;



function neighbourhoodHighlight(params) {
    // if something is selected:
    if (params.nodes.length > 0) {
        highlightActive = true;
        var i, j;
        var selectedNode = params.nodes[0];
        var degrees = 2;

        // mark all nodes as hard to read.
        for (var nodeId in allNodes) {
            allNodes[nodeId].color = 'rgba(200,200,200,0.5)';
            if (allNodes[nodeId].hiddenLabel === undefined) {
                allNodes[nodeId].hiddenLabel = allNodes[nodeId].label;
                allNodes[nodeId].label = undefined;
            }
        }
        var connectedNodes = network.getConnectedNodes(selectedNode);
        var allConnectedNodes = [];

        // get the second degree nodes
        for (i = 1; i < degrees; i++) {
            for (j = 0; j < connectedNodes.length; j++) {
                allConnectedNodes = allConnectedNodes.concat(network.getConnectedNodes(connectedNodes[j]));
            }
        }

        // all second degree nodes get a different color and their label back
        for (i = 0; i < allConnectedNodes.length; i++) {
            allNodes[allConnectedNodes[i]].color = 'rgba(150,150,150,0.75)';
            if (allNodes[allConnectedNodes[i]].hiddenLabel !== undefined) {
                allNodes[allConnectedNodes[i]].label = allNodes[allConnectedNodes[i]].hiddenLabel;
                allNodes[allConnectedNodes[i]].hiddenLabel = undefined;
            }
        }

        // all first degree nodes get their own color and their label back
        for (i = 0; i < connectedNodes.length; i++) {
            allNodes[connectedNodes[i]].color = undefined;
            if (allNodes[connectedNodes[i]].hiddenLabel !== undefined) {
                allNodes[connectedNodes[i]].label = allNodes[connectedNodes[i]].hiddenLabel;
                allNodes[connectedNodes[i]].hiddenLabel = undefined;
            }
        }

        // the main node gets its own color and its label back.
        allNodes[selectedNode].color = undefined;
        if (allNodes[selectedNode].hiddenLabel !== undefined) {
            allNodes[selectedNode].label = allNodes[selectedNode].hiddenLabel;
            allNodes[selectedNode].hiddenLabel = undefined;
        }
    } else if (highlightActive === true) {
        // reset all nodes
        for (var nodeId in allNodes) {
            allNodes[nodeId].color = undefined;
            if (allNodes[nodeId].hiddenLabel !== undefined) {
                allNodes[nodeId].label = allNodes[nodeId].hiddenLabel;
                allNodes[nodeId].hiddenLabel = undefined;
            }
        }
        highlightActive = false;
    }

    // transform the object into an array
    var updateArray = [];
    for (nodeId in allNodes) {
        if (allNodes.hasOwnProperty(nodeId)) {
            updateArray.push(allNodes[nodeId]);
        }
    }
    nodesDataset.update(updateArray);
}
;

window.autoArrange = function () {
    options = {
        physics: {
            enabled: false
        },
        layout: {
            randomSeed: undefined
        }
    };
    network = new vis.Network(container, data, options);
};

window.togglePhysic = function () {
    options = {
        physics: {
            enabled: false
        }
    };
    network = new vis.Network(container, data, options);
};

function objectToArray(obj) {
    return Object.keys(obj).map(function (key) {
        //console.log(obj[key]);
        obj[key].id = key;
        return obj[key];
    });
}

function addConnections(elem, index) {
    // need to replace this with a tree of the network, then get child direct children of the element
    elem.connections = network.getConnectedNodes(index);
}
function getNodeData(data) {
    var networkNodes = [];

    nodesData.forEach(function (item, i) {
        var flag = false;
        var nodeVisi = false;
        var size = 20;
        if (item.topo == 1)
            size = 80;
        if (item.topo == 2)
            size = 40;
        if (item.topo == 3)
            size = 60;
        if (!$('#topo_access').is(":checked"))
        {
            //if (item.topo == 4 || item.topo == 5 || item.topo == 6 || item.topo == 13) { // cac type id thuoc access
            if (item.topo > 3) {
                nodeVisi = true;
            }
            // it is checked
        }
        data.forEach(function (elem, index, array) {
            if (elem.id == item.id) {

                networkNodes.push({id: elem.id, label: item.label, x: elem.x, y: elem.y, shape: 'image', image: item.image, hidden: nodeVisi, topo: item.topo, chaId: item.chaId, size: size, ip: item.ip});
                flag = true;
            }
        });
        if (!flag) {
            networkNodes.push({id: item.id, label: item.label, shape: 'image', image: item.image, hidden: nodeVisi, topo: item.topo, chaId: item.chaId, size: item.size, ip: item.ip});
        }
        //networkNodes.push({id: elem.id, label: elem.id, x: elem.x, y: elem.y});
    });

    return networkNodes;
}
function getEdgeData(data) {
    var networkEdges = [];


    edgesData.forEach(function (item, i) {
        var flag = false;
        //var edgesVisi = checkLinkVisi(item.from, item.to);
        var edgesVisi = item.loai == 1 ? (item.type == 1 || $('#topo_access').is(":checked") ? false : true) : true;
        data.forEach(function (elem, index, array) {
            if (elem.loai == null) {
                elem.loai = 1;
            }
            if (elem.tid == item.tid && elem.loai == item.loai) {
                if (elem.smooth.type != undefined)
                {
                    networkEdges.push({id: item.id, tid: item.tid, loai: item.loai, from: item.from, to: item.to, label: item.label, smooth: {roundness: elem.smooth.roundness, type: elem.smooth.type}, font: {align: 'middle'}, hidden: edgesVisi, type: item.type, color: item.color});
                } else
                    networkEdges.push({id: item.id, tid: item.tid, loai: item.loai, from: item.from, to: item.to, label: item.label, smooth: {roundness: elem.smooth.roundness, type: 'curvedCW'}, font: {align: 'middle'}, hidden: edgesVisi, type: item.type, color: item.color});
                flag = true;
            }
        });
        if (!flag) {
            networkEdges.push({id: item.id, tid: item.tid, loai: item.loai, from: item.from, to: item.to, label: item.label, smooth: {roundness: getRndInteger(-5, 5), type: 'curvedCW'}, font: {align: 'middle'}, hidden: edgesVisi, type: item.type, color: item.color});
        }
        //networkNodes.push({id: elem.id, label: elem.id, x: elem.x, y: elem.y});
    });

    return networkEdges;
}

function updateVisiAcess(hidden, chaId) {
    if (hidden) {
        //hien acccess len
        nodesData.forEach(function (item, i) {
            if (chaId == "") {
                //nodesDataset.update({id: item.id, hidden: false});
                nodesData[i].hidden = false;
            } else
            {
                if (item.chaId.indexOf("," + chaId + ",") > -1) {
                    //nodesDataset.update({id: item.id, hidden: false});
                    nodesData[i].hidden = false;
                }
            }
        });
        // update luon de o duoi con duyet link an hay hien
        nodesDataset.update(nodesData);

        edgesData.forEach(function (item, i) {
            if (item.loai == 1) {
                if (chaId == "") {
                    edgesData[i].hidden = false;
                    //edgesDataset.update({id: item.id, hidden: false});
                } else {
                    var edgesVisi = checkLinkVisi(item.from, item.to);
                    edgesData[i].hidden = edgesVisi;
                    //var edgesVisi = item.type == 1 ? false : true;
                    //edgesDataset.update({id: item.id, hidden: edgesVisi});
                }
            }
        });
    } else {
        // an access di
        nodesData.forEach(function (item, i) {
            if (item.topo > 3) {
                if (chaId == "") {
                    //nodesDataset.update({id: item.id, hidden: true});
                    nodesData[i].hidden = true;
                } else
                {
                    if (item.chaId.indexOf("," + chaId + ",") > -1) {
                        //nodesDataset.update({id: item.id, hidden: true});
                        nodesData[i].hidden = true;
                    }
                }
            }
        });

        // update luon de o duoi con duyet link an hay hien
        nodesDataset.update(nodesData);
        edgesData.forEach(function (item, i) {
            if (item.loai == 1) {
                var edgesVisi = checkLinkVisi(item.from, item.to);
                edgesData[i].hidden = edgesVisi;
                //var edgesVisi = item.type == 1 ? false : true;
                //edgesDataset.update({id: item.id, hidden: edgesVisi});
            }
        });
    }
    edgesDataset.update(edgesData);
}

function checkLinkVisi(from, to)
{
    var flag = false;
    var nodeTemp = nodesData;
    if (undefined != nodesDataset) {
        nodeTemp = objectToArray(nodesDataset._data);
        //console.log(nodesData);
    }
    nodeTemp.forEach(function (item, i) {
        if (item.hidden == true)
        {
            if (from == item.id) {
                flag = true;
            }
        }
    });
    nodeTemp.forEach(function (item, i) {
        if (item.hidden == true)
        {
            if (to == item.id) {
                flag = true;
            }
        }

    });
    return flag;
}

function getNodeById(data, id) {
    for (var n = 0; n < data.length; n++) {
        if (data[n].id == id) {  // double equals since id can be numeric or string
            return data[n];
        }
    }
    ;

    throw 'Can not find id \'' + id + '\' in data';
}
function getRndInteger(min, max) {

    return (Math.floor(Math.random() * (max - min + 1)) + min) / 10;

}

function getRnd(min, max) {

    return (Math.floor(Math.random() * (max - min + 1)) + min);

}
//redrawAll();
function showAlert(content, flag) {
//    $("#success-alert").alert();
//    window.setTimeout(function () {
//        $("#lbAlert").text(content);
//        $("#success-alert").alert('close');
//    }, 2000);
    $("#lbAlert").text(content);
    if (flag)
    {
        $("#success-alert").attr('class', 'alert alert-success');
    } else
        $("#success-alert").attr('class', 'alert alert-danger');
    $("#success-alert").fadeTo(2000, 500).slideUp(500, function () {
        $("#success-alert").slideUp(500);
    });
}

function boDau(strConvert2AnSi) {
    strConvert2AnSi = strConvert2AnSi.replaceAll("Đ", "D");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Đ", "D");
    strConvert2AnSi = strConvert2AnSi.replaceAll("đ", "d");
    strConvert2AnSi = strConvert2AnSi.replaceAll("đ", "d");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Đ", "D");
    strConvert2AnSi = strConvert2AnSi.replaceAll("đ", "d");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Á", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("á", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("À", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("à", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ã", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ã", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ả", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ả", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ạ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ạ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ạ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Â", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("â", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ấ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ấ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ầ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ầ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẫ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẫ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẩ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẩ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ậ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ậ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ă", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ă", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ắ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ắ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ằ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ằ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẵ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẵ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẳ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẳ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ặ", "A");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ặ", "a");
    strConvert2AnSi = strConvert2AnSi.replaceAll("É", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("é", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("È", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("è", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẽ", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẽ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẻ", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẻ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ẹ", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẹ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ẹ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ê", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ê", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ế", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ế", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ề", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ề", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ễ", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ễ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ể", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ể", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ệ", "E");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ệ", "e");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Í", "I");
    strConvert2AnSi = strConvert2AnSi.replaceAll("í", "i");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ì", "I");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ì", "i");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ĩ", "I");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ĩ", "i");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỉ", "I");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỉ", "i");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ị", "I");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ị", "i");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ó", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ó", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ò", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ò", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Õ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("õ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỏ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỏ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ọ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ọ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ô", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ô", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ố", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ố", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ồ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ồ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỗ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỗ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ổ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ổ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ộ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ộ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ơ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ơ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ớ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ớ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ờ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ờ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỡ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỡ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ở", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ở", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ợ", "O");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ợ", "o");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ú", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ú", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ù", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ù", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ũ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ũ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ủ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ủ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ụ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ụ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ư", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ư", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ứ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ứ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ừ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ừ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ữ", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ữ", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ử", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ử", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ự", "U");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ự", "u");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ý", "Y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ý", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỳ", "Y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỳ", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỹ", "Y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỹ", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỷ", "Y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỷ", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("Ỵ", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("ỵ", "y");
    strConvert2AnSi = strConvert2AnSi.replaceAll("̀", "");
    strConvert2AnSi = strConvert2AnSi.replaceAll("̃", "");
    strConvert2AnSi = strConvert2AnSi.replaceAll("̉", "");
    strConvert2AnSi = strConvert2AnSi.replaceAll("́", "");
    strConvert2AnSi = strConvert2AnSi.replaceAll("̣", "");
    return strConvert2AnSi;
}
String.prototype.replaceAll = function (search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

function info() {
//    if($('#noteTopo').is(":visible")){
//        $('#noteTopo').show();
//    }else{
//        $('#noteTopo').hide();
//    }
    $('#noteTopo').toggle("slow");
}

function configTypeId(typeId) {
    if (typeId == 1)
        return 'AGG';
    else if (typeId == 2)
        return 'UPE';
    else if (typeId == 3)
        return 'NPE';
    else if (typeId == 4)
        return 'DSLAM';
    else if (typeId == 5)
        return 'SWITCH';
    else if (typeId == 6)
        return 'GPON';
    else if (typeId == 7)
        return 'P ROUTER';
    else if (typeId == 8)
        return 'PE';
    else if (typeId == 9)
        return 'ASBR';
    else if (typeId == 10)
        return 'NIX';
    else if (typeId == 11)
        return 'BRAS';
    else if (typeId == 12)
        return 'CPE';
    else if (typeId == 13)
        return 'MxU';
    else
        return '';
}


function getPositionAcess(location, truc, type) { //  = 'x', ='y'
    if (location == 1) { // Left tinh A
        if (truc == 'x')
            return getRnd(-750, -50);
        else
            return getRnd(200, 500);
    } else { // Righ tinh B
        if (truc == 'x')
            return getRnd(50, 750);
        else
            return getRnd(200, 500);
    }
}

function getPositionManE(location, truc, type) { //  = 'x', ='y'
    if (location == 1) { // Left tinh A
        if (truc == 'x')
        {
            return getRnd(-750, -100);
        } else {
            if (type == 1) // AGG
                return getRnd(-500, -600);
            else
                return getRnd(-100, -400);
        }
    } else { // Righ tinh B
        if (truc == 'x')
            return getRnd(100, 750);
        else
        if (type == 1) // AGG
            return getRnd(-500, -600);
        else
            return getRnd(-100, -400);
    }

}

function getPositionVN2(location, truc, type) { //  = 'x', ='y'
    if (location == 1) { // Left tinh A
        if (truc == 'x')
            return -200;
        else
            return -900;
    } else { // Righ tinh B
        if (truc == 'x')
            return 200;
        else
            return -900;
    }
}

function getPotionNodeData(tnode1, tnode2) {
    var networkNodes = [];
    var location = 1; // Tinh A
    var tinhA = '';
    var tinhB = '';
    var x = 0, y = 0;
    nodesData.forEach(function (item, index) {
        var size = 20;
        if (item.topo == 1 || item.topo == 7 || item.topo == 8)
            size = 80;
        if (item.topo == 2)
            size = 40;
        if (item.topo == 3)
            size = 60;
        if (item.id == 8888888 || item.id == 9999999) {
            size = 60;
        }
        if (index == 0)
            tinhA = item.maTinh.trim();
        if (tinhA == item.maTinh.trim())
            location = 1; // Tinh A
        else {
            location = 2;
            tinhB = item.maTinh.trim();
        }
        if (item.topo == 1 || item.topo == 2 || item.topo == 3) // manE
        {
            x = getPositionManE(location, 'x', item.topo);
            y = getPositionManE(location, 'y', item.topo);
        }
        if (item.topo == 4 || item.topo == 5 || item.topo == 6 || item.topo == 12 || item.topo == 13) // access
        {
            x = getPositionAcess(location, 'x', item.topo);
            y = getPositionAcess(location, 'y', item.topo);
        }
        if (item.topo == 7 || item.topo == 8 || item.topo == 9 || item.topo == 10 || item.topo == 11) // VN2
        {
            x = getPositionVN2(location, 'x', item.topo);
            y = getPositionVN2(location, 'y', item.topo);
            //console.log("x" + x);
            //console.log("y" + y);
        }

        if (item.id == tnode1 || item.id == tnode2) {
            networkNodes.push({id: item.id, label: item.label, font: {color: 'red'}, tinhTpId: item.tinhTpId, x: x, y: y, shape: 'image', image: item.image, topo: item.topo, chaId: item.chaId, size: size, ip: item.ip});
        } else {
            networkNodes.push({id: item.id, label: item.label, tinhTpId: item.tinhTpId, maTinh: item.maTinh, x: x, y: y, shape: 'image', image: item.image, topo: item.topo, chaId: item.chaId, size: size, ip: item.ip});
        }

    });
    // add truc OX và OY
    networkNodes.push({id: 111111, x: -1250, y: 0});
    networkNodes.push({id: 222222, x: 1250, y: 0});
    networkNodes.push({id: 333333, x: 0, y: -1250});
    networkNodes.push({id: 444444, x: 0, y: 1250});
    networkNodes.push({id: 555555, x: -1250, y: -750});
    networkNodes.push({id: 666666, x: 1250, y: -750});
    networkNodes.push({id: 111222, x: -1250, y: 700, label: "ACCESS " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 222111, x: 1250, y: 700, label: "ACCESS " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 111333, x: -1250, y: -400, label: "MANE " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 333111, x: 1250, y: -400, label: "MANE " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 111444, x: -1250, y: -1200, label: "VN2 " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 444111, x: 1250, y: -1200, label: "VN2 " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    edgesData.push({id: 111111, from: 111111, to: 222222, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    edgesData.push({id: 222222, from: 333333, to: 444444, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    edgesData.push({id: 333333, from: 555555, to: 666666, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    return networkNodes;
}


function getPotionNodeDataVPN(tnode1, tnode2, dir) {
    var networkNodes = [];
    var location = 1; // Tinh A
    var tinhA = '';
    var tinhB = '';
    var x = 0, y = 0;
    // draw cloud vpn L3
    networkNodes.push({id: 777777, x: 0, y: -1200, label: 'VPN L3', shape: 'image', image: dir + 'cloud_vpn.png', size: 350});

    nodesData.forEach(function (item, index) {
        var size = 20;
        if (item.topo == 1 || item.topo == 7 || item.topo == 8)
            size = 80;
        if (item.topo == 2)
            size = 40;
        if (item.topo == 3)
            size = 60;
        if (item.id == 8888888 || item.id == 9999999) {
            size = 60;
        }
        if (index == 0)
            tinhA = item.maTinh.trim();
        if (tinhA == item.maTinh.trim())
            location = 1; // Tinh A
        else {
            location = 2;
            tinhB = item.maTinh.trim();
        }
        if (item.topo == 1 || item.topo == 2 || item.topo == 3) // manE
        {
            x = getPositionManE(location, 'x', item.topo);
            y = getPositionManE(location, 'y', item.topo);
        }
        if (item.topo == 4 || item.topo == 5 || item.topo == 6 || item.topo == 12 || item.topo == 13) // access
        {
            x = getPositionAcess(location, 'x', item.topo);
            y = getPositionAcess(location, 'y', item.topo);
        }
        if (item.topo == 7 || item.topo == 8 || item.topo == 9 || item.topo == 10 || item.topo == 11) // VN2
        {
            x = getPositionVN2(location, 'x', item.topo);
            y = getPositionVN2(location, 'y', item.topo);
            //console.log("x" + x);
            //console.log("y" + y);
        }

        if (item.id == tnode1 || item.id == tnode2) {
            networkNodes.push({id: item.id, label: item.label, font: {color: 'red'}, tinhTpId: item.tinhTpId, x: x, y: y, shape: 'image', image: item.image, topo: item.topo, chaId: item.chaId, size: size, ip: item.ip});
        } else {
            networkNodes.push({id: item.id, label: item.label, tinhTpId: item.tinhTpId, maTinh: item.maTinh, x: x, y: y, shape: 'image', image: item.image, topo: item.topo, chaId: item.chaId, size: size, ip: item.ip});
        }

    });
    // add truc OX và OY
    networkNodes.push({id: 111111, x: -1250, y: 0});
    networkNodes.push({id: 222222, x: 1250, y: 0});
    networkNodes.push({id: 333333, x: 0, y: -750});
    networkNodes.push({id: 444444, x: 0, y: 1250});
    networkNodes.push({id: 555555, x: -1250, y: -750});
    networkNodes.push({id: 666666, x: 1250, y: -750});


    networkNodes.push({id: 111222, x: -1250, y: 700, label: "ACCESS " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 111444, x: -1250, y: -1200, label: "VN2 " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    networkNodes.push({id: 111333, x: -1250, y: -400, label: "MANE " + tinhA, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    if (tinhB != '') {
        networkNodes.push({id: 333111, x: 1250, y: -400, label: "MANE " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
        networkNodes.push({id: 222111, x: 1250, y: 700, label: "ACCESS " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
        networkNodes.push({id: 444111, x: 1250, y: -1200, label: "VN2 " + tinhB, color: "#FFCFCF", shape: 'box', font: {face: 'monospace', align: 'left', size: 100}});
    }
    edgesData.push({id: 111111, from: 111111, to: 222222, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    edgesData.push({id: 222222, from: 333333, to: 444444, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    edgesData.push({id: 333333, from: 555555, to: 666666, color: '990404', smooth: {roundness: 0, type: "curvedCW"}});
    return networkNodes;
}


