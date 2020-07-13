function child(id, index) {
    $.ajax({
        url: "/getinvitation",
        type: "post",
        data: {
            id: id
        },
        success: function (data) {
            var result = typeof data == 'string' ? JSON.parse(data) : data;
            console.log(result);
            $(".userMininame span").text(result.result.user.miniName);
            $(".userId span").text("pid:" + result.result.user.id);
            $(".invitationTitle h1").text(result.result.invitation.title);
            $(".invitationMessage p").text(result.result.invitation.text);
            if (result.result.invitation.havePicture == 1) {
                var i = 0;
                for (i; i < result.result.pictureList.length; i = i + 1) {
                    $(".imagelist").append("<img src=\"" + result.result.pictureList[i].url + "\" alt=\"\"><br>");
                }
            }
        }
    })
}