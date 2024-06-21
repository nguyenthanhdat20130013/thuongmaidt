package websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIChatWebSocketServer extends WebSocketServer {

    private ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread pool for handling requests

    public AIChatWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message);
        executorService.submit(() -> processMessage(conn, message));
    }

    private void processMessage(WebSocket conn, String message) {
        try {
            String ollamaCommand = "ollama run qwen2:0.5b";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", ollamaCommand);
            Process process = processBuilder.start();

            OutputStream os = process.getOutputStream();
            os.write((message + "\n").getBytes());
            os.flush();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int ch;
            while ((ch = reader.read()) != -1) {
                conn.send(String.valueOf((char) ch));
                Thread.sleep(50); // Tạo hiệu ứng gõ chữ
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("AI Chat WebSocket Server started successfully!");
    }
}
