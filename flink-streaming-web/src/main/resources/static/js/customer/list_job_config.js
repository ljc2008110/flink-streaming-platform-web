function stop(id) {
    if (confirm("是否执行savepoint？")) {
        stopSP(id, true);
    } else {
        stopSP(id, false);
    }
}
function stopSP(id, isSavePoint) {
    $.post("../api/stop", {
            id: id,
            isSavePoint: isSavePoint
        },
        function (data, status) {
            if (data!=null && data.success){
                $.gritter.add({
                    title: 'Success!',
                    text: '提交成功，请稍后刷新',
                    sticky: false,
                    time: 1500,
                    class_name: 'gritter-light,gritter-fontsize',
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


function deleteConfig(id) {
    if(confirm('确定要删除吗')==true){
        $.post("../api/delete", {
                id: id
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

function copyConfig(id) {
    $.post("../api/copyConfig", {
            id: id,
        },
        function (data, status) {
            if (data != null && data.success){
                $.gritter.add({
                    title: 'Success!',
                    text: '复制成功',
                    sticky: false,
                    time: 1500,
                    class_name: 'gritter-fontsize',
                    after_close: function(e) {
                        refreshForm();
                    }
                });
                return true;
            }

            alert("复制失败：" + data.message);
        }
    );
}

function openConfig(id) {
    $.post("../api/open", {
            id: id
        },
        function (data, status) {
            if (data!=null && data.success){
                $.gritter.add({
                    title: 'Success!',
                    text: '执行成功',
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


function closeConfig(id) {
    $.post("../api/close", {
            id: id
        },
        function (data, status) {
            if (data!=null && data.success){
                $.gritter.add({
                    title: 'Success!',
                    text: '执行成功',
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
                    time: 1500,
                    after_close: function(e) {
                    }
                });
            }

        }
    );
}

function  savePoint(id){
    if(confirm('确定要手执行savePoint吗？')==true){
        $.post("../api/savepoint", {
                id: id
            },
            function (data, status) {
                if (data!=null && data.success){
                    $.gritter.add({
                        title: 'Success!',
                        text: '执行成功，请稍后刷新',
                        sticky: false,
                        time: 1500,
                        class_name: 'gritter-light,gritter-fontsize',
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
    }else{
        return false;

    }
}

function searchForm(pageNum) {
    $("#pageNum").attr("value", pageNum);
    $("form[name='search']").submit();

}

function refreshForm() {
    $("form[name='search']").submit();

}

// @author Kevin.Lin
// @date 2021-12-29 11:30:39
function onekeyBackup() {
    if(confirm('确定要备份所有任务吗？') == true){
        $.post("../api/onekeyBackup", null,
            function (data, status) {
                if (data!=null && data.success){
                    $.gritter.add({
                        title: 'Success!',
                        text: '备份所有任务执行成功。',
                        sticky: false,
                        time: 1500,
                        class_name: 'gritter-light,gritter-fontsize',
                        after_close: function(e) {
                            refreshForm();
                        }
                    });
                }else{
                    $.gritter.add({
                        title: 'Fail!',
                        text: '备份所有任务执行失败：' + data.message,
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

// @author Kevin.Lin
// @date 2021-12-29 11:15:08
function onekeyRestore() {
    alert("一键恢复功能暂未开放！");
    return;
    if(confirm('确定要恢复所有任务吗？') == true){
        $.post("../api/onekeyRestore", {
                id: id
            },
            function (data, status) {
                if (data!=null && data.success){
                    $.gritter.add({
                        title: 'Success!',
                        text: '执行成功，请稍后刷新',
                        sticky: false,
                        time: 1500,
                        class_name: 'gritter-light,gritter-fontsize',
                        after_close: function(e) {
                            window.location.reload();
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
    }else{
        return false;

    }
}


