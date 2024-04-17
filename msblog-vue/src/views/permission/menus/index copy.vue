<template>
  <div class="app-container">
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
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="父级菜单" align="center" prop="parentId" />
      <el-table-column label="显示顺序" align="center" prop="orderNum" />
      <el-table-column label="路径" align="center" prop="path" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="组件" align="center" prop="component" />
      <el-table-column label="图标" align="center" prop="icon" />
      <el-table-column class-name="status-col" label="类型" align="center">
        <template slot-scope="scope">
          <el-tag type="success" class="mb10">{{ scope.row.menuType | formatMenuType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="权限标识" align="center" prop="perms" />
      <el-table-column class-name="status-col" label="是否隐藏" align="center">
        <template slot-scope="scope">
          {{ scope.row.hasHidden }}
          <el-tag type="success" class="mb10">{{ scope.row.hasHidden | formatHidden }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="重定向" align="center" prop="redirect" />
      <el-table-column label="创建时间" width="200" align="center" prop="createTime" />
      <el-table-column class-name="status-col" label="状态" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            :active-value="1"
            :inactive-value="0"
            
            @change="setAdminAystemic(scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作">
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
            plain
            title="删除"
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="showDeleteDialog(row)"
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
    >
      <el-form
        ref="form"
        class="cus-search-box cus-dialog-form-box"
        :model="form"
        :rules="rules"
      >
        <el-form-item
          label="标题"
          prop="title"
        >
          <el-input
            v-model="form.title"
            placeholder="请输入标题"
          />
        </el-form-item>
        <el-form-item
          label="父级菜单"
          prop="parentId"
        >
          <el-select
            v-model="form.parentId"
            placeholder="请选择类型"
            clearable
            filterable
          >
            <el-option
              v-for="item in list"
              :key="item.id"
              :label="item.title"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="显示顺序"
          prop="orderNum"
        >
          <el-input
            v-model="form.orderNum"
            placeholder="请输入显示顺序"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="路径"
          prop="path"
        >
          <el-input
            v-model="form.path"
            placeholder="请输入路径"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="名称"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入名称"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="组件"
          prop="component"
        >
          <el-input
            v-model="form.component"
            placeholder="请输入组件"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="图标"
          prop="icon"
        >
          <el-input
            v-model="form.icon"
            placeholder="请输入图标"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="类型"
          prop="menuType"
        >
          <el-select
            v-model="form.menuType"
            placeholder="请选择类型"
            clearable
            filterable
          >
            <el-option
              v-for="item in menuTypeEnums"
              :key="item.type"
              :label="item.name"
              :value="item.type"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="是否隐藏"
          prop="hasHidden"
        >
          <el-input
            v-model="form.hasHidden"
            placeholder="请输入是否隐藏"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="重定向"
          prop="redirect"
        >
          <el-input
            v-model="form.redirect"
            placeholder="请输入重定向"
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
import { getMenuPage, updateMenu } from '@/api/menu'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: '正常',
        0: '锁定'
      }
      return statusMap[status]
    },
    formatHidden(val) {
      const hiddenStatus = {
        1: '隐藏',
        0: '显示'
      }
      return hiddenStatus[val]
    },
    formatMenuType(type) {
      const statusMap = {
        'M': '目录',
        'C': '菜单',
        'F': '按钮'
      }
      return statusMap[type]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      addOrUpdateVisible: false,
      pages: {
        page: 1,
        pageSize: 4,
        total: 0,
        status: null
      },
      form: {
        id: null,
        title: '',
        parentId: '',
        orderNum: '',
        path: '',
        name: '',
        component: '',
        icon: '',
        menuType: '',
        hasHidden: '',
        redirect: ''
      },
      // 表单校验
      rules: {
        title: [{ required: true, message: '不能为空', trigger: 'blur' }],
        parentId: [{ required: true, message: '不能为空', trigger: 'blur' }],
        orderNum: [{ required: true, message: '不能为空', trigger: 'blur' }],
        menuType: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      menuTypeEnums: [
        {
          name: '目录',
          type: 'M'
        },
        {
          name: '菜单',
          type: 'C'
        },
        {
          name: '按钮',
          type: 'F'
        }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      this.listLoading = true
      try {
        const res = await getMenuPage(this.pages)
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
    showEditDialog(row) {
      this.$nextTick(() => {
        this.addOrUpdateVisible = true
        this.form = { ...row }
      })
    },
    async submitForm() {
      try {
        const res = await updateMenu(this.form)
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
    }
  }
}
</script>
