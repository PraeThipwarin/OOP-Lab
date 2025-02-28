import { ChatMessage } from "./chat_message";

export interface Room {
  messages: ChatMessage[];
  users: number;
}
