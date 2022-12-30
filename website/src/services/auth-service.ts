import type { User } from "@supabase/supabase-js";
import { unknownError } from "@/services/errors";
import { supabase } from "@/services/supabase";

export { login, logout, me };

async function login() {
  const { user, error } = await supabase.auth.signIn({
    provider: "discord",
  });
  if (error) {
    unknownError(error);
  }
  return user;
}

async function logout() {
  const { error } = await supabase.auth.signOut();
  if (error) {
    unknownError(error);
  }
}

async function me(): Promise<User | null> {
  return supabase.auth.user();
}
