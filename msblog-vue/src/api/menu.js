import request from '@/utils/request'

/**
 * 菜单分页查询
 * @param {*} data
 * @returns
 */
export function getMenuPage(data) {
  return request({
    url: '/menu/page',
    method: 'post',
    data
  })
}

/**
 * 查询所有菜单
 * @param {*} data
 * @returns
 */
export function getMenuList() {
  return request({
    url: '/menu/list',
    method: 'get'
  })
}

/**
 * 查询所有菜单
 * @param {*} data
 * @returns
 */
export function getMenuListByUserId() {
  return request({
    url: '/menu/getMenuListByUserId',
    method: 'get'
  })
}

/**
 * 查询菜单树形结构
 * @param {*} data
 * @returns
 */
export function getMenuTree() {
  return request({
    url: '/menu/menuTree',
    method: 'get'
  })
}

/**
 * 更新菜单
 * @param {*} data
 * @returns
 */
export function updateMenu(data) {
  return request({
    url: '/menu',
    method: 'put',
    data
  })
}

/**
 * 新增菜单
 * @param {*} data
 * @returns
 */
export function addMenu(data) {
  return request({
    url: '/menu',
    method: 'post',
    data
  })
}

/**
 * 删除菜单
 * @param {*} data
 * @returns
 */
export function deleteMenu(id) {
  return request({
    url: `/menu/${id}`,
    method: 'delete'
  })
}
