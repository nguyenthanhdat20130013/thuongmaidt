package websocket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.InetSocketAddress;

@WebListener
public class WebSocketServerInitializer implements ServletContextListener {

    private SocketServer socketServer;
    private AIChatWebSocketServer aiChatWebSocketServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Khởi tạo và bắt đầu WebSocketServer khi ứng dụng được khởi động
        socketServer = new SocketServer(new InetSocketAddress("localhost", 8887));
        socketServer.start();
        System.out.println("WebSocketServer started successfully!");

        // Khởi tạo và bắt đầu AIChatWebSocketServer khi ứng dụng được khởi động
        aiChatWebSocketServer = new AIChatWebSocketServer(new InetSocketAddress("localhost", 8888));
        aiChatWebSocketServer.start();
        System.out.println("AIChatWebSocketServer started successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Dừng WebSocketServer khi ứng dụng bị hủy
        if (socketServer != null) {
            try {
                socketServer.stop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("WebSocketServer stopped successfully!");
        }

        // Dừng AIChatWebSocketServer khi ứng dụng bị hủy
        if (aiChatWebSocketServer != null) {
            try {
                aiChatWebSocketServer.stop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("AIChatWebSocketServer stopped successfully!");
        }
    }
}
