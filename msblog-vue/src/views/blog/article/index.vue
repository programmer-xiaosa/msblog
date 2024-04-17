<template>
  <div class="app-container customer-page-box">
    <el-form
      ref="queryForm"
      class="cus-search-box"
      :model="pages"
    >
      <el-form-item
        label="文章标题"
        prop="title"
      >
        <el-input
          v-model="pages.title"
          placeholder="请输入文章标题"
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
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="封面" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="scope.row.image"
            fit="contain"
            :preview-src-list="[scope.row.image]"
          />
        </template>
      </el-table-column>
      <el-table-column label="分类名称" align="center">
        <template slot-scope="scope">
          {{ scope.row.categoryName }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="标签" align="center">
        <template slot-scope="scope">
          <el-tag v-for="(item, i) in scope.row.tagsList" :key="i" type="info" class="mb10 ml10">{{ item }}</el-tag>
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
      <el-table-column label="创建人" align="center">
        <template slot-scope="scope">
          {{ scope.row.createUserName }}
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
            @click="deleteArticleHandle(row.id)"
          />
        </template>
      </el-table-column>
    </el-table>

    <el-row class="page">
      <el-col :span="24">
        <el-pagination
          background
          :current-page="pages.pagenum"
          :page-sizes="[4, 10, 20, 50]"
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
      append-to-body
      :close-on-click-modal="false"
      @close="closeHandle"
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
          style="width: 100%"
        >
          <el-input
            v-model="form.title"
            placeholder="请输入标题"
          />
        </el-form-item>
        <el-form-item
          label="摘要"
          style="width: 100%"
        >
          <el-input
            v-model="form.summary"
            placeholder="请输入摘要"
            clearable
            type="textarea"
            :rows="3"
          />
        </el-form-item>
        <el-form-item
          label="分类"
          prop="categoryId"
        >
          <el-select
            v-model="form.categoryId"
            placeholder="请选择分类"
            clearable
            filterable
          >
            <el-option
              v-for="cate in cateList"
              :key="cate.id"
              :label="cate.name"
              :value="cate.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="标签"
          prop="tagsId"
        >
          <el-select
            v-model="form.tagsId"
            placeholder="请选择标签"
            clearable
            filterable
            multiple
          >
            <el-option
              v-for="cate in tagsList"
              :key="cate.id"
              :label="cate.name"
              :value="cate.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          v-if="editorVisible"
          label="内容"
          prop="content"
          style="width: 100%"
        >
          <MdEditor :content.sync="form.content" />
        </el-form-item>
        <el-form-item
          label="封面"
          prop="image"
        >
          <el-upload
            class="avatar-uploader"
            :action="baseUrl + '/common/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <el-image
              v-if="form.image"
              :src="form.image"
              class="avatar"
              fit="cover"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
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
  </div>
</template>

<script>
import { getArticlesList, addArticles, updateArticles, deleteArticles, getArticleById } from '@/api/articles'
import { getCategoryAll } from '@/api/category'
import { getTagsAll } from '@/api/tags'

export default {
  name: 'Article',
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: '启用',
        0: '停用'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      editorVisible: false,
      addOrUpdateVisible: false,
      baseUrl: process.env.VUE_APP_BASE_API,
      pages: {
        page: 1,
        pageSize: 4,
        total: 0,
        status: null,
        title: ''
      },
      form: {
        id: null,
        title: '',
        summary: '',
        categoryId: '',
        tagsId: '',
        content: '',
        status: 1,
        image: ''
      },
      // 表单校验
      rules: {
        title: [{ required: true, message: '不能为空', trigger: 'blur' }],
        // summary: [{ required: true, message: '不能为空', trigger: 'blur' }],
        categoryId: [{ required: true, message: '不能为空', trigger: 'blur' }],
        tagsId: [{ required: true, message: '不能为空', trigger: 'blur' }],
        content: [{ required: true, message: '不能为空', trigger: 'blur' }],
        image: [{ required: true, message: '不能为空', trigger: 'blur' }],
        status: [{ required: true, message: '不能为空', trigger: 'blur' }]
      },
      list: null,
      listLoading: true,
      cateList: [],
      tagsList: []
    }
  },
  created() {
    this.getCateList()
    this.getTagsList()
    this.fetchData()
  },
  methods: {
    async getCateList() {
      try {
        const res = await getCategoryAll()
        this.cateList = res.data
      } catch (error) {
        console.log(error)
      }
    },
    async getTagsList() {
      try {
        const res = await getTagsAll()
        this.tagsList = res.data
      } catch (error) {
        console.log(error)
      }
    },
    async fetchData() {
      this.listLoading = true
      try {
        const res = await getArticlesList(this.pages)
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
        title: '',
        summary: '',
        categoryId: '',
        tagsId: '',
        image: '',
        content: '',
        status: 1
      }
      this.handleQuery()
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.pages.page = 1
      this.fetchData()
    },
    async showEditDialog(row) {
      this.resetForm('form')
      this.form = {
        title: '',
        summary: '',
        categoryId: '',
        tagsId: '',
        image: '',
        content: '',
        status: 1
      }
      if (row) {
        try {
          const res = await getArticleById(row.id)
          const tagsId = row?.tagsId && JSON.parse(row.tagsId)
          this.form = { ...res.data, tagsId }
          this.addOrUpdateVisible = true
        } catch (error) {
          console.log(error)
        }
      } else {
        this.form.content = ''
        this.addOrUpdateVisible = true
      }
      this.editorVisible = true
    },
    closeHandle() {
      this.editorVisible = false
      this.form = {}
      this.form.content = ''
    },
    async submitForm() {
      this.$refs.form.validate(async(valid) => {
        if (valid) {
          const deepForm = this.lodash.cloneDeep(this.form)
          deepForm.tagsId = deepForm?.tagsId && JSON.stringify(deepForm.tagsId)
          try {
            if (this.form.id) {
              try {
                const res = await updateArticles(deepForm)
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
                const res = await addArticles(deepForm)
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
        const res = await updateArticles(row)
        if (res.errCode === 0) this.fetchData()
      } catch (error) {
        console.log(error)
      } finally {
        this.listLoading = false
      }
    },
    async deleteArticleHandle(id) {
      try {
        await this.$confirm('是否确认删除文章编号为"' + id + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })

        const res = await deleteArticles(id)
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
    handleAvatarSuccess(res, file) {
      this.form.image = res.data
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

<style lang="scss" scoped>
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
    width: 240px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
  .avatar {
    width: 240px;
    height: 150px;
    display: block;
  }
</style>
