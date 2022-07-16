import { defineStore } from "pinia";
import { getMyUser, logout, type State, type User } from "@/services/users";
import { v4 as uuidv4 } from "uuid";
import Base64 from "crypto-js/enc-base64";
import Utf8 from "crypto-js/enc-utf8";

export const useMyUserStore = defineStore("myUser", {
  state: () => {
    return {
      me: null as User | null,
      state: null as State | null,
    };
  },
  actions: {
    async update() {
      if (!this.me) {
        try {
          this.me = await getMyUser();
        } catch (error) {
          // Do nothing
        }
      }
    },
    async logout() {
      this.me = null;
      await logout();
    },
    createState(): string {
      const state = {
        state: uuidv4().substring(0, 16),
        redirectPath: window.location.pathname,
      };
      this.state = state;

      const stateEncoded = Base64.stringify(Utf8.parse(JSON.stringify(state)));
      return stateEncoded;
    },
    verifyAndClearState(stateEncoded: string): string | null {
      if (this.state === null) {
        return null;
      }
      const stateDecoded: State = JSON.parse(
        Utf8.stringify(Base64.parse(stateEncoded))
      );
      const existingState: State = this.state;
      this.state = null;
      return stateDecoded.state === existingState.state
        ? existingState.redirectPath
        : null;
    },
  },
});
