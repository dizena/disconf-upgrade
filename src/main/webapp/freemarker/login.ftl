<#include "/basehead.ftl">

<#include "/head.ftl">

<div id="Wrapper" class="clearfix">
    <div id="Main">
        <div class="container-fluid">

            <form class="form-single" id="login-form" style="padding:0px;border:0px">
                <div class="well" style="border:2px solid #e3e3e3;width:380;padding:20px 30px;">

                    <h2>登录</h2>

                    <div class="form required email">
                        <label for="account">用户名</label>
                        <input id="name" class="input-block-level" type="text" value="" name="useremail">
                    </div>

                    <div class="form required">
                        <label for="password">密码</label>
                        <input id="password" style="" class="input-block-level" name="oldpass" autocomplete="off"
                               value="" type="password">
                    </div>

                    <label class="checkbox inline">
                        <input style="width:15px" type="checkbox" id="inlineCheckbox2" name="permanent" value="y">
                        &nbsp;一个月内免登录 </label>

                    <div class="form-submit">
                        <a class="btn btn-primary" type="submit"> 登录 </a>
                    </div>

                    <br>
                    <ul class="unstyled text-error">
                        <li>
                            <div id="loginError" style="display:none;">
                                	用户名或密码不能为空
                            </div>
                        </li>
                    </ul>

                </div>
            </form>

        </div>
    </div>
</div>

<#include "/foot.ftl">

<script src="/html/assets/js/login.js"></script>

<#include "/basefoot.ftl">
