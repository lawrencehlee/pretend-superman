import * as http from "@/services/http";
import { unknownError } from "@/services/errors";

export { getMyUser, logout, type User };

const client = http.instance;

async function getMyUser(): Promise<User> {
  try {
    const response = await client.get<User>("users/@me");
    return response.data;
  } catch (error: any) {
    if (error.response.status != 401) {
      unknownError(error);
    }
    throw error;
  }
}

async function logout() {
  await client.post("auth/logout");
}

interface User {
  id: number;
  username: string;
  discordUsername: string;
}

export interface State {
  state: string;
  redirectPath: string;
}
