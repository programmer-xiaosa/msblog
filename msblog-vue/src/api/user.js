import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo(id) {
  return request({
    url: `/${id}`,
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
