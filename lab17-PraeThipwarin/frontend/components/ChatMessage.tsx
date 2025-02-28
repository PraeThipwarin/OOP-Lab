import React from "react";
import { FormatChatDate } from "@/utils/formatDateTime";
import { useAppSelector } from "@/stores/hook";
import { selectUser } from "@/stores/slices/userSlice";
import { ChatMessage } from "@/types/chat_message";

interface ChatMessageProps {
  msg: ChatMessage;
}

const ChatMessageCard: React.FC<ChatMessageProps> = ({ msg }) => {
  const user = useAppSelector(selectUser);
  const renderMessageContent = () => {
    const isMessageFromCurrentUser = msg.sender === user?.username;
    const isChatMessage = msg.type === "CHAT";

    const itemAlignment = isChatMessage
      ? isMessageFromCurrentUser
        ? "justify-end"
        : "justify-start"
      : "justify-center";

    const messageStyles = {
      JOIN: "text-indigo-600 text-center text-lg",
      CHAT: "text-gray-800 text-lg",
      LEAVE: "text-red-600 text-center text-lg",
    };

    return (
      <div className={`${itemAlignment} flex items-start space-x-3 p-2`}>
        {isChatMessage && (
          <div className="flex flex-col justify-center">
            <div className="h-12 w-12 flex items-center justify-center rounded-full bg-gradient-to-br from-blue-500 to-blue-700 text-white font-semibold uppercase shadow-md border-2 border-white">
              {msg.sender.substring(0, 3)}
            </div>
          </div>
        )}
        <div className="max-w-sm bg-white px-4 py-3 rounded-xl shadow-lg border border-gray-200">
          <p className={`leading-relaxed break-words whitespace-pre-wrap ${messageStyles[msg.type] || ""}`}>
            {msg.message}
          </p>
          {isChatMessage && (
            <span className="block mt-2 text-xs text-gray-500 text-right">
              {FormatChatDate(msg.timestamp)}
            </span>
          )}
        </div>
      </div>
    );
  };

  return renderMessageContent();
};

export default ChatMessageCard;