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
    },
    async updateMePostLogin(user: User) {
      this.me = user;
    },
    async updateMeWithRetry() {
      await this.updateMe();
      if (!this.me) {
        // Crazy hack cause Supabase CLI doesn't immediately return a user after discord redirect
        await new Promise((r) => setTimeout(r, 100));
        await this.updateMe();
      }
    },
  },
});
