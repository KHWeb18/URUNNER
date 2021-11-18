<template>
    <div>
        <v-container>
                    <v-row>
                        <v-col cols="40" xs="40" align="center">
                            <img class="mb-3" src="@/assets/together.jpeg">
                        </v-col>
                        <v-col cols="12" xs="12" align="center">
                            <h3 class="mb-3">{{ contents }}</h3>
                        <p> {{ body }}</p>
                        <p> {{ body2 }}</p>
                        </v-col>
                    </v-row>
        </v-container><br><br><br>
        <v-container>
            <div align="center"> 
            <h1>유러너의 약속</h1>
            <p>_________</p>
            </div><br>
                <v-col cols="12" xs="12" align="center">
                    <v-card
                        class="d-flex flex-nowrap py-3"
                        flat="flat"
                        tile="tile"
                        width="900">
                    <v-card class="order-3 pa-6" outlined="outlined" tile="tile">
                        <img src="http://www.adddata.co.kr/default/img/company/img0105.png">
                    <div class="co-title">
                        <h3>PARTNER</h3>
                        <h5>URUNNER은 언제나 여러분의 파트너 입니다.</h5>
                    </div>
            </v-card>
            <v-card class="order-3 pa-6" outlined="outlined" tile="tile">
                <img src="http://www.adddata.co.kr/default/img/company/img0106.png">
                    <div class="co-title">
                        <h3>PASSION</h3>
                        <h5>URUNNER은 끊없는 열정으로 사이트를 운용하겠습니다.</h5>
                    </div>
                </v-card>
                <v-card class="order-3 pa-6" outlined="outlined" tile="tile">

                    <img src="http://www.adddata.co.kr/default/img/company/img0104.png">
                        <div class="co-title">
                            <h3>GUIDE</h3>
                            <h5>URUNNER은 모든순간의 진실한 가이드가 되겠습니다.</h5>
                        </div>
                    </v-card>
                </v-card>
                </v-col>
        </v-container>
    </div>
</template>

<script>
export default {
    data () {
        return {
            contents: '우리는 때로 무언가를 배워야만 합니다.',
            body: '꿈을 이루기 위해서, 하고 싶은걸 해나가기 위해선 그 분야에 대한 배움이 가장 기본적인 요소이기 때문입니다. 하지만, 모두에게 배우기 좋은 환경이 보장 되진 않죠.',
            body2: '그 환경을 저희 유러너가 만들고 지원 하겠습니다!'
        }
    }
}
</script>