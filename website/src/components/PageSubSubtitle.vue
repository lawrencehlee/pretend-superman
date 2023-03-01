<script lang="ts" setup>
defineProps<{
  text: string;
}>();
</script>

<template>
  <h3 class="py-2">
    <span class="text-xl font-bold align-middle">{{ text }}</span>
  </h3>
</template>
