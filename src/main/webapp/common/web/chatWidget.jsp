<%@ page import="model.UserModel" %>
<%--webapp/common/web/chatWidget.jsp--%>
<%--
Created by IntelliJ IDEA.
User: Truong
Date: 4/18/2024
Time: 9:32 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        /* Basic styling */
        #chat-bubble {
            position: fixed;
            bottom: 30px;
            left: 30px;
            width: 60px;
            height: 60px;
            background-color: #007bff;
            color: white;
            border-radius: 50%;
            text-align: center;
            line-height: 60px;
            font-size: 30px;
            cursor: pointer;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }

        #chat-bubble:hover {
            background-color: #0056b3;
        }

        /* Chat interface styling */
        #chat-interface {
            display: none; /* Hidden by default */
            position: fixed;
            bottom: 100px;
            left: 30px;
            width: 350px;
            height: 500px;
            background-color: white;
            border: 1px solid #ccc;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            z-index: 1000;
        }

        /* Tab layout */
        .tab-header {
            display: flex;
            background-color: #007bff;
            color: white;
            padding: 10px;
        }

        .tab-header div {
            flex: 1;
            text-align: center;
            cursor: pointer;
        }

        .tab-header .active {
            background-color: #0056b3;
        }

        /* Chat content styling */
        .chat-content {
            padding: 10px;
            height: 400px;
            overflow-y: auto;
            display: none;
        }

        .chat-message {
            margin: 5px;
            padding: 10px;
            border-radius: 10px;
        }

        .admin-message {
            background-color: #f1f1f1;
            color: white;
            text-align: left;
        }

        .user-message {
            background-color: #007bff;
            text-align: right;
            direction: rtl;
        }

        .chat-input {
            position: absolute;
            bottom: 0;
            width: 100%;
            padding: 10px;
        }

        input[type="text"] {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 18%;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .avatar {
            vertical-align: middle;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<%
    // Define variables for avatars
    String adminAvatar = "https://e7.pngegg.com/pngimages/45/992/png-clipart-customer-service-representative-help-desk-customer-support-others-miscellaneous-service.png";
    String userAvatar = "https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png";
    String chatbotAvatar = "https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg?size=338&ext=jpg&ga=GA1.1.553209589.1713139200&semt=ais"; // Change this to the URL of your chatbot avatar

    UserModel user = (UserModel) session.getAttribute("user");
    int userId = (user != null) ? user.getId() : -1;  // -1 signifies no user logged in

%>

<div id="chat-bubble" onclick="toggleChat()">
    <i class="fa fa-comments" aria-hidden="true"></i>
</div>

<div id="chat-interface">
    <div class="tab-header">
        <div onclick="switchTab('admin-chat')" class="active">Admin</div>
        <div onclick="switchTab('ai-chat')">AI Chatbot</div>
    </div>
    <div id="admin-chat" class="chat-content">
        <!-- Chat messages and input for admin chat -->
        <div id="admin-chat-messages" class="chat-messages">
            <div class="chat-message admin-message">
                <%--                <img src="<%= adminAvatar %>" alt="Admin" class="avatar">--%>
                <%--                Hello! How can I help you today?--%>
            </div>
            <!-- User's message -->
            <div class="chat-message user-message">
                <%--                <img src="<%= userAvatar %>" alt="User" class="avatar">--%>
                <%--                I need help with my order.--%>
            </div>
        </div>
        <div class="chat-input">
            <input id="admin-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendMessage('admin-chat')">Send</button>
        </div>
    </div>

    <div id="ai-chat" class="chat-content">
        <!-- Chat messages and input for AI chatbot -->
        <div class="chat-message admin-message">
            <img src="<%= chatbotAvatar %>" alt="AI" class="avatar">
            Welcome to our AI assistant!
        </div>
        <div class="chat-input">
            <input type="text" placeholder="Type your message...">
            <button onclick="sendMessage('ai-chat')">Send</button>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    var senderId = <%= userId %>;
    var receiverId = <%= 69 %>;
    var webSocket;
    var readyState;
    $(document).ready(function () {
        // toggleChat();
        switchTab('admin-chat');
        initializeWebSocket();
    });

    function toggleChat() {
        var chatInterface = document.getElementById('chat-interface');
        var isVisible = chatInterface.style.display === 'block';
        chatInterface.style.display = isVisible ? 'none' : 'block';
        if (!isVisible) {

            if (webSocket === null || readyState !== WebSocket.OPEN) // Khởi tạo WebSocket nếu đã đóng
                initializeWebSocket();

            fetchMessages();
            setTimeout(() => {
                var chatContents = document.querySelectorAll('.chat-content');
                chatContents.forEach(chatContent => {
                    if (chatContent.style.display === 'block') {
                        chatContent.scrollTop = chatContent.scrollHeight;
                    }
                });
            }, 100); // Allow time for messages to load and adjust the scroll
        }
    }


    function switchTab(tabName) {
        var tabs = document.getElementsByClassName('chat-content');
        var headers = document.getElementsByClassName('tab-header')[0].children;
        for (var i = 0; i < tabs.length; i++) {
            tabs[i].style.display = 'none';
            headers[i].classList.remove('active');
        }
        document.getElementById(tabName).style.display = 'block';
        headers[Array.from(headers).findIndex(header => header.textContent.includes(tabName.split('-')[0]))].classList.add('active');
    }

    function sendMessage(tabId) {
        console.log('sendMessage');
        var input = document.querySelector('#' + tabId + ' input[type="text"]');
        var message = input.value.trim();
        if (message && receiverId !== -1 && webSocket && webSocket.readyState === WebSocket.OPEN) {
            var msgObj = {
                senderId: senderId,
                receiverId: receiverId,
                messageText: message
            };
            webSocket.send(JSON.stringify(msgObj));
            displayMessage(msgObj, true);
            input.value = ''; // Clear input field
        }
    }

    function displayMessage(message, isSender) {
        var chatDiv = $('#admin-chat-messages');
        var messageClass = isSender ? "user-message" : "admin-message"; // Toggle classes based on the message sender
        var avatarURL = isSender ? "<%= userAvatar %>" : "<%= adminAvatar %>";
        var alignment = isSender ? "right" : "left"; // Right-align user's messages, left-align admin's messages
        var msgDiv = $('<div class="chat-message ' + messageClass + '"><img src="' + avatarURL + '" class="avatar" alt="' + (isSender ? 'User' : 'Admin') + '">' + message.messageText + '</div>');
        chatDiv.append(msgDiv);
        chatDiv.scrollTop(chatDiv.prop("scrollHeight")); // Auto-scroll to the newest message
    }

    function fetchMessages() {
        if (senderId === -1) return;  // Do not fetch messages if user is not logged in
        $.ajax({
            url: 'messages',
            type: 'GET',
            data: {
                senderId: senderId,
                receiverId: receiverId // Admin ID
            },
            success: function (messages) {
                var chatDiv = $('#admin-chat-messages');
                chatDiv.empty(); // Clear previous messages
                $.each(messages, function (index, message) {
                    var msgDiv = (message.senderId == senderId) ? '<div class="chat-message user-message"><img src="<%= userAvatar %>" alt="User" class="avatar">' + message.messageText + '</div>' : '<div class="chat-message admin-message"><img src="<%= adminAvatar %>" alt="Admin" class="avatar">' + message.messageText + '</div>';
                    chatDiv.append(msgDiv);
                });
            },
            error: function (error) {
                fetchMessages(); // Retry fetching messages
                console.log('Error fetching messages: ', error);
            }
        });
    }

    function initializeWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8887?userId=" + senderId; // Add userId to the query string
        webSocket = new WebSocket(wsUri);

        webSocket.onopen = function (evt) {
            console.log("WebSocket connection opened.");
            readyState = webSocket.readyState;
        };

        webSocket.onmessage = function (evt) {
            var message = JSON.parse(evt.data);
            if (message.receiverId === senderId && message.senderId === receiverId) {
                displayMessage(message, false);

            }
        };

        webSocket.onerror = function (evt) {
            console.error("WebSocket error observed:", evt);
        };

        webSocket.onclose = function (evt) {
            console.log("WebSocket connection closed.");
        };
    }

</script>


</body>
</html>