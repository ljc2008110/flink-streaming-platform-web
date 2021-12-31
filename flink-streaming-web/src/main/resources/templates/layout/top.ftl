<footer class="navbar-fixed-top">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Flink流计算平台</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li <#if open??&& open=="config" > class="dropdown" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-list"></i>
                        <span class="menu-text"> 任务管理 </span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/listPage">
                                <i class="menu-icon fa fa-caret-right"></i>
                                SQL流任务列表
                            </a>
                        </li>
                        <li>
                            <a href="/admin/batchListPage">
                                <i class="menu-icon fa fa-caret-right"></i>
                                SQL批任务列表
                            </a>
                        </li>
                        <li>
                            <a href="/admin/jarListPage">
                                <i class="menu-icon fa fa-caret-right"></i>
                                JAR任务列表
                            </a>
                        </li>
                    </ul>
                </li>
                <li <#if open??&& open=="log" > class="dropdown" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-file-o"></i>
                        <span class="menu-text">日志管理</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/logList">
                                <i class="menu-icon fa fa-caret-right"></i>
                                运行日志
                            </a>
                        </li>
                    </ul>
                </li>

                <li <#if open??&& open=="system" > class="dropdown" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-desktop"></i>
                        <span class="menu-text">系统管理</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/sysConfig">
                                <i class="menu-icon fa fa-caret-right"></i>
                                系统设置
                            </a>
                        </li>
                    </ul>
                </li>
                <li <#if open??&& open=="alart" > class="dropdown" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-list-alt"></i>
                        <span class="menu-text">报警管理</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/alartConfig">
                                <i class="menu-icon fa fa-caret-right"></i>
                                报警设置
                            </a>
                        </li>
                        <li>
                            <a href="/admin/alartLogList">
                                <i class="menu-icon fa fa-caret-right"></i>
                                报警日志
                            </a>
                        </li>
                    </ul>
                </li>
                <li <#if open??&& open=="user" > class="dropdown" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-user" style="color: #444"></i>
                        <span class="menu-text">用户管理</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/userList">
                                <i class="menu-icon fa fa-caret-right"></i>
                                用户列表
                            </a>
                        </li>
                    </ul>
                </li>
                <li <#if open??&& open=="qrcode" > class="open" </#if>>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="menu-icon  fa fa-search" ></i>
                        <span class="menu-text">联系方式</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/admin/qrcode" target="_blank">
                                <i class="menu-icon fa fa-caret-right"></i>
                                联系方式
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <span class="user-info">
                                <small>欢迎</small>
                                ${user!""}
                            </span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="/admin/userList">
                                <i class="ace-icon fa fa-user"></i>
                                用户管理
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/admin/index">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</footer>
