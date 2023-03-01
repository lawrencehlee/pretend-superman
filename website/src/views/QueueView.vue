<script lang="ts" setup>
import useRequireLogin from "@/composables/use-require-login";
import useBreadcrumbs from "@/composables/use-breadcrumbs";
import BreadcrumbTrail from "@/components/BreadcrumbTrail.vue";
import useResourceByRouteId from "@/composables/use-resource-by-id";
import {getQueue} from "@/services/queues-service";
import PageTitle from "@/components/PageTitle.vue";

useRequireLogin();

const breadcrumbs = useBreadcrumbs();
const queue = useResourceByRouteId((route) => route.params.queueId, getQueue);
</script>

<template>
  <BreadcrumbTrail :crumbs="breadcrumbs"></BreadcrumbTrail>
  <div v-if="!queue">Not found TODO</div>
  <div v-else>
    <PageTitle :text="queue.name"></PageTitle>
  </div>
</template>
