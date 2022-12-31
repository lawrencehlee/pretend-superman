<script lang="ts" setup>
import {Ref, ref, watch} from "vue";
import CreateButton from "@/components/CreateButton.vue";
import StyledInput from "@/components/StyledInput.vue";
import type {Team} from "@/services/queues-service";

const emit = defineEmits<{
  (e: "update", teams: Partial<Team>[]): void;
}>();

const rows: Ref<Partial<Team>[]> = ref([]);

function addRow() {
  rows.value.push({});
}

function duplicateRow(row: Partial<Team>) {
  rows.value.push({...row});
}

function deleteRow(index: number) {
  rows.value.splice(index, 1);
}

watch(rows, () => {
  emit("update", rows.value);
}, {deep: true});

</script>

<template>
  <div class="">
    Teams
    <div v-for="(row, index) in rows" v-bind:key="index" class="my-1">
      <div class="flex flex-row max-w-full justify-between items-center gap-1">
        <div class="flex flex-row basis-4/5 gap-2">
          <div class="basis-1/2">
            <StyledInput
                v-model.trim="row.name"
                label="Team name"
                required
                :apply-margins="false"
                input-class="min-w-full w-0"
                type="text"
                :show-label="false"
            ></StyledInput>
          </div>
          <div class="basis-1/2">
            <StyledInput
                v-model="row.size"
                label="Size"
                required
                :apply-margins="false"
                type="number"
                input-class="min-w-full w-0"
                :show-label="false"
            ></StyledInput>
          </div>
        </div>
        <div class="flex flex-row justify-evenly gap-2">
          <button @click="duplicateRow(row)" type="button">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-copy" width="24" height="24"
                 viewBox="0 0 24 24" stroke-width="1.5" stroke="#65a30d" fill="none" stroke-linecap="round"
                 stroke-linejoin="round">
              <title>Duplicate row</title>
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <rect x="8" y="8" width="12" height="12" rx="2"/>
              <path d="M16 8v-2a2 2 0 0 0 -2 -2h-8a2 2 0 0 0 -2 2v8a2 2 0 0 0 2 2h2"/>
            </svg>
          </button>
          <button @click="deleteRow(index)" type="button">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-trash" width="24" height="24"
                 viewBox="0 0 24 24" stroke-width="1.5" stroke="#65a30d" fill="none" stroke-linecap="round"
                 stroke-linejoin="round">
              <title>Delete row</title>
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <line x1="4" y1="7" x2="20" y2="7"/>
              <line x1="10" y1="11" x2="10" y2="17"/>
              <line x1="14" y1="11" x2="14" y2="17"/>
              <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12"/>
              <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3"/>
            </svg>
          </button>
        </div>
      </div>
    </div>
    <div>
      <CreateButton @click="addRow()">+ Add team</CreateButton>
    </div>
  </div>
</template>
