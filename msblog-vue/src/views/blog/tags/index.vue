<template>
  <div class="app-container customer-page-box">
    <el-form
      ref="queryForm"
      class="cus-search-box"
      :model="pages"
    >
      <el-form-item
        label="标签名称"
        prop="name"
      >
        <el-input
          v-model="pages.name"
          placeholder="请输入标签名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item class="search-btn wp-75">
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
          @click="importCategoryData()"
        >批量导入</el-button>
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
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ (pages.page - 1) * pages.pageSize + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="标签名称">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.sort }}</span>
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
      <el-table-column align="center" prop="createTime" label="创建时间" width="200">
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
          :page-sizes="[5, 10, 20, 50]"
          :page-size="pages.pageSize"
          layout="sizes, total, prev, pager, next, jumper"
          :total="pages.total"
          :hide-on-single-page="true"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改员工弹出框 -->
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
          label="标签名称"
          prop="name"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入标签名称"
          />
        </el-form-item>
        <el-form-item
          label="标签顺序"
          prop="sort"
        >
          <el-input
            v-model="form.sort"
            placeholder="请输入标签顺序"
            clearable
          />
        </el-form-item>
        <el-form-item
          label="状态"
          prop="status"
        >
          <el-switch
            v-model="form.status"
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

    <!-- 导入弹出框 -->
    <Upload v-if="uploadVisible" ref="uploadData" @refreshDataList="handleQuery" />

  </div>
</template>

<script>
import { getTagsList, addTags, updateTags, deleteTags } from '@/api/tags'
import Upload from './upload'

export default {
  components: {
    Upload
  },
  data() {
    return {
      uploadVisible: false,
      addOrUpdateVisible: false,
      pages: {
        page: 1,
        pageSize: 5,
        total: 0,
        status: null,
        name: ''
      },
      list: null,
      listLoading: true,
      form: {
        id: null,
        name: '',
        sort: '',
        status: 1
      },
      // 表单校验
      rules: {
        name: [{ required: true, message: '不能为空', trigger: 'blur' }],
        sort: [{ required: true, message: '不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      this.listLoading = true
      try {
        const res = await getTagsList(this.pages)
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
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.pages = {
        page: 1,
        pageSize: 5,
        total: 0,
        status: null,
        name: ''
      }
      this.handleQuery()
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.pages.page = 1
      this.fetchData()
    },
    showEditDialog(row) {
      this.resetForm('form')
      if (row) {
        const deepForm = this.lodash.cloneDeep(row)
        deepForm.menus = deepForm?.menus && JSON.parse(deepForm.menus)
        this.form = deepForm
        this.addOrUpdateVisible = true
      } else {
        this.form = {
          name: '',
          sort: '',
          status: 1
        }
        this.addOrUpdateVisible = true
      }
    },
    async submitForm() {
      this.$refs.form.validate(async(valid) => {
        if (valid) {
          const formatForm = this.lodash.cloneDeep(this.form)
          try {
            if (this.form.id) {
              try {
                const res = await updateTags(formatForm)
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
                const res = await addTags(formatForm)
                if (res.errCode === 0) {
                  this.$message({
                    message: '新增成功',
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
    async setStatus(row) {
      this.listLoading = true
      try {
        const res = await updateTags(row)
        if (res.errCode === 0) this.fetchData()
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
      }
    },
    async deleteRoleHandle(id) {
      try {
        await this.$confirm('是否确认删除标签编号为"' + id + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        const res = await deleteTags(id)
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
    },
    importCategoryData() {
      this.uploadVisible = true
      this.$nextTick(() => {
        this.$refs.uploadData.init()
      })
    }
  }
}
</script>
