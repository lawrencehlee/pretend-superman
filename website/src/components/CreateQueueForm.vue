<script lang="ts" setup>
import { ref } from "vue";
import StyledInput from "@/components/StyledInput.vue";
import CancelButton from "@/components/CancelButton.vue";
import CreateButton from "@/components/CreateButton.vue";
import type { Queue } from "@/services/queues-service";
import DynamicRowsInput from "@/components/DynamicRowsInput.vue";
import PageSubSubtitle from "@/components/PageSubSubtitle.vue";
import { createQueue } from "@/services/queues-service";

const props = defineProps<{
  communityId: number;
}>();

const emit = defineEmits(["created", "cancelled"]);

const queue = ref<Partial<Queue>>({
  communityId: props.communityId,
  algorithm: "Trueskill",
  teams: [],
  settings: {},
});

async function create() {
  await createQueue(queue.value);
  emit("created");
}
</script>

<template>
  <PageSubSubtitle text="Create queue"></PageSubSubtitle>
  <form>
    <StyledInput
      label="Name"
      placeholder="Quickplay"
      required
      v-model.trim="queue.name"
      type="text"
    >
    </StyledInput>
    <DynamicRowsInput
      @update="(teams) => (queue.teams = teams)"
    ></DynamicRowsInput>
  </form>
  <div class="flex flex-row justify-end gap-x-8">
    <CancelButton @click="$emit('cancelled')">Cancel</CancelButton>
    <CreateButton @click="create">Create</CreateButton>
  </div>
</template>
