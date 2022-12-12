<script lang="ts" setup>
import type Breadcrumb from "@/services/breadcrumb";

defineProps<{
  crumbs: Breadcrumb[];
}>();
</script>

<template>
  <div>
    <span
      v-for="(crumb, index) in crumbs"
      :key="crumb.path"
      class="text-sm text-zinc-700"
    >
      <RouterLink :to="crumb.path">{{ crumb.name ?? "unknown" }}</RouterLink>
      <span v-if="index < crumbs.length - 1"> &#8250; </span>
    </span>
  </div>
</template>
