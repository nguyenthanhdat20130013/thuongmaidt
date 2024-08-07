package controller.web.Order;

import beans.Cart;
import model.*;
import service.IntroService;
import service.OrderService;
import service.PostService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AddOrderSuccess", value = "/add_order_success")
public class AddOrderSuccess extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService oderService = new OrderService();
        int orderid = oderService.getMaxMHD();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotal() == 0) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        String phone = request.getParameter("phone");
        String paymentMethod = request.getParameter("paymentMethod");
        String address = request.getParameter("address");
        String message = request.getParameter("message");
        long totalAmount = cart.getTotal();
        Date orderDate = Date.valueOf(LocalDate.now());
        OrderService orderService = new OrderService();
        String shippingFee = request.getParameter("shippingFee");
        int fee ;
        //dia chi giao hang
        String provinceId = request.getParameter("province-id");
        String districtId = request.getParameter("district-id");
        String wardId = request.getParameter("ward-id");
        String valId = provinceId+":"+districtId+":"+wardId;
        //
        String provinceValue = request.getParameter("province-value");
        String districtValue = request.getParameter("district-value");
        String wardValue = request.getParameter("ward-value");
        String valAdd = wardValue +", "+ districtValue+", "+provinceValue;
        if(paymentMethod.equals("Giao hàng thu tiền tận nhà") || paymentMethod.equals("Nhận hàng tại cửa hàng")) {
            fee = 0;
        } else {
            fee = Integer.parseInt(shippingFee);
        }
        try {
            // Lấy múi giờ của Việt Nam
            ZoneId vietnamTimeZone = ZoneId.of("Asia/Ho_Chi_Minh");
            // Định dạng thời gian với múi giờ của Việt Nam
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(vietnamTimeZone);
            // Lấy thời gian hiện tại trong múi giờ của Việt Nam và định dạng thành chuỗi
            LocalDateTime currentDateTime = LocalDateTime.now(vietnamTimeZone);
            String formattedDateTime = currentDateTime.format(formatter);
            // Chuyển đổi chuỗi thời gian thành LocalDateTime
            LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
            Order order = new Order(orderid, user.getUserName(), totalAmount, fee, parsedDateTime, paymentMethod, valId, 0, valAdd, message, phone);
            orderService.addOder(order);

            order.setOder_id(orderid);

            for (ProductInCart product : cart.getListProductInCart()) {
                Order_detail orderDetail = new Order_detail(0, order, product.getProduct().getProduct_id(), product.getProduct().getPrice_sell(), product.getQuantity(), fee, (product.getProduct().getPrice_sell() * product.getQuantity()));
                orderService.addOrderDetail(orderDetail);
            }

            session.removeAttribute("cart");
            cart = null;
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        response.sendRedirect("/success");
//        RequestDispatcher rd = request.getRequestDispatcher("/views/web/order-success.jsp");
//        rd.forward(request, response);
    }

}
