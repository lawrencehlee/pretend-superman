<script lang="ts" setup>
import * as AuthService from "@/services/auth-service";
import { useUserStore } from "@/stores/user-store";
import { onMounted } from "vue";

const userStore = useUserStore();
onMounted(async () => {
  await userStore.updateMe();
});

async function logout() {
  await AuthService.logout();
  await userStore.updateMe();
}

async function login() {
  await AuthService.login();
  await userStore.updateMe();
}
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <a v-if="!userStore.me" href="#" @click="login">Login</a>
        <a v-if="userStore.me" href="#" @click="logout"> Logout </a>
      </nav>
    </div>
  </header>
</template>
