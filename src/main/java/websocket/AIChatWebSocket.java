package websocket;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatMessageRole;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatRequestBuilder;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatRequestModel;
import io.github.amithkoujalgi.ollama4j.core.models.chat.OllamaChatResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AIChatWebSocket extends WebSocketServer {
    private final Map<String, WebSocket> clientMap = new ConcurrentHashMap<>();
    private final OllamaAPI ollamaAPI;
    private final OllamaChatRequestBuilder builder;

    public AIChatWebSocket(InetSocketAddress address) {
        super(address);
        String host = "http://localhost:11434/"; // Địa chỉ máy chủ Ollama chạy cục bộ
        this.ollamaAPI = new OllamaAPI(host);
        this.builder = OllamaChatRequestBuilder.getInstance("qwen2:0.5b");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String sessionId = handshake.getResourceDescriptor().split("\\?")[1].split("=")[1];
        clientMap.put(sessionId, conn);
        sendMessage(conn, "WebSocket connection established with session ID: " + sessionId);
        System.out.println("New connection with session ID: " + sessionId);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clientMap.values().remove(conn);
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        String sessionId = conn.getResourceDescriptor().split("\\?")[1].split("=")[1];
        OllamaChatRequestModel requestModel = builder.withMessage(OllamaChatMessageRole.USER, message).build();

        OllamaChatResult chatResult = null;
        try {
            chatResult = ollamaAPI.chat(requestModel);
        } catch (OllamaBaseException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        sendMessage(conn, chatResult.getResponse());

        // Tiếp tục cuộc hội thoại với lịch sử chat
        builder.withMessages(chatResult.getChatHistory());
    }

    private void sendMessage(WebSocket conn, String text) {
        try {
            if (conn.isOpen()) {
                conn.send(text);
                System.out.println("Message sent: " + text);
            } else {
                System.err.println("Attempted to send message, but WebSocket session is not open. Session ID: " + conn.getResourceDescriptor());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while sending WebSocket message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            System.err.println("An error occurred on connection " + conn.getRemoteSocketAddress() + ": " + ex);
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    public static void main(String[] args) {
        String host = "http://localhost:11434/";

        OllamaAPI ollamaAPI = new OllamaAPI(host);

        OllamaResult result =
                null;
        try {
            result = ollamaAPI.generate("qwen2:0.5b", "làm bài văn tả mẹ", new OptionsBuilder().build());
        } catch (OllamaBaseException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(result.getResponse());
    }
}
