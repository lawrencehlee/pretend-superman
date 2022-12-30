<script lang="ts" setup>
import PageSubtitle from "@/components/PageSubtitle.vue";
import {onMounted, ref} from "vue";
import {list, Queue} from "@/services/queues-service";

const creating = ref(false);
const queues = ref<Queue[]>([]);
const props = defineProps<{
  communityId: number;
}>();

function setCreating(value: boolean) {
  creating.value = value;
}

onMounted(async () => {
  queues.value = await list(props.communityId);
})
</script>

<template>
  <PageSubtitle text="Queues">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="icon icon-tabler icon-tabler-tallymarks inline"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      stroke-width="1.5"
      stroke="#65a30d"
      fill="none"
      stroke-linecap="round"
      stroke-linejoin="round"
    >
      <path stroke="none" d="M0 0h24v24H0z" fill="none" />
      <line x1="6" y1="5" x2="6" y2="19" />
      <line x1="10" y1="5" x2="10" y2="19" />
      <line x1="14" y1="5" x2="14" y2="19" />
      <line x1="18" y1="5" x2="18" y2="19" />
      <line x1="3" y1="17" x2="21" y2="7" />
    </svg>
  </PageSubtitle>
  <div v-if="!creating">
    <CreateButton @click="setCreating(true)">+ Create</CreateButton>
    <div v-if="queues.length > 0">
      <div v-for="queue in queues" :key="queue.id">
<!--        <NormalLink :to="`/communities/${community.slug}`">-->
<!--          <span class="text-xl">{{ community.name }}</span>-->
<!--          <SubscriptText :text="community.slug"></SubscriptText>-->
<!--        </NormalLink>-->
      </div>
    </div>
    <div v-if="queues.length == 0">No queues found.</div>
  </div>
  <div v-else>
    <CreateCommunityForm
        @cancelled="setCreating(false)"
        @created="postCreated()"
    ></CreateCommunityForm>
  </div>
</template>
