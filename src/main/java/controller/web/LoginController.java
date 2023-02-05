package controller.web;

import model.Article_Category;
import model.Introduce;
import model.Product_type;
import model.UserModel;
import service.ArticleService;
import service.IntroService;
import service.ProductService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

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


        RequestDispatcher rd = request.getRequestDispatcher("views/web/user-login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        UserModel user = UserService.checkLogin(userName, passWord);

            if (user != null) {
                if(user.getEnable() != 1 ) {
                    request.setAttribute("error", "Tài khoản của bạn đã bị khoá");
                    request.getRequestDispatcher("views/web/user-login.jsp").forward(request, response);
                    return;
                }
                request.getSession().setAttribute("user", user);
                response.sendRedirect("account");
            } else {
                request.setAttribute("error", "Thông tin đăng nhập không hợp lệ.");
                request.getRequestDispatcher("views/web/user-login.jsp").forward(request, response);
        }
    }
}
