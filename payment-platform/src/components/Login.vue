<script lang="ts" setup>
import {ref} from 'vue'
import {Lock, User} from '@element-plus/icons-vue'
import {ElImage, ElInput} from 'element-plus'

const loginType = ref('account') // account 或 qrcode
const loginForm = ref({
  username: '',
  password: '',
  remember: false
})

const handleLogin = () => {
  // 处理登录逻辑
  console.log('登录信息：', loginForm.value)
}
</script>

<template>
  <div class="index-container">
    <div class="login-box">
      <div class="title">
        <h1>优付支付管理平台</h1>
      </div>
      <div class="login-form">
        <h2>欢迎登录</h2>
        <div class="login-type">
          <span 
            :class="{ active: loginType === 'account' }" 
            @click="loginType = 'account'"
          >账号登录</span>
          <span 
            :class="{ active: loginType === 'qrcode' }" 
            @click="loginType = 'qrcode'"
          >二维码登录</span>
        </div>

        <!-- 账号密码登录 -->
        <template v-if="loginType === 'account'">
          <div class="form-item">
            <el-input
              v-model="loginForm.username"
              :prefix-icon="User"
              placeholder="请输入用户名"
            />
          </div>
          <div class="form-item">
            <el-input
              v-model="loginForm.password"
              :prefix-icon="Lock"
              placeholder="请输入密码"
              type="password"
            />
          </div>
          <div class="form-options">
            <label>
              <input v-model="loginForm.remember" type="checkbox">
              <span>记住我</span>
            </label>
            <a class="forget-pwd" href="#">忘记密码？</a>
          </div>
          <button class="login-btn" @click="handleLogin">登录</button>
        </template>

        <!-- 二维码登录 -->
        <template v-else>
          <div class="qrcode-container">
            <p class="qrcode-tip">请用手机APP扫一扫二维码</p>
            <div class="qrcode-img">
              <!-- 这里替换成实际的二维码图片 -->
              <el-image fit="contain" src="path/to/qrcode.png" />
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<style scoped>
.index-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #fce38a 10%, #f38181 100%);
}

.login-box {
  display: flex;
  width: 900px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.title {
  flex: 1.1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  padding: 20px;
}

.title::before {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -65%);
  width: 80%;
  height: 260px;
  background-image: url('@/assets/login-bg.svg');
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
}

.title h1 {
  font-size: 36px;
  color: #333;
  font-weight: bold;
  margin-top: 220px;
  position: relative;
  z-index: 1;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
}

.login-form {
  flex: 0.8;
  padding: 20px;
  min-height: 300px;
  display: flex;
  flex-direction: column;
}

.login-form h2 {
  font-size: 24px;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

.login-type {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
  border-bottom: 1px solid #ddd;
}

.login-type span {
  padding: 10px 20px;
  cursor: pointer;
  margin: 0 10px;
  position: relative;
}

.login-type span.active {
  color: #f38181;
}

.login-type span.active::after {
  content: '';
  position: absolute;
  bottom: -1px;
  left: 0;
  width: 100%;
  height: 2px;
  background: #f38181;
}

.form-item {
  margin-bottom: 20px;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  font-size: 14px;
}

.forget-pwd {
  color: #f38181;
  text-decoration: none;
}

.login-btn {
  width: 100%;
  padding: 12px;
  background: #f38181;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.login-btn:hover {
  background: #ff9a9a;
}

.qrcode-container {
  text-align: center;
  padding: 20px;
}

.qrcode-tip {
  font-size: 14px;
  color: #666;
  margin-bottom: 20px;
}

.qrcode-img {
  width: 200px;
  height: 200px;
  margin: 0 auto;
}
</style>