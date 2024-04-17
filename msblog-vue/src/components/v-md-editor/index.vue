<template>
  <div>
    <v-md-editor
      v-model="editorValue"
      :disabled-menus="[]"
      height="400px"
      @upload-image="handleUploadImage"
    />
  </div>
</template>

<script>
import { uploadFile } from '@/api/articles'

export default {
  name: 'MdEditor',
  props: {
    // 接收值父组件传递值
    content: String
  },
  data() {
    return {
      editorValue: this.content != null ? this.content : ''
    }
  },
  watch: {
    editorValue: function(newNum, oldNum) {
      // 修改调用者传入的值
      this.$emit('update:content', newNum)
    }
  },
  created() {
  },
  methods: {
    // v-md-editor 文件上传
    handleUploadImage(event, insertImage, files) {
      event.preventDefault()
      const file = files[0]
      const formData = new FormData()
      formData.append('file', file)

      const form = new FormData()
      form.append('file', file)

      uploadFile(form).then(res => {
        // 添加图片到内容
        insertImage({
          url: res.data
        })
      })
    }
  }
}
</script>

<style>
</style>
