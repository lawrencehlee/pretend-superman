<script lang="ts" setup>
import PageSubtitle from "@/components/PageSubtitle.vue";
import { onMounted, ref } from "vue";
import { listQueues, Queue } from "@/services/queues-service";
import CreateQueueForm from "@/components/CreateQueueForm.vue";
import CreateButton from "@/components/CreateButton.vue";
import { useRoute } from "vue-router";
import NormalLink from "@/components/NormalLink.vue";

const creating = ref(false);
const queues = ref<Queue[]>([]);
const props = defineProps<{
  communityId: number;
}>();
const route = useRoute();

function setCreating(value: boolean) {
  creating.value = value;
}

async function postCreated() {
  queues.value = await listQueues(props.communityId);
  setCreating(false);
}

onMounted(async () => {
  queues.value = await listQueues(props.communityId);
});
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
      <title>Queues</title>
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
        <NormalLink :to="`${route.path}/queues/${queue.id}`">
          <span class="text-xl">{{ queue.name }}</span>
        </NormalLink>
      </div>
    </div>
    <div v-if="queues.length === 0">No queues found.</div>
  </div>
  <div v-else>
    <CreateQueueForm
      :community-id="communityId"
      @cancelled="setCreating(false)"
      @created="postCreated()"
    ></CreateQueueForm>
  </div>
</template>
