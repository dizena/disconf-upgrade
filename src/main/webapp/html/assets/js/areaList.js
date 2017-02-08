//删除区域信息
function delArea(id){
    if(confirm("您要删除该区域信息吗?")){
    	$.get("/api/area/delete?id="+id,function(data){
    		if (data.success === "true") {
    			window.location.href="area.html";
    		}else{
    			alert("删除失败："+data)
    		}
        });
    }
}
    
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
                html += '<tr>';
				html += '<td>'+(index+1)+'</td>'; 
				html += '<td>'+(item.id)+'</td>';
				html += '<td>'+(item.desc)+'</td>';
				html += '<td>'+(item.hostport)+'</td>';
				html += '<td>'+(item.name)+'</td>';
				html += '<td>'+(item.password)+'</td>';
				html += '<td><a href="javascript:delArea('+(item.id)+');" >删除</a></td>';             
				html += '</tr>';
             });
            $("#areaBody").html(html);   
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