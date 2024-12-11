<%@ page import="model.UserModel" %>
<%--webapp/common/admin/chatWidgetAdmin.jsp--%>
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
        #chat-bubble {
            position: fixed;
            bottom: 30px;
            right: 30px;
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
        #chat-interface, #user-list {
            display: none; /* Hidden by default */
            position: fixed;
            bottom: 100px;
            right: 30px;
            width: 350px;
            height: 500px;
            background-color: white;
            border: 1px solid #ccc;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            z-index: 1000;
        }

        #user-list {
            right: 390px; /* Adjusted to display next to the chat interface */
            width: 200px; /* Smaller width for the user list */
        }

        .user-entry {
            padding: 10px;
            border-bottom: 1px solid #ccc;
            cursor: pointer;
        }

        .user-entry:hover {
            background-color: #f1f1f1;
        }

        .active-user {
            background-color: #007bff;
            color: white;
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
            display: flex;
            flex-direction: column;
            padding: 10px;
            height: 400px;
            overflow-y: auto;
        }

        .chat-message {
            display: flex;
            align-items: center;
            margin: 5px;
            padding: 10px;
            border-radius: 10px;
            width: 100%;
            box-sizing: border-box;
        }

        .admin-message {
            justify-content: flex-end;
            background-color: #007bff;
            color: white;
            text-align: right;
        }

        .user-message {
            justify-content: flex-start;
            background-color: #f1f1f1;
            color: black;
            text-align: left;
        }

        .ai-message {
            justify-content: flex-start;
            background-color: #e0e0e0;
            color: black;
            text-align: left;
        }

        .chatbot-message {
            justify-content: flex-start;
            background-color: #e0e0e0;
            color: black;
            text-align: left;
        }

        .avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .chat-input {
            position: absolute;
            bottom: 0;
            width: 100%;
            padding: 10px;
        }

        input[type="text"], button {
            flex-grow: 1;
            margin: 0 2px;
        }

        button {
            flex-basis: 20%;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<%
    // Define variables for avatars
    String adminAvatar = "https://e7.pngegg.com/pngimages/45/992/png-clipart-customer-service-representative-help-desk-customer-support-others-miscellaneous-service.png";
    String userAvatar = "https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png";
    String chatbotAvatar = "https://img.freepik.com/free-vector/chatbot-chat-message-vectorart_78370-4104.jpg?size=338&ext=jpg&ga=GA1.1.553209589.1713139200&semt=ais"; // Change this to the URL of your chatbot avatar

    UserModel admin = (UserModel) session.getAttribute("auth");
    int adminId = (admin != null) ? admin.getId() : -1;  // -1 signifies no admin logged in

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
        <div id="admin-chat-messages" class="chat-messages"></div>
        <div class="chat-input">
            <input id="admin-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendMessage('admin-chat')">Send</button>
        </div>
    </div>

    <div id="ai-chat" class="chat-content">
        <!-- Chat messages and input for AI chatbot -->
        <div id="ai-chat-messages" class="chat-messages"></div>
        <div class="chat-input">
            <input id="ai-chat-input" type="text" placeholder="Type your message...">
            <button onclick="sendAIMessage()">Send</button>
        </div>
    </div>
</div>

<div id="user-list"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
    var senderId = <%= adminId %>;
    var receiverId = -1;
    var webSocket;
    var aiWebSocket;
    var readyState;
    var aiMessageContainer = null;

    $(document).ready(function () {
        initializeWebSocket();
        initializeAIWebSocket();
        switchTab('admin-chat');
    });

    function toggleChat() {
        var chatInterface = document.getElementById('chat-interface');
        var userList = document.getElementById('user-list');
        var isVisible = chatInterface.style.display === 'block';
        chatInterface.style.display = isVisible ? 'none' : 'block';
        userList.style.display = isVisible ? 'none' : 'block';

        if (!isVisible) {
            fetchUserList();
            if (webSocket === null || readyState !== WebSocket.OPEN) initializeWebSocket();
            if (aiWebSocket === null || readyState !== WebSocket.OPEN) initializeAIWebSocket();
            fetchMessages();
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

    function debounce(func, wait, immediate) {
        var timeout;
        return function() {
            var context = this, args = arguments;
            var later = function() {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    }

    var debouncedFetchUserList = debounce(function() {
        fetchUserList();
    }, 1000, false);

    function sendMessage(tabId) {
        var input = document.querySelector('#' + tabId + ' input[type="text"]');
        var message = input.value.trim();
        if (tabId === 'admin-chat' && message && receiverId !== -1 && webSocket && webSocket.readyState === WebSocket.OPEN) {
            var msgObj = {
                senderId: senderId,
                receiverId: receiverId,
                messageText: message
            };
            webSocket.send(JSON.stringify(msgObj));
            displayMessage(msgObj, true, tabId);
            input.value = '';
        } else if (tabId === 'ai-chat' && message && aiWebSocket && aiWebSocket.readyState === WebSocket.OPEN) {
            aiWebSocket.send(message);
            var chatDiv = $('#ai-chat-messages');
            var msgDiv = $('<div class="chat-message user-message"><img src="<%= adminAvatar %>" class="avatar" alt="Admin">' + message + '</div>');
            chatDiv.append(msgDiv);
            input.value = '';
            ensureScrollToBottom();
            aiMessageContainer = null;
        }
        debouncedFetchUserList();
    }

    function displayMessage(message, isSender, tabId) {
        var chatDiv = (tabId === 'ai-chat') ? $('#ai-chat-messages') : $('#admin-chat-messages');
        var messageClass = isSender ? "admin-message" : (chatDiv.is('#ai-chat-messages') ? "ai-message" : "user-message");
        var avatarURL = isSender ? "<%= adminAvatar %>" : (chatDiv.is('#ai-chat-messages') ? "<%= chatbotAvatar %>" : "<%= userAvatar %>");
        var msgDiv = $('<div class="chat-message ' + messageClass + '"><img src="' + avatarURL + '" class="avatar" alt="' + (isSender ? 'Admin' : (chatDiv.is('#ai-chat-messages') ? 'AI' : 'User')) + '"><span class="message-text">' + message.messageText + '</span></div>');
        chatDiv.append(msgDiv);
        ensureScrollToBottom();
    }

    function ensureScrollToBottom() {
        setTimeout(() => {
            var chatContents = document.querySelectorAll('.chat-content');
            chatContents.forEach(chatContent => {
                if (chatContent.style.display === 'block') {
                    chatContent.scrollTop = chatContent.scrollHeight;
                }
            });
        }, 100);
    }

    function selectUser(userId) {
        receiverId = userId;
        $('#user-list .user-entry').removeClass('active-user');
        $('#user-' + userId).addClass('active-user');
        fetchMessages();
    }

    function fetchUserList() {
        $.ajax({
            url: 'messages?action=listUsers&adminId=' + senderId,
            type: 'GET',
            dataType: 'json',
            success: function (users) {
                var userListDiv = $('#user-list');
                userListDiv.empty();
                var userSelected = false;

                if (users.length > 0) {
                    users.forEach(function (user, index) {
                        var userEntry = $('<div id="user-' + user.id + '" class="user-entry" onclick="selectUser(' + user.id + ')">' + user.fullName + '</div>');
                        userListDiv.append(userEntry);

                        if (index === 0 && receiverId === -1) {
                            selectUser(user.id);
                            userSelected = true;
                        }
                    });

                    if (!userSelected && receiverId !== -1) {
                        $('#user-' + receiverId).addClass('active-user');
                    }
                } else {
                    console.log('No users to display');
                }
            },
            error: function () {
                console.log('Error fetching user list');
            }
        });
    }

    function fetchMessages() {
        if (senderId === -1 || receiverId === -1) return;
        $.ajax({
            url: 'messages?action=getMessages',
            type: 'GET',
            data: {
                senderId: senderId,
                receiverId: receiverId
            },
            success: function (messages) {
                var chatDiv = $('#admin-chat-messages');
                chatDiv.empty();
                $.each(messages, function (index, message) {
                    displayMessage(message, message.senderId === senderId, 'admin-chat');
                });
                ensureScrollToBottom();
            },
            error: function (error) {
                console.log('Error fetching messages: ', error);
            }
        });
    }

    function initializeWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8887?userId=" + senderId;
        webSocket = new WebSocket(wsUri);

        webSocket.onopen = function (evt) {
            console.log("WebSocket connection opened.");
            readyState = webSocket.readyState;
        };

        webSocket.onmessage = function (evt) {
            fetchUserList();
            var message = JSON.parse(evt.data);
            if (message.receiverId === senderId && message.senderId === receiverId) {
                displayMessage(message, false, 'admin-chat');
            }
        };

        webSocket.onerror = function (evt) {
            console.error("WebSocket error observed:", evt);
        };

        webSocket.onclose = function (evt) {
            console.log("WebSocket connection closed.");
        };
    }

    function initializeAIWebSocket() {
        var protocol = window.location.protocol === "https:" ? "wss://" : "ws://";
        var wsUri = protocol + window.location.hostname + ":8888";
        aiWebSocket = new WebSocket(wsUri);

        aiWebSocket.onopen = function(evt) {
            console.log("AI WebSocket connection opened.");
            readyState = aiWebSocket.readyState;
        };

        aiWebSocket.onmessage = function(evt) {
            displayAIMessage(evt.data);
        };

        aiWebSocket.onerror = function(evt) {
            console.error("AI WebSocket error observed:", evt);
        };

        aiWebSocket.onclose = function(evt) {
            console.log("AI WebSocket connection closed.");
        };
    }

    function displayAIMessage(character) {
        if (!aiMessageContainer) {
            aiMessageContainer = $('<div class="chat-message ai-message"><img src="<%= chatbotAvatar %>" class="avatar" alt="AI"><span class="message-text"></span></div>');
            $('#ai-chat-messages').append(aiMessageContainer);
        }
        var messageTextSpan = aiMessageContainer.find('.message-text');
        messageTextSpan.append(character);
        ensureScrollToBottom();
    }

    function sendAIMessage() {
        var input = document.querySelector('#ai-chat-input');
        var message = input.value.trim();
        if (message) {
            aiWebSocket.send(message);
            var chatDiv = $('#ai-chat-messages');
            var msgDiv = $('<div class="chat-message admin-message"><img src="<%= adminAvatar %>" class="avatar" alt="Admin">' + message + '</div>');
            chatDiv.append(msgDiv);
            input.value = '';
            ensureScrollToBottom();
            aiMessageContainer = null;
        }
    }
</script>
</body>
</html>
