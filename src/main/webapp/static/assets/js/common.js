// 初始入口
(function () {
    window.VISITOR = {};
})();

//
// 头部显示初始化
//
function headShowInit() {
    if (VISITOR.id) {
        $(".login-no").hide();
        $(".login-yes").show();
        $("#username").show();
        $("#username").html(VISITOR.name);
        
        syncget();
        
    } else {
        $(".login-no").show();
        $(".login-yes").hide();
        $("#username").hide();
    }
}

//
// 登录其它的控制
//
function loginActions() {
    if (VISITOR.id) {
        $("#brand_url").attr("href", "/main");
    } else {
        $("#brand_url").attr("href", "/login");
    }
}

//
// 获取Session信息
//
function getSession() {
    $.ajax({
        type: "GET",
        url: "/api/account/session",
        timeout: 3000 // 3s timeout
    }).done(function (data) {
        if (data.success === "true") {
            window.VISITOR = data.message.visitor;
            headShowInit();
        } else {
        	window.location.href = "/login";
        }
    }).fail(function (xmlHttpRequest, textStatus) {
        window.location.href = "/login";
    });
}

// 获取是否登录并且进行跳转
function getSession2Redirect() {
    $.ajax({
        type: "GET",
        url: "/api/account/session"
    }).done(function (data) {
        if (data.success === "true") {
            window.location.href = "/main";
        } else {
        	//window.location.href = "/html/login.html";
        }
    });
    loginActions();
}


//是否设置了同步
function syncget(){
	$.ajax({
        type: "GET",
        url: "/api/account/getsync"
    }).done(function (data) {
        if(data==0){
			syncset();
		}
    });
}

//设置同步
function syncset(){
	var flag=0;
	if(confirm("是否将本机的 增、删、改 同步到其他区域? ")){
		flag=1;
	}
	$.ajax({
        type: "GET",
        url: "/api/account/sync?flag="+flag
    }).done(function (data) {
       
    });
}