import request from '@/utils/request'

/**
 * 管理员分页查询
 * @param {*} data
 * @returns
 */
export function getAdminList(data) {
  return request({
    url: '/page',
    method: 'post',
    data
  })
}

/**
 * 启用禁用管理员账号
 * @param {*} data
 * @returns
 */
export function startOrStop(data) {
  return request({
    url: `/status/${data.status}?id=${data.id}`,
    method: 'post'
  })
}

/**
 * 管理员分页查询
 * @param {*} data
 * @returns
 */
export function updateAadmin(data) {
  return request({
    // url: '/',
    method: 'put',
    data
  })
}

/**
 * 修改密码
 * @param {*} data
 * @returns
 */
export function updatePassword(data) {
  return request({
    url: '/password',
    method: 'put',
    data
  })
}

export function addAdmin(data) {
  return request({
    // url: '/',
    method: 'post',
    data
  })
}

// 部门导出
export function excelUser() {
  return request({
    url: '/export',
    method: 'get',
    responseType: 'blob'
  })
}

/**
 * 删除管理员
 * @param {*} data
 * @returns
 */
export function deleteAdmin(id) {
  return request({
    url: `/${id}`,
    method: 'delete'
  })
}
