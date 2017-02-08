    
(function ($) {


    getSession();   
    //
    // 获取Area信息
    //
    $.ajax({
        type: "GET",
        url: "/api/data/list"
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
            $("#dataBody").html(html);   
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