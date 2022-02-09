import request from '@/utils/request'
import Qs from 'qs'

/**
 * 查询任务的最新savepoint记录
 * @param {任务编号} id
 * @returns
 */
export function queryLastSavePoint(id) {
  return querySavePointList10(id).then(response => {
    const { code, data, success, message } = response
    if (code !== '200' || !success) {
      this.$message({ type: 'error', message: (message || '请求数据异常！') })
      console.log(message)
      return
    }
    return (data && data.data) ? data.data[0] : {}
  }).catch(error => {
    this.$message({ type: 'error', message: '请求异常！' })
    console.log(error)
    return
  })
}

/**
 * 查询SavePoint历史列表（最近10条）
 * @param {任务编号} taskid
 * @returns
 */
export function querySavePointList10(taskid) {
  return request({
    url: '/querySavePointList10',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      taskid: taskid
    }
  })
}

/**
 * 手动增加savepoint保存地址
 * @param {任务编号} jobConfigId
 * @param {savepoint路径} savepointPath
 * @returns
 */
export function addSavepoint(jobConfigId, savepointPath) {
  return request({
    url: '/addSavepoint',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      jobConfigId: jobConfigId,
      savepointPath: savepointPath
    }
  })
}
