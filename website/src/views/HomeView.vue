<script lang="ts" setup>
import { useUserStore } from "@/stores/user-store";

const userStore = useUserStore();
</script>

<template>
  <main>
    <div v-if="userStore.me">{{ userStore.me }}</div>
    <div v-if="!userStore.me">Not logged in</div>
  </main>
</template>
