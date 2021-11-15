<template>
 <tiptap-vuetify
            v-model="content"
            :extensions="extensions"
            :toolbar-attributes="{ color: '' }"
            placeholder="Describe Your Lecture.."
            @input="submitContent"
            />
</template>
 
<script>

import { TiptapVuetify, Heading, Bold, Italic, Strike, Underline, Code, Paragraph, BulletList, 
        OrderedList, ListItem, Link, Blockquote, HardBreak, HorizontalRule, History } from 'tiptap-vuetify'
 
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
            ],
            content: '',
        }
    },
    methods: {
      submitContent() {
        this.$emit('submitContent', this.content)
      }
    }
}
</script>