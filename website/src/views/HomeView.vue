<script lang="ts" setup>
import { useUserStore } from "@/stores/user-store";
import CommunitiesList from "../components/CommunitiesList.vue";

const userStore = useUserStore();
</script>

<template>
  <main>
    <div v-if="!userStore.me">
      <p>
        Chilli is a simple yet flexible bot for facilitating online pick-up
        games (PUGs). Login with Discord to get started.
      </p>
    </div>
    <div v-if="userStore.me">
      <CommunitiesList></CommunitiesList>
    </div>
  </main>
</template>
