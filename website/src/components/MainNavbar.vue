<script lang="ts" setup>
import * as AuthService from "@/services/auth-service";
import { useUserStore } from "@/stores/user-store";
import { onMounted } from "vue";
import NavbarLink from "@/components/NavbarLink.vue";

const userStore = useUserStore();

onMounted(async () => {
  await userStore.updateMeWithRetry();
});

async function logout() {
  await AuthService.logout();
  await userStore.updateMe();
}

async function login() {
  const user = await AuthService.login();
  if (!user) {
    // TODO - better handling
    throw Error("No user");
  }
  await userStore.updateMePostLogin(user);
}
</script>

<template>
  <header>
    <div class="pt-8 pb-4 grid justify-items-center">
      <div>Chilli logo here</div>
    </div>
    <nav class="py-2 px-8 grid grid-flow-col justify-items-center">
      <NavbarLink>
        <RouterLink to="/">Home</RouterLink>
      </NavbarLink>
      <NavbarLink v-if="!userStore.me">
        <a href="#" @click="login">Login</a>
      </NavbarLink>
      <NavbarLink v-if="userStore.me">
        <a href="#" @click="logout">Logout</a>
      </NavbarLink>
    </nav>
  </header>
</template>
