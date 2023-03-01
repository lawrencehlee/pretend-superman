<script lang="ts" setup>
import SubscriptText from "@/components/SubscriptText.vue";

interface Props {
  modelValue: string;
  label: string;
  showLabel?: boolean;
  placeholder?: string;
  pattern?: string;
  maxlength?: string;
  required: boolean;
  help?: string;
  type: string;
  applyMargins?: boolean;
  inputClass?: string;
}

withDefaults(defineProps<Props>(), {
  showLabel: true,
  applyMargins: true,
  inputClass: "",
});
defineEmits<{
  (e: "update", modelValue: string): void;
}>();
</script>

<template>
  <div :class="{ 'mb-3': applyMargins }">
    <label class="block" :class="{ 'mb-1': applyMargins }" v-if="showLabel">
      {{ label }}
      <SubscriptText text="required"></SubscriptText>
    </label>
    <input
      :value="modelValue"
      @input="$emit('update:modelValue', $event.target.value)"
      :name="label.toLowerCase()"
      :type="type"
      :placeholder="placeholder ?? label"
      :pattern="pattern"
      :required="required"
      :maxlength="maxlength"
      class="py-1 rounded-lg bg-zinc-100 px-2 invalid:border-red-600"
      :class="inputClass"
    />
    <p v-if="help" class="text-xs text-zinc-500">{{ help }}</p>
  </div>
</template>
