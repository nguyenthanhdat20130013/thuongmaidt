package controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.LogDAO;
import model.Log;
import model.LogStatistics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name="StatisticsLog" , value = "/statistics-log")
public class StatisticsLog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LogStatistics>  logsToDay = LogDAO.countBytoDay();
        List<LogStatistics> logsThisMonth = LogDAO.countThisMonth();
        List<LogStatistics> logsIpAddress = LogDAO.countByIpAddress();
        request.setAttribute("logsToDay",logsToDay);
        request.setAttribute("logsThisMonth",logsThisMonth);
        request.setAttribute("logsIpAddress",logsIpAddress);
        request.getRequestDispatcher("/views/admin/statistics-log.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timestamp day = request.getAttribute("day")==null?null:(Timestamp)request.getAttribute("day");
        List<LogStatistics> logsDay = LogDAO.countByDay(day);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),logsDay);
    }
}
