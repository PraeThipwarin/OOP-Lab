import { useDispatch } from "react-redux";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import {
  selectWebsocket,
  setConnectionStatus,
  setWebSocketClient,
} from "@/stores/slices/webSocketSlice";
import { useAppSelector } from "@/stores/hook";
import {addMessageToRoom, updateUserCount} from "@/stores/slices/roomSlice";

export const useWebSocket = () => {
  const dispatch = useDispatch();
  const { client, isConnected } = useAppSelector(selectWebsocket);
  const serverUrl = process.env.API_BASE_URL;

  const subscribe = (destination: string, callback: (payload: Stomp.Message) => void) => { 
    if (client && isConnected) {
      const subscription = client.subscribe(destination, callback);
      console.log(`Subscribed to ${destination}`);
      return subscription;
    } else {
      console.log("No active WebSocket connection to disconnect.");
    }
  } 

  const unsubscribe = (subscription: Stomp.Subscription| undefined) => { 
    if (client && isConnected && subscription) {
      client.unsubscribe(subscription.id);
      console.log(`Unsubscribed from ${subscription.id}`);
    } else {
      console.log("No active WebSocket connection to disconnect.");
    }
  }
  
  const sendMessage = (destination: string, message: any) => {
    if (client && isConnected) {
      console.log("send", JSON.stringify(message));
      client.send(`/app${destination}`, {}, JSON.stringify(message));
    } else {
      console.log("No active WebSocket connection to disconnect.");
    }
  };

  const connect = () => {
    try {
      const webSocket = new SockJS(`${serverUrl}/ws`);
      const stompClient = Stomp.over(webSocket);
      stompClient.connect({}, () => onConnected(stompClient));
      stompClient.debug = () => {};
    } catch (e) {
      console.log(e);
    }
  };

  const disconnect = () => {
    if (client && isConnected) {
      client.disconnect(() => {
        dispatch(setWebSocketClient(null));
        dispatch(setConnectionStatus(false));
      });
      console.log("WebSocket disconnected");
    } else {
      console.log("No active WebSocket connection to disconnect.");
    }
  };

  const onConnected = (stompClient: Stomp.Client) => {
    stompClient.subscribe(`/topic/messages`, onUpdateRoom);
    stompClient.subscribe(`/topic/users`, onUpdateUserCount);
    dispatch(setWebSocketClient(stompClient));
    dispatch(setConnectionStatus(true));
    console.log("WebSocket connected successfully"); 
  };

  const onUpdateRoom = (payload: Stomp.Message) => {
    const newMessageObject = JSON.parse(payload.body);
    dispatch(addMessageToRoom(newMessageObject));
    console.log("Receive new message object", newMessageObject);
  };

  const onUpdateUserCount = (payload: Stomp.Message) => {
    const userCount = JSON.parse(payload.body);
    dispatch(updateUserCount(userCount));
    console.log("Updated user count:", userCount);
  };

  return {
    connect,
    disconnect,
    sendMessage,
    subscribe,
    unsubscribe,
  };
};
