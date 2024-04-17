const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  userId: state => state.user.userId,
  menus: state => state.user.menus,
  permissions: state => state.user.permissions,
  menusRoutes: state => state.permission.routes
}
export default getters
