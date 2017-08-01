[@override name="title"]用户管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
[/@override]
[@override name="subContent"]
    [@mc.showAlert /]
<h4>推广连接:${url!}${command.parent!}</h4>
<div class="smart-widget widget-green">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
        <form class="form-inline no-margin" role="form">
            <div class="form-group">
                <label for="userName" class="control-label">用户名</label>
                <input type="text" class="form-control" id="userName" name="userName" value="${command.userName!}"
                       placeholder="用户名"/>
            </div>
            <div class="form-group">
                <label for="nickName" class="control-label">昵称</label>
                <input type="text" class="form-control" id="nickName" name="nickName" value="${command.nickName!}"
                       placeholder="昵称"/>
            </div>
            <input type="text" name="parent" value="${command.parent!}" hidden/>
            <div class="form-group">
                <label for="status" class="control-label">用户状态</label>
                <select name="status" id class="form-control">
                    [#assign status = (command.status!)?default("") /]
                    <option value="ALL" [@mc.selected status "ALL" /]>全部</option>
                    <option value="ENABLE" [@mc.selected status "ENABLE" /]>启用</option>
                    <option value="DISABLE" [@mc.selected status "DISABLE" /]>禁用</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
            </div>
        </form>
    </div>
    <div class="smart-widget-inner">
        <div class="smart-widget-hidden-section" style="display: none;">
            <ul class="widget-color-list clearfix">
                <li style="background-color:#20232b;" data-color="widget-dark"></li>
                <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                <li style="background-color:#2baab1;" data-color="widget-green"></li>
                <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                <li style="background-color:#e36159;" data-color="widget-red"></li>
                <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                <li style="background-color:#fff;" data-color="reset"></li>
            </ul>
        </div>
        <div class="smart-widget-body no-padding">
            <div class="padding-md">
                <section class="overflow-auto nice-scrollbar">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>创建时间</th>
                            <th>昵称</th>
                            <th>余额</th>
                            <th>状态</th>
                            <th>上级</th>
                            <th>vip</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as user ]
                                <tr>
                                    <td>${user.userName!}</td>
                                    <td>[@mc.dateShow user.createDate/]</td>
                                    <td>${user.name!}</td>
                                    <td>${user.money!}</td>
                                    <td>${(user.status.getName())!}</td>
                                    <td>${user.parent!}</td>
                                    <td>[#if user.getVip()]是[#else]否[/#if]</td>
                                    <td>
                                        <a href="[@spring.url '/user/children?parent=${user.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="取消vip">
                                            <span class="label label-primary">查看下级</span>
                                        </a>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination??]
            [@mc.showPagination '/user/pagination?userName=${command.userName!}&nickName=${command.nickName!}&status=${command.status!}&parent=${command.parent!}' /]
        [/#if]
            </div>
        </div>
    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator_user.ftl"/]