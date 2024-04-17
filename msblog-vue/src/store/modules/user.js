import { login, logout } from '@/api/user'
import { getStorage, setStorage, removeStorage } from '@/utils/auth'
import { resetRouter } from '@/router'
import { removeItem } from '@/utils/storage'

const getDefaultState = () => {
  return {
    token: getStorage('token'),
    name: getStorage('name'),
    avatar: getStorage('avatar'),
    permissions: getStorage('permissions') && JSON.parse(getStorage('permissions')),
    userId: getStorage('userId')
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_MENUS: (state, menus) => {
    state.menus = menus
  },
  SET_PERMISSIONS: (state, permissions) => {
    state.permissions = permissions
  },
  SET_USER_Id: (state, userId) => {
    state.userId = userId
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { email, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ email: email.trim(), password: password }).then(response => {
        const { data } = response

        commit('SET_TOKEN', data.token)
        commit('SET_PERMISSIONS', data.permissions)
        commit('SET_MENUS', data.menus)
        commit('SET_NAME', data.name)
        commit('SET_AVATAR', data.image)
        commit('SET_USER_Id', data.id)

        setStorage('token', data.token)
        setStorage('name', data.name)
        setStorage('avatar', data.image)
        setStorage('userId', data.id)
        setStorage('permissions', JSON.stringify(data.permissions))

        resolve(data.menus)
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeStorage('token')
        removeStorage('name')
        removeStorage('avatar')
        removeStorage('userId')
        removeStorage('permissions')
        // removeStorage('menus')

        // removeItem('permissions')
        removeItem('menus')

        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  },

  // remove token
  resetStorage({ commit }) {
    return new Promise(resolve => {
      removeStorage('token')
      removeStorage('name')
      removeStorage('avatar')
      removeStorage('userId')
      removeStorage('permissions')
      removeStorage('menus')

      removeItem('permissions')
      removeItem('menus')

      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

