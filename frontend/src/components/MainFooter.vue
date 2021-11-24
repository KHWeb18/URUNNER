<template>
    <v-footer dark="dark" padless="padless">
        <v-card
            flat="flat"
            tile="tile"
            class="blue-grey darken-4 white--text text-center hidden-md-and-down"
            width="100%">
            <v-card-text>
                <v-btn :href="'https://facebook.com'" target="_blank" class="mx-4 white--text" icon="icon">
                    <v-icon size="24px">mdi-facebook</v-icon>
                </v-btn>
                <v-btn :href="'https://twitter.com'" target="_blank" class="mx-4 white--text" icon="icon">
                    <v-icon size="24px">mdi-twitter</v-icon>
                </v-btn>
                <v-btn :href="'https://instagram.com'" target="_blank" class="mx-4 white--text" icon="icon">
                    <v-icon size="24px">mdi-instagram</v-icon>
                </v-btn>
                <v-btn :href="'https://linkedin.com'" target="_blank" class="mx-4 white--text" icon="icon">
                    <v-icon size="24px">mdi-linkedin</v-icon>
                </v-btn>
            </v-card-text>

            <v-card-text class="white--text pt-0">
                (주)URUNNER | 대표: 유러너 | 소재지: KH정보교육원<br>
                    This is the 스마트 콘텐츠와 웹융합 응용SW개발자 Team Project Web Page.<br>
                    Copyright© 2021 URUNNER. All Rights Reserved.
                </v-card-text>

                <v-divider></v-divider>

                <v-card-text class="white--text">
                    {{ new Date().getFullYear() }}
                    —
                    <strong>URUNNER</strong>
                </v-card-text>
            </v-card>
        </v-footer>
</template>

<script>

export default {
  name: 'Footer',

  data: () => ({
    icons: [
      '',
      'mdi-twitter',
      'mdi-linkedin',
      'mdi-instagram',
    ],
  }),
};
</script>