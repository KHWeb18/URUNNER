<template>
    <div>
        <tiptap-vuetify
            v-model="introduce"
            :extensions="extensions"
            :toolbar-attributes="{ color: '' }"
            placeholder="Write something …"
            />
    </div>
</template>
 
<script>

import { TiptapVuetify, History, Blockquote, Link, Underline, Strike, Italic, ListItem,
           Bold, Code, HorizontalRule, HardBreak, Image, Heading } from 'tiptap-vuetify'

export default {
    components: { TiptapVuetify },
    props: {
        introduceForChild: {
            type: String,
            required: true
        }
    },
    created() {
      console.log('에디터 진입 완료')
        console.log(this.introduceForChild)
        this.introduce = this.introduceForChild
    },
    data: () => ({
      introduce: '',
        snackbar: false,
      activator: null,
      attach: null,
      colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
      editing: null,
      editingIndex: -1,
      nonce: 1,
      menu: false,
      tags: [
        {
          text: 'Java',
          color: 'blue',
        },
      ],
      x: 0,
      search: null,
      y: 0,

      extensions: [
        History,
        Blockquote,
        Link,
        Underline,
        Strike,
        Italic,
        ListItem,
        Bold,
        Code,
        HorizontalRule,
        HardBreak,
        Image,
        [Heading, {
            options: {
            levels: [1, 2, 3]
            }
        }],
      ]
    }),

    watch: {
      tags (val, prev) {
        if (val.length === prev.length) return

        this.tags = val.map(v => {
          if (typeof v === 'string') {
            v = {
              text: v,
              color: this.colors[this.nonce - 1],
            }

            this.items.push(v)

            this.nonce++
          }

          return v
        })
      },

      introduce() {
        const { introduce } = this
        this.$emit("fromEditor", { introduce })
        console.log("감지")
      }
    },

    methods: {
      edit (index, item) {
        if (!this.editing) {
          this.editing = item
          this.editingIndex = index
        } else {
          this.editing = null
          this.editingIndex = -1
        }
      }
    }
  }
</script>

<style scoped>
.tiptap-vuetify-editor .ProseMirror {
    min-height: 600px !important;
}
.v-application .mr-9 {
     min-height: 600px !important;
}
.v-application .ProseMirror {
    min-height: 600px !important;
}
.ProseMirror {
 min-height: 600px !important;
}
.container.container--fluid {
  padding: 4px 0px 0px 0px
}
.button_box2 {
    display: flex;
    justify-content: flex-end;
    margin: 0px;
}
.regist_btn {
  margin-left: 10px;
}
.v-input--selection-controls.v-input {
  margin: 5px 15px;
}
</style>