<script lang="ts" setup>
defineProps<{
  text: string;
}>();
</script>

<template>
  <h1 class="py-2">
    <slot></slot>
    <span class="text-3xl font-bold align-middle px-2">{{ text }}</span>
  </h1>
</template>
