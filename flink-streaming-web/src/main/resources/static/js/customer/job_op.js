// 停止任务
function stopJob(id, name) {
    showStopModal(id, name);
}
// 停止任务是否保存savepoint
function stopSP(id, isSavePoint) {
    showDoProgress();
    $.post("../api/stop", {
            id: id,
            isSavePoint: isSavePoint
        },
        function (data, status) {
            if (data!=null && data.success){
                showDoResult(true);
            }else{
                showDoResult(false, data.message);
            }

        }
    );
}
// 启动任务
function start(id) {
    $.post("../api/start", {
            id: id
        },
        function (data, status) {
            if (data !== null && data.success) {
                $.gritter.add({
                    title: 'Success!',
                    text: '提交成功，请稍后刷新',
                    sticky: false,
                    time: 1500,
                    class_name: 'gritter-fontsize',
                    after_close: function(e) {
                        refreshForm();
                    }
                });
            }else{
                $.gritter.add({
                    title: 'Fail!',
                    text: '执行失败：' + data.message,
                    sticky: false,
                    time: 3000,
                    after_close: function(e) {
                    }
                });
            }
        }
    );
}
// 开启/关闭从savepoint恢复
function setAutoRestore(id, autoRestore) {
    var msg = '确定要' + (autoRestore==1 ? '开启' : '关闭') + '从savepoint恢复启动方式吗？';
    if(confirm(msg)==true){
        $.post("../api/autoRestore", {
                id: id,
                autoRestore: autoRestore
            },
            function (data, status) {
                if (data!=null && data.success){
                    refreshForm();
                }else{
                    $.gritter.add({
                        title: 'Fail!',
                        text: '执行失败：' + data.message,
                        sticky: false,
                        time: 3000,
                        after_close: function(e) {
                        }
                    });
                }
            }
        );

    }else{
        return false;
    }
}
// 开启配置
function openConfig(id) {
    showDoProgress();
    $.post("../api/open", {
            id: id
        },
        function (data, status) {
            if (data!=null && data.success){
                showDoResult(true, 50);
            }else{
                showDoResult(false, data.message, 50);
            }

        }
    );
}
// 关闭配置
function closeConfig(id, name) {
    showDoProgress();
    $.post("../api/close", {
            id: id
        },
        function (data, status) {
            if (data!=null && data.success){
                showDoResult(true, 50);
            }else{
                showDoResult(false, data.message, 50);
            }

        }
    );
}

