<script lang="ts" setup>
import CommunitiesList from "@/components/CommunitiesList.vue";
import useRequireLogin from "@/composables/use-require-login";
import BreadcrumbTrail from "@/components/BreadcrumbTrail.vue";
import useBreadcrumbs from "@/composables/use-breadcrumbs";

useRequireLogin();
const breadcrumbs = useBreadcrumbs();
</script>

<template>
  <BreadcrumbTrail :crumbs="breadcrumbs"></BreadcrumbTrail>
  <CommunitiesList></CommunitiesList>
</template>
