import { supabase } from "@/services/supabase";
import { unknownError } from "@/services/errors";
import { camelizeKeys, decamelizeKeys } from "humps";

export { type Queue, type Team, listQueues, createQueue };

interface Queue {
  id: number;
  communityId: number;
  name: string;
  teams: Team[];
  algorithm: string;
  settings: Record<string, never>;
}

interface Team {
  id: number;
  name: string;
  size: number;
}

/**
 * Represents JSON columns as strings
 */
interface DbQueue extends Omit<Queue, "teams"> {
  teams: string;
}

async function listQueues(communityId: number): Promise<Queue[]> {
  const { data, error } = await supabase
    .from("queues")
    .select()
    .eq("community_id", communityId);
  if (error) {
    unknownError(error);
    return [];
  }
  return (camelizeKeys(data) as object[]).map(construct);
}

async function createQueue(queue: Partial<Queue>) {
  const toInsert: Partial<DbQueue> = decamelizeKeys({
    ...queue,
    teams: JSON.stringify(queue.teams),
  }) as Partial<DbQueue>;
  const { error } = await supabase.from("queues").insert(toInsert);
  if (error) {
    // TODO
  }
}

export async function getQueue(id: number): Promise<Readonly<Queue> | null> {
  const { data, error } = await supabase.from("queues").select().eq("id", id);
  if (error) {
    unknownError(error);
    throw error;
  }
  if (data?.length !== 1) {
    return null;
  }
  return construct(camelizeKeys(data[0]));
}

function construct(queueAsObject: object): Queue {
  const typed = queueAsObject as DbQueue;
  return { ...typed, teams: JSON.parse(typed.teams) } as Queue;
}
