<template>
  <div class="app-container customer-page-box">
    <el-form
      ref="queryForm"
      class="cus-search-box"
      :model="pages"
    >
      <el-form-item
        label="角色名称"
        prop="roleName"
      >
        <el-input
          v-model="pages.roleName"
          placeholder="请输入角色名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="权限字符"
        prop="roleEn"
      >
        <el-input
          v-model="pages.roleEn"
          placeholder="请输入权限字符"
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
      <el-table-column align="center" label="序号" width="65">
        <template slot-scope="scope">
          {{ (pages.page - 1) * pages.pageSize + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="角色名称" align="center" prop="roleName" />
      <el-table-column label="权限字符" align="center" prop="roleEn" />
      <el-table-column label="描述" align="center" prop="info" />
      <el-table-column class-name="status-col" label="是否为系统内置角色" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.systemic"
            :active-value="1"
            :inactive-value="0"
            @change="setRoleStatus(scope.row)"
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
            :disabled="row.systemic == 1"
            plain
            title="删除"
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="deleteRoleHandle(row.id)"
          />
        </template>
      </el-table-column>
    </el-table>

    <el-row class="page">
      <el-col :span="24">
        <el-pagination
          background
          :current-page="pages.pagenum"
          :page-sizes="[2, 4, 10, 20]"
          :page-size="pages.pageSize"
          layout="sizes, total, prev, pager, next, jumper"
          :total="pages.total"
          :hide-on-single-page="true"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改员工对话框 -->
    <el-dialog
      :visible.sync="addOrUpdateVisible"
      :title="!form.id ? '添加' : '修改'"
      width="618px"
      append-to-body
      :close-on-click-modal="false"
      @close="setCloseProps"
    >
      <el-form
        ref="form"
        class="cus-search-box cus-dialog-form-box"
        :model="form"
        :rules="rules"
      >
        <el-form-item
          label="角色名称"
          prop="roleName"
        >
          <el-input
            v-model="form.roleName"
            placeholder="请输入角色名称"
          />
        </el-form-item>
        <el-form-item
          label="权限字符"
          prop="roleEn"
        >
          <el-input
            v-model="form.roleEn"
            placeholder="请输入角色英文名称"
            clearable
          />
        </el-form-item>
        <el-form-item v-if="showMenuTreeItem" label="菜单权限集合">
          <el-tree
            ref="menus"
            :data="menus"
            show-checkbox
            node-key="id"
            highlight-current
            :props="defaultProps"
            expand-on-click-node
          />
        </el-form-item>
        <el-form-item
          label="描述"
          prop="info"
        >
          <el-input
            v-model="form.info"
            placeholder="请输入描述"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="是否为系统内置角色"
          prop="systemic"
        >
          <el-switch
            v-model="form.systemic"
            :active-value="1"
            :inactive-value="0"
          />
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
  </div>
</template>

<script>
import { getRolePage, updateRole, addRole, deleteRole } from '@/api/role'
import { getMenuTree } from '@/api/menu'
// import { mapGetters } from 'vuex'

export default {
  name: 'Role',
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: '正常',
        0: '锁定'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      showMenuTreeItem: false,
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      list: null,
      listLoading: true,
      addOrUpdateVisible: false,
      pages: {
        page: 1,
        pageSize: 4,
        total: 0,
        roleName: '',
        roleEn: ''
      },
      form: {
        id: null,
        roleName: '',
        roleEn: '',
        menus: '',
        info: '',
        systemic: 0
      },
      // 表单校验
      rules: {
        roleName: [{ required: true, message: '不能为空', trigger: 'blur' }],
        roleEn: [{ required: true, message: '不能为空', trigger: 'blur' }],
        menus: [{ required: true, message: '不能为空', trigger: 'blur' }],
        systemic: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      menus: []
    }
  },
  computed: {
    // ...mapGetters([
    //   'userId',
    //   'permissions',
    //   'menus'
    // ])
  },
  watch: {
    addOrUpdateVisible(val) {
      if (val && this.form.id >= 0) this.setDefaultChecked()
    }
  },
  created() {
    this.fetchData()
    this.getMenus()
  },
  methods: {
    async getMenus() {
      try {
        const res = await getMenuTree()
        this.menus = res.data
      } catch (error) {
        console.log(error)
      }
    },
    async fetchData() {
      this.listLoading = true
      try {
        const res = await getRolePage(this.pages)
        this.list = res.data.list
        this.pages.total = res.data.total
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.pages.page = 1
      this.fetchData()
      this.getMenus()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.pages = {
        page: 1,
        pageSize: 4,
        total: 0,
        roleName: '',
        roleEn: ''
      }
      this.handleQuery()
    },
    async setRoleStatus(row) {
      this.listLoading = true
      try {
        const res = await updateRole(row)
        if (res.errCode === 0) this.fetchData()
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
      }
    },
    showEditDialog(row) {
      this.resetForm('form')
      if (row) {
        this.showMenuTreeItem = true
        const deepForm = this.lodash.cloneDeep(row)
        deepForm.menus = deepForm?.menus && JSON.parse(deepForm.menus)
        this.form = deepForm
        this.addOrUpdateVisible = true
      } else {
        this.form = {}
        this.showMenuTreeItem = true
        this.addOrUpdateVisible = true
      }
    },
    setCloseProps() {
      this.form = {}
      this.showMenuTreeItem = false
    },
    setDefaultChecked() {
      this.$nextTick(() => {
        this.$refs.menus.setCheckedKeys(this.form.menus)
      })
    },
    async submitForm() {
      this.$refs.form.validate(async(valid) => {
        if (valid) {
          const formatForm = this.lodash.cloneDeep(this.form)
          const checkedKeys = this.$refs.menus.getCheckedKeys()
          formatForm.menus = checkedKeys && JSON.stringify(checkedKeys)
          try {
            if (this.form.id) {
              try {
                const res = await updateRole(formatForm)
                if (res.errCode === 0) {
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                  this.fetchData()
                  this.addOrUpdateVisible = false
                }
              } catch (error) {
                console.log(error)
              }
            } else {
              try {
                const res = await addRole(formatForm)
                if (res.errCode === 0) {
                  this.$message({
                    message: '新增角色成功',
                    type: 'success'
                  })
                }
                this.fetchData()
                this.addOrUpdateVisible = false
              } catch (error) {
                console.log(error)
              }
            }
          } catch (error) {
            console.log(error)
          }
        }
      })
    },
    async deleteRoleHandle(id) {
      try {
        await this.$confirm('是否确认删除角色编号为"' + id + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        const res = await deleteRole(id)
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
