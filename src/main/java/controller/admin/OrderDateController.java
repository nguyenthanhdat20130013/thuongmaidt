package controller.admin;

import com.google.gson.Gson;
import service.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDate", value = "/admin-order-date-years")
public class OrderDateController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> years = StatisticsService.getAllYears();
        // Gửi dữ liệu mới dưới dạng JSON về cho trình duyệt
        String jsonData = "{\"years\": " + new Gson().toJson(years) + "}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }
}
