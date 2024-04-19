package service;

import dao.DBConnection;
import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

    // Fetches messages between two users
    public static List<Message> getMessages(int senderId, int receiverId) {
        System.out.println("getMessages");
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT sender_id, receiver_id, message_text FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY message_id ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set parameters for both directions of communication
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ps.setInt(4, senderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    messages.add(new Message(
                            rs.getInt("sender_id"),
                            rs.getInt("receiver_id"),
                            rs.getString("message_text")
                    ));
                }
            }
            System.out.println("messages:" + messages);
        } catch (SQLException | ClassNotFoundException e) {
            // Log the exception or handle it based on your application's requirements
            throw new RuntimeException("Database error during message retrieval", e);
        }
        return messages;
    }

    public static void sendMessage(Message message) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, message_text) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, message.getSenderId());
            ps.setInt(2, message.getReceiverId());
            ps.setString(3, message.getMessageText());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database error during message sending", e);
        }
    }

    public static void main(String[] args) {
        // Test the MessageService class
        Message message = new Message(68, 69, "Hello, how are you?");
        sendMessage(message);
        List<Message> messages = getMessages(68, 69);
        System.out.println(messages);
    }
}
