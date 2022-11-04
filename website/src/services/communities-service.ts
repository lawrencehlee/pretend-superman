import { supabase } from "@/services/supabase";
import { unknownError } from "@/services/errors";

export { list, type Community };

interface Community {
  id?: number;
  slug?: string;
  name?: string;
  createdAt?: Date;
}

async function list(): Promise<any[]> {
  const { data, error } = await supabase.from("communities").select();
  if (error) {
    unknownError(error);
    return [];
  }
  return data;
}
