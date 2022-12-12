import { supabase } from "@/services/supabase";

export { type Member, joinAsAdmin };

interface Member {
  userId: number;
  communityId: number;
  role: string;
  createdAt?: Date;
}

async function joinAsAdmin(communityId: number) {
  const { error } = await supabase.from("members").insert({
    user_id: supabase.auth.user()?.id,
    community_id: communityId,
    role: "ADMIN",
  });
  if (error) {
    throw error;
  }
}
