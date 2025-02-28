import { RootState } from "../store";
import { Room } from "@/types/room";
import { ChatMessage } from "@/types/chat_message";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState: { room: Room } = {
  room: {
    messages: [],
    users: 0, // Add users count to track active users
  },
};

const roomSlice = createSlice({
  name: "room",
  initialState,
  reducers: {
    setRoom(state, action: PayloadAction<Room>) {
      state.room = action.payload;
    },
    addMessageToRoom(state, action: PayloadAction<ChatMessage>) {
      state.room.messages.push(action.payload);
    },
    updateUserCount(state, action: PayloadAction<number>) {
      state.room.users = action.payload; // Update user count
    },
  },
});

export const { setRoom, addMessageToRoom, updateUserCount } = roomSlice.actions;
export const selectRoom = (state: RootState) => state.room.room;
export default roomSlice.reducer;
