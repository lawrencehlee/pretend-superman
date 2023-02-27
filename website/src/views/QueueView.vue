<script lang="ts" setup>
import useRequireLogin from "@/composables/use-require-login";
import { useRoute } from "vue-router";
import useBreadcrumbs from "@/composables/use-breadcrumbs";
import BreadcrumbTrail from "@/components/BreadcrumbTrail.vue";

useRequireLogin();

const route = useRoute();
const breadcrumbs = useBreadcrumbs();
</script>

<template>
  <BreadcrumbTrail :crumbs="breadcrumbs"></BreadcrumbTrail>
  hi
</template>
