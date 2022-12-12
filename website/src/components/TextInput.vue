<script lang="ts" setup>
import SubscriptText from "@/components/SubscriptText.vue";

defineProps<{
  modelValue: string;
  label: string;
  placeholder?: string;
  pattern?: string;
  maxlength?: string;
  required: boolean;
  help?: string;
}>();
defineEmits<{
  (e: "update", modelValue: string): void;
}>();
</script>

<template>
  <div class="mb-3">
    <label class="block mb-1">
      {{ label }}
      <SubscriptText text="required"></SubscriptText>
    </label>
    <input
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      :name="label.toLowerCase()"
      type="text"
      :placeholder="placeholder ?? label"
      :pattern="pattern"
      :required="required"
      :maxlength="maxlength"
      class="p-1 rounded-lg bg-zinc-100 px-2 invalid:border-red-600"
    />
    <p v-if="help" class="text-xs text-zinc-500">{{ help }}</p>
  </div>
</template>
