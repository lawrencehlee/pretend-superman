<script lang="ts" setup>
import { useMyUserStore } from "@/stores/my-user";

const myUserStore = useMyUserStore();
</script>

<template>
  <main>
    <div v-if="myUserStore.me">{{ myUserStore.me }}</div>
  </main>
</template>
