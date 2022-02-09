(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-60a048cc"],{5424:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[0==t.subPageFlag?a("div",{class:t.backFlag?"fl-container2":"fl-container"},[1==t.backFlag?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"返回",placement:"right"}},[a("i",{ref:"backbutton",staticClass:"el-icon-d-arrow-left fl-back",on:{click:function(e){return t.handleBack()}}})]):t._e(),a("el-form",{ref:"queryform",attrs:{model:t.queryform,inline:!0}},[a("el-form-item",[a("el-input",{staticClass:"wl-input",attrs:{placeholder:"Flink任务Id"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobId,callback:function(e){t.$set(t.queryform,"jobId",e)},expression:"queryform.jobId"}})],1),a("el-form-item",[a("el-input",{staticClass:"wl-input",attrs:{placeholder:"任务Id"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobConfigId,callback:function(e){t.$set(t.queryform,"jobConfigId",e)},expression:"queryform.jobConfigId"}})],1),a("el-form-item",[a("el-input",{staticClass:"wl-input",attrs:{placeholder:"任务名称(模糊查询)"},on:{input:function(e){return t.handleQuery()}},model:{value:t.queryform.jobName,callback:function(e){t.$set(t.queryform,"jobName",e)},expression:"queryform.jobName"}},[a("el-button",{staticClass:"wl-search",attrs:{slot:"append",type:"primary",icon:"el-icon-search"},on:{click:function(e){return t.handleQuery()}},slot:"append"})],1)],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.handleQuery()}}},[t._v("查询")])],1)],1),a("el-table",{staticClass:"wl-table",attrs:{data:t.list,"header-cell-style":{background:"#f4f4f5","text-align":"center"},border:""}},[a("el-table-column",{attrs:{prop:"id","show-overflow-tooltip":!0,label:"编号","min-width":"60",width:"80",align:"center",fixed:""}}),a("el-table-column",{attrs:{"show-overflow-tooltip":!0,label:"任务ID","min-width":"60",width:"80",align:"center",fixed:""},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{"margin-right":"5px"}},[t._v(t._s(e.row.jobConfigId))]),a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:t.getTaskTypeName(e.row.jobTypeEnum),placement:"right"}},["SQL_STREAMING"===e.row.jobTypeEnum?a("i",{staticClass:"iconfont my-icon-jiediansql",staticStyle:{"font-size":"16px"}}):t._e(),"SQL_BATCH"===e.row.jobTypeEnum?a("i",{staticClass:"iconfont my-icon-file-SQL",staticStyle:{"font-size":"16px"}}):t._e(),"JAR"===e.row.jobTypeEnum?a("i",{staticClass:"iconfont my-icon-suffix-jar",staticStyle:{"font-size":"16px"}}):t._e()])]}}],null,!1,845553989)}),a("el-table-column",{attrs:{prop:"jobName","show-overflow-tooltip":!0,label:"任务名称","min-width":"100",align:"center",fixed:""}}),a("el-table-column",{attrs:{prop:"deployMode","show-overflow-tooltip":!0,label:"运行模式",width:"105",align:"center"}}),a("el-table-column",{attrs:{prop:"jobStatus",label:"状态",width:"90",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[-2===e.row.jobStatus||"UNKNOWN"===e.row.jobStatus?a("el-tag",{attrs:{type:"info",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):-1===e.row.jobStatus||"FAIL"===e.row.jobStatus?a("el-tag",{attrs:{type:"danger",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):0===e.row.jobStatus||"STOP"===e.row.jobStatus?a("el-tag",{attrs:{type:"warning",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):1===e.row.jobStatus||"RUN"===e.row.jobStatus?a("el-tag",{attrs:{type:"success",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):2===e.row.jobStatus||"STARTING"===e.row.jobStatus?a("el-tag",{attrs:{size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):3===e.row.jobStatus||"SUCCESS"===e.row.jobStatus?a("el-tag",{attrs:{type:"success",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))]):a("el-tag",{attrs:{type:"info",size:"mini"}},[t._v(t._s(t.getStatusDesc(e.row.jobStatus)))])]}}],null,!1,3120881821)}),a("el-table-column",{attrs:{prop:"jobId","show-overflow-tooltip":!0,label:"Flink任务Id","min-width":"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.jobId))])]}}],null,!1,146262124)}),a("el-table-column",{attrs:{prop:"createTime","show-overflow-tooltip":!0,label:"创建时间","min-width":"100",width:"135",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(t.formatDateTime(e.row.editTime)))])]}}],null,!1,960744588)}),a("el-table-column",{attrs:{prop:"updateTime","show-overflow-tooltip":!0,label:"修改时间","min-width":"100",width:"135",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(t.formatDateTime(e.row.editTime)))])]}}],null,!1,960744588)}),a("el-table-column",{attrs:{prop:"operate",label:"操作",width:"100",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("router-link",{attrs:{to:{name:"ViewLogDetail",params:{flag:"loglist",context:t.queryContent(),data:e.row}}}},[a("el-link",{attrs:{type:"info",icon:"el-icon-message"}},[t._v("详情")])],1)]}}],null,!1,1421094889)})],1),t.pageshow?a("el-pagination",{staticClass:"wl-pagination",attrs:{background:"",layout:"total, sizes, prev, pager, next","current-page":t.currentPage,"page-sizes":[10,15,20,50,100,150,200],"page-size":t.pageSize,total:t.count},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}):t._e()],1):t._e(),a("router-view")],1)},n=[],r=(a("b0c0"),a("ac1f"),a("5319"),a("498a"),a("8916")),s={name:"LogManage",data:function(){return{loading:!1,subPageFlag:!1,backFlag:!1,params:{flag:"",data:{},context:""},queryform:{jobId:"",jobConfigId:"",jobName:""},list:[],count:0,pageSize:15,currentPage:1,pageshow:!0}},mounted:function(){if("FlinkLogManage"===this.$route.name){this.subPageFlag=!1;var t=this.$route.params;t&&(this.queryform.jobId=t.jobId?t.jobId:"",this.queryform.jobConfigId=t.jobConfigId?t.jobConfigId:"",this.queryform.jobName=t.jobName?t.jobName:"",t.currentPage&&(this.count=t.count,this.currentPage=t.currentPage,this.pageSize=t.pageSize),"tasklist"===t.flag&&(this.backFlag=!0,this.params.flag=t.flag,this.params.data=t.data,this.params.context=t.context),t.parentContent&&(this.backFlag=!0,this.params.context=t.parentContent)),this.handleQuery()}else this.subPageFlag=!0},methods:{handleBack:function(){this.$router.replace({name:"FlinkTaskManage",params:this.params.context})},queryContent:function(){return{count:this.count,currentPage:this.currentPage,pageSize:this.pageSize,jobId:this.queryform.jobId,jobConfigId:this.queryform.jobConfigId,jobName:this.queryform.jobName,parentContent:this.params.context}},handleQuery:function(t){var e=this;this.pageshow=!1,this.getLogs(),this.$nextTick((function(){e.pageshow=!0}))},handleSizeChange:function(t){this.pageSize=t,this.handleQuery()},handleCurrentChange:function(t){this.currentPage=t,this.handleQuery()},getLogs:function(){var t=this;this.loading=!0;var e=this.queryform.jobName?this.queryform.jobName.trim():"",a=this.queryform,o=a.jobId,n=a.jobConfigId;Object(r["b"])(this.currentPage,this.pageSize,o,n,e).then((function(e){t.loading=!1;var a=e.code,o=e.success,n=e.message,r=e.data;"200"===a&&o?(t.list=r.data,t.count=r.total,t.count>0&&0==t.list.length&&(t.currentPage=Math.ceil(t.count/t.pageSize),t.getLogs())):t.$message({type:"error",message:n||"请求数据异常！"})})).catch((function(e){t.loading=!1,t.$message({type:"error",message:"请求异常！"}),console.log(e)}))},formatDateTime:function(t){return this.dayjs(t).format("YYYY-MM-DD HH:mm:ss")},getTaskTypeName:function(t){switch(t){case"SQL_STREAMING":return"SQL流任务";case"SQL_BATCH":return"SQL批任务";case"JAR":return"JAR包";default:return t}},getStatusDesc:function(t){switch(t){case-2:return"未知";case-1:return"失败";case 0:return"停止";case 1:return"运行中";case 2:return"启动中";case 3:return"提交成功";case"UNKNOWN":return"未知";case"FAIL":return"失败";case"STOP":return"停止";case"RUN":return"运行中";case"STARTING":return"启动中";case"SUCCESS":return"提交成功";default:return""}}}},i=s,l=(a("f29a"),a("2877")),u=Object(l["a"])(i,o,n,!1,null,"0e4faf3a",null);e["default"]=u.exports},8916:function(t,e,a){"use strict";a.d(e,"b",(function(){return s})),a.d(e,"a",(function(){return i}));var o=a("b775"),n=a("4328"),r=a.n(n);function s(t,e,a,n,s){return Object(o["a"])({url:"/logList",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return r.a.stringify(t)}],data:{pageNum:t,pageSize:e,jobId:a,jobConfigId:n,jobName:s}})}function i(t){return Object(o["a"])({url:"/logDetail",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(t){return r.a.stringify(t)}],data:{logid:t}})}},e169:function(t,e,a){},f29a:function(t,e,a){"use strict";a("e169")}}]);