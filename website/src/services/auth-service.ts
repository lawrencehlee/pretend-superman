import { createClient } from "@supabase/supabase-js";

export { login };

const supabase = createClient(
  "http://localhost:54321",
  "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZS1kZW1vIiwicm9sZSI6ImFub24ifQ.625_WdcF3KHqz5amU0x2X5WWHP-OEs_4qj0ssLNHzTs"
);

async function login() {
  const { user, session, error } = await supabase.auth.signIn({
    provider: "discord",
  });
}

async function logout() {
  const { error } = await supabase.auth.signOut();
}
