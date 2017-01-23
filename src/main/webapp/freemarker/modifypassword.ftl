<#include "/basehead.ftl">

<#include "/head.ftl">

<div id="Wrapper" class="clearfix">
    <div id="Main" style="margin-top:50px;">
        <div class="container-fluid">

            <div class="row-fluid">
                <ul class="breadcrumb" style="background-color:#fff;">
                    <li>
                        <a href="/main.html">配置</a>
                    </li>
                    <li class="active">
                        / 修改密码
                    </li>
                </ul>
            </div>

            <div class="row-fluid">

                <div class="span12">

                    <div class="content oz">

                        <form id="form" class="private-form clearfix">
                            <h2 class="autoPush-detail-title">修改密码</h2>

                            <div class="private-item">
                                <span class="private-item-key">原密码：</span>
                                <input id="old_password" type="text" class="private-item-value"/>
                            </div>

                            <div class="private-item">
                                <span class="private-item-key">新密码：</span>
                                <input id="new_password" type="password" class="private-item-value"/>
                            </div>

                            <div class="private-item">
                                <span class="private-item-key">新密码(重复)：</span>
                                <input id="new_password_2" type="password" class="private-item-value"/>
                            </div>

                            <div id="error" class="alert alert-warning hide" role="alert">
                                	表单选项不能为空或填写格式错误！
                            </div>
                            <div class="private-item" style="text-align:center">
                                <a id="item_submit" class="btn btn-primary"> 修改 </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/foot.ftl">

<script src="/html/assets/js/modify_password.js"></script>

<#include "/basefoot.ftl">

