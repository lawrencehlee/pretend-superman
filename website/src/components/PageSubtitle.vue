<script lang="ts" setup>

defineProps<{
  text: string;
}>();
</script>

<template>
    <h2 class="text-2xl font-bold py-2">{{ text }}</h2>
</template>
