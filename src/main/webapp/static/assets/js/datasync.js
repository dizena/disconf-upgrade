    
(function ($) {


    getSession();   
    //
    // 获取Area信息
    //
    $.ajax({
        type: "GET",
        url: "/api/area/list"
    }).done(function (data) {
    	if (data.success === "true") {
            var html = "";
            var result = data.page.result;
            $.each(result,function (index, item) {
            	html += '<option value="'+(item.id)+'">';
            	html += (item.desc);
            	html += '</option>';
             });
           $("#areaA").html(html);  
           $("#areaB").html(html);  
        }
    });
    
    
    // 提交
    $("#ok_submit").bind("click", function (e) {
        var a=$("#areaA").val();
        var b=$("#areaB").val();
        if(a==b){
        	alert("数据同步区域不可以是同一区域！")
        }else{
        	if(confirm("确定要把 "+a+" 区域数据同步到 "+b+" 区域?")){
        		$.post("/api/data/sync",{"a":a,"b":b},function(data){
        			if(data==1){
        				window.location.href="/data";
        			}
        		});
        	}
        }
    });
    
	function echoAttr(myObject) {
		var s = "";
		for ( var property in myObject) {
			s = s + "\n " + property + ": " + myObject[property];
		}
		alert(s);
	}
    
})(jQuery);