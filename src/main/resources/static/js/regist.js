var flag = true;

function registsub() {
    var mininame = $.trim($("#mininame").val());
    var email = $.trim($("#email").val());
    var passwd = $.trim($("#passwd").val());
    var repasswd = $.trim($("#repasswd").val());

    if (mininame == "") {
        $("#mininame").css("border", "2px solid red");
        $(".mininameMessage").css("visibility", "visible");
        flag = false;
    } else {
        $("#mininame").css("border", "");
        $(".mininameMessage").css("visibility", "hidden");
    }

    if (email == "") {
        $("#email").css("border", "2px solid red");
        $(".emailMessage").css("visibility", "visible");
        flag = false;
    } else {
        $("#email").css("border", "");
        $(".emailMessage").css("visibility", "hidden");
    }

    if (passwd == "") {
        $("#passwd").css("border", "2px solid red");
        $(".passwdMessage").css("visibility", "visible");
        flag = false;
    } else {
        $("#passwd").css("border", "");
        $(".passwdMessage").css("visibility", "hidden");
    }

    if (repasswd == "") {
        $("#repasswd").css("border", "2px solid red");
        $(".repasswdMessage").css("visibility", "visible");
        flag = false;
    } else {
        $("#repasswd").css("border", "");
        $(".repasswdMessage").css("visibility", "hidden");
    }

    if (passwd == repasswd) {
        if (flag) {
            $.ajax({
                url: "/regist",
                type: "post",
                dataType: "text",
                data: {
                    mininame: mininame,
                    email: email,
                    passwd: passwd
                },
                success: function (data) {
                    var message = JSON.parse(data);
                    if (message.resultCode == "1") {
                        window.location.href = "initlogin";
                    } else {
                        alert(message.resultMessage);
                    }
                },
                error: function () {
                    alert("注册失败");
                }
            });
        }
    } else {
        alert("两次输入的密码不一致")
    }
}

$(document).ready(function () {
    $("#mininame").change(function () {
        var mininame = $.trim($("#mininame").val());
        $.ajax({
            url: "/registcheck",
            type: "post",
            dataType: "text",
            data: {
                type: "mininame",
                message: mininame
            },
            success: function (data) {
                var message = JSON.parse(data);
                if (message.resultCode == "-1") {
                    $("#mininame").css("border", "2px solid red");
                    $(".mininameMessage span").text(message.resultMessage);
                    $(".mininameMessage").css("visibility", "visible");
                    flag = false;
                }else {
                    $("#mininame").css("border", "");
                    $(".mininameMessage span").text("请输入昵称");
                    $(".mininameMessage").css("visibility", "hidden");
                    flag = true;
                }
            }
        })
    });
});

$(document).ready(function () {
    $("#email").change( function () {
        var email = $.trim($("#email").val());
        $.ajax({
            url: "/registcheck",
            type: "post",
            dataType: "text",
            data: {
                type: "email",
                message: email
            },
            success: function (data) {
                var message = JSON.parse(data);
                if (message.resultCode == "-1") {
                    $("#email").css("border", "2px solid red");
                    $(".emailMessage span").text(message.resultMessage);
                    $(".emailMessage").css("visibility", "visible");
                    flag = false;
                }else {
                    $("#email").css("border", "");
                    $(".emailMessage span").text("请输入邮箱");
                    $(".emailMessage").css("visibility", "hidden");
                    flag = true;
                }
            }
        })
    });
});
