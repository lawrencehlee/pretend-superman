<script lang="ts" setup>
import * as MembersService from "@/services/members-service";
import type { Member } from "@/services/members-service";
import type { Ref } from "vue";
import { onMounted, ref } from "vue";
import PageSubtitle from "@/components/PageSubtitle.vue";

const props = defineProps<{
  communityId: number;
}>();

const members: Ref<Member[]> = ref([]);

onMounted(async () => {
  members.value = await MembersService.list(props.communityId);
});
</script>

<template>
  <PageSubtitle text="Members">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      class="icon icon-tabler icon-tabler-users inline"
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
      <circle cx="9" cy="7" r="4" />
      <path d="M3 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
      <path d="M16 3.13a4 4 0 0 1 0 7.75" />
      <path d="M21 21v-2a4 4 0 0 0 -3 -3.85" />
    </svg>
  </PageSubtitle>
  <table class="min-w-full max-w-full">
    <thead class="bg-slate-300 border-b rounded-md">
      <tr class="text-left">
        <th class="px-2">Discord username</th>
        <th class="px-2">Role</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="member in members"
        v-bind:key="member.userId"
        class="bg-slate-100 border-b"
      >
        <td class="px-2">{{ member.user.discordFullName }}</td>
        <td class="px-2 capitalize">{{ member.role.toLowerCase() }}</td>
      </tr>
    </tbody>
  </table>
</template>
