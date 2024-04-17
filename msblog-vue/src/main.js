import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/styles/element-variables.scss'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import '@/assets/iconfont/iconfont.css'

import '@/styles/index.scss' // global css
import '@/styles/common.scss' // common css

import App from './App'
import store from './store'
import router from './router'
import _ from 'lodash' // 导入loadsh插件

import '@/icons' // icon
import '@/permission' // permission control
import { getStorage } from '@/utils/auth'
import {
  parseTime,
  resetForm,
  addDateRange,
  selectDictLabel,
  selectDictLabels,
  download,
  handleTree
} from '@/utils/common'

/* v-md-editor 编辑器 start  */
/* 1、v-md-editor 基础引用  */
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

/* 2、v-md-editor 代码块关键字高亮  */
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
// 引入所有语言包
import hljs from 'highlight.js';
VueMarkdownEditor.use(githubTheme, {
  Hljs: hljs,
});
Vue.use(VueMarkdownEditor);

/* 3、v-md-editor 二次封装  */
import mdEditor from '@/components/v-md-editor/index';
Vue.component('MdEditor', mdEditor);
/* v-md-editor 编辑器 end  */


// 全局方法挂载
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree
Vue.prototype.lodash = _

// 登录成功时已经添加了一遍，登陆后刷新时才需要再次动态添加
// 页面刷新时重新动态添加路由
if (getStorage('token')) {
  router.onReady(async() => {
    const accessRoutes = await store.dispatch('permission/generateRoutes')
    router.addRoutes(accessRoutes)
  })
}

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
