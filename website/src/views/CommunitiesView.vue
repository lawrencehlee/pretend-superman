<script lang="ts" setup>
import CommunitiesList from "@/components/CommunitiesList.vue";
import { useRequireLogin } from "@/composables/useRequireLogin";
import BreadcrumbTrail from "@/components/BreadcrumbTrail.vue";
import type Breadcrumb from "@/services/breadcrumb";

useRequireLogin();

const crumbs: Breadcrumb[] = [
  {
    name: "Communities",
    path: "/communities",
  },
];
</script>

<template>
  <BreadcrumbTrail :crumbs="crumbs"></BreadcrumbTrail>
  <CommunitiesList></CommunitiesList>
</template>
