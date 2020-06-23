function openInvitation() {
    layer.open({
        type: 2,
        resize: false, //静止改变窗口大小
        scrollbar: false, //禁止背景页面滚动
        area: ['80%', '90%'], //宽高
        closeBtn: 0, //取消关闭按钮
        shadeClose: true, //点击框外关闭弹窗
        move: false, //禁止移动弹窗
        title: false, //取消弹出层的标题
        content: 'invitation',
        end: function () {}
    })
}

// 以下两个方法用于改变回到顶部的位置
function invitationtop() {
    $("#topbutton").attr("href", "#invitationtop")
}

function commenttop() {
    $("#topbutton").attr("href", "#commenttop")
}