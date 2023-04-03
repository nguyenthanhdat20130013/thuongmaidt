package controller.web;

import beans.Cart;
import model.*;
import service.PostService;
import service.IntroService;
import service.OrderService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "OrderSuccess", value = "/order_success")
public class OrderSuccess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService service = new PostService();
        ProductService productService = new ProductService();
        List<Post_Category> list = service.getListPostCategory();
        request.setAttribute("listAr", list);
        //Lay ra danh sach loai sp de chen vao header
        List<Product_type> listType = productService.getAllProduct_type();
        request.setAttribute("listType", listType);
        //Lay ra thong tin de chen vao footer
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);


//        HttpSession session = request.getSession();
//        UserModel user = (UserModel) session.getAttribute("user");
//        if (user == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null || cart.getTotal() == 0) {
//            response.sendRedirect(request.getContextPath() + "/home");
//            return;
//        }
//
//        // Khai báo và khởi tạo các đối tượng và biến cần thiết
//        String phone = request.getParameter("phone");
//        String paymentMethod = request.getParameter("thanhtoan");
//        long orderId = 0;
//        long totalAmount = cart.getTotal();
//        Date orderDate = Date.valueOf(LocalDate.now());
//        OrderService orderService = new OrderService();
//
//
//        try {
//            // Tạo mới đơn hàng
//            Order order = new Order(orderid, user.getUserName(), totalAmount, 0, orderDate, paymentMethod, "TRUCK", 0, "HCM", "", phone);
//            orderService.addOder(order);
//
//            // Lấy lại id của đơn hàng sau khi tạo mới
//            order.setOder_id(orderid);
//
//            // Lưu chi tiết đơn hàng
//            for (ProductInCart product : cart.getListProductInCart()) {
//                Order_detail orderDetail = new Order_detail(0, order, product.getProduct().getProduct_id(), product.getProduct().getPrice_sell(), product.getQuantity(), 0, (product.getProduct().getPrice_sell() * product.getQuantity()));
//                orderService.addOrderDetail(orderDetail);
//            }
//
//            // Xoá giỏ hàng và các thuộc tính khác của phiên làm việc
//            session.removeAttribute("cart");
//            cart = null;
//        } catch (Exception e) {
//            response.sendRedirect(request.getContextPath() + "/home");
//            return;
//        }
//        RequestDispatcher rd = request.getRequestDispatcher("/views/web/order-success.jsp");
//        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
