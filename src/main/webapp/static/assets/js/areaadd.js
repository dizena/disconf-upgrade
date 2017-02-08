
getSession();

// 提交
$("#area_submit").bind("click", function (e) {
    $("#error").addClass("hide");
    var hostport = $("#hostport").val();
    var name = $("#name").val();
    var password = $("#password").val();
    var desc = $("#desc").val();

    // 验证
    if (!desc || !hostport|| !name|| !password) {
        $("#error").removeClass("hide");
        $("#error").html("表单不能为空或填写格式错误！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/api/area/add",
        data: {
            "hostport": hostport,
            "name": name,
            "password": password,
            "desc":desc
        }
    }).done(function (data) {
        $("#error").removeClass("hide");
        if (data.success === "true") {
        	window.location.href="/area";
        } else {
            Util.input.whiteError($("#error"), data);
        }
    });
});
