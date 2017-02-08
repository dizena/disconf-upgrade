		
        <link rel="stylesheet" href="/static/dep/bootstrap/css/bootstrap.css" />
        <!--[if lte IE 6]>
        <link rel="stylesheet" href="/static/dep/bootstrap/css/bootstrap-ie6.css" />
        <![endif]-->
        <!--[if lte IE 7]>
        <link rel="stylesheet" href="/static/dep/bootstrap/css/ie.css" />
        <![endif]-->
        <![if !IE]>
        <link rel="stylesheet" href="/static/dep/bootstrap/css/noie.css" />
        <![endif]>
        <link rel="stylesheet" href="/static/dep/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
        <link rel="stylesheet" type="text/css" href="/static/assets/css/project.css" />
    </head>

    <body>
        
        <div class="navbar navbar-fixed-top clearfix">
            <div class="navbar-inner zu-top">
                <div class="container">
                    <button type="button" class="btn btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <div class="nav-collapse collapse">

                        <a id="brand_url" href="/main" class="brand" style="margin-left:0px;padding:8px;"> <span class="zu-top-nav-link">深思云配置中心</span> </a>
                        
                        <ul class="nav pull-right" >
                            
                            <div class="login-yes"  style="display:none;padding:10px;">
                                
                                <li style="display:inline;" class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                                        <span class="zu-top-nav-link">区域配置<b class="caret"></b></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="area">查看区域服务</a></li>
                                        <li><a href="areaadd">新建区域服务</a></li>
                                    </ul>
                                </li>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <li style="display:inline;" class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                                        <span class="zu-top-nav-link">应用配置<b class="caret"></b></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                    	<li><a href="app">查看应用</a></li>
                                        <li><a href="newapp">新建APP</a></li>
                                    </ul>
                                </li>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <li style="display:inline;" class="dropdown">
                                    <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                                        <span class="zu-top-nav-link">文件配置<b class="caret"></b></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                    	<li><a href="main">查看配置</a></li>
                                        <li><a href="newconfig_item">新建配置项</a></li>
                                        <li><a href="newconfig_file">新建配置文件</a></li>
                                    </ul>
                                </li>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <li style="display:inline;">
                                    <a href="data">
                                        <span class="zu-top-nav-link" >数据通知</span>
                                    </a>
                                </li>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <li style="display:inline;">
                                    <a href="modifypassword">
                                        <span class="zu-top-nav-link" >修改密码</span>
                                    </a>
                                </li>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <li style="display:inline;">
                                    <a href="#" id="signout">
                                    	<span class="zu-top-nav-link loginName" id="username"></span>
                                        <span class="zu-top-nav-link" >退出</span>
                                    </a>
                                </li>
                                
                            </div>
                            
                            <div class="login-no"  style="padding:10px;">
                                <li style="display:inline;">
                                    <a href="/login"><span class="zu-top-nav-link">登录</span></a>
                                </li>   
                            </div>
                            
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
    
