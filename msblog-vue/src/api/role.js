import request from '@/utils/request'

/**
 * 角色分页查询
 * @param {*} data
 * @returns
 */
export function getRolePage(data) {
  return request({
    url: '/role/page',
    method: 'post',
    data
  })
}

/**
 * 查询所有角色
 * @param {*} data
 * @returns
 */
export function getRoleList() {
  return request({
    url: '/role/list',
    method: 'get'
  })
}

/**
 * 更新角色
 * @param {*} data
 * @returns
 */
export function updateRole(data) {
  return request({
    url: '/role',
    method: 'put',
    data
  })
}

/**
 * 新增角色
 * @param {*} data
 * @returns
 */
export function addRole(data) {
  return request({
    url: '/role',
    method: 'post',
    data
  })
}

/**
 * 删除角色
 * @param {*} data
 * @returns
 */
export function deleteRole(id) {
  return request({
    url: `/role/${id}`,
    method: 'delete'
  })
}

