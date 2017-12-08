$(function () {
    setNavBg();
    message();
});

//设置当前菜单背景高亮
function setNavBg() {
    var urlstr = location.href;
    var urlstatus = false;
    $('#menu a').each(function () {
        if ((urlstr + '/').indexOf($(this).attr('href')) > -1 && $(this).attr('href') != '') {
            $(this).addClass('currentNav');
            urlstatus = true;
        } else {
            $(this).removeClass('cur');
        }
    });
    if (!urlstatus) {
        $('#menu a').eq(0).addClass('currentNav');
    }
}

//删除按钮提示框
function message() {

    $(".del").click(function () {
        var res = confirm("删除后不可恢复，是否继续删除？");
        if (!res) {
            return false;
        }
    });
}

