import request from '@/utils/request'

export function getArticlesList(data) {
  return request({
    url: '/articles/page',
    method: 'post',
    data
  })
}

/**
 * 更新文章
 * @param {*} data
 * @returns
 */
export function updateArticles(data) {
  return request({
    url: '/articles',
    method: 'put',
    data
  })
}

/**
 * 新增文章
 * @param {*} data
 * @returns
 */
export function addArticles(data) {
  return request({
    url: '/articles',
    method: 'post',
    data
  })
}

/**
 * 删除文章
 * @param {*} data
 * @returns
 */
export function deleteArticles(id) {
  return request({
    url: `/articles/${id}`,
    method: 'delete'
  })
}

/**
 * 根据Id查询文章详情
 * @param {*} data
 * @returns
 */
export function getArticleById(id) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}


export function uploadFile(formData) {
  return request({
    url: '/common/upload',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
