import request from '@/utils/request'
import Qs from 'qs'
// import {querySavePointList10} from "@/api/savepoint";

/**
 * 查询任务列表
 * @param {*} pageNum
 * @param {*} pageSize
 * @param {任务名称} jobName
 * @param {Flink运行任务编号} jobId
 * @param {任务类型} jobType
 * @param {任务状态} status
 * @param {开启状态} open
 * @returns
 */
export function getTasks(pageNum, pageSize, jobName, jobId, jobType, status, open) {
  return request({
    url: '/listTask',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      pageNum: pageNum,
      pageSize: pageSize,
      jobName: jobName,
      jobId: jobId,
      jobType: jobType,
      status: status,
      open: open
    }
  })
}

/**
 * 通过id获取任务
 * @param {任务配置ID} id
 * @returns
 */
export function getTask(id) {
  return request({
    url: '/getTask',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 开启/关闭从savepoint恢复
 * @param {任务编号} id
 * @param {开关} autoRestore
 * @returns
 */
export function setAutoRestore(id, autoRestore) {
  return request({
    url: '/setAutoRestore',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id,
      autoRestore: autoRestore
    }
  })
}

/**
 * 开启任务
 * @param {任务编号} id
 * @returns
 */
export function openTask(id) {
  return request({
    url: '/open',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 关闭任务
 * @param {任务编号} id
 * @returns
 */
export function closeTask(id) {
  return request({
    url: '/close',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 启动任务
 * @param {任务编号} id
 * @returns
 */
export function startTask(id) {
  return request({
    url: '/start',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 停止任务
 * @param {boolean} id
 * @param {是否执行savepoint} isSavePoint
 * @returns
 */
export function stopTask(id, isSavePoint) {
  return request({
    url: '/stop',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id,
      isSavePoint: isSavePoint
    }
  })
}

/**
 * 从savepoint备份地址中启动任务
 * @param {任务编号} id
 * @param {备份编号} savepointId
 * @returns
 */
export function startSavepoint(id, savepointId) {
  return request({
    url: '/start',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id,
      savepointId: savepointId
    }
  })
}

/**
 * 备份
 * @param {任务编号} id
 * @returns
 */
export function savePoint(id) {
  return request({
    url: '/savepoint',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 复制任务
 * @param {任务编号} id
 * @returns
 */
export function copyConfig(id) {
  return request({
    url: '/copyConfig',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 删除任务
 * @param {任务编号} id
 * @returns
 */
export function deleteTask(id) {
  return request({
    url: '/delete',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      id: id
    }
  })
}

/**
 * 预校验SQL
 * @param {Flink SQL} flinkSql
 * @returns
 */
export function checkfSql(flinkSql) {
  return request({
    url: '/checkfSql',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: {
      flinkSql: flinkSql
    }
  })
}

/**
 * 新增任务
 * @param {任务对象} data
 * jobName: my_online
 * jobDesc: 我的测试任务
 * deployMode: LOCAL
 * flinkRunConfig:
 * flinkCheckpointConfig:  -checkpointInterval 300000 -checkpointDir file:///home/flink/flink-streaming-platform-web/savepoint
 * flinkSql: --
 * jobType: 0
 * alarmTypes:
 * extJarPath:
 * customArgs:
 * customMainClass: com.xxxy.Demo
 * customJarUrl: http://test.coahuae.com/xxx.jar
 * @returns
 */
export function addConfig(data) {
  return request({
    url: '/addConfig',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: data
  })
}

/**
 * 修改任务
 * @param {任务对象} data
 * id: 1
 * jobName: my_online
 * jobDesc: 我的测试任务
 * deployMode: LOCAL
 * flinkRunConfig:
 * flinkCheckpointConfig:  -checkpointInterval 300000 -checkpointDir file:///home/flink/flink-streaming-platform-web/savepoint
 * flinkSql: --
 * jobType: 0
 * alarmTypes:
 * extJarPath:
 * customArgs:
 * customMainClass: com.xxxy.Demo
 * customJarUrl: http://test.coahuae.com/xxx.jar
 * @returns
 */
export function editConfig(data) {
  return request({
    url: '/editConfig',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: data
  })
}

/**
 * 查询历史版本列表
 * @param {*} data (jobConfigId,jobName)
 * @returns
 */
export function getTaskHistory(data) {
  return request({
    url: '/jobConfigHistoryPage',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: data
  })
}

/**
 * 查询历史版本详情
 * @param {*} id
 * @returns
 */
export function getTaskHistoryDetail(id) {
  return request({
    url: '/jobConfigHistoryDetail',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    transformRequest: [function(data) { return Qs.stringify(data) }],
    data: { id: id }
  })
}

/**
 * 获取任务状态描述
 * @param {任务状态} status
 * @returns
 */
export function getJobStatusDesc(status) { // 任务状态
  switch (status) {
    case -2:
      return '未知'
    case -1:
      return '失败'
    case 0:
      return '停止'
    case 1:
      return '运行中'
    case 2:
      return '启动中'
    case 3:
      return '提交成功'
    case 95:
      return '退出中'
    case 96:
      return '退出中'
    case 97:
      return '已退出'
    case 98:
      return '已完成'
    case 99:
      return '重启中'
    case 100:
      return '已挂起'
    case 'UNKNOWN':
      return '未知'
    case 'FAIL':
      return '失败'
    case 'STOP':
      return '停止'
    case 'RUN':
      return '运行中'
    case 'STARTING':
      return '启动中'
    case 'SUCCESS':
      return '提交成功'
    case 'CANCELING':
      return '退出中'
    case 'FAILING':
      return '退出中'
    case 'CANCELED':
      return '已退出'
    case 'FINISHED':
      return '已完成'
    case 'RESTARTING':
      return '重启中'
    case 'SUSPENDED':
      return '已挂起'
    default:
      return status + '　'
  }
}
