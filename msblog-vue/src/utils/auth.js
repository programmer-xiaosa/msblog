import Cookies from 'js-cookie'

export function getStorage(key) {
  return Cookies.get(key)
}

export function setStorage(key, value) {
  return Cookies.set(key, value)
}

export function removeStorage(key) {
  return Cookies.remove(key)
}
