<#include "/basehead.ftl">

<#include "/head.ftl">

	<div id="Wrapper" class="clearfix">
			<div id="Main" style="margin-top:50px;">
				<div class="container-fluid">
					
					<div class="row-fluid">
		                <ul class="breadcrumb" style="background-color:#fff;">
		                    <li>
		                        <a href="/area">区域</a>
		                    </li>
		                    <li class="active">
		                        / 新建区域
		                    </li>
		                </ul>
		            </div>
		            
		            <div class="row-fluid">
						<div class="span12">
							<div class="content oz">
								<form id="form" class="private-form clearfix">
		                            <h2 class="autoPush-detail-title">新建区域</h2>
		
		                            <div class="private-item">
		                                <span class="private-item-key">服务地址：</span>
		                                <input id="hostport" type="text" class="private-item-value"/>
		                            </div>
		                            
		                            <div class="private-item">
		                                <span class="private-item-key">用户名：</span>
		                                <input id="name" type="text" class="private-item-value"/>
		                            </div>
		                            
		                            <div class="private-item">
		                                <span class="private-item-key">密码：</span>
		                                <input id="password" type="text" class="private-item-value"/>
		                            </div>
		
		                            <div class="private-item">
		                                <span class="private-item-key">说明：</span>
		                                <textarea id="desc" type="text" class="private-item-value" style="width:400px;height:200px;"></textarea>
		                            </div>
		
		                            <div id="error" class="alert alert-warning hide" role="alert">
		                               	 表单选项不能为空或填写格式错误！
		                            </div>
		                            <div class="private-item" style="text-align:center">
		                                <a id="area_submit" class="btn btn-primary"> 新建 </a>
		                            </div>
		                        </form>						
							</div>
						</div>
					</div>
					
					
				</div>
			</div>
		</div>

<#include "/foot.ftl">

<script src="/static/assets/js/areaadd.js"></script>

<#include "/basefoot.ftl">
