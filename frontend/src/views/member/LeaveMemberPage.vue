<template>
    <v-container style="max-width: 500px;">
        <h2 style="padding:0px 0px 20px 0px;">회원탈퇴</h2>
        <div>
          <div class="secession">
                    <div class="label_wrap">
                      <span class="toggle_btn"><i class="fal fa-chevron-right"></i></span>
                    </div>

                    <div class="optional">
                      <div class="notice">
                        <h3 style="padding:0px 0px 20px 0px;">탈퇴 안내 사항</h3>
                        <p>유러너에 만족하지 못하셨나요? 탈퇴하기 전에 먼저 개선 요청을 해보시는 건 어떨까요?<br>
                          그래도 탈퇴하시겠다면 탈퇴 전에 아래 유의사항을 꼭 읽어주세요!</p>
                        <p>🙇🏻‍♂️ 감사합니다 🙇🏻‍♀️</p><br>
                        <p>1. 회원이 작성한 콘텐츠(동영상, 게시물, 댓글 등)는 자동적으로 삭제되지 않으며, 만일 삭제를 원하시면 탈퇴 이전에 삭제가 필요합니다.</p>
                        <p>2. 탈퇴 후 동일한 메일로 재가입이 가능하지만, 탈퇴 한 계정과 연동되지 않습니다.</p>
                      </div>

                        <!-- 비밀번호 확인 구간 미구현

                        <p>3. 현재 비밀번호를 입력하고 탈퇴하기를 누르시면 위 내용에 동의하는 것으로 간주됩니다.</p>
                      </div>

                      <v-text-field
                        v-model="password"
                        :error-messages="passwordErrors"
                        label="현재 비밀번호"
                        required
                        password
                        @input="$v.password.$touch()"
                        @blur="$v.password.$touch()"
                        ></v-text-field>

                        <v-text-field
                        v-model="confirmPassword"
                        :error-messages="passwordErrors"
                        label="현재 비밀번호 확인"
                        required
                        password
                        @input="$v.password.$touch()"
                        @blur="$v.password.$touch()"
                      ></v-text-field>
                      -->

                      <small class="invalid"></small>
                      <div class="button_container">

                        <div class="버튼">
                            <v-btn
                                class="mb-3"
                                @click="onDelete"
                                fluid
                                >
                                탈퇴하기
                            </v-btn>

                            <router-link style="text-decoration: none; color:black"
                            :to="{ name: '' }">
                                    <v-btn
                                    class="mb-3"
                                    fluid
                                    >
                                    돌아가기
                                </v-btn>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </v-container>
</template>

<script>


import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
    name: 'LeaveMemberPage',
    props: {
        memberNo: {
            type: String,
            required: true
        }
    },
    components: {
    },
    computed: {
        ...mapState(['member'])
    },
    created () {
        this.fetchMember(this.memberNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchMember']),
        onDelete () {
            var result = confirm('탈퇴 하시겠습니까?')
            if(result) {
                const { memberNo } = this.member
                axios.delete(`http://localhost:7777/memberManagement/leaveMember/${memberNo}`)
                        .then(() => {
                            alert('탈퇴 되었습니다.')
                            this.$router.push({ name: 'MainPage' })
                        })
                        .catch(err => {
                            alert(err.response.data.message)
                        })
            }
        }
    }
}
</script>

<style scoped>

.버튼 {
  padding:0px 200px 0px 200px;
  align-content: center;
}
</style>