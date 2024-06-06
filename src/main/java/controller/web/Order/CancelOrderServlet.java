package controller.web.Order;

import model.Introduce;
import model.Post_Category;
import model.Product_type;
import model.UserModel;
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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CancelOrder", value = "/cancel_order")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay ra danh sach loai bai viet
        PostService service = new PostService();
        ProductService productService = new ProductService();
        List<Post_Category> list = service.getListPostCategory();
        request.setAttribute("listAr", list);
        //Lay ra danh sach loai sp de chen vao header
        List<Product_type> listType = productService.getAllProduct_type();
        request.setAttribute("listType",listType);
        //Lay ra thong tin de chen vao footer
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);
        //
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        // Thêm orderId vào request attribute để sử dụng trong JSP
        request.setAttribute("orderId", orderId);

        RequestDispatcher rd = request.getRequestDispatcher("views/web/cancel_order.jsp");
        rd.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String reason = request.getParameter("reason");
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        int uid = user.getId();
        OrderService orderService = new OrderService();
        orderService.cancelOrder(orderId, uid, reason);
        // Chuyển hướng sau khi hủy đơn hàng thành công
        response.sendRedirect("/list_order");
    }
}
