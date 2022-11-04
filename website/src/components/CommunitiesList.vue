<script lang="ts" setup>
import { onMounted, type Ref, ref } from "vue";
import * as CommunitiesService from "@/services/communities-service";
import CreateButton from "@/components/CreateButton.vue";
import CreateCommunityForm from "@/components/CreateCommunityForm.vue";

const communities: Ref<any[]> = ref([]);
const creating = ref(false);

onMounted(async () => {
  communities.value = await CommunitiesService.list();
});

function setCreating(value: boolean) {
  creating.value = value;
}
</script>

<template>
  <h1 class="text-3xl font-bold py-2">Communities</h1>
  <div v-if="!creating">
    <CreateButton @click="setCreating(true)">+ Create</CreateButton>
    <div v-if="communities.length > 0">
      {{ communities }}
    </div>
    <div v-if="communities.length == 0">No communities found.</div>
  </div>
  <div v-if="creating">
    <CreateCommunityForm
      @cancel-create="setCreating(false)"
    ></CreateCommunityForm>
  </div>
</template>
