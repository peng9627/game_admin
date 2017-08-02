[@override name="title"]系统公告管理 - 系统公告创建[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a onclick="window.location.href='/logged'" href="javascript:void(0)">首页</a></li>
    <li><a href="/notice/pagination">系统公告管理</a></li>
    <li>系统公告创建</li>
</ul>
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/notice/create" method="post" data-parsley-validate>

            [@spring.bind "command.title"/]
            <div class="form-group">
                <label for="title" class="col-md-3 control-label">系统公告标题*</label>
                <div class="col-md-9">
                    <input class="form-control" id="title" name="title"
                           value="${command.title!}" placeholder="输入系统公告标题"
                           data-parsley-required="true" data-parsley-required-messages="系统公告不能为空标题"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "title" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.content"/]
            <div class="form-group">
                <label for="content" class="col-md-3 control-label">系统公告内容*</label>
                <div class="col-md-9">
                    <textarea class="form-control" id="content" name="content" style="height: 200px"
                              placeholder="输入系统公告内容"
                              data-parsley-required="true" data-parsley-required-messages="系统公告不能为空内容"
                              data-parsley-trigger="change">${command.content!}</textarea>
                    [@spring.showErrors "content" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">创建</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">创建注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>

[/@override]
[@extends name="/decorator.ftl"/]