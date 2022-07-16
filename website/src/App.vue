<script lang="ts" setup>
import { RouterView, useRoute, useRouter } from "vue-router";
import Navbar from "@/components/Navbar.vue";
import { useMyUserStore } from "@/stores/my-user";

const myUserStore = useMyUserStore();

const route = useRoute();
const router = useRouter();

// Validates state param from login redirect
const stateQueryParam = route.query.state;
if (stateQueryParam) {
  const stateEncoded =
    stateQueryParam instanceof Array ? stateQueryParam[0] : stateQueryParam;
  if (stateEncoded) {
    const redirectPath: string | null =
      myUserStore.verifyAndClearState(stateEncoded);
    if (redirectPath === null) {
      router.push("/");
    } else {
      router.push(redirectPath);
    }
  }
}

myUserStore.update();
</script>

<template>
  <Navbar />
  <RouterView />
</template>
