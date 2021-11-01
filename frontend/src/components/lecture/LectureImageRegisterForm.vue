<template>
  <v-container>
    <h2>강의 이미지 등록</h2>
    <hr>
    <form @submit.prevent="onSubmit">
      <div>
        <label for="thumb">썸네일 이미지: </label>
        <v-file-input label="upload tumbnail image" id="thumb" @change="thumbFile"></v-file-input>
        <v-alert border="top" colored-border type="info" elevation="2">
         <ul>
            <li>파일 사이즈 : 5M 이하</li>
            <li>파일 확장자 : png, jpg만 가능</li>
          </ul>
        </v-alert>

        <label for="detail">디테일 이미지: </label>
        <v-file-input label="upload detail image" id="detil" @change="detailFile"></v-file-input>
        <v-alert border="top" colored-border type="info" elevation="2">
         <ul>
            <li>파일 사이즈 : 5M 이하</li>
            <li>파일 확장자 : png, jpg만 가능</li>
          </ul>
        </v-alert>

        <v-btn @click="onSubmit">등록</v-btn>
      </div>
    </form>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      thumbnailImage: "thumbnail image",
      detailImage: "detail image",
    }
  },
  methods: {
     async onSubmit() {
      const formData1 = new FormData();
      formData1.append("image", this.thumbnailImage);

      const formData2 = new FormData();
      formData2.append("image", this.detailImage);

      console.log(formData1);
      console.log(this.thumbnailImage);
      this.$router.push('/mypage/lecture/lectureList')

      // try {
      //   const { data1 } = await axios.post("/lecture/upload/image/thumbnail/:lectureId", formData1,
      //     {
      //       headers: {
      //         "Content-Type": "multipart/form-data",
      //       },
      //     }
      //   );
      //   console.log(data);

      //   const { data2 } = await axios.post("/lecture/upload/image/detail/:lectureId", formData2,
      //     {
      //       headers: {
      //         "Content-Type": "multipart/form-data",
      //       },
      //     }
      //   );
      //   console.log(data2);

      // } catch (err) {
      //   console.log(err);
      // }
    },

    // 파일 변경 시 이벤트 핸들러
    thumbFile(file) {
      this.thumbnailImage = file;
    },
    detailFile(file) {
      this.detailImage = file;
    }
  }

}
</script>

<style>

</style>