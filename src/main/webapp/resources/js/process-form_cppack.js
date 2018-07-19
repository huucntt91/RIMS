//// Pagination for table

$(document).ready(
        function() {
            $('#table-data').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 25,
                destroy: true
            });

            $('#table-data-1').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 25,
                destroy: true
            });
            $('#table-data-2').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 25,
                destroy: true
            });
            $('#table-data-3').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 25,
                destroy: true
            });

            $('#table-data-ps').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 25,
                destroy: true
            });

            $('#table-data-karaoke').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }],
                "order": [[1, "asc"]],
                destroy: true
            });

            $('#table-data-live').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }],
                "order": [[1, "asc"]],
                destroy: true
            });

            $('#table-data-vod').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }],
                "order": [[1, "asc"]],
                destroy: true
            });

            $('#table-data-live-detail').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "order": [[2, "asc"]],
                destroy: true
            });

            $('#table-data-vod-detail').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "order": [[2, "asc"]],
                destroy: true
            });

            $('#table-data-karaoke-detail').DataTable({
                "pagingType": "full_numbers",
                "pageLength": 50,
                "order": [[2, "asc"]],
                destroy: true
            });

            $('#table-data-live-detail tbody').on('click', 'tr', function() {
                $(this).toggleClass('selected');
            });

            $('#table-data-karaoke-detail tbody').on('click', 'tr', function() {
                $(this).toggleClass('selected');
            });

            $('#table-data-vod-detail tbody').on('click', 'tr', function() {
                $(this).toggleClass('selected');
            });

            $('#table-data-ps tbody').on('click', 'tr', function() {
                $(this).toggleClass('selected');
            });
        }
);
// change select box to hide show table package service add
$(document).ready(function() {
    $(".servSelect").change(
            function() {
                $(this).find("option:selected").each(
                        function() {
                            // vod
                            if ($(this).attr("value") === "0") {
                                $(".boxc").not(".vod").hide();
                                $(".vod").show();
                                $('input[name=cgroup]').prop('checked', false);
                            } // karaoke
                            else if ($(this).attr("value") === "2") {
                                $(".boxc").not(".karaoke").hide();
                                $(".karaoke").show();
                                $('input[name=cgroup]').prop('checked', false);
                            } // live
                            else if ($(this).attr("value") === "10") {
                                $(".boxc").not(".live").hide();
                                $(".live").show();
                                $('input[name=cgroup]').prop('checked', false);
                            } else {
                                $(".boxc").hide();
                            }
                        });
            }).change();
    $(".live").hide();
    $(".karaoke").hide();
    $(".vod").hide();

});

// check all karaoke
//$(document).on("click", ".paginate_button", function () {
//    var selected = [];
//    $('input[name=list]:checked').each(function () {
//        selected.push($(this).attr('name'));
//    });
//    if (selected.length === 50) {
//        $("#checkAll").prop('checked', true);
//    } else {
//        $("#checkAll").prop('checked', false);
//    }
//});

// confirm add content vod for content group
$('#getAllCheckboxVod').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
    if (check.length === 0) {
        $("#err_message").text("Không có nội dung nào được ch�?n");
        $("#err_message").css('color', 'red');
    } else {
        var i;
        var result = "";
        for (i = 0; i < check.length; i++) {
            result += "-" + check[i][0];
        }
        var action = $("#formAddContent").attr("action");

        $("#contentList").val(result.substring(1, result.length));
        $("#formAddContent").submit();
        $("#formAddContent").attr("action", action);
    }
});

// confirm add content vod for content group
$('#getAllCheckboxLive').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
//    if (check
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    var action = $("#formAddContent").attr("action");

    $("#contentList").val(result.substring(1, result.length));
    $("#formAddContent").submit();
    $("#formAddContent").attr("action", action);
});

// confirm add content vod for content group
$('#getAllCheckboxKaraoke').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    var action = $("#formAddContent").attr("action");

    $("#contentList").val(result.substring(1, result.length));
    $("#formAddContent").submit();
    $("#formAddContent").attr("action", action);
});
// delete all live detail
$('#delete_all_ps').click(function() {
    var oTable = $('#table-data-ps').DataTable();
//    alert(oTable.rows('.selected').data()[0][0]);
    var check = oTable.rows('.selected').data();
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    if (check.length === 0) {
        alert("Không bản ghi nào được ch�?n");
    } else {
        result = result.substring(1, result.length);

        var link = "/CMS/package-service/delete-all-ps?ids=" + result;
        window.location = link;
    }

});

// delete all live detail
$('#delete_all_live').click(function() {
    var oTable = $('#table-data-live-detail').DataTable();
//    alert(oTable.rows('.selected').data()[0][0]);
    var check = oTable.rows('.selected').data();
    var i;
    var result = "";
    result += check[0][0];
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][1];
    }
    if (check.length === 0) {
        alert("Không bản ghi nào được ch�?n");
    } else {
        var link = "/CMS/content/delete-all-cg-live?ids=" + result;
        window.location = link;
    }

});

// delete all live detail
$('#delete_all_karaoke').click(function() {
    var oTable = $('#table-data-karaoke-detail').DataTable();
//    alert(oTable.rows('.selected').data()[0][0]);
    var check = oTable.rows('.selected').data();
    var i;
    var result = "-";
    result += check[0][0];
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][1];
    }

    if (check.length === 0) {
        alert("Không bản ghi nào được ch�?n");
    } else {
        result = result.substring(1, result.length);

        var link = "/CMS/content/delete-all-cg-karaoke?ids=" + result;
        window.location = link;
    }

});

// delete all live detail
$('#delete_all_vod').click(function() {
    var oTable = $('#table-data-vod-detail').DataTable();
//    alert(oTable.rows('.selected').data()[0][0]);
    var check = oTable.rows('.selected').data();
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    if (check.length === 0) {
        alert("Không bản ghi nào được ch�?n");
    } else {
        result = result.substring(1, result.length);

        var link = "/CMS/content/delete-all-cg-vod?ids=" + result;
        window.location = link;
    }

});

// Code goes here

$(document).ready(function() {
    var selected = [];
    var selected2 = [];
    var stockTable = $('#table-source')
            .DataTable({
                "pagingType": "full_numbers",
                "pageLength": 10,
                "bLengthChange": false,
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }],
                "order": [[1, "asc"]],
                destroy: true,
                "bPaginate": true,
                bFilter: true
            }); // first table 
    $('#table-source tbody').on('click', 'tr', function() {
        var id = this.id;
        var index = $.inArray(id, selected);

        if (index === -1) {
            selected.push(id);
        } else {
            selected.splice(index, 1);
        }

        $(this).toggleClass('selected');
    });
    var catalogTable = $('#table-dest').DataTable({
        "pagingType": "full_numbers",
        "pageLength": 20,
        "aoColumnDefs": [{
                'bSortable': false,
                'aTargets': [0]
            }],
        "order": [[1, "asc"]],
        destroy: true,
        "bPaginate": true,
        bFilter: true,
        "bSort" : false
       
    }); // Second table 
//    stockTable.on('click', 'tbody tr', function () {
//        $(this).toggleClass('selected');
//    });
//    catalogTable.on('click', 'tbody tr', function () {
//        $(this).toggleClass('selected');
//    });
    $('#table-dest tbody').on('click', 'tr', function() {
//        check status
        var oTable = $('#table-dest').DataTable();
        var data = oTable.row(this).data();

        if (data[4] == "Hết hiệu lực" || data[4] == "Đang hoạt động"
                || data[4] == "Khởi tạo") {
            return;
        }
//        end check status        
        var id = this.id;
        var index = $.inArray(id, selected2);

        if (index === -1) {
            selected2.push(id);
        } else {
            selected2.splice(index, 1);
        }


        $(this).toggleClass('selected');
    });
    $('#removeRows').click(function() {
        moveRows(catalogTable, stockTable);

    });

    $('#addRows').click(function() {
        moveRows(stockTable, catalogTable);
    });
});
//
function moveRows(fromTable, toTable) {
    var rows = fromTable.rows('.selected').data();
    var destination = toTable.rows().data();
    var i, j;
    for (i = 0; i < rows.length; i++) {
        var check = true;
//        for (j = 0; j < destination.length; j++) {
//            if (rows[i][0] === destination[j][0]) {
//                check = false;
//            }
//        }
        if (check === true) {
            toTable.row.add(rows[i]).draw();
        }
    }
    var deleteRows = fromTable.rows('.selected');
    deleteRows.remove().draw();
}

// select all content
$('#selectPage1').click(function() {
    var table = $('#table-source').DataTable();
//    var p = table.rows({"filter": "applied"}).nodes();
    var p = table.rows({page: 'current'}).nodes();
    $(p).toggleClass('selected');
});
// delect all content
$('#selectPage2').click(function() {
    var table = $('#table-dest').DataTable();
//    var p = table.rows({"filter": "applied"}).nodes();
    var p = table.rows({page: 'current'}).nodes();
    $(p).toggleClass('selected');
});


$('#CheckAllKaraDetail').click(function() {
    var table = $('#table-data-karaoke-detail').DataTable();
//    var p = table.rows({"filter": "applied"}).nodes();
    var p = table.rows({page: 'current'}).nodes();
    $(p).toggleClass('selected');
});
$('#CheckAllLiveDetail').click(function() {
    var table = $('#table-data-live-detail').DataTable();
//    var p = table.rows({"filter": "applied"}).nodes();
    var p = table.rows({page: 'current'}).nodes();
    $(p).toggleClass('selected');
});
$('#CheckAllVodDetail').click(function() {
    var table = $('#table-data-vod-detail').DataTable();
//    var p = table.rows({"filter": "applied"}).nodes();
    var p = table.rows({page: 'current'}).nodes();
    $(p).toggleClass('selected');
});


// confirm add content vod for content group
//$('#testAll').click(function() {
//    var oTable = $('#table-dest').DataTable();
//    var check = oTable.rows().data();
//    var i;
//    var result = "";
//    for (i = 0; i < check.length; i++) {
//        var obj = check[i][2];
////        var obj = $("#sl");
//        var status = $(":selected", obj).val();
//        result += "-" + check[i][1] + "/" + status;
//    }
//    alert(result.substring(1, result.length));
//
//});
$('#addZoneToCampaign').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
//    if (check.length === 0) {
//        $("#err_message").text("Không có nội dung nào được ch�?n");
//        $("#err_message").css('color', 'red');
//    } else {
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    $("#contentList").val(result.substring(1, result.length));

    var urlWeb = $('#action').val();
//        alert(urlWeb)
    var info = [];
    info[0] = $('#id').val();
    info[1] = $("#contentList").val();
    $.ajax({
        type: "GET",
        url: urlWeb,
        dataType: "xml",
        data: {id: info[0],
            contentList: info[1]},
        success: function(data) {
            var code = "";
            $(data).find("responseCode").each(function() {
                code = code + $(this).text() + "";
            });
            if (code == '01') {
                alert("Có lỗi trong quá trình xử lý. Vui lòng thử lại");
            } else if (code == '00') {
                alert("Xử lý thành công");
            }
        },
        error: function(result) {
            alert('error_ajax_xml');
            // do something.
        }
    });
});



$('#btnSubmitCampaignContact').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
//    if (check.length === 0) {
//        $("#err_message").text("Không có nội dung nào được ch�?n");
//        $("#err_message").css('color', 'red');
//    } else {
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        result += "-" + check[i][0];
    }
    $("#contentList").val(result.substring(1, result.length));
    var urlWeb = $('#action').val();
    var info = [];
    info[0] = $('#id').val();
    info[1] = $("#contentList").val();
    $.ajax({
        type: "GET",
        url: urlWeb,
        dataType: "xml",
        data: {id: info[0],
            contentList: info[1]},
        success: function(data) {
            // do something.
            var code = "";
            $(data).find("responseCode").each(function() {
                code = code + $(this).text() + "";
            });
            if (code == '01') {
                alert("Có lỗi trong quá trình xử lý. Vui lòng thử lại");
            } else if (code == '00') {
                alert("Xử lý thành công");
            }
        },
        error: function(result) {
            alert('error_ajax_xml');
            // do something.
        }
    });
});



$('#btnAddPackageToCP').click(function() {
    var oTable = $('#table-dest').DataTable();
    var check = oTable.rows().data();
//    if (check.length === 0) {
//        $("#err_message").text("Không có nội dung nào được ch�?n");
//        $("#err_message").css('color', 'red');
//    } else {
    var i;
    var result = "";
    for (i = 0; i < check.length; i++) {
        if (check[i][4] == "Hết hiệu lực" || check[i][4] == "Đang hoạt động") {

        } else {
            result += "-" + check[i][0];
        }
    }
    var price = "";
    for (i = 0; i < check.length; i++) {
        if (check[i][4] == "Hết hiệu lực" || check[i][4] == "Đang hoạt động") {

        } else {
            price += "-" + check[i][2];
        }
    }

//    var action = $("#formAddContent").attr("action");
//    alert(action)

    $("#contentList").val(result.substring(1, result.length));
    $("#priceList").val(price.substring(1, result.length));
//    $("#formAddContent").submit();
//        $("#formAddContent").attr("action", action);
//    }
//        var ctx = <%=request.getContextPath()%>;

    var urlWeb = $('#action').val();
//        alert(urlWeb)
    var info = [];
    info[0] = $('#id').val();
    info[1] = $("#contentList").val();
    info[2] = $("#priceList").val();

    $.ajax({
        type: "GET",
        url: urlWeb,
        dataType: "xml",
        data: {id: info[0],
            contentList: info[1],
            priceList: info[2]},
        success: function(data) {
            // do something.

            var code = "";
            $(data).find("responseCode").each(function() {
                code = code + $(this).text() + "";
            });
            if (code == '01') {
                alert("Có lỗi trong quá trình xử lý. Vui lòng thử lại");
            } else if (code == '00') {
                alert("Xử lý thành công");
            }
        },
        error: function(result) {
            alert('error_ajax_xml');
            // do something.
        }
    });
});
