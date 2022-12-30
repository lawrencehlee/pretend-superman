<script lang="ts" setup>
import { reactive } from "vue";
import type { Community } from "@/services/communities-service";
import * as CommunitiesService from "@/services/communities-service";
import * as MembersService from "@/services/members-service";
import TextInput from "@/components/TextInput.vue";
import CancelButton from "@/components/CancelButton.vue";
import CreateButton from "@/components/CreateButton.vue";
import PageSubtitle from "@/components/PageSubtitle.vue";

const emit = defineEmits(["created", "cancelled"]);

const community: Community = reactive({});

async function create() {
  const created = await CommunitiesService.create(community);
  if (!created.id) {
    throw Error("No community id");
  }
  await MembersService.joinAsAdmin(created.id);

  emit("created");
}
</script>

<template>
  <PageSubtitle text="Create community"></PageSubtitle>
  <form>
    <TextInput
      label="Slug"
      pattern="^[a-z0-9]+(-[a-z0-9]+)*$"
      maxlength="32"
      placeholder="my-toxic-wonderland"
      required
      :help="
        'Lowercase alphanumeric and hyphens. A globally unique identifier that will appear in urls.' +
        'Choose carefully!'
      "
      v-model="community.slug"
    >
    </TextInput>
    <TextInput
      label="Name"
      placeholder="My Toxic Wonderland"
      required
      v-model="community.name"
    >
    </TextInput>
  </form>
  <div class="flex flex-row justify-end gap-x-8">
    <CancelButton @click="$emit('cancelled')">Cancel</CancelButton>
    <CreateButton @click="create">Create</CreateButton>
  </div>
</template>
