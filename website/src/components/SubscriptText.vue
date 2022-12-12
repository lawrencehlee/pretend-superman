<script lang="ts" setup>
defineProps<{
  text: string;
}>();
</script>

<template>
  <span class="text-xs text-zinc-700 ml-0.5">({{ text }})</span>
</template>
