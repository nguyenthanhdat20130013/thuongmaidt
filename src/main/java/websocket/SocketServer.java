package websocket;

import model.Message;
import org.json.JSONException;
import org.json.JSONObject;
import service.MessageService;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketServer extends WebSocketServer {
    private Map<Integer, WebSocket> clientMap = new ConcurrentHashMap<>();

    public SocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        try {
            String query = handshake.getResourceDescriptor().split("\\?")[1];
            String[] params = query.split("&");
            String userIdStr = null;
            for (String param : params) {
                String[] pair = param.split("=");
                if (pair.length == 2 && pair[0].equals("userId")) {
                    userIdStr = pair[1];
                    break;
                }
            }

            System.out.println("userIdStr: " + userIdStr);
            System.out.println("New ip connect:" + conn.getRemoteSocketAddress());

            if (userIdStr != null && !userIdStr.isEmpty()) {
                int userId = Integer.parseInt(userIdStr);
                clientMap.put(userId, conn);
                System.out.println("New connection with userId: " + userId);
            } else {
                System.out.println("New connection without userId.");
            }

            System.out.println("clientMap: " + clientMap);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid userId received: " + e.getMessage());
        }
    }


    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
        clientMap.values().remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message from " + conn.getRemoteSocketAddress() + ": " + message);
        processMessage(message);
    }

    private void processMessage(String message) {
        try {
            JSONObject json = new JSONObject(message);
            int senderId = json.getInt("senderId");
            int receiverId = json.getInt("receiverId");
            String messageText = json.getString("messageText");

            // Save the message to the database
            Message msg = new Message(senderId, receiverId, messageText);
            MessageService.sendMessage(msg);  // Assume this method handles the DB operations

            // Forward the message to the receiver if they are connected
            WebSocket receiverConn = clientMap.get(receiverId);
            System.out.println("clientMap.get(receiverId):" + clientMap.get(receiverId));
            if (receiverConn != null) {
                receiverConn.send(message); // Forward the entire JSON message
            }
        } catch (JSONException e) {
            System.out.println("Error parsing JSON message: " + e.getMessage());
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            System.err.println("An error occurred on connection " + conn.getRemoteSocketAddress() + ":" + ex);
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }
}
