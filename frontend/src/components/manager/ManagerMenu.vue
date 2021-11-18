<template>
<div>
<el-container align=center>
    <!-- header 사용자이름 [로그아웃] -->
    <el-header height="20px">
      <el-row>
        <el-col :span="4">관리자 관리 메뉴</el-col>
        <el-col :span="19" class="nav-right">
          <span class="nav-name">{{this.$cookies.get('USER_NAME')}}</span>
          <el-button class="logout" @click="logout" size="mini" type="info">홈으로</el-button>
        </el-col>
      <el-col align=center>
        <v-btn color="green" class="ml-2" depressed route :to="{name: 'ManagerNoticeListPage'}" >
            공지사항
        </v-btn>
        <v-btn color="orange" class="ml-2" depressed route :to="{name: 'ManagerMemberList'}" >
            회원관리
        </v-btn>
      </el-col>
      </el-row>
    </el-header>
  </el-container>
</div>

</template>

<script>
export default {
    methods: {
        logout (){
          let router = this.$router;
      router.push('/'); //메인으로 이동  
        }
    }
}
</script>


<style scoped>
  .form{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #9C97F0;
    background-color:#9C97F0;
    }
  .center{
    justify-content: center;
    align-items: center;
    background-color:#9C97F0; 
    padding: 10px 60px 1px 550px; 
    text-align:center;
  }
  .center div{
    margin-bottom: 6px;
  }
  .el-row{
    margin: 10px;
  }
  .button-style{
    width: 300px; 
    background-color:#9C97F0;
  }
  .nav-right{
    text-align: right; 
    font-size: 15px;
  }
  .nav-name{
    margin-right: 10px;
  }
  .el-header {
    padding: 6px 20px 80px 20px;
    background-color: #9C97F0;
    color: white;
  }
  .logout{
    background-color: white;
    color: #9C97F0;
  }
</style>