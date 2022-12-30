<script lang="ts" setup>
import { computed, onMounted, ref, watch } from "vue";
import type { Community } from "@/services/communities-service";
import { get } from "@/services/communities-service";
import { useRoute } from "vue-router";
import { collapseToString } from "@/lib/utils";
import PageTitle from "@/components/PageTitle.vue";
import BreadcrumbTrail from "@/components/BreadcrumbTrail.vue";
import type Breadcrumb from "@/services/breadcrumb";
import { useRequireLogin } from "@/composables/useRequireLogin";
import MembersTable from "@/components/MembersTable.vue";
import QueuesList from "@/components/QueuesList.vue";

useRequireLogin();

const community = ref<Community>();
const route = useRoute();
const crumbs = computed<Breadcrumb[]>(() => [
  {
    name: "Communities",
    path: "/communities",
  },
  {
    name: community?.value?.slug,
    path: route.path,
  },
]);
onMounted(async () => {
  community.value =
    (await get(collapseToString(route.params.slug))) ?? undefined;
});

watch(
  () => route.params.slug,
  async (newSlug) => {
    community.value = (await get(collapseToString(newSlug))) ?? undefined;
  }
);
</script>

<template>
  <BreadcrumbTrail :crumbs="crumbs"></BreadcrumbTrail>
  <div v-if="!community">Not found TODO</div>
  <div v-if="community">
    <PageTitle :text="community.name" :suffix-subscript="community.slug">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="icon icon-tabler icon-tabler-social inline"
        width="28"
        height="28"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="#65a30d"
        fill="none"
        stroke-linecap="round"
        stroke-linejoin="round"
      >
        <path stroke="none" d="M0 0h24v24H0z" fill="none" />
        <circle cx="12" cy="5" r="2" />
        <circle cx="5" cy="19" r="2" />
        <circle cx="19" cy="19" r="2" />
        <circle cx="12" cy="14" r="3" />
        <line x1="12" y1="7" x2="12" y2="11" />
        <line x1="6.7" y1="17.8" x2="9.5" y2="15.8" />
        <line x1="17.3" y1="17.8" x2="14.5" y2="15.8" />
      </svg>
    </PageTitle>
    <QueuesList :community-id="community.id"></QueuesList>
    <MembersTable :community-id="community.id"></MembersTable>
  </div>
</template>
