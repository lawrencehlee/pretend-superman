import { useUserStore } from "@/stores/user-store";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

export function useRequireLogin() {
  const userStore = useUserStore();
  const router = useRouter();

  userStore.$subscribe(async (mutation, state) => {
    if (!state.me) {
      await router.push("/");
    }
  });

  onMounted(async () => {
    if (!userStore.me) {
      await router.push("/");
    }
  });
}
