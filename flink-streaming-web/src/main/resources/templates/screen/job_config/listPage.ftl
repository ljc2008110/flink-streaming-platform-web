<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>查询列表</title>
    <meta name="description" content="overview &amp; stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <#include "../../control/public_css_js.ftl">
</head>
<style>
    .ant-tag-green{
        color: #52c41a;
        background: #f6ffed;
        border-color: #52c41b;
        border: 1px solid #52c41a ;
        padding:0 4px;
        border-radius:6px;
    }

    .ant-tag-magenta{
        color: #eb2f96;
        background: #fff0f6;
        border-color: #ffadd2;
        border: 1px solid #eb2f96 ;
        padding:0 4px;
        border-radius:6px;
    }

</style>

<body class="no-skin">
    <!-- start top-->
    <div id="navbar" class="navbar navbar-default ">
        <#include "../../layout/top.ftl">
    </div>
    <!-- end top-->

    <div class="container col-xs-12" id="main-container">
        <script type="text/javascript">
            try{ace.settings.loadState('main-container')}catch(e){}
            $(function () { $("[data-toggle='tooltip']").tooltip(); });
        </script>

    <#--    <#include "../../layout/menu.ftl">-->

        <div class="row">
            <div class="breadcrumbs" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <a href="#">配置管理</a>
                    </li>
                    <li class="active">Flink-SQL流任务列表</li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="panel-body">
                    <div class="col-sm-8">
                        <form class="form-inline" role="form" action="/admin/listPage" name="search" method="get">
                            <input type="hidden" name="pageNum" id="pageNum" value="${jobConfigParam.pageNum}">
                            <input type="hidden" name="pageSize" id="pageSize"  value="${jobConfigParam.pageSize}">
                            <input type="text" class="form-control" placeholder="任务名称(模糊查询)"
                                   name="jobName" <#if (jobConfigParam??)> value="${jobConfigParam.jobName!""}" </#if> />&nbsp;
                            <input type="text" class="form-control" placeholder="任务id"
                                   name="jobId"  <#if (jobConfigParam??) >  value="${jobConfigParam.jobId!""}" </#if> />&nbsp;
                            <select class="form-control" name="stauts">
                                <option value=""> 运行状态</option>
                                <option value="1" <#if (jobConfigParam??) &&(jobConfigParam.stauts??) && jobConfigParam.stauts==1> selected</#if> >
                                   运行中
                                </option>
                                <option value="0" <#if (jobConfigParam??) &&(jobConfigParam.stauts??) && jobConfigParam.stauts==0> selected</#if> >
                                    停止中
                                </option>
                                <option value="-1" <#if (jobConfigParam??)&&(jobConfigParam.stauts??) && jobConfigParam.stauts==-1> selected</#if> >
                                    运行失败
                                </option>
                            </select>&nbsp;
                            <select class="form-control" name="open">
                                <option value=""> 配置状态</option>
                                <option value="1" <#if (jobConfigParam??) &&(jobConfigParam.open??) && jobConfigParam.open==1> selected</#if> >
                                    开启
                                </option>
                                <option value="0" <#if (jobConfigParam??) &&(jobConfigParam.open??) && jobConfigParam.open==0> selected</#if> >
                                    关闭
                                </option>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-primary btn-sm" onclick="searchForm(1)">搜索</button>
                        </form>
                    </div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-success btn-sm" onclick="refreshForm()">刷新</button>
                        <button type="button" class="btn btn-success btn-sm" onclick="onekeyBackup()">一键备份</button>
                        <button type="button" class="btn btn-success btn-sm" onclick="onekeyRestore()">一键恢复</button>
                        <a class="btn btn-primary btn-sm" href="/admin/addPage">新增流任务</a>
                    </div>
                </div>

                <div class="panel-body">
                    <table class="table table-hover table-condensed">
                        <thead>
                            <tr>
                                <th>配置ID</th>
                                <th>任务名称</th>
                                <th>运行模式</th>
                                <th>运行状态</th>
                                <th>任务id</th>
                                <th>上次运行</th>
                                <th>操作</th>
                                <th>辅助<span class="glyphicon glyphicon-info-sign" aria-hidden="true" data-toggle="tooltip" title="默认开启钉钉和自动重启"></span></th>
                            </tr>
                        </thead>
                        <tbody>
                        <#if jobConfigList?size == 0>
                            <tr>
                                <td colspan="8" align="center">
                                    没有数据
                                </td>
                            </tr>
                        <#else>
                            <#list jobConfigList as jobConfigVO>
                                <tr>
                                    <td>${jobConfigVO.id!""}</td>
                                    <td style="text-align: right">
                                        ${jobConfigVO.jobName!""}
                                        <#if jobConfigVO.isOpen==1>
                                            <a href="#" class="btn-success btn btn-xs right-arrow-button"
                                               onclick="closeConfig(${jobConfigVO.id})">ON</a>
                                        <#else>
                                            <a href="#" class="btn-danger btn btn-xs right-arrow-button"
                                               onclick="openConfig(${jobConfigVO.id})">OFF</a>
                                        </#if>
                                    </td>
                                    <td>${jobConfigVO.deployMode!""}</td>
                                    <#if jobConfigVO.stauts==1>
                                        <td class="success">
                                    <#else>
                                        <#if jobConfigVO.stauts==-1>
                                            <td class="danger">
                                        <#else>
                                            <td>
                                        </#if>
                                    </#if>
                                        ${jobConfigVO.stautsStr!""}
                                    </td>
                                    <#if (jobConfigVO.jobId)??>
                                        <td> <a href="${jobConfigVO.flinkRunUrl!""}" target="_blank"> ${jobConfigVO.jobId!""}</a>  </td>
                                    <#else>
                                        <td></td>
                                    </#if>
                                    <td>${jobConfigVO.lastStartTime!""}</td>
                                    <td>
                                        <div class="btn-group">
                                            <button class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">
                                                操作
                                                <span class="caret" style="margin-top: 5px;"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li>
                                                    <#if jobConfigVO.isOpen==1>
                                                        <#if 0 < jobConfigVO.stauts && jobConfigVO.stauts != 97>
                                                            <a href="#" onclick="stop(${jobConfigVO.id})">停止任务</a>
                                                        <#else>
                                                            <a href="#" onclick="start(${jobConfigVO.id})">提交任务</a>
                                                        </#if>
                                                    <#else>
                                                        <a href="#" onclick="deleteConfig(${jobConfigVO.id})">删除</a>
                                                    </#if>
                                                </li>
                                                <li>
                                                    <a href="/admin/editPage?id=${jobConfigVO.id}"  target="_blank">修改</a>
                                                </li>
                                                <li>
                                                    <a href="/admin/detailPage?id=${jobConfigVO.id}" target="_blank">详情</a>
                                                </li>
                                                <li>
                                                    <a href="#" onclick="copyConfig(${jobConfigVO.id})">复制</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li>
                                                    <a href="/admin/savepointList?jobConfigId=${jobConfigVO.id!""}"
                                                       target="_blank">恢复任务</a>
                                                </li>
                                                <li>
                                                    <a href="#" onclick="savePoint(${jobConfigVO.id!""})">手动备份</a>
                                                </li>
                                                <#if jobConfigVO.lastRunLogId??>
                                                    <li class="divider"></li>
                                                    <li>
                                                        <a href="/admin/detailLog?id=${jobConfigVO.lastRunLogId!""}"  target="_blank">日志详情 </a>
                                                    </li>
                                                    <li>
                                                        <a href="/admin/logList?jobConfigId=${jobConfigVO.id!""}"  target="_blank">历史日志 </a>
                                                    </li>
                                                </#if>
                                                <li class="divider"></li>
                                                <li>
                                                    <a href="/admin/jobConfigHistoryPage?jobConfigId=${jobConfigVO.id!""}" target="_blank">历史版本 </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
                                    <td><#--${jobConfigVO.alarmStrs!""}-->
                                        <#if jobConfigVO.autoRestore?? && jobConfigVO.autoRestore == 1>
                                            <a href="#" class="glyphicon glyphicon-floppy-saved" data-toggle="tooltip"
                                               title="恢复任务时从savepoint备份恢复" onclick="setAutoRestore(${jobConfigVO.id!""}, 0)"></a>
                                        <#else>
                                            <a href="#" class="glyphicon glyphicon-floppy-remove" data-toggle="tooltip"
                                               title="恢复任务时【不】从savepoint备份恢复" onclick="setAutoRestore(${jobConfigVO.id!""}, 1)"></a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                </div>

                <#if jobConfigList?size != 0>
                    <div class="panel-body">
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
                                <li class="disabled "><a class="page-link" href="#" onclick="">下一页</a></li>
                            <#else>
                                <li>
                                    <a class="page-link" onclick="searchForm(${pageVO.pageNum+1})"  href="#" >下一页</a>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </#if>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->
    <#include "../../layout/bottom.ftl">

    <script src="/static/js/customer/list_job_config.js?version=20210123"></script>
</body>
</html>
