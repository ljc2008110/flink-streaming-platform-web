(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d031122e"],{3763:function(t,e,n){"use strict";n("c2fb")},5424:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[0==t.subPageFlag?n("div",{class:t.backFlag?"fl-container2":"fl-container"},[1==t.backFlag?n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"返回",placement:"right"}},[n("i",{ref:"backbutton",staticClass:"el-icon-d-arrow-left fl-back",on:{click:function(e){return t.handleBack()}}})]):t._e(),n("el-form",{ref:"queryform",attrs:{model:t.queryform,inline:!0}},[n("el-form-item",[n("el-input",{staticClass:"wl-input",attrs:{placeholder:"Flink任务Id"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobId,callback:function(e){t.$set(t.queryform,"jobId",e)},expression:"queryform.jobId"}})],1),n("el-form-item",[n("el-input",{staticClass:"wl-input",attrs:{placeholder:"任务Id"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobConfigId,callback:function(e){t.$set(t.queryform,"jobConfigId",e)},expression:"queryform.jobConfigId"}})],1),n("el-form-item",[n("el-input",{staticClass:"wl-input",attrs:{placeholder:"任务名称(模糊查询)"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobName,callback:function(e){t.$set(t.queryform,"jobName",e)},expression:"queryform.jobName"}},[n("el-button",{staticClass:"wl-search",attrs:{slot:"append",type:"primary",icon:"el-icon-search"},on:{click:function(e){return t.handleQuery()}},slot:"append"})],1)],1),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")])],1)],1),n("el-table",{staticClass:"wl-table",attrs:{data:t.list,"header-cell-style":{background:"#f4f4f5","text-align":"center"},border:""}},[n("el-table-column",{attrs:{prop:"id","show-overflow-tooltip":!0,label:"编号","min-width":"60",width:"80",align:"center",fixed:""}}),n("el-table-column",{attrs:{"show-overflow-tooltip":!0,label:"任务ID","min-width":"60",width:"80",align:"center",fixed:""},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",{staticStyle:{"margin-right":"5px"}},[t._v(t._s(e.row.jobConfigId))]),n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:t.getTaskTypeName(e.row.jobTypeEnum),placement:"right"}},["SQL_STREAMING"===e.row.jobTypeEnum?n("i",{staticClass:"iconfont my-icon-jiediansql",staticStyle:{"font-size":"16px"}}):t._e(),"SQL_BATCH"===e.row.jobTypeEnum?n("i",{staticClass:"iconfont my-icon-file-SQL",staticStyle:{"font-size":"16px"}}):t._e(),"JAR"===e.row.jobTypeEnum?n("i",{staticClass:"iconfont my-icon-suffix-jar",staticStyle:{"font-size":"16px"}}):t._e()])]}}],null,!1,845553989)}),n("el-table-column",{attrs:{prop:"jobName","show-overflow-tooltip":!0,label:"任务名称","min-width":"100",align:"center",fixed:""}}),n("el-table-column",{attrs:{prop:"deployMode","show-overflow-tooltip":!0,label:"运行模式",width:"105",align:"center"}}),n("el-table-column",{attrs:{prop:"jobStatus",label:"状态",width:"90",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[-2===e.row.jobStatus||"UNKNOWN"===e.row.jobStatus?n("el-tag",{attrs:{type:"info",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):-1===e.row.jobStatus||"FAIL"===e.row.jobStatus?n("el-tag",{attrs:{type:"danger",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):0===e.row.jobStatus||"STOP"===e.row.jobStatus?n("el-tag",{attrs:{type:"warning",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):1===e.row.jobStatus||"RUN"===e.row.jobStatus?n("el-tag",{attrs:{type:"success",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):2===e.row.jobStatus||"STARTING"===e.row.jobStatus?n("el-tag",{attrs:{size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):3===e.row.jobStatus||"SUCCESS"===e.row.jobStatus?n("el-tag",{attrs:{type:"success",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):n("el-tag",{attrs:{type:"info",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))])]}}],null,!1,3120881821)}),n("el-table-column",{attrs:{prop:"jobId","show-overflow-tooltip":!0,label:"Flink任务Id","min-width":"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(e.row.jobId))])]}}],null,!1,146262124)}),n("el-table-column",{attrs:{prop:"createTime","show-overflow-tooltip":!0,label:"创建时间","min-width":"100",width:"135",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(t.formatDateTime(e.row.editTime)))])]}}],null,!1,960744588)}),n("el-table-column",{attrs:{prop:"updateTime","show-overflow-tooltip":!0,label:"修改时间","min-width":"100",width:"135",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(t.formatDateTime(e.row.editTime)))])]}}],null,!1,960744588)}),n("el-table-column",{attrs:{prop:"operate",label:"操作",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("router-link",{attrs:{to:{name:"ViewLogDetail",params:{flag:"loglist",context:t.queryContent(),data:e.row}}}},[n("el-link",{attrs:{type:"info",icon:"el-icon-message"}},[t._v("详情")])],1)]}}],null,!1,1421094889)})],1),t.pageshow?n("el-pagination",{staticClass:"wl-pagination",attrs:{background:"",layout:"total, sizes, prev, pager, next","current-page":t.currentPage,"page-sizes":[10,15,20,50,100,150,200],"page-size":t.pageSize,total:t.count},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}):t._e()],1):t._e(),n("router-view")],1)},o=[],a=(n("b0c0"),n("ac1f"),n("5319"),n("498a"),n("8916")),i=n("b199"),s={name:"LogManage",data:function(){return{loading:!1,subPageFlag:!1,backFlag:!1,params:{flag:"",data:{},context:""},queryform:{jobId:"",jobConfigId:"",jobName:""},list:[],count:0,pageSize:15,currentPage:1,pageshow:!0}},mounted:function(){if("FlinkLogManage"===this.$route.name){this.subPageFlag=!1;var t=this.$route.params;t&&(this.queryform.jobId=t.jobId?t.jobId:"",this.queryform.jobConfigId=t.jobConfigId?t.jobConfigId:"",this.queryform.jobName=t.jobName?t.jobName:"",t.currentPage&&(this.count=t.count,this.currentPage=t.currentPage,this.pageSize=t.pageSize),"tasklist"===t.flag&&(this.backFlag=!0,this.params.flag=t.flag,this.params.data=t.data,this.params.context=t.context),t.parentContent&&(this.backFlag=!0,this.params.context=t.parentContent)),this.handleQuery()}else this.subPageFlag=!0},methods:{handleBack:function(){this.$router.replace({name:"FlinkTaskManage",params:this.params.context})},queryContent:function(){return{count:this.count,currentPage:this.currentPage,pageSize:this.pageSize,jobId:this.queryform.jobId,jobConfigId:this.queryform.jobConfigId,jobName:this.queryform.jobName,parentContent:this.params.context}},handleQuery:function(t){var e=this;this.pageshow=!1,this.getLogs(),this.$nextTick((function(){e.pageshow=!0}))},handleSizeChange:function(t){this.pageSize=t,this.handleQuery()},handleCurrentChange:function(t){this.currentPage=t,this.handleQuery()},getLogs:function(){var t=this;this.loading=!0;var e=this.queryform.jobName?this.queryform.jobName.trim():"",n=this.queryform,r=n.jobId,o=n.jobConfigId;Object(a["b"])(this.currentPage,this.pageSize,r,o,e).then((function(e){t.loading=!1;var n=e.code,r=e.success,o=e.message,a=e.data;"200"===n&&r?(t.list=a.data,t.count=a.total,t.count>0&&0===t.list.length&&(t.currentPage=Math.ceil(t.count/t.pageSize),t.getLogs())):t.$message({type:"error",message:o||"请求数据异常！"})})).catch((function(e){t.loading=!1,t.$message({type:"error",message:"请求异常！"}),console.log(e)}))},formatDateTime:function(t){return this.dayjs(t).format("YYYY-MM-DD HH:mm:ss")},getTaskTypeName:function(t){switch(t){case"SQL_STREAMING":return"SQL流任务";case"SQL_BATCH":return"SQL批任务";case"JAR":return"JAR包";default:return t}},getStatusDesc:function(t){return Object(i["g"])(t)}}},u=s,c=(n("3763"),n("2877")),l=Object(c["a"])(u,r,o,!1,null,"63437f68",null);e["default"]=l.exports},8916:function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"a",(function(){return s}));var r=n("b775"),o=n("4328"),a=n.n(o);function i(t,e,n,o,i){return Object(r["a"])({url:"/logList",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{pageNum:t,pageSize:e,jobId:n,jobConfigId:o,jobName:i}})}function s(t){return Object(r["a"])({url:"/logDetail",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{logid:t}})}},b199:function(t,e,n){"use strict";n.d(e,"j",(function(){return i})),n.d(e,"h",(function(){return s})),n.d(e,"m",(function(){return u})),n.d(e,"k",(function(){return c})),n.d(e,"c",(function(){return l})),n.d(e,"o",(function(){return f})),n.d(e,"p",(function(){return d})),n.d(e,"n",(function(){return p})),n.d(e,"l",(function(){return m})),n.d(e,"d",(function(){return g})),n.d(e,"e",(function(){return h})),n.d(e,"b",(function(){return b})),n.d(e,"a",(function(){return w})),n.d(e,"f",(function(){return y})),n.d(e,"i",(function(){return j})),n.d(e,"g",(function(){return S}));var r=n("b775"),o=n("4328"),a=n.n(o);function i(t,e,n,o,i,s,u){return Object(r["a"])({url:"/listTask",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{pageNum:t,pageSize:e,jobName:n,jobId:o,jobType:i,status:s,open:u}})}function s(t){return Object(r["a"])({url:"/getTask",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function u(t,e){return Object(r["a"])({url:"/setAutoRestore",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t,autoRestore:e}})}function c(t){return Object(r["a"])({url:"/open",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function l(t){return Object(r["a"])({url:"/close",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function f(t){return Object(r["a"])({url:"/start",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function d(t,e){return Object(r["a"])({url:"/stop",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t,isSavePoint:e}})}function p(t,e){return Object(r["a"])({url:"/start",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t,savepointId:e}})}function m(t){return Object(r["a"])({url:"/savepoint",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function g(t){return Object(r["a"])({url:"/copyConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function h(t){return Object(r["a"])({url:"/delete",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{id:t}})}function b(t){return Object(r["a"])({url:"/checkfSql",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:{flinkSql:t}})}function w(t){return Object(r["a"])({url:"/addConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:t})}function y(t){return Object(r["a"])({url:"/editConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:t})}function j(t){return Object(r["a"])({url:"/jobConfigHistoryPage",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return a.a.stringify(t)}],data:t})}function S(t){switch(t){case-2:return"未知";case-1:return"失败";case 0:return"停止";case 1:return"运行中";case 2:return"启动中";case 3:return"提交成功";case 95:return"退出中";case 96:return"退出中";case 97:return"已退出";case 98:return"已完成";case 99:return"重启中";case 100:return"已挂起";case"UNKNOWN":return"未知";case"FAIL":return"失败";case"STOP":return"停止";case"RUN":return"运行中";case"STARTING":return"启动中";case"SUCCESS":return"提交成功";case"CANCELING":return"退出中";case"FAILING":return"退出中";case"CANCELED":return"已退出";case"FINISHED":return"已完成";case"RESTARTING":return"重启中";case"SUSPENDED":return"已挂起";default:return t+"　"}}},c2fb:function(t,e,n){}}]);