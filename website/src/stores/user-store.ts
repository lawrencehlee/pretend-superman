import { defineStore } from "pinia";
import type { User } from "@supabase/supabase-js";
import * as AuthService from "@/services/auth-service";

export const useUserStore = defineStore("user", {
  state: () => {
    return {
      me: null as User | null,
    };
  },
  actions: {
    async updateMe() {
      this.me = await AuthService.me();
      // Crazy hack cause Supabase CLI doesn't immediately return a user after discord redirect
      if (!this.me) {
        await new Promise((r) => setTimeout(r, 500));
        this.me = await AuthService.me();
      }
    },
  },
});
