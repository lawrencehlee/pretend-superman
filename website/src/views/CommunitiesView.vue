<script lang="ts" setup>
import CommunitiesList from "@/components/CommunitiesList.vue";
import { useRequireLogin } from "@/composables/useRequireLogin";

useRequireLogin();
</script>

<template>
  <CommunitiesList></CommunitiesList>
</template>
