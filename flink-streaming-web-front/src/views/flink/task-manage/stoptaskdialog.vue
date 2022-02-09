<!-- 停止任务是否执行savepoint -->
<template>
  <el-dialog
    v-loading.fullscreen.lock="loading"
    :title="title"
    :visible.sync="visible"
    :close-on-click-modal="false"
    :modal-append-to-body="false"
    class="wl-dialog"
    width="400px"
    @close="doClose()"
  >
    <span class="wl-title">
      任务ID：{{ task ? task.id : '' }}<br>
      任务名称：{{ task ? task.jobName : '' }}<br>
      关闭该任务时是否执行savepoint？
    </span>
    <el-form ref="form" :model="form" :inline="true" :rules="rules" style="padding-top: 10px;" />
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="stopTaskWsp(true)">执行</el-button>
      <el-button type="warning" @click="stopTaskWsp(false)">不执行</el-button>
      <el-button @click="doCancel()">取 消</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { stopTask } from '@/api/task'

export default {
  name: 'StopTaskDialog',
  data() {
    return {
      loading: false,
      visible: false,
      title: '停止任务',
      form: { // 基本设置表单
      },
      rules: {},
      task: {}
    }
  },
  methods: {
    openDialog(task) {
      this.visible = true
      this.task = task
      this.form.taskid = task.id
      this.title = `停止任务[${task.jobName}]`
    },
    stopTaskWsp(isSavePoint) {
      const id = this.form.taskid
      this.loading = true
      stopTask(id, isSavePoint).then(response => {
        this.loading = false
        const { code, success, message } = response
        if (code !== '200' || !success) {
          this.$message({ type: 'error', message: (message || '请求数据异常！') })
          return
        }
        this.$message({ type: 'success', message: `停止任务[ ${this.task.jobName} ]成功!` })
        this.visible = false
        this.doClose()
        this.$parent.getTasks()
      }).catch(error => {
        this.loading = false
        this.$message({ type: 'error', message: '请求异常！' })
        console.log(error)
        this.visible = false
      })
    },
    doCancel() {
      this.visible = false
    },
    doClose() {
      this.list = []
      this.form.savepoint = ''
      this.form.taskid = ''
      this.task = null
      this.title = ''
      this.currentRow = null
      this.radioIndex = false
    }
  }
}
</script>

<style scoped>
  .wl-input {
    width: 660px;
  }
  .wl-title {
    font-size: 16px;
    font-weight: 600;
    cursor: default;
    padding-right: 2px;
  }
  .wl-popover {
    line-height: 8px;
    color: red;
  }
  .wl-popover p {
    line-height: 8px;
  }
  .wl-dialog >>> .el-dialog__body {
    padding: 5px 20px !important;
  }
  .wl-dialog >>> .el-radio {
    margin-left: 6px;
  }
  .wl-dialog >>> .el-radio .el-radio__label {
    display: none;
  }
</style>
