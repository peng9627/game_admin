<aside class="sidebar-menu fixed">
    <div class="sidebar-inner scrollable-sidebar">
        <div class="main-menu">
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">首页</span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm submenu-label">用户管理</span>
                            <span class="submenu-icon"></span>
                        </span>
                        <span class="menu-content-hover submenu-label block">用户管理</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/user/pagination'/]')">
                                <span class="submenu-label">用户管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/seal/pagination'/]')">
                                <span class="submenu-label">封号管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette3">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/money_detailed/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-money fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">资金明细</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">资金明细</span>
                    </a>
                </li>
                <li class="bg-palette4">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/gold_detailed/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-money fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">金币明细</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">金币明细</span>
                    </a>
                </li>
                <li class="bg-palette1">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/notice/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-volume-up fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">系统公告</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">系统公告</span>
                    </a>
                </li>
                <li class="bg-palette2">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/recharge/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-bookmark fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">充值记录</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">充值记录</span>
                    </a>

                </li>
                <li class="bg-palette3">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/gamerecord/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-camera-retro fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">游戏记录</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">游戏记录</span>
                    </a>
                </li>
                <li class="bg-palette4">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/recharge_give/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-gift fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">充值满送</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">充值满送</span>
                    </a>
                </li>
                <li class="bg-palette1">
                    <a href="javascript:void(0)" onclick="load('[@spring.url '/logger/pagination'/]')">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-pencil-square-o fa-lg"></i></span>
                             <span class="text m-left-sm submenu-label">系统日志</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">系统日志</span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-cogs fa-lg"></i></span>
                            <span class="text m-left-sm text m-left-sm">系统设置</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">系统设置</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/system/edit'/]')">
                                <span class="submenu-label">系统配置修改</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/permission/pagination'/]')">
                                <span class="submenu-label">权限管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/role/pagination'/]')">
                                <span class="submenu-label">角色管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="load('[@spring.url '/account/pagination'/]')">
                                <span class="submenu-label">账号管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette3">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm submenu-label">退出</span>
                        </span>
                        <span class="menu-content-hover submenu-label block">退出</span>
                    </a>
                </li>
            </ul>
        </div>
    </div><!-- sidebar-inner -->
</aside>