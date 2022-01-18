<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="">
    <title>新增配置</title>
    <#include "../../control/public_css_js.ftl">

    <link rel="stylesheet" type="text/css" href="/static/codemirror/css/codemirror.css?version=20210123"/>
    <link rel="stylesheet" type="text/css" href="/static/codemirror/theme/mbo.css?version=20210123"/>
    <link rel="stylesheet" type="text/css" href="/static/codemirror/hint/show-hint.css?version=20210123"/>
    <script type="text/javascript" src="/static/codemirror/js/codemirror.js?version=20210123"></script>
    <script type="text/javascript" src="/static/codemirror/js/css.js?version=20210123"></script>
    <script type="text/javascript" src="/static/codemirror/js/sql.js?version=20210123"></script>
    <script type="text/javascript" src="/static/codemirror/hint/show-hint.js?version=20210123"></script>
    <script type="text/javascript" src="/static/codemirror/hint/sql-hint.js?version=20210123"></script>
    <script type="text/javascript" src="/static/codemirror/hint/formatting.js?version=20210123"></script>
    <style>
        label{font-weight: 800}
    </style>
</head>

<body class="no-skin">
    <!-- start top-->
    <div id="navbar" class="navbar navbar-default          ace-save-state">
        <#include "../../layout/top.ftl">
    </div>
    <!-- end top-->
    <div class="container col-xs-12" id="main-container">
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
                    <li class="active">新增流任务</li>
                </ul>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <input type="hidden" name="jobType" id="jobType" value="0" />
                <div class="form-horizontal col-xs-12 panel-body">
                    <div class="form-group">
                        <label for="jobName" class="control-label col-sm-1">*任务名称：</label>
                        <div class="col-sm-3">
                            <input class="form-control" type="text" placeholder="任务名称" name="jobName" id="jobName">
                        </div>

                        <label for="deployMode" class="col-sm-1 control-label">*运行模式：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="deployMode">
                                <option value="">请选择</option>
                                <option value="YARN_PER">YARN_PER</option>
                                <option value="LOCAL">Local Cluster</option>
                                <option value="STANDALONE">Standalone Cluster</option>
                            </select>
                        </div>

                        <label for="alarmType" class="col-sm-1 control-label">辅助配置：</label>
                        <div class="col-sm-3 checkbox">
                            <label><input type="checkbox" name="alarmType" value="1" checked /> 钉钉告警</label>
                            <label><input type="checkbox" name="alarmType" value="2" /> Http回调告警</label>
                            <label><input type="checkbox" name="alarmType" value="3" checked /> 退出自动拉起</label>
                            <label><input type="checkbox" name="autoRestore" value="1" />自动恢复savepoint</label>
                        </div>

                        <div class="col-sm-1 checkbox">
                            <a href="https://github.com/ljc2008110/flink-streaming-platform-web/blob/master/docs/manual-sql.md"
                                target="_blank">配置说明</a>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="flinkRunConfig" class="col-sm-1 control-label">
                            *Flink运行配置：
                        </label>
                        <div class="col-sm-3">
                            <textarea class="form-control " name="flinkRunConfig" id="flinkRunConfig" rows="2"
                                      placeholder="如yarn模式：-yjm 1024m -ytm 1024m -p 1 -yqu streaming"></textarea>
                        </div>

                        <label for="flinkCheckpointConfig" class="col-sm-1 control-label">
                            Checkpoint信息：
                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true" data-toggle="tooltip" data-html="true" data-placement="auto top"
                                  title="不填默认不开启checkpoint机制，参数只支持
                                   -checkpointInterval
                                   -checkpointingMode
                                   -checkpointTimeout
                                   -checkpointDir
                                   -tolerableCheckpointFailureNumber
                                   -asynchronousSnapshots 如:-asynchronousSnapshots true
                                   -checkpointDir  hdfs//XXX/flink/checkpoint/
                                   -externalizedCheckpointCleanup DELETE_ON_CANCELLATION or RETAIN_ON_CANCELLATION"></span>
                        </label>
                        <div class="col-sm-7">
                            <textarea class="form-control"  name="flinkCheckpointConfig" id="flinkCheckpointConfig"
                                      placeholder="Checkpoint信息 如   -checkpointDir  hdfs//XXX/flink/checkpoint/" rows="2"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="extJarPath" class="col-sm-1 control-label">三方jar地址：</label>
                        <div class="col-sm-11">
                            <textarea class="form-control"  name="extJarPath" id="extJarPath" rows="2"
                                placeholder=" 自定义udf、连接器等jar地址，多个用换行(如 http://xxxx.com/udf.jar)。目前只支持http。"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="flinkSql" class="col-sm-1 control-label">*SQL语句：</label>
                        <div class="col-sm-12">
                            <textarea name="flinkSql" id="flinkSql"> </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-3">
                            <a class="btn btn-info btn-sm" onclick="addConfig()" href="#errorMessage">提交保存</a>
                            <a class="btn btn-success btn-sm " style="margin-left: 60px"
                               onclick="autoFormatSelection()"> 格式化代码</a>
                            <a class="btn btn-warning btn-sm" style="margin-left: 60px"  onclick="checkSql()">
                                sql预校验</a>
                        </div>
                        <div class="col-sm-9">
                            <div name="errorMessage" id="errorMessage">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="form-group">
                    <h5 style="color: #FFB752">
                        sql预校验 备注：只能校验单个sql语法正确与否，不能校验上下文之间关系，
                        如：这张表是否存在数据类型是否正确等无法校验，总之不能完全保证运行的时候sql没有异常，只是能校验出一些语法错误。
                    </h5>
                </div>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->


    <#include "../../layout/bottom.ftl">

</div><!-- /.main-container -->

<script>
    var flinkSqlVal;
    myTextarea = document.getElementById("flinkSql");
    var editor = CodeMirror.fromTextArea(myTextarea, {
        mode: "flink/x-fsql",
        lineNumbers: true,//显示行数
        matchBrackets: true,  // 括号匹配（这个需要导入codemirror的matchbrackets.js文件）
        indentUnit: 2,//缩进块用多少个空格表示 默认是2
        theme: "mbo",
        extraKeys: {'Ctrl': 'autocomplete'},//自定义快捷键
        hintOptions: {//自定义提示选项
            completeSingle: false, // 当匹配只有一项的时候是否自动补全
            tables: {
                'table.dynamic-table-options.enabled': [],
                'table.sql-dialect': [],
                'table.local-time-zone': [],
                'table.generated-code.max-length': [],
                'table.exec': ['state.ttl', 'source.idle-timeout',
                    'source.cdc-events-duplicate', 'window-agg.buffer-size-limit', 'source.cdc-events-duplicate',
                    'mini-batch.enabled', 'mini-batch.allow-latency', 'mini-batch.enabled', 'mini-batch.allow-latency',
                    'mini-batch.size', 'sink.not-null-enforcer', 'resource.default-parallelism', 'async-lookup.buffer-capacity',
                    'async-lookup.timeout'],
                'table.optimizer': ['distinct-agg.split.enabled',
                    'distinct-agg.split.bucket-num',
                    'agg-phase-strategy',
                    'reuse-sub-plan-enabled',
                    'reuse-source-enabled',
                    'source.predicate-pushdown-enabled',
                    'join-reorder-enabled'],
            }
        }
    });

    editor.setSize('auto', '550px');

    //代码自动提示功能，记住使用cursorActivity事件不要使用change事件，这是一个坑，那样页面直接会卡死
    editor.on('keypress', function () {
        editor.showHint()
    });



    function getSelectedRange() {
        return { from: editor.getCursor(true), to: editor.getCursor(false) };
    }

    function autoFormatSelection() {
        CodeMirror.commands["selectAll"](editor);
        var range = getSelectedRange();
        editor.autoFormatRange(range.from, range.to);
    }

    function commentSelection(isComment) {
        var range = getSelectedRange();
        editor.commentRange(isComment, range.from, range.to);
    }


    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });


    function checkSql(){
        flinkSqlVal=editor.getValue();
        $.post("../api/checkfSql", {
                flinkSql:   flinkSqlVal
            },
            function (data, status) {
                $("#errorMessage").removeClass();
                if (data!=null && data.success){
                    $("#errorMessage").addClass("alert alert-success alert-dismissable")
                    $("#errorMessage").html("校验Sql通过");
                }else{
                    $("#errorMessage").addClass("alert alert-danger alert-dismissable")
                    $("#errorMessage").html(data.message);

                }

            }
        );
    }


    function addConfig() {
        flinkSqlVal = editor.getValue();
        var chk_value =[];//定义一个数组
        $('input[name="alarmType"]:checked').each(function(){
            chk_value.push($(this).val());
        });
        $.post("../api/addConfig", {
                jobName: $('#jobName').val(),
                deployMode: $('#deployMode').val(),
                flinkRunConfig: $('#flinkRunConfig').val(),
                flinkCheckpointConfig: $('#flinkCheckpointConfig').val(),
                flinkSql: flinkSqlVal,
                jobType: $('#jobType').val(),
                alarmTypes:   chk_value.toString(),
                extJarPath: $('#extJarPath').val()
            },
            function (data, status) {
                $("#errorMessage").removeClass();
                if (data != null && data.success) {
                    skipUrl("/admin/listPage")
                } else {
                    $("#errorMessage").addClass("form-group alert alert-danger")
                    $("#errorMessage").html(data.message);

                }

            }
        );
    }

    $('#deployMode').change(function () {
        if ("LOCAL" == $(this).val()) {
            $("#configDiv").hide();
        } else {
            $("#configDiv").show();
        }
    })

</script>
</body>
</html>
