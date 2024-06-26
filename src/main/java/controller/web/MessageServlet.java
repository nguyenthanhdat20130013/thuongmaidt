package controller.web;

import model.UserModel;
import service.MessageService;
import model.Message;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

@WebServlet(name = "MessageServlet", urlPatterns = {"/messages"})
public class MessageServlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("action: " + action);

        if ("listUsers".equals(action)) {
            handleListUsers(request, response);
        } else if ("getMessages".equals(action)) {
            handleGetMessages(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing action parameter.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int senderId = Integer.parseInt(request.getParameter("senderId"));
            int receiverId = Integer.parseInt(request.getParameter("receiverId"));
            String messageText = request.getParameter("message");

            Message message = new Message(senderId, receiverId, messageText);
            MessageService.sendMessage(message);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Message sent");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user or admin ID format.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }
    }

    private void handleListUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        try {
            List<UserModel> userModels = MessageService.getUsersWhoMessagedAdmin(adminId);
            String userModelsJson = gson.toJson(userModels);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(userModelsJson);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid admin ID format.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }
    }

    private void handleGetMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int senderId = Integer.parseInt(request.getParameter("senderId"));
            int receiverId = Integer.parseInt(request.getParameter("receiverId"));

            List<Message> messages = MessageService.getMessages(senderId, receiverId);
            String messagesJson = gson.toJson(messages);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(messagesJson);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user or admin ID format.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }
    }
}
