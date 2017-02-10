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
		                            <h2 class="autoPush-detail-title">数据同步</h2>
		
		                            <div class="private-item">
		                                <span class="private-item-key">从区域：</span>
		                                
		                                <select id="areaA">
		                                	
		                                </select>
		                                
		                                <span class="private-item-key">同步到区域：</span>
		                                
		                                <select id="areaB">
		                                	
		                                </select>
		                            </div>
		                            
		                            
		                            <div class="private-item" style="text-align:center">
		                                <a id="ok_submit" class="btn btn-primary">确定 </a>
		                            </div>
		                            
		                        </form>						
							</div>
						</div>
					</div>
					
					
				</div>
			</div>
		</div>

<#include "/foot.ftl">

<script src="/static/assets/js/datasync.js"></script>

<#include "/basefoot.ftl">
