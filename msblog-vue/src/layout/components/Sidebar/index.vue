<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        unique-opened
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="(item, i) in routes" :key="i" :item="item" :base-path="item.path" />
      </el-menu>

      <a v-if="!isCollapse" target="_blank" href="https://qarabala.gitee.io/blog/" style="padding: 6px; text-align: center; margin-top: auto">
        <el-image
          style="width: 90%;"
          src="https://img.quanxiaoha.com/quanxiaoha/169496189031444"
          fit="fit"
        />
      </a>
    </el-scrollbar>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { Logo, SidebarItem },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      // return this.$router.options.routes
      return this.$store.getters.menusRoutes
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
