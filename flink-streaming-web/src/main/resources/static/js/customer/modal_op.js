const stopJobTitle = '停止任务({jobName})';
const openConfigTitle = '开启配置({jobName})';
const closeConfigTitle = '关闭配置({jobName})';
const stopJobConfirmTextNode = document.createTextNode('关闭该任务时是否执行savepoint？');

function showModal(title, body, footer) {
    $('#globalModal').on('show.bs.modal', function (event) {
        var modal = $(this);  //get modal itself
        modal.find('.modal-title').html(title);
        modal.find('.modal-body').html(body);
        modal.find('.modal-footer').html(footer);
    });
    $('#globalModal').modal('show');
}

function showStopModal(id, name) {
    $('#modalJobId').val(id);
    $('#modalJobIdLabel').html(id);
    $('#modalJobNameLabel').html(name);
    const title = stopJobTitle.replace('{jobName}', name);
    const body = $('#jobBody').append(stopJobConfirmTextNode).html();
    const footer = $('#stopJobFooter').html();
    showModal(title, body, footer);
}

function showDoProgress() {
    $('#globalModal').find('.modal-body').html($('#stopJobProgressBody').html());
    $('#globalModal').find('.modal-footer').html($('#stopJobProgressFooter').html());
}

function showDoResult(isSuccess, message, reloadAfterTime) {
    const resultNode = document.createElement('p');
    if (isSuccess) {
        resultNode.setAttribute('class', 'label label-success');
        resultNode.appendChild(document.createTextNode('执行成功。'));
    } else {
        resultNode.setAttribute('class', 'label label-danger');
        resultNode.appendChild(document.createTextNode('执行失败。' + message));
    }
    $('#globalModal').find('.modal-body').html(resultNode);
    $('#globalModal').find('.modal-footer').html($('#onlyCloseFooter').html());
    $('#globalModal').on('hide.bs.modal', function (event) {
        if (reloadAfterTime) {
            reloadAfter(reloadAfterTime);
        }
    });
}

function showOpenModal(id, name, openStatus) {
    $('#modalJobId').val(id);
    $('#modalJobIdLabel').html(id);
    $('#modalJobNameLabel').html(name);
    let title, body, footer;
    if (openStatus == 0) {
        title = openConfigTitle.replace('{jobName}', name);
        body = $('#jobBody').append("确定要开启该任务配置吗？").html();
        footer = $('#openJobConfigFooter').html();
    } else {
        title = closeConfigTitle.replace('{jobName}', name);
        body = $('#jobBody').append("确定要关闭该任务配置吗？").html();
        footer = $('#closeJobConfigFooter').html();
    }
    showModal(title, body, footer);
}
