layui.use('flow', function () {
    var flow = layui.flow;

    flow.load({
        //加载的容器
        elem: '.messageBox',
        //距离底部多少距离自动刷新
        mb: 40,
        isAuto: true,
        end: "已经到底了",
        done: function (page, next) {
            var lis = [];
            if (page > 1) {
                var firstdate = $(".createTime:first").text();
            } else {
                var firstdate = "first";
            }

            setTimeout(function () {
                $.ajax({
                    url: "/getinvitationlist",
                    type: "post",
                    data: {
                        page: page,
                        createDate: firstdate
                    },
                    success: function (data) {
                        var result = typeof data == 'string' ? JSON.parse(data) : data;
                        if (result.resultCode == "1") {
                            var resultlist = result.result;
                            var i = 0;
                            for (i; i < resultlist.length; i = i + 1) {
                                if (resultlist[i].invitation.havePicture == 1) {
                                    //帖子有图片
                                    lis.push("<div class=\"message\" onclick=\"openinvitationwin()\">" +
                                        "<div class=\"messagePicture\">\n" +
                                        "<img src='" + resultlist[i].pictureList[0].url + "' alt='封面'>\n" +
                                        "</div>\n" +
                                        "<div class=\"messageTitle\">\n" +
                                        "<span>" + resultlist[i].invitation.title + "</span>\n" +
                                        "<br><span class=\"createTime\">" + resultlist[i].invitation.createDate + "</span>" +
                                        "</div>" +
                                        "</div>");
                                } else {
                                    //帖子无图片
                                    lis.push("<div class=\"message\" onclick=\"openinvitationwin()\" style=\"height: 15%\">\n" +
                                        "<div class=\"messageTitle\">\n" +
                                        "<span>" + resultlist[i].invitation.title + "</span>\n" +
                                        "<br><span class=\"createTime\">" + resultlist[i].invitation.createDate + "</span>" +
                                        "</div>\n" +
                                        "</div>\n" +
                                        "</div>");
                                }
                            }
                            next(lis.join(''), page < resultlist[0].pageCount);
                        } else {
                            alert(result.resultMessage);
                        }
                    }
                })
            }, 500);
        }
    });
});