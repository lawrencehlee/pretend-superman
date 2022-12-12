<script lang="ts" setup>
import SubscriptText from "@/components/SubscriptText.vue";

defineProps<{
  text: string;
  suffixSubscript?: string;
}>();
</script>

<template>
  <h1 class="py-2">
    <slot></slot>
    <span class="text-3xl font-bold align-middle px-2">{{ text }}</span>
    <SubscriptText v-if="suffixSubscript" :text="suffixSubscript"></SubscriptText>
  </h1>
</template>
