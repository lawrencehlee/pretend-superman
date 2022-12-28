import { supabase } from "@/services/supabase";
import { unknownError } from "@/services/errors";
import { camelizeKeys } from "humps";

export { type Member, joinAsAdmin, list };

interface Member {
  readonly userId: number;
  readonly communityId: number;
  readonly role: string;
  readonly user: User;
}

interface User {
  readonly discordFullName: string;
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

async function list(communityId: number): Promise<Member[]> {
  const { data, error } = await supabase
    .from("members")
    .select("user_id, community_id, role, user:users!inner (discord_full_name)")
    .eq("community_id", communityId);

  if (error) {
    unknownError(error);
    return [];
  }

  return camelizeKeys(data) as Member[];
}
