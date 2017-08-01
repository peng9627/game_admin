[@override name="title"]权限管理 - 权限修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li><a href="/permission/pagination">权限管理</a></li>
    <li>权限修改</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/permission/edit" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${permission.id!command.id}"/>
            <input type="hidden" name="version" value="${permission.version!command.version}"/>

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">权限名称*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${permission.name!command.name}" placeholder="输入权限名称"
                           data-parsley-required="true" data-parsley-required-messages="权限名称不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.value"/]
            <div class="form-group">
                <label for="projectName" class="col-md-3 control-label">权限值*</label>
                <div class="col-md-9">
                    <input class="form-control" id="value" name="value"
                           value="${permission.value!command.value}" placeholder="输入权限值"
                           data-parsley-required="true" data-parsley-required-messages="权限值不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "value" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.description"/]
            <div class="form-group">
                <label for="description" class="col-md-3 control-label">权限描述*</label>
                <div class="col-md-9">
                    <input class="form-control" id="description" name="description"
                           value="${permission.description!command.description}" placeholder="输入权限描述"
                           data-parsley-required="true" data-parsley-required-messages="权限描述不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">修改</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">修改注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]