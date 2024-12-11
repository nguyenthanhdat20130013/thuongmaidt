package controller.admin;


import com.google.gson.Gson;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import service.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet(name = "TotalRevenueStatistics", value = "/admin-total-revenue")
public class TotalRevenueStatistics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate currentDate = LocalDate.now();
        Map<String, Integer> result = StatisticsService.statisticsRevenueByDay(currentDate.getMonthValue(), currentDate.getYear());

        List<String> label = new ArrayList<>(result.keySet());
        List<Integer> data = new ArrayList<>();
        for (String s : label) {
            data.add(result.get(s));
        }
        request.setAttribute("Label", label);
        request.setAttribute("Data", data);
        request.getRequestDispatcher("views/admin/bar-chart.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type").trim();
        Map<String, Integer> result = new HashMap<>();
        switch (type){
            case "month":
                // Lấy tham số tháng từ request
                String monthYear = request.getParameter("month").trim();
                // Lấy tháng và năm
                int month = Integer.parseInt(monthYear.substring(5,7));
                int year = Integer.parseInt(monthYear.substring(0,4));
                // Xử lý dữ liệu
                result = StatisticsService.statisticsRevenueByDay(month, year);
                break;
            case "date":
                // Lấy tham số tháng từ request
                String startDate = request.getParameter("startDate").trim();
                String endDate = request.getParameter("endDate").trim();
                // Xử lý dữ liệu
                result = StatisticsService.statisticsRevenueBySpace(startDate, endDate);
                break;
            case "year":
                int oyear = Integer.parseInt(request.getParameter("year").trim());
                result = StatisticsService.statisticsRevenueByYear(oyear);
                break;
            default:
                break;
        }
        List<String> labels = new ArrayList<>(result.keySet());
        List<Integer> data = new ArrayList<>();
        for (String s : labels) {
            data.add(result.get(s));
        }
        // Gửi dữ liệu mới dưới dạng JSON về cho trình duyệt
        String jsonData = "{\"labels\": " + new Gson().toJson(labels) + ", \"data\": " + new Gson().toJson(data) + "}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }

}
