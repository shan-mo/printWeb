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
                var result = JSON.parse(data);
                if (result.resultCode == "1") {
                    window.location.href = "index";
                } else {
                    alert(result.resultMessage);
                    console.log(result);
                    $("#aptcha").attr("src", "http://localhost:8080/getcha");
                }
            },
            error: function () {
                alert("登陆失败");
            }
        })
    }
}
function change() {
    $("#aptcha").attr("src", "http://localhost:8080/getcha");
}

