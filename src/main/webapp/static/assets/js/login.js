(function ($) {

    $("#indexMain").attr("href", "/index");

    getSession2Redirect();
    
    document.onkeydown = function(e){ 
		var ev = document.all ? window.event : e;
		if(ev.keyCode==13) {
           login();
		}
	}

    // 发送登录请求
    $(".form-submit").on("click", function () {
    	login();
    });

})(jQuery);

function login(){
	var me = this;
    var email = $("#name").val();
    var pwd = $("#password").val();
    var remember = $("#inlineCheckbox2").is(':checked') ? 1 : 0;

    // 验证
    if (email.length <= 0 || !pwd) {
        $("#loginError").show();
        return;
    }

    $.ajax({
        type: "POST",
        url: "/api/account/signin",
        data: {
            "name": email,
            "password": pwd,
            "remember": remember
        }
    }).done(function (data) {
        if (data.success === "true") {
            window.VISITOR = data.message.visitor;
            $("#loginError").hide();
            headShowInit();
            window.location.href = "/main";
        } else {
            Util.input.whiteError($("#loginError"), data);
            $("#loginError").show();
        }
    });
}