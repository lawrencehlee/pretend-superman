import {supabase} from "@/services/supabase";
import {unknownError} from "@/services/errors";

export {listMine, create, type Community};

interface Community {
    id?: number;
    slug?: string;
    name?: string;
    createdAt?: Date;
}

async function listMine(): Promise<Community[]> {
    const {data, error} = await supabase.from("communities")
        .select("id, slug, name, created_at, members!inner (user_id) ")
        .eq("members.user_id", supabase.auth.user()?.id);
    if (error) {
        unknownError(error);
        return [];
    }
    return data;
}

async function create(community: Community): Promise<Community> {
    const {data, error} = await supabase.from("communities").insert(community).select();
    if (error) {
        throw error;
    }
    return data[0];
}
