<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="">
    <title>查询列表</title>
    <#include "../../control/public_css_js.ftl">
</head>

<body class="no-skin">
<!-- start top-->
<div id="navbar" class="navbar navbar-default">
    <#include "../../layout/top.ftl">
</div>
<!-- end top-->

<div class="container col-xs-12" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>
    <#include "../../layout/menu.ftl">

    <div class="row">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <a href="#">日志管理</a>
                </li>
                <li class="active">日志列表</li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="panel-body">
            <div class="col-xs-12">
                <form class="form-inline" action="/admin/logList" name="search" method="post">
                    <input type="hidden" name="pageNum" id="pageNum" value="${jobRunLogParam.pageNum}">
                    <input type="hidden" name="pageSize" id="pageSize"  value="${jobRunLogParam.pageSize}">
                    <input type="text" class="form-control" placeholder="配置id"
                           name="jobConfigId"  <#if (jobRunLogParam??) >  value="${jobRunLogParam.jobConfigId!""}" </#if> />
                    <input type="text" class="form-control" placeholder="任务名称(模糊查询)"
                           name="jobName" <#if (jobRunLogParam??)> value="${jobRunLogParam.jobName!""}" </#if> />
                    <input type="text" class="form-control" placeholder="任务id"
                           name="jobId"  <#if (jobRunLogParam??) >  value="${jobRunLogParam.jobId!""}" </#if> />
                    <button type="button" class="btn btn-primary btn-mini " onclick="searchForm(1)">搜索</button>
                    <span style="color: red">(只显示最近30天)</span>
                </form>
            </div><!-- /.col -->
        </div>
    </div><!-- /.row -->
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>配置ID</th>
                    <th>任务名称</th>
                    <th>运行模式</th>
                    <th>运行状态</th>
                    <th>任务运行ID</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <#if jobRunLogList?size == 0>
                    <tr>
                        <td colspan="8" align="center">
                            没有数据
                        </td>
                    </tr>
                <#else>

                    <#list jobRunLogList as jobRunLogVO>
                        <tr>
                            <td>${jobRunLogVO.jobConfigId!""}</td>
                            <td>${jobRunLogVO.jobName!""}</td>
                            <td>${jobRunLogVO.deployMode!""}</td>
                            <td>${jobRunLogVO.jobStatus!""} </td>
                            <td>${jobRunLogVO.jobId!""}</td>
                            <td>${jobRunLogVO.createTime!""}</td>
                            <td>${jobRunLogVO.editTime!""}</td>
                            <td>
                                <a href="/admin/detailLog?id=${jobRunLogVO.id!""}"  target="_blank">日志详情 </a>
                            </td>
                        </tr>
                    </#list>

                </#if>
                </tbody>
            </table>
        </div><!-- /.col -->
    </div><!-- /.row -->
    <div class="row">
        <div class="col-xs-12">
            <#if jobRunLogList?size != 0>
                <ul class="pagination">

                    <#if pageVO.pages lte 1>
                        <li class="disabled "><a class="page-link" href="#">上一页</a></li>
                    <#else>
                        <li>
                            <a class="page-link" href="#" onclick="searchForm(${pageVO.pages -1})" >上一页</a>
                        </li>
                    </#if>

                    <#list 1..pageVO.pages as index>
                        <#if pageVO.pageNum == index>
                            <li class="page-item active "><a class="page-link" href="#" onclick="searchForm(${index})" >${index}</a>
                            </li>
                        <#else>
                            <li>
                                <a class="page-link" href="#" onclick="searchForm(${index})" >${index}</a>
                            </li>
                        </#if>
                    </#list>

                    <#if pageVO.pageNum gte pageVO.pages>
                        <li class="disabled "><a class="page-link" href="#">下一页</a></li>
                    <#else>
                        <li>
                            <a class="page-link" onclick="searchForm(${pageVO.pageNum+1})"  href="#">下一页</a>
                        </li>
                    </#if>
                </ul>
            </#if>
        </div><!-- /.col -->
    </div><!-- /.row -->

</div><!-- /.main-container -->
<#include "../../layout/bottom.ftl">

<script>
    function searchForm(pageNum) {
        $("#pageNum").attr("value", pageNum);
        $("form[name='search']").submit();
    }

    $(function () { $('.popover-show').popover('show');});
    $(function () { $('.popover-hide').popover('hide');});
    $(function () { $('.popover-destroy').popover('destroy');});
    $(function () { $('.popover-toggle').popover('toggle');});
    $(function () { $(".popover-options a").popover({html : true });});
</script>
</body>
</html>
