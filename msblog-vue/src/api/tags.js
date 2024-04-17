import request from '@/utils/request'

export function getTagsList(data) {
  return request({
    url: '/tags/page',
    method: 'post',
    data
  })
}

export function getTagsAll(data) {
  return request({
    url: '/tags/list',
    method: 'get',
    data
  })
}

/**
 * 更新标签
 * @param {*} data
 * @returns
 */
export function updateTags(data) {
  return request({
    url: '/tags',
    method: 'put',
    data
  })
}

/**
 * 新增标签
 * @param {*} data
 * @returns
 */
export function addTags(data) {
  return request({
    url: '/tags',
    method: 'post',
    data
  })
}

/**
 * 删除标签
 * @param {*} data
 * @returns
 */
export function deleteTags(id) {
  return request({
    url: `/tags/${id}`,
    method: 'delete'
  })
}

// 下载标签导入模板
export function donwloadTagsTemplate() {
  return request({
    url: '/tags/download',
    method: 'get',
    responseType: 'blob'
  })
}
