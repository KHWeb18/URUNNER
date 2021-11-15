export const API_BASE_URL = 'http://localhost:7777';

export const API_URL_LOGIN = 'http://localhost:7777/api/login';

export const SAVE_COOKIE_ACCESS = 1 * 60 * 10000

export const SAVE_COOKIE_REFRESH = 60 * 60 * 10000

export const USER_NAME = 'USER_NAME';

export const ROLES = 'ROLES';

export const ACCESS_TOKEN = 'ACCESS_TOKEN';

export const REFRESH_TOKEN = 'REFRESH_TOKEN';

export const BEARER = "Bearer "

export const NAME = "NAME"

export const NICKNAME = "NICKNAME"

export const OAUTH2_REDIRECT_URI = 'http://localhost:8080/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const KAKAO_AUTH_URL = API_BASE_URL + '/oauth2/authorize/kakao?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const NAVER_AUTH_URL = API_BASE_URL + '/oauth2/authorize/naver?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const IMAGE_REQUEST = "http://localhost:7777/lecture/image"
