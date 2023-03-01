<script lang="ts" setup>
import MainNavbar from "./components/MainNavbar.vue";
import { RouterView } from "vue-router";
</script>

<template>
  <div class="container mx-auto px-8 max-w-5xl">
    <MainNavbar />
    <RouterView />
  </div>
</template>
