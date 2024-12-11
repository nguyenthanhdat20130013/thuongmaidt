<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.io.OutputStream, java.net.HttpURLConnection, java.net.URL" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String message = request.getParameter("message");
    String ollamaCommand = "ollama run qwen2:0.5b";

    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", ollamaCommand);
    Process process = processBuilder.start();

    OutputStream os = process.getOutputStream();
    os.write((message + "\n").getBytes());
    os.flush();
    os.close();

    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    StringBuilder ollamaResponse = new StringBuilder();
    while ((line = reader.readLine()) != null) {
        ollamaResponse.append(line).append("\n");
    }
    reader.close();

    out.print(ollamaResponse.toString());
%>
