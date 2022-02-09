(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-25621689"],{2017:function(e,t,n){"use strict";n("cafe")},"9ed6":function(e,t,n){"use strict";n.r(t);var s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"login-container"},[n("div",{staticClass:"logindiv"},[n("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,autocomplete:"on","label-position":"left"}},[n("div",{staticClass:"title-container"},[n("h3",{staticClass:"title"},[e._v(" "+e._s(e.$t("login.title"))+" ")]),n("lang-select",{staticClass:"set-language"})],1),n("el-form-item",{attrs:{prop:"username"}},[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"user"}})],1),n("el-input",{ref:"username",attrs:{placeholder:e.$t("login.username"),name:"username",type:"text",tabindex:"1",autocomplete:"on"},model:{value:e.loginForm.username,callback:function(t){e.$set(e.loginForm,"username",t)},expression:"loginForm.username"}})],1),n("el-tooltip",{attrs:{content:"Caps lock is On",placement:"right",manual:""},model:{value:e.capsTooltip,callback:function(t){e.capsTooltip=t},expression:"capsTooltip"}},[n("el-form-item",{attrs:{prop:"password"}},[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"password"}})],1),n("el-input",{key:e.passwordType,ref:"password",attrs:{type:e.passwordType,placeholder:e.$t("login.password"),name:"password",tabindex:"2",autocomplete:"on"},on:{blur:function(t){e.capsTooltip=!1}},nativeOn:{keyup:[function(t){return e.checkCapslock(t)},function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleLogin(t)}]},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}}),n("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[n("svg-icon",{attrs:{"icon-class":"password"===e.passwordType?"eye":"eye-open"}})],1)],1)],1),n("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleLogin(t)}}},[e._v(" "+e._s(e.$t("login.logIn"))+" ")])],1)],1)])},o=[],a=(n("d3b7"),n("b64b"),n("61f7")),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-dropdown",{staticClass:"international",attrs:{trigger:"click"},on:{command:e.handleSetLanguage}},[n("div",[n("svg-icon",{attrs:{"class-name":"international-icon","icon-class":"language"}})],1),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",{attrs:{disabled:"zh"===e.language,command:"zh"}},[e._v(" 中文 ")]),n("el-dropdown-item",{attrs:{disabled:"en"===e.language,command:"en"}},[e._v(" English ")]),n("el-dropdown-item",{attrs:{disabled:"es"===e.language,command:"es"}},[e._v(" Español ")]),n("el-dropdown-item",{attrs:{disabled:"ja"===e.language,command:"ja"}},[e._v(" 日本語 ")])],1)],1)},i=[],l={computed:{language:function(){return this.$store.getters.language}},methods:{handleSetLanguage:function(e){this.$i18n.locale=e,this.$store.dispatch("app/setLanguage",e),this.$message({message:"Switch Language Success",type:"success"})}}},c=l,d=n("2877"),u=Object(d["a"])(c,r,i,!1,null,null,null),p=u.exports,g={name:"Login",components:{LangSelect:p},data:function(){var e=function(e,t,n){Object(a["e"])(t)?n():n(new Error("Please enter the correct user name"))},t=function(e,t,n){t.length<6?n(new Error("The password can not be less than 6 digits")):n()};return{loginForm:{username:"",password:""},loginRules:{username:[{required:!0,trigger:"blur",validator:e}],password:[{required:!0,trigger:"blur",validator:t}]},passwordType:"password",capsTooltip:!1,loading:!1,showDialog:!1,redirect:void 0,otherQuery:{}}},watch:{$route:{handler:function(e){var t=e.query;t&&(this.redirect=t.redirect,this.otherQuery=this.getOtherQuery(t))},immediate:!0}},created:function(){},mounted:function(){""===this.loginForm.username?this.$refs.username.focus():""===this.loginForm.password&&this.$refs.password.focus()},destroyed:function(){},methods:{checkCapslock:function(e){var t=e.key;this.capsTooltip=t&&1===t.length&&t>="A"&&t<="Z"},showPwd:function(){var e=this;"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick((function(){e.$refs.password.focus()}))},handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(t){if(!t)return console.log("error submit!!"),!1;e.loading=!0,e.$store.dispatch("user/login",e.loginForm).then((function(){e.$router.push({path:e.redirect||"/",query:e.otherQuery}),e.loading=!1})).catch((function(t){e.loading=!1,e.$message({type:"error",message:t})}))}))},getOtherQuery:function(e){return Object.keys(e).reduce((function(t,n){return"redirect"!==n&&(t[n]=e[n]),t}),{})}}},m=g,h=(n("2017"),n("ded6"),Object(d["a"])(m,s,o,!1,null,"db466f3c",null));t["default"]=h.exports},afdb:function(e,t,n){},cafe:function(e,t,n){},ded6:function(e,t,n){"use strict";n("afdb")}}]);