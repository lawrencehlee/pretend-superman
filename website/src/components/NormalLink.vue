<script lang="ts" setup>
defineProps<{
  to: string;
}>();
</script>

<template>
  <RouterLink :to="to" class="hover:bg-zinc-100 rounded-l">
    <slot></slot>
  </RouterLink>
</template>
