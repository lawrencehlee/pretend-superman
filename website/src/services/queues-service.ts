import {supabase} from "@/services/supabase";
import {unknownError} from "@/services/errors";
import {camelizeKeys} from "humps";

export { type Queue, list };

interface Queue {
    id: number;
    communityId: number;
    name: string;
    numTeams: number;
    playersPerTeam: number;
    numPlayers: number;
    algorithm: string;
    settings: Record<string, never>
}

async function list(communityId: number): Promise<Queue[]> {
    const { data, error } = await supabase
        .from("queues")
        .select()
        .eq("community_id", communityId);
    if (error) {
        unknownError(error);
        return [];
    }
    return camelizeKeys(data) as Queue[];
}