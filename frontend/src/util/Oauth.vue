<template>
  <div></div>
</template>

<script>
//      그냥 querySTring을 받기위한 컴포넌트입니다.
//      혹시 이 부분에서 좋은 아이디어있으시면 추천부탁드려요
import { PasingInfor } from "./APIUtil"
import EventBus from "../event";

export default {
    created() {
        // console.log("router", this.$route.query.access_token)
        // console.log("test1")
        PasingInfor(this.$route.query)
        EventBus.$emit('isLogin', "isLogin");

        
        this.$router.push("/")
    }

}
</script>

<style>

</style>