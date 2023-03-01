import { supabase } from "@/services/supabase";
import { unknownError } from "@/services/errors";
import { camelizeKeys, decamelizeKeys } from "humps";
import type { PostgrestError } from "@supabase/supabase-js";

export interface Community {
  id?: number;
  slug?: string;
  name?: string;
}

export async function listMyCommunities(): Promise<Community[]> {
  const { data, error } = await supabase
    .from("communities")
    .select("id, slug, name, created_at, members!inner (user_id) ")
    .eq("members.user_id", supabase.auth.user()?.id);
  if (error) {
    unknownError(error);
    return [];
  }
  return camelizeKeys(data) as Community[];
}

export async function createCommunity(
  community: Partial<Community>
): Promise<{ data?: Community; error?: PostgrestError }> {
  const { data, error } = await supabase
    .from("communities")
    .insert(decamelizeKeys(community))
    .select();
  if (error) {
    return { error: error };
  }
  return { data: camelizeKeys(data[0]) };
}
export async function getCommunity(
  id: number
): Promise<Readonly<Community> | null> {
  const { data, error } = await supabase
    .from("communities")
    .select()
    .eq("id", id);
  if (error) {
    unknownError(error);
    throw error;
  }
  if (data?.length !== 1) {
    return null;
  }
  return camelizeKeys(data[0]);
}

export async function deleteCommunity(id: number) {
  await supabase.from("communities").delete().eq("id", id);
}
