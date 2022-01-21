
<!-- 通用模态框（Modal） -->
<div class="modal fade" id="globalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                </h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div id="jobBody" hidden>
    <input type="hidden" id="modalJobId" />
    任务ID：<span id="modalJobIdLabel" class="danger"></span><br/>
    任务名称：<span id="modalJobNameLabel" class="danger"></span><br/>
</div>

<div id="stopJobFooter" hidden>
    <button type="button" class="btn btn-primary" onclick="stopSP($('#modalJobId').val(), true);">
        执行
    </button>
    <button type="button" class="btn btn-warning" onclick="stopSP($('#modalJobId').val(), false);">
        不执行
    </button>
    <button type="button" class="btn btn-default" data-dismiss="modal">
        取消
    </button>
</div>

<div id="stopJobProgressBody" hidden>
    <div class="progress progress-striped">
        <div class="progress-bar progress-bar-success active" role="progressbar"
             aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
             style="width: 100%;">
            <span class="sr-only">100% 完成（成功）</span>
        </div>
    </div>
</div>

<div id="onlyCloseFooter" hidden>
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
    </button>
</div>

<div id="openJobConfigFooter" hidden>
    <button type="button" class="btn btn-primary" onclick="openConfig($('#modalJobId').val());">
        确认
    </button>
    <button type="button" class="btn btn-default" data-dismiss="modal">
        取消
    </button>
</div>

<div id="closeJobConfigFooter" hidden>
    <button type="button" class="btn btn-primary" onclick="closeConfig($('#modalJobId').val());">
        确认
    </button>
    <button type="button" class="btn btn-default" data-dismiss="modal">
        取消
    </button>
</div>