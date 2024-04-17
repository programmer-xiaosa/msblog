<template>
  <div class="setting">
    <el-form ref="form" class="cus-search-box cus-dialog-form-box" :model="form" :rules="rules" label-position="top">
      <el-form-item label="博客名称" prop="blogName">
        <el-input v-model="form.blogName" placeholder="请输入博客名称" />
      </el-form-item>

      <el-form-item label="作者名" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者名" />
      </el-form-item>

      <el-form-item label="文档库地址" prop="docLibrary">
        <el-input v-model="form.docLibrary" placeholder="请输入文档库地址" />
      </el-form-item>

      <el-form-item label="GitHub 主页访问地址" prop="githubHomepage">
        <el-input v-model="form.githubHomepage" placeholder="请输入GitHub 主页访问地址" />
      </el-form-item>

      <el-form-item label="Gitee 主页访问地址" prop="giteeHomepage">
        <el-input v-model="form.giteeHomepage" placeholder="请输入Gitee 主页访问地址" />
      </el-form-item>

      <el-form-item label="B站 主页访问地址" prop="bilibiliHomepage">
        <el-input v-model="form.bilibiliHomepage" placeholder="请输入B站 主页访问地址" />
      </el-form-item>

      <el-form-item
        label="博客 LOGO"
        prop="logo"
      >
        <el-upload
          class="avatar-uploader"
          :action="baseUrl + '/common/upload'"
          :show-file-list="false"
          :on-success="(res) => {
            return form.logo = res.data
          }"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="form.logo" :src="form.logo" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>

      <el-form-item
        label="作者头像"
        prop="avatar"
      >
        <el-upload
          class="avatar-uploader"
          :action="baseUrl + '/common/upload'"
          :show-file-list="false"
          :on-success="(res) => {
            return form.avatar = res.data
          }"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>

      <el-form-item label="介绍语" prop="introduction">
        <el-input v-model="form.introduction" type="textarea" :rows="3" placeholder="请输入介绍语" />
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button type="primary" @click="submitForm">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getSettings, updateSetting } from '@/api/setting'

export default {
  name: 'Setting',
  data() {
    return {
      fullscreenLoading: false,
      baseUrl: process.env.VUE_APP_BASE_API,
      form: {
        author: '',
        avatar: '',
        blogName: '',
        docLibrary: '',
        giteeHomepage: '',
        githubHomepage: '',
        bilibiliHomepage: '',
        introduction: '',
        logo: ''
      },
      // 表单校验
      rules: {
        author: [{ required: true, message: '不能为空', trigger: 'blur' }],
        avatar: [{ required: true, message: '不能为空', trigger: 'blur' }],
        blogName: [{ required: true, message: '不能为空', trigger: 'blur' }],
        docLibrary: [{ required: true, message: '不能为空', trigger: 'blur' }],
        giteeHomepage: [{ required: true, message: '不能为空', trigger: 'blur' }],
        githubHomepage: [{ required: true, message: '不能为空', trigger: 'blur' }],
        bilibiliHomepage: [{ required: true, message: '不能为空', trigger: 'blur' }],
        introduction: [{ required: true, message: '不能为空', trigger: 'blur' }],
        logo: [{ required: true, message: '不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      const loading = this.$loading({
        lock: true,
        text: '加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      try {
        const res = await getSettings()
        this.form = res.data
      } catch (error) {
        console.log(error)
      } finally {
        loading.close()
      }
    },
    submitForm() {
      const loading = this.$loading({
        lock: true,
        text: '加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.$refs.form.validate(async(valid) => {
        if (valid) {
          try {
            const deepForm = this.lodash.cloneDeep(this.form)
            const res = await updateSetting(deepForm)
            if (res.errCode === 0) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
              this.fetchData()
            }
          } catch (error) {
            console.log(error)
          } finally {
            loading.close()
          }
        }
      })
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 1
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 1MB!')
      }
      return isLt2M
    }
  }
}
</script>
<style lang="scss" scoped >
.setting {
  padding: 30px 60px;

  .el-form-item {
    width: 40%;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height: 100px;
    display: block;
    border-radius: 6px;
  }
}
</style>
