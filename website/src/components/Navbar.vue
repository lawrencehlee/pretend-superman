<script lang="ts" setup>
import { useMyUserStore } from "@/stores/my-user";

const myUserStore = useMyUserStore();

async function logout() {
  await myUserStore.logout();
}

async function login() {
  const stateEncoded = myUserStore.createState();
  const url = `https://discord.com/api/oauth2/authorize?client_id=966114573226770462&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fapi%2Foauth2%2Fdiscord%2Fredirect&response_type=code&scope=identify%20email&state=${stateEncoded}`;
  window.location.href = url;
}
</script>

<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
        <a v-if="!myUserStore.me" href="#" @click="login">Login with Discord</a>
        <a v-if="myUserStore.me" href="#" @click="logout"> Logout </a>
      </nav>
    </div>
  </header>
</template>
