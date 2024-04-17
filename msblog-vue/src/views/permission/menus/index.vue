<template>
  <div class="app-container customer-page-box">
    <!-- <el-form
      v-show="showSearch"
      ref="queryForm"
      class="cus-search-box"
      :model="pages"
      :inline="true"
    >
      <el-form-item label="菜单名称" prop="title" class="reset-form-item">
        <el-input
          v-model="pages.title"
          placeholder="请输入菜单名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="visible" class="reset-form-item">
        <el-select
          v-model="pages.visible"
          placeholder="菜单状态"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item class="reset-form-item" />
      <el-form-item class="search-btn reset-form-item">
        <el-button
          class="cus-search-btn"
          type="primary"
          size="mini"
          @click="handleQuery"
        >搜索</el-button>
        <el-button
          class="cus-reset-btn"
          size="mini"
          @click="resetQuery"
        >重置</el-button>
      </el-form-item>
    </el-form> -->

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:menu:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </el-row> -->
    <div class="cus-btn-box">
      <div class="cus-btn-con">
        <!-- v-hasPermi="['system:menu:add']" -->
        <el-button
          class="primary-btn"
          type="primary"
          plain
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </div>
    </div>

    <el-table
      v-loading="loading"
      :data="menuList"
      stripe
      row-key="id"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      empty-text="暂无数据"
    >
      <el-table-column
        prop="title"
        label="菜单名称"
        :show-overflow-tooltip="true"
        min-width="160"
      />
      <el-table-column prop="icon" label="图标" align="center" min-width="100">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="orderNum" label="排序" min-width="100" />
      <el-table-column
        prop="perms"
        label="权限标识"
        :show-overflow-tooltip="true"
        min-width="200"
      />
      <el-table-column
        prop="component"
        label="组件路径"
        :show-overflow-tooltip="true"
        min-width="200"
      />
      <el-table-column
        prop="path"
        label="路由地址"
        :show-overflow-tooltip="true"
        min-width="200"
      />
      <el-table-column
        prop="menuType"
        label="菜单类型"
        min-width="100"
      >
        <template slot-scope="scope">
          <el-tag :type="scope.row.menuType | menuTypeTag" class="mb10">{{ scope.row.menuType | formatMenuType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="菜单状态"
        min-width="100"
      >
        <template slot-scope="scope">
          <span>{{ scope.row | formatStatus }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        min-width="160"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column> -->
      <el-table-column
        fixed="right"
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        min-width="120"
      >
        <template slot-scope="scope">
          <!-- v-hasPermi="['system:menu:edit']" -->
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <!-- v-hasPermi="['system:menu:add']" -->
          <el-button
            size="mini"
            type="text"
            @click="handleAdd(scope.row)"
          >新增</el-button>
          <!-- v-hasPermi="['system:menu:remove']" -->
          <el-button
            size="mini"
            type="text"
            class="del-text"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改菜单对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="618px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单">
              <treeselect
                v-model="form.parentId"
                :options="menuOptions"
                :normalizer="normalizer"
                :show-count="true"
                placeholder="选择上级菜单"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="form.menuType">
                <el-radio label="M">目录</el-radio>
                <el-radio label="C">菜单</el-radio>
                <el-radio label="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item v-if="form.menuType != 'F'" label="菜单图标">
              <el-popover
                placement="bottom-start"
                width="460"
                trigger="click"
                @show="$refs['iconSelect'].reset()"
              >
                <IconSelect ref="iconSelect" @selected="selected" />
                <el-input
                  slot="reference"
                  v-model="form.icon"
                  placeholder="点击选择图标"
                  readonly
                >
                  <svg-icon
                    v-if="form.icon"
                    slot="prefix"
                    :icon-class="form.icon"
                    class="el-input__icon"
                    style="height: 32px; width: 16px"
                  />
                  <i
                    v-else
                    slot="prefix"
                    class="el-icon-search el-input__icon"
                  />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="title">
              <el-input v-model="form.title" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number
                v-model="form.orderNum"
                controls-position="right"
                :min="0"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'F'" label="是否外链">
              <el-radio-group v-model="form.isFrame">
                <el-radio :label="0">是</el-radio>
                <el-radio :label="1">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              v-if="form.menuType != 'F'"
              label="路由地址"
              prop="path"
            >
              <el-input v-model="form.path" placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
          <el-col v-if="form.menuType == 'C'" :span="12">
            <el-form-item label="组件路径" prop="component">
              <el-input v-model="form.component" placeholder="请输入组件路径" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.menuType != 'M'" label="权限标识">
              <el-input
                v-model="form.perms"
                placeholder="请权限标识"
                maxlength="50"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- <el-form-item v-if="form.menuType != 'F'" label="显示状态">
              <el-radio-group v-model="form.visible">
                <el-radio
                  v-for="dict in visibleOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                  >{{ dict.dictLabel }}</el-radio
                >
              </el-radio-group>
            </el-form-item> -->
            <el-form-item v-if="form.menuType != 'F'" label="显示状态" prop="visible">
              <el-radio-group v-model="form.visible">
                <el-radio :label="0">显示</el-radio>
                <el-radio :label="1">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item v-if="form.menuType != 'F'" label="菜单状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                  >{{ dict.dictLabel }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col> -->
          <!-- <el-col :span="12">
            <el-form-item v-if="form.menuType == 'C'" label="是否缓存">
              <el-radio-group v-model="form.isCache">
                <el-radio label="0">缓存</el-radio>
                <el-radio label="1">不缓存</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col> -->
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button class="cus-reset-btn" @click="cancel">取 消</el-button>
        <el-button class="cus-search-btn" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getMenuList, getMenu, deleteMenu, addMenu, updateMenu } from '@/api/menu'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import IconSelect from '@/components/IconSelect'

export default {
  name: 'Menu',
  components: { Treeselect, IconSelect },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: '正常',
        0: '锁定'
      }
      return statusMap[status]
    },
    formatStatus(row) {
      if (row.menuType === 'F') {
        return ''
      }
      const status = {
        0: '停用',
        1: '正常'
      }
      return status[row.status]
    },
    formatMenuType(type) {
      const statusMap = {
        M: '目录',
        C: '菜单',
        F: '按钮'
      }
      return statusMap[type]
    },
    menuTypeTag(type) {
      const statusMap = {
        M: 'success',
        C: 'warning',
        F: 'info'
      }
      return statusMap[type]
    }
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 菜单表格树数据
      menuList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 显示状态数据字典
      visibleOptions: [
        [
          {
            dictCode: 4,
            dictSort: 1,
            dictLabel: '显示',
            dictValue: '0'
          },
          {
            dictCode: 5,
            dictSort: 2,
            dictLabel: '隐藏',
            dictValue: '1'
          }
        ]
      ],
      // 菜单状态数据字典
      statusOptions: [
        [
          {
            createBy: 'admin',
            createTime: '2021-03-31 03:12:16',
            dictCode: 6,
            dictSort: 1,
            dictLabel: '正常',
            dictValue: '0',
            dictType: 'sys_normal_disable',
            cssClass: '',
            listClass: 'primary',
            isDefault: 'Y',
            visible: '0',
            default: true
          },
          {
            createBy: 'admin',
            createTime: '2021-03-31 03:12:16',
            dictCode: 7,
            dictSort: 2,
            dictLabel: '停用',
            dictValue: '1',
            dictType: 'sys_normal_disable',
            cssClass: '',
            listClass: 'danger',
            isDefault: 'N',
            visible: '0',
            default: false
          }
        ]
      ],
      // 表单参数
      form: {
        visible: 0
      },
      // 表单校验
      rules: {
        title: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' }
        ],
        orderNum: [
          { required: true, message: '菜单顺序不能为空', trigger: 'blur' }
        ],
        path: [
          { required: true, message: '路由地址不能为空', trigger: 'blur' }
        ]
      },
      pages: {
        page: 1,
        pageSize: 4,
        total: 0,
        status: null
      }
    }
  },
  created() {
    this.getList()
    // this.getDicts('sys_show_hide').then(response => {
    //   this.visibleOptions = response.data
    // })
    // this.getDicts('sys_normal_disable').then(response => {
    //   this.statusOptions = response.data
    // })
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name
    },
    /** 查询菜单列表 */
    async getList() {
      this.loading = true
      try {
        const res = await getMenuList(this.pages)
        this.menuList = this.handleTree(res.data, 'id')
      } catch (error) {
        console.log(error)
      } finally {
        this.loading = false
      }
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      // 去掉children=null的属性
      if (node.children == null || node.children === 'null') {
        delete node.children
      }
      // if (node.children === null && node.children === 'null') {
      //   delete node.children
      // }
      return {
        id: node.id,
        label: node.title,
        children: node.children
      }
    },
    /** 查询菜单下拉树结构 */
    async getTreeselect() {
      try {
        const res = await getMenuList(this.pages)
        this.menuOptions = []
        const menu = { id: 0, title: '主类目', children: [] }
        menu.children = this.handleTree(res.data, 'id')
        this.menuOptions.push(menu)
      } catch (error) {
        console.error(error)
      }
    },
    // 显示状态字典翻译
    visibleFormat(row) {
      if (row.menuType === 'F') {
        return ''
      }
      return this.selectDictLabel(this.visibleOptions, row.visible)
    },
    // 菜单状态字典翻译
    statusFormat(row) {
      if (row.menuType === 'F') {
        return ''
      }
      return this.selectDictLabel(this.statusOptions, row.visible)
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        parentId: 0,
        title: undefined,
        icon: undefined,
        menuType: 'M',
        orderNum: undefined,
        isFrame: 1,
        visible: 0
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.$nextTick(async() => {
        this.reset()
        this.getTreeselect()
        if (row !== null && row.id) {
          this.form.parentId = row.id
        } else {
          this.form.parentId = 0
        }
        this.title = '添加菜单'
        this.open = true
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.getTreeselect()
      const form = this.lodash.cloneDeep(row)
      this.form = form
      this.open = true
      this.title = '修改菜单'
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id !== undefined) {
            updateMenu(this.form).then(() => {
              this.$message({
                message: '修改成功',
                type: 'success',
                duration: 1500
              })
              this.open = false
              this.getList()
            })
          } else {
            addMenu(this.form).then(() => {
              this.$message({
                message: '新增成功',
                type: 'success',
                duration: 1500
              })
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$confirm('是否确认删除名称为"' + row.title + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return deleteMenu(row.id)
        })
        .then(() => {
          this.getList()
          this.msgSuccess('删除成功')
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.reset-form-item {
  margin-right: 0px;
}
::v-deep .el-table {
  thead tr th:first-child {
    .cell {
      padding-left: 25px;
    }
  }
  tbody tr td:first-child {
    .cell {
      padding-left: 25px;
    }
  }
}
</style>
