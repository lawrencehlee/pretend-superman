<script lang="ts" setup>
import { reactive } from "vue";
import type { Community } from "@/services/communities-service";
import CreateButton from "@/components/CreateButton.vue";

const community: Community = reactive({});
</script>

<template>
  <h2 class="text-2xl font-bold py-2">Create community</h2>
  <form>
    <div>Slug</div>
    <input v-model="community.slug" type="text" />
  </form>
  <CreateButton @click="$emit('cancelCreate')">Cancel</CreateButton>
</template>
