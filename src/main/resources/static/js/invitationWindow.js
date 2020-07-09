function child(id, index) {
    $.ajax({
        url: "/getinvitation",
        type: "post",
        data: {
            id: id
        },
        success: function (data) {
            var result = typeof data == 'string' ? JSON.parse(data) : data;
            console.log(result.result);
        }
    })
}