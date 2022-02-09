<template>
  <div v-loading="loading" class="tab-container" size="mini" style="padding-top: 25px; padding-left: 15px;">

    <el-form ref="form" :model="form" label-width="250px">
      <el-form-item inline="true">
        <span slot="label">作者：朱慧培 & 林锦春 </span>
      </el-form-item>
      <el-form-item inline="true">
        <span slot="label">
          源码地址：
          <a href="https://github.com/ljc2008110/flink-streaming-platform-web" class="el-link" target="_blank">点击查看源码</a>
        </span>
      </el-form-item>
      <el-form-item inline="true">
        <span slot="label">钉钉群：34315096 </span>
      </el-form-item>
      <el-form-item inline="true">
        <img src="https://flink-sql.oss-cn-hangzhou.aliyuncs.com/dd2.png" style="width: 200px; height: 257px;">
      </el-form-item>
    </el-form>
  </div>
</template>
