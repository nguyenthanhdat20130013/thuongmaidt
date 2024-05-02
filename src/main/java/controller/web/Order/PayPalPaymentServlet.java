package controller.web.Order;

import beans.Cart;
import model.*;
import service.OrderService;

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

@WebServlet(name = "PayPalPaymentServlet", value = "/paypal-payment")
public class PayPalPaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService oderService = new OrderService();
        int orderid = oderService.getMaxMHD();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/lab/login");
            // Xử lý khi người dùng chưa đăng nhập
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotal() == 0) {
            response.sendRedirect(request.getContextPath() + "/lab/home");
            // Xử lý khi giỏ hàng trống
            return;
        }

        // Lấy thông tin từ yêu cầu
        String phone = request.getParameter("phone");
        String paymentMethod = "PayPal"; // Phương thức thanh toán là PayPal
        String address = request.getParameter("address");
        String message = request.getParameter("message");
        long totalAmount = cart.getTotal();
        int fee = 0; // Phí vận chuyển cho thanh toán PayPal




        Date orderDate = Date.valueOf(LocalDate.now());
        OrderService orderService = new OrderService();
        String shippingFee = request.getParameter("shippingFee");

        //dia chi giao hang
        String provinceId = request.getParameter("province");
        String districtId = request.getParameter("district");
        String wardId = request.getParameter("ward");
        String valId = provinceId+":"+districtId+":"+wardId;
        //
        String provinceValue = request.getParameter("provinceValue");
        String districtValue = request.getParameter("districtValue");
        String wardValue = request.getParameter("wardValue");
        String valAdd = wardValue +", "+ districtValue+", "+provinceValue;



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
            Order order = new Order(orderid, user.getUserName(), totalAmount, fee, parsedDateTime, paymentMethod, valId, 0, address, message, phone);
            orderService.addOder(order);

            order.setOder_id(orderid);

            for (ProductInCart product : cart.getListProductInCart()) {
                Order_detail orderDetail = new Order_detail(0, order, product.getProduct().getProduct_id(), product.getProduct().getPrice_sell(), product.getQuantity(), fee, (product.getProduct().getPrice_sell() * product.getQuantity()));
                orderService.addOrderDetail(orderDetail);
            }

            session.removeAttribute("cart");
            cart = null;
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            response.sendRedirect(request.getContextPath() + "/home");
           return;
        }

        // Gửi phản hồi về cho client
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
