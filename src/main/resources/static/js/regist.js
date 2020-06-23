function registsub() {
    var flag = true;

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

    if (flag) {
        $.ajax({
            url: "/regist",
            type: "post",
            dataType: "text",
            data: {
                mininame: mininame,
                email: email,
                passwd: passwd,
                repasswd: repasswd
            },
            success: function (data) {
                if (data == "success") {
                    window.location.href = "initlogin";
                }
            },
            error: function () {
                alert("注册失败");
            }
        })
    }
}