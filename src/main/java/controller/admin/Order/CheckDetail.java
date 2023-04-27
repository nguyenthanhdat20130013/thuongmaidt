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
        String status = request.getParameter("status");
        String order_id = request.getParameter("order_id");
        String address = request.getParameter("address");

        // Do something with the selected status
        OrderService orderService = new OrderService();

        int oid = Integer.parseInt(order_id);
        int st = Integer.parseInt(status);


        int oidd = 26;

        String from_district_id = "2264";
        String from_ward_id = "90816";
        String to_district_id = "2270";
        String to_ward_id = "231013";

        Order order = new Order();
        order.setOder_id(oidd);
        Login_API login_api = new Login_API();
        String API_KEY = login_api.login();
        System.out.println(API_KEY);
        RegisterTransport register = new RegisterTransport();
        Transport transport = register.registerTransport(API_KEY, order, from_district_id, from_ward_id, to_district_id, to_ward_id);
        orderService.addTransport(transport);
        orderService.updateStatus(oid, st);

        // Forward to another servlet
        request.getRequestDispatcher("/check_order").forward(request, response);
    }
}
