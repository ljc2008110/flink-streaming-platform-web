(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a6f7969e"],{"15f3":function(e,t,a){},"2dea":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticClass:"tab-container",attrs:{size:"mini"}},[a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"100px"}},[a("el-form-item",{attrs:{inline:"true"}},[a("span",{attrs:{slot:"label"},slot:"label"},[e._v("钉钉告警 "),a("el-popover",{attrs:{placement:"right",trigger:"hover"}},[a("p",[e._v("钉钉告警所需的url（如果不填写将无法告警）")]),a("p",[e._v("1、目前暂时钉钉群告警且只能添加一个 （当运行的任务挂掉的时候会告警）")]),a("p",[e._v("2、部署的机器需要支持外网否则无法支持钉钉发送")]),a("i",{staticClass:"el-icon-info",attrs:{slot:"reference"},slot:"reference"})])],1),a("el-input",{staticClass:"fl-form-item",attrs:{placeholder:"钉钉告警所需的url"},model:{value:e.form.dingding_alarm_url,callback:function(t){e.$set(e.form,"dingding_alarm_url",t)},expression:"form.dingding_alarm_url"}}),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.updateConfig("dingding_alarm_url",e.form.dingding_alarm_url)}}},[e._v("提交")]),a("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.deleteConfig("dingding_alarm_url")}}},[e._v("删除")]),a("el-button",{attrs:{type:"success"},on:{click:function(t){return e.testalarm("/testDingdingAlert")}}},[e._v("测试一下")])],1),a("el-form-item",{attrs:{inline:"true"}},[a("span",{attrs:{slot:"label"},slot:"label"},[e._v("自定义回调 "),a("el-popover",{attrs:{placement:"right",trigger:"hover",width:"800"}},[a("p",[e._v("自定义http回调告警")]),a("p",[e._v("1、回调url用于支持用户自定义告警，当任务出现告警时，会通过回调url通知 如 http://127.0.0.1/alarmCallback")]),a("p",[e._v("2、回调url支持post、get请求，请求参数是appId、jobName、deployMode 在自定义开发的时候必须要有这三个请求参数，且URN必须是alarmCallback")]),a("i",{staticClass:"el-icon-info",attrs:{slot:"reference"},slot:"reference"})])],1),a("el-input",{staticClass:"fl-form-item",attrs:{placeholder:"自定义http回调告警"},model:{value:e.form.callback_alarm_url,callback:function(t){e.$set(e.form,"callback_alarm_url",t)},expression:"form.callback_alarm_url"}}),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.updateConfig("callback_alarm_url",e.form.callback_alarm_url)}}},[e._v("提交")]),a("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.deleteConfig("callback_alarm_url")}}},[e._v("删除")]),a("el-button",{attrs:{type:"success"},on:{click:function(t){return e.testalarm("/testHttpAlert")}}},[e._v("测试一下")])],1)],1)],1)},r=[],o=(a("d3b7"),a("159b"),a("da71")),l={name:"AlarmCfg",data:function(){return{loading:!1,form:{dingding_alarm_url:"",callback_alarm_url:""}}},mounted:function(){this.queryConfig()},methods:{queryConfig:function(){var e=this;this.loading=!0,Object(o["a"])().then((function(t){e.loading=!1;var a=t.code,n=t.success,r=t.message,o=t.data;"200"===a&&n?(e.form.dingding_alarm_url="",e.form.callback_alarm_url="",o.forEach((function(t){"dingding_alarm_url"===t.key?e.form.dingding_alarm_url=t.val:"callback_alarm_url"===t.key&&(e.form.callback_alarm_url=t.val)}))):e.$message({type:"error",message:r||"请求数据异常！"})})).catch((function(t){e.loading=!1,e.$message({type:"error",message:"请求异常！"}),console.log(t)}))},updateConfig:function(e,t){var a=this;this.loading=!0,Object(o["e"])(e,t).then((function(e){a.loading=!1;var t=e.code,n=e.success,r=e.message;e.data;"200"===t&&n?(a.$message({type:"success",message:"更新成功！"}),a.queryConfig()):a.$message({type:"error",message:r||"请求数据异常！"})})).catch((function(e){a.loading=!1,a.$message({type:"error",message:"请求异常！"}),console.log(e)}))},testalarm:function(e){var t=this;console.log(e),Object(o["d"])(e).then((function(e){var a=e.code,n=e.success,r=e.message;e.data;"200"===a&&n?(t.$message({type:"success",message:"测试成功！"}),t.queryConfig()):t.$message({type:"error",message:r||"测试数据异常！"})})).catch((function(e){t.$message({type:"error",message:"测试异常！"}),console.log(e)}))},deleteConfig:function(e){var t=this,a="";"dingding_alarm_url"===e?a="钉钉告警通知配置":"callback_alarm_url"===e&&(a="自定义告警通知配置"),this.$confirm("是否删除".concat(a),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.loading=!0,Object(o["b"])(e).then((function(e){t.loading=!1;var a=e.code,n=e.success,r=e.message;e.data;"200"===a&&n?(t.$message({type:"success",message:"删除成功！"}),t.queryConfig()):t.$message({type:"error",message:r||"请求数据异常！"})})).catch((function(e){t.loading=!1,t.$message({type:"error",message:"请求异常！"}),console.log(e)}))}))}}},s=l,i=(a("db4c"),a("2877")),c=Object(i["a"])(s,n,r,!1,null,"483545c9",null);t["default"]=c.exports},da71:function(e,t,a){"use strict";a.d(t,"a",(function(){return l})),a.d(t,"c",(function(){return s})),a.d(t,"e",(function(){return i})),a.d(t,"d",(function(){return c})),a.d(t,"b",(function(){return u}));var n=a("b775"),r=a("4328"),o=a.n(r);function l(){return Object(n["a"])({url:"/alarmConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return o.a.stringify(e)}],data:{}})}function s(){return Object(n["a"])({url:"/sysConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return o.a.stringify(e)}],data:{}})}function i(e,t){return Object(n["a"])({url:"/upsertSynConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return o.a.stringify(e)}],data:{key:e,val:t}})}function c(e){return Object(n["a"])({url:e,method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function u(e){return Object(n["a"])({url:"/deleteConfig",method:"post",headers:{"Content-Type":"application/x-www-form-urlencoded"},transformRequest:[function(e){return o.a.stringify(e)}],data:{key:e}})}},db4c:function(e,t,a){"use strict";a("15f3")}}]);