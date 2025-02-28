import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import Stomp from "stompjs";
import { RootState } from "../store";

interface WebSocketState {
  client: Stomp.Client | null;
  isConnected: boolean;
}

const initialState: WebSocketState = {
  client: null,
  isConnected: false,
};

const websocketSlice = createSlice({
  name: "websocket",
  initialState,
  reducers: {
    setWebSocketClient: (state, action: PayloadAction<Stomp.Client | null>) => {
      state.client = action.payload;
    },
    setConnectionStatus: (state, action: PayloadAction<boolean>) => {
      state.isConnected = action.payload;
    },
  },
});

export const { setWebSocketClient, setConnectionStatus } =
  websocketSlice.actions;
export const selectWebsocket = (state: RootState) => state.websocket;
export default websocketSlice.reducer;
