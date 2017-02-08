//删除信息
function delApp(id){
    if(confirm("您要删除该APP吗?")){
    	$.get("/api/app/delete?id="+id,function(data){
    		if (data.success === "true") {
    			window.location.href="/app";
    		}else{
    			alert("删除失败："+data)
    		}
        });
    }
}
    
(function ($) {


    getSession();   
    //
    // 获取信息
    //
    $.ajax({
        type: "GET",
        url: "/api/app/listapp"
    }).done(function (data) {
    	if (data.success === "true") {
            var html = "";
            var result = data.page.result;
            $.each(result,function (index, item) {
                html += '<tr>';
				html += '<td>'+(index+1)+'</td>'; 
				html += '<td>'+(item.id)+'</td>';
				html += '<td>'+(item.name)+'</td>';
				html += '<td>'+(item.desc)+'</td>';
				html += '<td>'+(item.createTime)+'</td>';
				html += '<td>'+(item.emails)+'</td>';
				html += '<td><a href="javascript:delApp('+(item.id)+');" >删除</a></td>';             
				html += '</tr>';
             });
            $("#appBody").html(html);   
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