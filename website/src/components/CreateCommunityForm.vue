<script lang="ts" setup>
import { Ref, ref } from "vue";
import type { Community } from "@/services/communities-service";
import * as CommunitiesService from "@/services/communities-service";
import * as MembersService from "@/services/members-service";
import StyledInput from "@/components/StyledInput.vue";
import CancelButton from "@/components/CancelButton.vue";
import CreateButton from "@/components/CreateButton.vue";
import PageSubtitle from "@/components/PageSubtitle.vue";

const emit = defineEmits(["created", "cancelled"]);

const community: Ref<Partial<Community>> = ref({});

async function create() {
  const { data } = await CommunitiesService.createCommunity(community.value);
  if (!data || !data.id) {
    // TODO
    throw Error("No community id");
  }

  // No transactions :/
  const { error } = await MembersService.joinAsAdmin(data.id);
  if (error) {
    await CommunitiesService.deleteCommunity(data.id);
  }

  emit("created");
}
</script>

<template>
  <PageSubtitle text="Create community"></PageSubtitle>
  <form>
    <StyledInput
      label="Slug"
      pattern="^[a-z0-9]+(-[a-z0-9]+)*$"
      maxlength="32"
      placeholder="my-toxic-wonderland"
      required
      :help="
        'Lowercase alphanumeric and hyphens. A globally unique identifier that will appear in urls.' +
        'Choose carefully!'
      "
      v-model.trim="community.slug"
      type="text"
    >
    </StyledInput>
    <StyledInput
      label="Name"
      placeholder="My Toxic Wonderland"
      required
      v-model.trim="community.name"
      type="text"
    >
    </StyledInput>
  </form>
  <div class="flex flex-row justify-end gap-x-8">
    <CancelButton @click="$emit('cancelled')">Cancel</CancelButton>
    <CreateButton @click="create">Create</CreateButton>
  </div>
</template>
