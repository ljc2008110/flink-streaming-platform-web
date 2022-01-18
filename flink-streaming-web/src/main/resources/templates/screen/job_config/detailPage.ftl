<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>配置详情</title>
    <#include "../../control/public_css_js.ftl">

</head>

<body class="no-skin">
    <!-- start top-->
    <div id="navbar" class="navbar navbar-default">
        <#include "../../layout/top.ftl">
    </div>
    <!-- end top-->
    <div class="main-container col-xs-12" id="main-container">
        <script type="text/javascript">
            try{ace.settings.loadState('main-container')}catch(e){}
        </script>
        <#include "../../layout/menu.ftl">

        <div class="row">
            <div class="breadcrumbs" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <a href="#">配置管理</a>
                    </li>
                    <li class="active">编辑配置</li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 panel-body form-horizontal">
                <#if message??>
                    <div class="form-group alert alert-danger">
                        ${message}
                    </div>
                <#else >
                    <div class="form-group">
                        <label for="inputfile" class="control-label col-sm-1">*任务名称：</label>
                        <div class="col-sm-3">
                            <input class="form-control " type="text" placeholder="任务名称" name="jobName"  value="${jobConfig.jobName!""}" id="jobName" readonly>
                        </div>

                        <label for="inputfile" class="col-sm-1 control-label">任务状态：</label>
                        <div class="col-sm-1">
                            <a class="btn btn-sm left-arrow-button
                                <#if jobConfig.stauts==1>
                                    btn-success
                                <#else>
                                    <#if jobConfig.stauts==-1>
                                        btn-danger
                                    <#else>
                                    </#if>
                                </#if>">${jobConfig.stautsStr!""}</a>
                        </div>

                        <label for="inputfile" class="col-sm-1 control-label">配置是否开启：</label>
                        <div class="col-sm-1">
                            <#if jobConfig.isOpen==1>
                                <a href="#" class="btn-success btn btn-sm left-arrow-button"
                                   onclick="closeConfig(${jobConfig.id})">ON</a>
                            <#else>
                                <a href="#" class="btn-danger btn btn-sm left-arrow-button"
                                   onclick="openConfig(${jobConfig.id})">OFF</a>
                            </#if>
                        </div>

                        <label for="inputfile" class="col-sm-1 control-label">运行模式：</label>
                        <div class="col-sm-2">
                            <a href="#" class="btn-primary btn btn-sm left-arrow-button">${jobConfig.deployMode!""}</a>
                        </div>

                        <div class="col-sm-1">
                            <a class="btn btn-primary btn-sm left-button" href="/admin/editPage?id=${jobConfig.id!""}" >编辑</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="flinkRunConfig" class="col-sm-1 control-label">Flink运行配置：</label>
                        <div class="col-sm-3">
                            <textarea class="form-control" name="flinkRunConfig" id="flinkRunConfig"
                                      rows="2" readonly>${jobConfig.flinkRunConfig!""}</textarea>
                        </div>

                        <label for="flinkCheckpointConfig" class="col-sm-1 control-label">Checkpoint信息：</label>
                        <div class="col-sm-7">
                            <textarea class="form-control" placeholder="Checkpoint信息" name="flinkCheckpointConfig"
                                      id="flinkCheckpointConfig" rows="2" readonly>${jobConfig.flinkCheckpointConfig!""}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="extJarPath"  class="col-sm-1 control-label">三方jar地址：</label>
                        <div class="col-sm-11">
                            <textarea class="form-control"  name="extJarPath" id="extJarPath" rows="2" readonly>${jobConfig.extJarPath!"无"}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="flinkSql" class="col-sm-1 control-label">*SQL语句：</label>
                        <div class="col-sm-12">
                            <pre>${jobConfig.flinkSql!""}</pre>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <a class="btn btn-primary btn-sm right-button" href="/admin/editPage?id=${jobConfig.id!""}" >编辑</a>
                        </div>
                    </div>
                </#if>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
    <#include "../../layout/bottom.ftl">
</body>
</html>
