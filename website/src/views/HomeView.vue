<script lang="ts" setup>
import { useUserStore } from "@/stores/user-store";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();

onMounted(() => {
  if (userStore.me) {
    router.push("/communities");
  }
});
</script>

<template>
  <main>
    <p>
      Chilli is a simple yet flexible bot for facilitating online pick-up games
      (PUGs). Login with Discord to get started.
    </p>
  </main>
</template>
