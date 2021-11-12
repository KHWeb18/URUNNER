<template>
    <div>
        <tiptap-vuetify
            v-model="content"
            :extensions="extensions"
            :toolbar-attributes="{ color: '' }"
            placeholder="Write something …"
            />
        <div class="comment_register_btn">
            <v-btn color="blue-grey darken-1 white-text" @click="contentSubmit()" class="item">
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
            this.$emit("fromEditor", this.content)
            this.content = ''
        }
        

    }
}
</script>

<style scoped>
.comment_register_btn {
    text-align: right;
    padding: 0px;
    padding-left: 20px;
}
</style>