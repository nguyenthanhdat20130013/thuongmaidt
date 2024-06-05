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

import static service.API_LOGISTIC.RegisterTransport.getDeliveryDate;

@WebServlet(name = "CheckSuccess", value = "/check_success")
public class CheckSuccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String status = request.getParameter("status");
        String order_id = request.getParameter("order_id");
        String address = request.getParameter("addressId");
        String[] arr = address.split(":");
        String value1 = arr[0]; // giá trị đầu tiên
        String to_district_id = arr[1]; // giá trị thứ hai
        String to_ward_id = arr[2]; // giá trị thứ ba
        String from_district_id = "3695";
        String from_ward_id = "90737";
        // Do something with the selected status
        OrderService orderService = new OrderService();
        int oid = Integer.parseInt(order_id);
        int st = Integer.parseInt(status);
        int fromDistrictId = Integer.parseInt(from_district_id);
        int toDistrictId = Integer.parseInt(to_district_id);
        String payment = request.getParameter("payment_method");
        orderService.updateStatus(oid, st);
        int numTrans = orderService.getNumTrans(oid);
       if (st == 8 && numTrans == 0) {
            Order order = new Order();
            order.setOder_id(oid);
            String API_KEY = "1ec8d4c1-f724-11ee-a6e6-e60958111f48";
            Transport transport = RegisterTransport.registerShipment(API_KEY, order, from_district_id, from_ward_id, to_district_id, to_ward_id);
            orderService.addTransport(transport);
        }
       if (st == 1){
           String currentDay =  RegisterTransport.getCurrentDateTime();
           String token = "1ec8d4c1-f724-11ee-a6e6-e60958111f48";
           int serviceId = 53320;
           String deliveryDate = getDeliveryDate(token, fromDistrictId, from_ward_id, toDistrictId, to_ward_id, serviceId);
           RegisterTransport.updateOrderStatusByTransportLeadTime(oid, currentDay, deliveryDate);
       }
        // Forward to another servlet
        response.sendRedirect("admin-check_order");
    }
}
