import { supabase } from "@/services/supabase";
import { unknownError } from "@/services/errors";
import { camelizeKeys } from "humps";

export { listMine, create, get, type Community };

interface Community {
  id?: number;
  slug?: string;
  name?: string;
}

async function listMine(): Promise<Community[]> {
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

async function create(community: Community): Promise<Community> {
  const { data, error } = await supabase
    .from("communities")
    .insert(community)
    .select();
  if (error) {
    throw error;
  }
  return camelizeKeys(data[0]);
}

async function get(slug: string): Promise<Community | null> {
  const { data, error } = await supabase
    .from("communities")
    .select()
    .eq("slug", slug);
  if (error) {
    unknownError(error);
    throw error;
  }
  if (data?.length !== 1) {
    return null;
  }
  return camelizeKeys(data[0]);
}
