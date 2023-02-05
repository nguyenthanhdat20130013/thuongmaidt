package controller.admin;

import model.Introduce;
import model.Order;
import model.Order_detail;
import service.IntroService;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetail", value = "/order_detail")
public class OrderDetail extends HttpServlet {
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
        request.getRequestDispatcher("/views/admin/invoice-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
