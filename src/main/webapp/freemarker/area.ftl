<#include "/basehead.ftl">

<#include "/head.ftl">

	<div id="Wrapper" class="clearfix">
		<div id="Main" style="margin-top:50px;">
			<div class="container-fluid">
				<div class="row-fluid" style="min-height:400px;">
					<table id="arealist" class="table table-bordered table-condensed table-hover account-tableWrap table-striped" >
                        <thead>
                            <tr>
                                <td>#</td>
                                <td>区域编号</td>
                                <td>描述</td>
                                <td>服务地址</td>
                                <td>用户名</td>
                                <td>密码</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody id="areaBody" style="max-width:760px; overflow-wrap: break-word;word-wrap: break-word;" class="account-tableWrap">
							
						</tbody>
                    </table>
					
				</div>
			</div>
		</div>
	</div>


<#include "/foot.ftl">
<script src="/static/assets/js/areaList.js"></script>
<#include "/basefoot.ftl">

