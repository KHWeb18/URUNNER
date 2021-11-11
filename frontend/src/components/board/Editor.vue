<template>
    <div>
        <tiptap-vuetify
            v-model="content"
            :extensions="extensions"
            :toolbar-attributes="{ color: '' }"
            placeholder="Write something …"
            />
        <div class="button_box">
        <v-text-field v-model="tags" placeholder="#Java#Vue#PYTHON"></v-text-field>
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

import { TiptapVuetify, Heading, Bold, Italic, Strike, Underline, Code, Paragraph, BulletList, 
        OrderedList, ListItem, Link, Blockquote, HardBreak, HorizontalRule, History, Image } from 'tiptap-vuetify'
 
export default {
    components: { TiptapVuetify },
    data() {
        return {
            extensions: [
            History,
            Blockquote,
            Link,
            Underline,
            Strike,
            Italic,
            ListItem,
            BulletList,
            OrderedList,
            [Heading, {
                options: {
                levels: [1, 2, 3]
                }
            }],
            Bold,
            Code,
            HorizontalRule,
            Paragraph,
            HardBreak,
            Image
            ],
            content: '',
            tags: ''
        }
    },
    methods: {
        contentSubmit() {
            const { content, tags } = this
            this.$emit("fromEditor", { content, tags })
        }
        

    }
}
</script>