<script lang="ts" setup>
defineProps<{
  text: string;
}>();
</script>

<template>
  <h2 class="py-2">
    <slot></slot>
    <span class="text-2xl font-bold align-middle px-2">{{ text }}</span>
  </h2>
</template>
