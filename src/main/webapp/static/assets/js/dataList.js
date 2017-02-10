    
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
				html += '<td>'+(item.areaId)+'</td>';
				html += '<td>'+(item.hostport)+'</td>';
				if(item.areaCount==0){
					html += '<td> 该区域无法连接，请检查服务！</td>';
				}else{
					html += '<td>区域:'+(item.areaCount)+'，应用:'+(item.appCount)+'，环境:'+(item.envCount)+'，配置:'+(item.cfgCount)+'</td>';
				}
				html += '</tr>';
             });
            $("#dataBody").html(html);   
        }
    });
    
})(jQuery);