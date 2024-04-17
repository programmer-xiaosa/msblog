import request from '@/utils/request'

export function getCategoryList(data) {
  return request({
    url: '/category/page',
    method: 'post',
    data
  })
}

export function getCategoryAll(data) {
  return request({
    url: '/category/list',
    method: 'get',
    data
  })
}

/**
 * 更新分类
 * @param {*} data
 * @returns
 */
export function updateCategory(data) {
  return request({
    url: '/category',
    method: 'put',
    data
  })
}

/**
 * 新增分类
 * @param {*} data
 * @returns
 */
export function addCategory(data) {
  return request({
    url: '/category',
    method: 'post',
    data
  })
}

/**
 * 删除分类
 * @param {*} data
 * @returns
 */
export function deleteCategory(id) {
  return request({
    url: `/category/${id}`,
    method: 'delete'
  })
}

// 下载分类导入模板
export function donwloadCategoryTemplate() {
  return request({
    url: '/category/download',
    method: 'get',
    responseType: 'blob'
  })
}
