<script lang="ts" setup>
import { onMounted, type Ref, ref } from "vue";
import * as CommunitiesService from "@/services/communities-service";
import CreateButton from "@/components/CreateButton.vue";
import CreateCommunityForm from "@/components/CreateCommunityForm.vue";
import type { Community } from "@/services/communities-service";
import SubscriptText from "@/components/SubscriptText.vue";
import PageTitle from "@/components/PageTitle.vue";
import NormalLink from "@/components/NormalLink.vue";

const communities: Ref<Community[]> = ref([]);
const creating = ref(false);

onMounted(async () => {
  communities.value = await CommunitiesService.listMine();
});

function setCreating(value: boolean) {
  creating.value = value;
}

async function postCreated() {
  setCreating(false);
  communities.value = await CommunitiesService.listMine();
}
</script>

<template>
  <PageTitle text="Communities">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="icon icon-tabler icon-tabler-social inline"
      width="30"
      height="30"
      viewBox="0 0 24 24"
      stroke-width="1.5"
      stroke="#65a30d"
      fill="none"
      stroke-linecap="round"
      stroke-linejoin="round"
    >
      <title>Communities</title>
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
  <div v-if="!creating">
    <CreateButton @click="setCreating(true)">+ Create</CreateButton>
    <div v-if="communities.length > 0">
      <div v-for="community in communities" :key="community.id">
        <NormalLink :to="`/communities/${community.id}`">
          <span class="text-xl">{{ community.name }}</span>
          <SubscriptText :text="community.slug"></SubscriptText>
        </NormalLink>
      </div>
    </div>
    <div v-if="communities.length == 0">No communities found.</div>
  </div>
  <div v-else>
    <CreateCommunityForm
      @cancelled="setCreating(false)"
      @created="postCreated()"
    ></CreateCommunityForm>
  </div>
</template>
