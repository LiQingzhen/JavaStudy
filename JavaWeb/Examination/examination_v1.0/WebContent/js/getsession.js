function GetMsg() {
    var arr = new Array();
    $.ajax({
        url: '../../../asmx/Login.asmx/GetSession',
        type: 'post',
        contentType: 'application/json',
        data: null,
        async: false,
        success: function (data) {
            $.each(data.d, function (index, e) {
                arr[0] = e.uid;
                arr[1] = e.rid;
                arr[2] = e.username;
            })
        }
    })

    return arr;
}
    