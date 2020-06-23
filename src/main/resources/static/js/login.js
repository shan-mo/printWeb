function checklogin() {

    var flag = true;

    var username = $.trim($("#username").val());
    var password = $.trim($("#password").val());
    var aptchaText = $.trim($("#aptchaText").val());
    if (username == "") {
        $("#username").css("border", "2px solid red");
        $(".usernameError").css("visibility", "visible");
        flag = false;
    } else {
        $("#username").css("border", "");
        $(".usernameError").css("visibility", "hidden");
    }

    if (password == "") {
        $("#password").css("border", "2px solid red");
        $(".passwdError").css("visibility", "visible");
        flag = false;
    } else {
        $("#password").css("border", "");
        $(".passwdError").css("visibility", "hidden");
    }

    if (aptchaText == "") {
        $("#aptchaText").css("border", "2px solid red");
        $(".aptchaError").css("visibility", "visible");
        flag = false;
    } else {
        $("#aptchaText").css("border", "");
        $(".aptchaError").css("visibility", "hidden");
    }

    if (flag) {
        $.ajax({
            url: "/login",
            type: "post",
            dataType: "Text",
            data: {
                username: username,
                password: password,
                aptcha: aptchaText
            },
            success: function (data) {
                if (data == "success") {
                    window.location.href = "index";
                } else {
                    alert("用户名或密码错误");
                }
            },
            error: function () {
                alert("登陆失败");
            }
        })
    }
}

