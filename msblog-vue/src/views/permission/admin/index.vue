<template>
  <div class="app-container customer-page-box">
    <el-form
      ref="queryForm"
      class="cus-search-box"
      :model="pages"
    >
      <el-form-item
        label="真实姓名"
        prop="name"
      >
        <el-input
          v-model="pages.name"
          placeholder="请输入真实姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="用户名"
        prop="username"
      >
        <el-input
          v-model="pages.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item class="search-btn wp-50">
        <el-button
          class="cus-search-btn"
          type="primary"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          class="cus-reset-btn"
          @click="resetQuery"
        >重置</el-button>
      </el-form-item>
    </el-form>

    <div class="cus-btn-box">
      <div class="cus-btn-con">
        <el-button
          class="primary-btn"
          @click="showEditDialog()"
        >新增</el-button>
        <el-button
          class="default-btn"
          @click="exceUserData()"
        >导出</el-button>
      </div>
    </div>

    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中..."
      border
      fit
      highlight-current-row
      empty-text="暂无数据"
    >
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          {{ (pages.page - 1) * pages.pageSize + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="真是姓名" align="center" prop="name" />
      <el-table-column label="头像" align="center" width="65">
        <template slot-scope="scope">
          <el-image
            style="width: 40px;height: 40px;border-radius: 10px;"
            :src="scope.row.image"
          />
        </template>
      </el-table-column>
      <el-table-column label="用户名" align="center" prop="username" width="70" />
      <el-table-column label="身份证号码" align="center" prop="idNumber" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column class-name="status-col" label="角色" align="center" width="130">
        <template slot-scope="scope">
          <el-tag v-for="(item, i) in scope.row.roles" :key="i" type="info" class="mb10">{{ item }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            :active-value="1"
            :inactive-value="0"
            @change="setStatus(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" prop="createTime" label="创建时间">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span> {{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="{ row }">
          <el-button
            plain
            title="编辑"
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="showEditDialog(row)"
          />
          <el-button
            v-if="userId == row.id"
            plain
            title="修改密码"
            type="warning"
            size="small"
            icon="el-icon-key"
            @click="showEditPasswordDialog(row)"
          />
          <el-button
            plain
            title="删除"
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="deleteAdminHandle(row.id)"
          />
        </template>
      </el-table-column>
    </el-table>

    <el-row class="page">
      <el-col :span="24">
        <el-pagination
          background
          :current-page="pages.pagenum"
          :page-sizes="[5, 19, 20, 50]"
          :page-size="pages.pageSize"
          layout="sizes, total, prev, pager, next, jumper"
          :total="pages.total"
          :hide-on-single-page="true"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </el-col>
    </el-row>

    <!-- 添加修改弹出框 -->
    <el-dialog
      :visible.sync="addOrUpdateVisible"
      :title="!form.id ? '添加' : '修改'"
      width="618px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form
        ref="form"
        class="cus-search-box cus-dialog-form-box"
        :model="form"
        :rules="rules"
      >
        <el-form-item
          label="真实姓名"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入真实姓名"
            :disabled="form.id != null"
          />
        </el-form-item>
        <el-form-item
          label="用户名"
          prop="name"
        >
          <el-input
            v-model="form.username"
            placeholder="请输入用户名"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="身份证号码"
          prop="idNumber"
        >
          <el-input
            v-model="form.idNumber"
            placeholder="请输入身份证号码"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="邮箱"
          prop="email"
        >
          <el-input
            v-model="form.email"
            placeholder="请输入邮箱"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="手机号"
          prop="phone"
        >
          <el-input
            v-model="form.phone"
            placeholder="请输入手机号"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="角色"
          prop="role"
        >
          <el-select
            v-model="form.role"
            placeholder="请选择角色"
            clearable
            filterable
            multiple
          >
            <el-option
              v-for="role in roleList"
              :key="role.id"
              :label="role.roleName"
              :value="role.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="头像"
          prop="image"
        >
          <el-upload
            class="avatar-uploader"
            :action="baseUrl + '/common/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="form.image" :src="form.image" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item
          label="性别"
          prop="sex"
        >
          <el-radio v-model="form.sex" label="1">男</el-radio>
          <el-radio v-model="form.sex" label="0">女</el-radio>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          class="cus-reset-btn"
          @click="addOrUpdateVisible = false"
        >取 消</el-button>
        <el-button
          class="cus-search-btn"
          @click="submitForm()"
        >确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改密码弹出框 -->
    <el-dialog
      :visible.sync="updatePasswordVisible"
      title="修改密码"
      width="618px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form
        ref="pwForm"
        class="cus-search-box cus-dialog-form-box"
        :model="pwForm"
        :rules="rulesPassword"
      >
        <el-form-item
          label="旧密码"
          prop="oldPassword"
        >
          <el-input
            v-model="pwForm.oldPassword"
            placeholder="请输入旧密码"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="新密码"
          prop="newPassword"
        >
          <el-input
            v-model="pwForm.newPassword"
            placeholder="请输入新密码"
            clearable
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          class="cus-reset-btn"
          @click="updatePasswordVisible = false"
        >取 消</el-button>
        <el-button
          class="cus-search-btn"
          @click="submitPasswordForm()"
        >确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getAdminList, startOrStop, updateAadmin, updatePassword, addAdmin, excelUser, deleteAdmin } from '@/api/admin'
import { getRoleList } from '@/api/role'
import { mapGetters } from 'vuex'
import { getNowFormatDate, download } from '@/utils/tool'

export default {
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASE_API,
      list: null,
      listLoading: true,
      hasUser: false,
      addOrUpdateVisible: false,
      updatePasswordVisible: false,
      pages: {
        page: 1,
        pageSize: 5,
        total: 0,
        status: null,
        name: '',
        username: ''
      },
      form: {
        id: null,
        name: '',
        username: '',
        email: '',
        password: '',
        phone: '',
        sex: '',
        idNumber: '',
        image: '',
        role: ''
      },
      pwForm: {
        id: '',
        oldPassword: '',
        newPassword: ''
      },
      // 表单校验
      rules: {
        name: [{ required: true, message: '不能为空', trigger: 'blur' }],
        username: [{ required: true, message: '不能为空', trigger: 'blur' }],
        email: [{ required: true, message: '不能为空', trigger: 'blur' }],
        phone: [{ required: true, message: '不能为空', trigger: 'blur' }],
        sex: [{ required: true, message: '不能为空', trigger: 'blur' }],
        idNumber: [{ required: true, message: '不能为空', trigger: 'blur' }],
        image: [{ required: true, message: '不能为空', trigger: 'blur' }],
        role: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      rulesPassword: {
        oldPassword: [{ required: true, message: '不能为空', trigger: 'blur' }],
        newPassword: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      roleList: []
    }
  },
  computed: {
    ...mapGetters([
      'userId',
      'permissions'
    ])
  },
  created() {
    this.fetchData()
    this.roleData()
  },
  methods: {
    async fetchData() {
      this.listLoading = true
      try {
        const res = await getAdminList(this.pages)
        this.list = res.data.list
        this.pages.total = res.data.total
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
      }
    },
    async roleData() {
      try {
        const res = await getRoleList()
        this.roleList = res.data
      } catch (error) {
        console.log(error)
      }
    },
    handleCurrentChange(val) {
      this.pages.page = val
      this.fetchData()
    },
    handleSizeChange(val) {
      this.pages.pageSize = val
      this.fetchData()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.pages = {
        page: 1,
        pageSize: 2,
        total: 0,
        status: null,
        name: '',
        username: ''
      }
      this.handleQuery()
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.pages.page = 1
      this.fetchData()
      this.roleData()
    },
    showEditDialog(row) {
      this.resetForm('form')
      if (row) {
        this.$nextTick(() => {
          const role = row?.role && JSON.parse(row.role)
          this.form = { ...row, role }
          this.addOrUpdateVisible = true
        })
      } else {
        this.$nextTick(() => {
          this.form = {}
          this.addOrUpdateVisible = true
        })
      }
    },
    showEditPasswordDialog(row) {
      this.resetForm('pwForm')
      this.pwForm = {}
      this.$nextTick(() => {
        this.updatePasswordVisible = true
        this.pwForm.id = row.id
      })
    },
    submitForm() {
      this.$refs.form.validate(async(valid) => {
        if (valid) {
          try {
            if (this.form.id) {
              const deepForm = this.lodash.cloneDeep(this.form)
              deepForm.role = deepForm?.role && JSON.stringify(deepForm.role)
              const res = await updateAadmin(deepForm)
              if (res.errCode === 0) {
                this.$message({
                  message: '修改成功',
                  type: 'success'
                })
                this.fetchData()
                this.addOrUpdateVisible = false
              }
            } else {
              const deepForm = this.lodash.cloneDeep(this.form)
              deepForm.role = deepForm?.role && JSON.stringify(deepForm.role)
              const res = await addAdmin(deepForm)
              if (res.errCode === 0) {
                this.$message({
                  message: '新增用户成功',
                  type: 'success'
                })
                this.fetchData()
                this.addOrUpdateVisible = false
              }
            }
          } catch (error) {
            console.log(error)
          }
        }
      })
    },
    async submitPasswordForm() {
      this.$refs.pwForm.validate(async(valid) => {
        if (valid) {
          try {
            const res = await updatePassword(this.pwForm)
            if (res.errCode === 0) {
              this.$message({
                message: '密码修改成功，请重新登录',
                type: 'success',
                duration: 1500
              })
              this.updatePasswordVisible = false
              setTimeout(async() => {
                await this.$store.dispatch('user/logout')
                this.$router.push(`/login?redirect=${this.$route.fullPath}`)
              }, 2000)
            }
          } catch (error) {
            console.log(error)
          }
        }
      })
    },
    async setStatus(row) {
      this.listLoading = true
      try {
        const res = await startOrStop(row)
        if (res.errCode === 0) this.fetchData()
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
      }
    },
    handleAvatarSuccess(res, file) {
      this.form.image = res.data
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 1
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 1MB!')
      }
      return isLt2M
    },
    // 导出数据
    async exceUserData() {
      try {
        await this.$confirm('是否确认导出所有用户数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        const result = await excelUser()
        download(result, '用户表' + getNowFormatDate() + '.xlsx') // 文件名的格式，一般是确定的，如果是excel的话就 .xls 或者 .xlsx 其中一个
      } catch (error) {
        console.log(error)
      }
    },
    async deleteAdminHandle(id) {
      try {
        await this.$confirm('是否确认删除管理员编号为"' + id + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        const res = await deleteAdmin(id)
        if (res.code === 1) {
          this.fetchData()
          this.$message({
            message: '删除成功',
            type: 'success'
          })
        }
      } catch (error) {
        console.log(error)
      }
    }
  }
}
</script>

<style>
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
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
