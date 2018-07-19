commonErrorAjax = function(xhr,textStatus,error){
    var t = 0;
    if(xhr.status != '901' ){
        t = t  + 1;
    }

    if( xhr.status != '902'){
        t = t  + 1;
    }

    if(t == 0){
        alert('Error when load ajax : ' + textStatus + ', ' + error);
    }
}
ajaxPaging = function(urlWeb, currentPage) {
    $.ajax({
//        url:'paging',// urlWeb,
//        url: 'http://localhost:8084/WelcomeSMS/'+urlWeb,
        url: urlWeb,
        data: {currentPage: currentPage}, // parameters
        beforeSend: function() {
//            $('#imageAjaxLoading').show();
        },
        complete: function() {
//            $('#imageAjaxLoading').hide();
        },
        error: function(xhr, textStatus, error) {
            commonErrorAjax(xhr,textStatus,error);
        },
        success: function(data) {
//            $('#tablePagingId').html("");
            $('#tablePagingId').html(data);        
        }
    });
}
