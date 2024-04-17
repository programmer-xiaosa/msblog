import { asyncRoutes, constantRoutes } from '@/router'
import { getMenuListByUserId } from '@/api/menu'
import Layout from '@/layout'
import { getItem, setItem } from '@/utils/storage'

/**
 * 静态路由懒加载
 * @param view  格式必须为 xxx/xxx 开头不要加斜杠
 * @returns
 */
export const loadView = (view) => {
  return (resolve) => require([`@/views/${view}.vue`], resolve)
}

/**
 * 把从后端查询的菜单数据拼装成路由格式的数据
 * @param routes
 * @param data 后端返回的菜单数据
 */

export function generaMenu(routes, menus) {
  let children = []
  /**
   * 方案一:
   * 1.先把列表转为树形结构
   * 2.遍历该树形结构,根据title映射生成另一棵由静态路由表中元素构成的树
   */
  // 先把菜单列表转为树形结构
  menus.forEach(menu => {
    const pid = menu.parentId
    if (pid !== 0) {
      menus.forEach(Menu => {
        if (Menu.id === pid) {
          if (!Menu.children) {
            Menu.children = []
          }
          Menu.children.push(menu)
        }
      })
    }
  })

  // // 只保留一级菜单
  menus = menus.filter(menu => menu.parentId === 0)
  // 解析menu树,构造动态菜单
  menus.forEach(menu => {
    children = generateRoutes(children, menu)
  })

  children.forEach(menu => {
    routes.push(menu)
  })

  return routes
}

// 向菜单树中添加节点
function generateRoutes(children, item) {
  if (item.children) {
    const parentMenu = {
      path: item.path,
      component: item.component === '' ? Layout : loadView(item.component),
      redirect: item.redirect,
      children: [],
      name: item.title,
      meta: {
        title: item.title,
        icon: item.icon
      }
    }

    item.children.forEach(childItem => {
      const childMenu = {
        path: childItem.path,
        component: loadView(childItem.component),
        children: [],
        name: childItem.title,
        meta: {
          title: childItem.title,
          icon: childItem.icon
        }
      }
      parentMenu.children.push(childMenu)
    })

    children.push(parentMenu)
  } else {
    // const path = item.path.split('/')
    const menu = {
      path: '',
      component: Layout,
      children: [
        {
          path: item.path,
          name: item.title,
          component: loadView(item.component),
          meta: {
            title: item.title,
            icon: item.icon
          }
        }
      ]
    }
    children.push(menu)
  }
  return children
}

const state = {
  routes: getItem('menus'),
  addRoutes: getItem('menus')
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    // 拼接静态路由和动态路由
    state.routes = constantRoutes.concat(routes)
    setItem('menus', constantRoutes.concat(routes))
  }
}

const actions = {
  generateRoutes({ commit }) {
    return new Promise(resolve => {
      // 从后端获取用户菜单，并加入全局状态
      getMenuListByUserId().then(res => {
        if (res?.code === 1) {
          const formatMenu = res.data.filter(item => item.menuType !== 'F')
          const menuData = Object.assign([], formatMenu)
          const tempAsyncRoutes = Object.assign([], asyncRoutes)
          const accessedRoutes = generaMenu(tempAsyncRoutes, menuData)
          commit('SET_ROUTES', accessedRoutes)
          resolve(accessedRoutes)
        }
      }).catch(error => {
        console.log(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

