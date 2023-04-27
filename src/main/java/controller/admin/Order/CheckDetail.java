package controller.admin.Order;

import model.Introduce;
import model.Order;
import model.Order_detail;
import service.API_LOGISTIC.Login_API;
import service.API_LOGISTIC.RegisterTransport;
import service.API_LOGISTIC.Transport;
import service.IntroService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckDetail", value = "/check_detail")
public class CheckDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idd = request.getParameter("id");
        int aid = Integer.parseInt(idd);
        OrderService orderService = new OrderService();
        Order od = orderService.getOderById(aid);
        request.setAttribute("order", od);
        List<Order_detail> detailList = orderService.getOrderDById(aid);
        request.setAttribute("orderDetails", detailList);
        //lay ra thong tin cua hang
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);
        request.getRequestDispatcher("/views/admin/check-invoice.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
