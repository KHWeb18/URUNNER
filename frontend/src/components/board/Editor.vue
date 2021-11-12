<template>
    <div>
        <tiptap-vuetify
            v-model="content"
            :extensions="extensions"
            :toolbar-attributes="{ color: '' }"
            placeholder="Write something …"
            />
        <div class="button_box">
        <v-container fluid>
            <v-combobox
            v-model="tags"
            :filter="filter"
            :hide-no-data="!search"
            :items="items"
            :search-input.sync="search"
            hide-selected
            label="Search for an option"
            multiple
            small-chips
            solo
            >
            <template v-slot:no-data>
                <v-list-item>
                <span class="subheading">Create</span>
                <v-chip
                    :color="`${colors[nonce - 1]} lighten-3`"
                    label
                    small
                >
                    {{ search }}
                </v-chip>
                </v-list-item>
            </template>
            <template v-slot:selection="{ attrs, item, parent, selected }">
                <v-chip
                v-if="item === Object(item)"
                v-bind="attrs"
                :color="`${item.color} lighten-3`"
                :input-value="selected"
                label
                small
                >
                <span class="pr-2">
                    {{ item.text }}
                </span>
                <v-icon
                    small
                    @click="parent.selectItem(item)"
                >
                    $delete
                </v-icon>
                </v-chip>
            </template>
            <template v-slot:item="{ index, item }">
                <v-text-field
                v-if="editing === item"
                v-model="editing.text"
                autofocus
                flat
                background-color="transparent"
                hide-details
                solo
                @keyup.enter="edit(index, item)"
                ></v-text-field>
                <v-chip
                v-else
                :color="`${item.color} lighten-3`"
                dark
                label
                small
                >
                {{ item.text }}
                </v-chip>
                <v-spacer></v-spacer>
                <v-list-item-action @click.stop>
                <v-btn
                    icon
                    @click.stop.prevent="edit(index, item)"
                >
                    <v-icon>{{ editing !== item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
                </v-btn>
                </v-list-item-action>
            </template>
            </v-combobox>
        </v-container>
            
        <router-link :to="{ name: 'FreeBoardListPage' }">
            <v-btn>
                취소
            </v-btn>
        </router-link>
        <v-btn color="light-blue lighten-1 text center" @click="contentSubmit()" class="item">
            등록
        </v-btn>
        </div>
    </div>
</template>
 
<script>

import { TiptapVuetify, History, Blockquote, Link, Underline, Strike, Italic, ListItem,
           Bold, Code, HorizontalRule, HardBreak, Image, Heading } from 'tiptap-vuetify'

export default {
    components: { TiptapVuetify },
    props: {
        board: {
            type: Object,
            required: true
        }
    },
    created() {
        this.tags = JSON.parse(this.board.tags)
        this.content = this.board.content
    },
    data: () => ({
        content: '',
      activator: null,
      attach: null,
      colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
      editing: null,
      editingIndex: -1,
      items: [
        { header: 'Select an option or create one' },
        {
          text: 'Java',
          color: 'blue',
        },
        {
          text: 'Vue',
          color: 'red',
        },
        {
          text: 'Spring',
          color: 'green',
        },
        {
          text: 'Python',
          color: 'purple',
        },
        {
          text: 'SQL',
          color: 'indigo',
        },
        {
          text: 'Javascript',
          color: 'cyan',
        }
      ],
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
      },
      filter (item, queryText, itemText) {
        if (item.header) return false

        const hasValue = val => val != null ? val : ''

        const text = hasValue(itemText)
        const query = hasValue(queryText)

        return text.toString()
          .toLowerCase()
          .indexOf(query.toString().toLowerCase()) > -1
      },
      contentSubmit() {
          this.tags = JSON.stringify(this.tags)
          console.log(this.tags)
          console.log(typeof(this.tags))
            const { content, tags } = this
            this.$emit("fromEditor", { content, tags })
        }
    },
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
</style>