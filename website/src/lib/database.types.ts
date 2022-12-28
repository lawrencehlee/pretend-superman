export type Json =
  | string
  | number
  | boolean
  | null
  | { [key: string]: Json }
  | Json[];

export interface Database {
  public: {
    Tables: {
      communities: {
        Row: {
          id: number;
          slug: string;
          name: string;
          createdAt: string | null;
        };
        Insert: {
          id?: never;
          slug: string;
          name: string;
          createdAt?: string | null;
        };
        Update: {
          id?: never;
          slug?: string;
          name?: string;
          createdAt?: string | null;
        };
      };
      members: {
        Row: {
          userId: string;
          communityId: number;
          role: string;
          createdAt: string;
        };
        Insert: {
          userId: string;
          communityId: number;
          role: string;
          createdAt?: string;
        };
        Update: {
          userId?: string;
          communityId?: number;
          role?: string;
          createdAt?: string;
        };
      };
    };
    Views: {
      users: {
        Row: {
          id: string | null;
          email: string | null;
          discordId: Json | null;
          discordFullName: Json | null;
          createdAt: string | null;
        };
        Insert: {
          id?: string | null;
          email?: string | null;
          discordId?: never;
          discordFullName?: never;
          createdAt?: string | null;
        };
        Update: {
          id?: string | null;
          email?: string | null;
          discordId?: never;
          discordFullName?: never;
          createdAt?: string | null;
        };
      };
    };
    Functions: {
      [_ in never]: never;
    };
    Enums: {
      [_ in never]: never;
    };
  };
}
