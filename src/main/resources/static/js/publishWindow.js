var imageunmber = 0;
var files;
var uploadInst = layui.upload.render({
    elem: '#fileupload',
    auto: false,
    choose: function (obj) {
        if (imageunmber < 5) {
            files = obj.pushFile(); //将每次选择的文件追加到文件队列
            obj.preview(function (index, file, result) {
                $('#showImage').append('<img id="image' + index +
                    '" src="' +
                    result + '" alt="' + file.name +
                    '" class="layui-upload-img">')
                // style="width: 200px;margin-left: 15px"
                // 点击图片进行删除
                $("#image" + index).bind("click", function () {
                    delete files[index];
                    $("#image" + index).remove();
                })

                // 重新加载css
                $("#image" + index).css("width", "200px");
                $("#image" + index).css("margin-left", "15px");
                $("#image" + index).hover(function () {
                    $("#image" + index).css("box-shadow", "3px 3px 3px black");
                })
                $("#image" + index).mouseout(function () {
                    $("#image" + index).css("box-shadow", "");
                })
            });
            imageunmber++;
        }
    }
});

//真正上传的逻辑，上面那个方法只是用来封装图片的
$("#publish").click(function () {
    var form = new FormData();
    for (let i in files) {
        form.append("file[]", files[i]);
    }
    form.append("title", $.trim($("#title").val()));
    form.append("textarea", $.trim($("#textarea").val()));
    $.ajax({
        url: "/publishinvation",
        type: "post",
        contentType: false,
        processData: false,
        async: true,
        data: form,
        success: function (data) {
            var jsondata = typeof data == 'string' ? JSON.parse(data) : data;
            var closebutton = false;
            var message = jsondata.resultMessage;
            var code = jsondata.resultCode;
            if (code == "-1") {
                closebutton = true;
            }
            layer.open({
                type: 0,
                resize: false, //静止改变窗口大小
                scrollbar: false, //禁止背景页面滚动
                area: ['30%', '40%'], //宽高
                closeBtn: 0, //取消关闭按钮
                move: false, //禁止移动弹窗
                title: false, //取消弹出层的标题
                btn: closebutton,//取消确认按钮，让确认框不能关闭
                content: message,
                end: function () {
                }
            });
        }
    });
})

