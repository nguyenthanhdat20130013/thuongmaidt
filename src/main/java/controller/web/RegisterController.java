package controller.web;

import domain.Email;
import model.Article_Category;
import model.Introduce;
import model.Product_type;
import service.ArticleService;
import service.IntroService;
import service.ProductService;
import service.UserService;
import util.EmailUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay ra danh sach loai bai viet
        ArticleService service = new ArticleService();
        ProductService productService = new ProductService();
        List<Article_Category> list = service.getListArCategory();
        request.setAttribute("listAr", list);
        //Lay ra danh sach loai sp de chen vao header
        List<Product_type> listType = productService.getAllProduct_type();
        request.setAttribute("listType",listType);
        //Lay ra thong tin de chen vao footer
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);
        RequestDispatcher rd = request.getRequestDispatcher("views/web/user-register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String full_name  = request.getParameter("full_name");
        String emailAddress = request.getParameter("email");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(UserService.checkUserName(username)){
            request.setAttribute("exist_user","Tên tài khoản đã tồn tại");
            response.sendRedirect("login");
        } else {
            UserService.register(username, password,emailAddress,full_name,gender);
            Email email = new Email();
            email.setFrom("happyhomenoithat@gmail.com");
            email.setTo(emailAddress);
            email.setFromPassword("smckqxzmhsecmqld");
            email.setSubject("Nội Thất HappyHome - Xác nhận tài khoản khách hàng");
            StringBuilder sb = new StringBuilder();
            sb.append("<div style=\"font-size:16px;color:black\">");
            sb.append("<p style=\"font-size:24px;\">Chào mừng quý khách hàng đến với Nội Thất HappyHome!</p>");
            sb.append("<span>Xin chào ").append(full_name).append("</span><br>");
            sb.append("<span>Chúc mừng quý khách hàng đã kích hoạt tài khoản khách hàng thành công. Lần mua hàng tiếp theo, hãy đăng nhập để tích lũy điểm nhận ưu đãi và việc thanh toán sẽ thuận tiện hơn.</span>").append("<br><br>");
            sb.append("<button style=\"padding:20px 15px;color:#fff;font-size:16px;background-color:#343a40;border-radius:4px\"><a style=\"color:#fff;text-decoration: none;\" href=http://localhost:8080/").append(request.getContextPath()+ "/home>").append("Ghé thăm website của HappyHome</a></button>").append("<br><br>");
            sb.append("<span>Trân trọng!</span>").append("<br>");
            sb.append("<span> Cảm ơn </span>");
            sb.append("</div>");
            email.setContent(sb.toString());
            EmailUtil.send(email);
            response.sendRedirect("login");
        }
    }
}
