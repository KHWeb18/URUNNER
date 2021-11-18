<template>
    <div>
        <v-spacer></v-spacer>
        <div align="center">
        <v-spacer></v-spacer><br>
            <h1>URUNNER 소개</h1>
            <p>_________</p>
        </div>
        <introduce-page-form/>
    </div>
</template>

<script>

import IntroducePageForm from '@/components/urunner/IntroducePageForm.vue'

export default {
    name: 'IntroducePage',
    components: {
        IntroducePageForm
    }
}
</script>