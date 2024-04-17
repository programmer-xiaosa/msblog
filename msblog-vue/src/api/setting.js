import request from '@/utils/request'

/**
 * 根据id查询设置
 * @param {*} data
 * @returns
 */
export function getSettings() {
  return request({
    url: '/setting/1',
    method: 'get'
  })
}

/**
 * 编辑设置
 * @param {*} data
 * @returns
 */
export function updateSetting(data) {
  return request({
    url: '/setting',
    method: 'put',
    data
  })
}
