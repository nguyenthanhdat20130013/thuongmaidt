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

@WebServlet(name = "VerifyController", urlPatterns = "/verify")
public class VerifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("id"));
        UserModel user = UserService.findById(user_id);
        //Lay ra danh sach loai bai viet
        ArticleService service = new ArticleService();
        ProductService productService = new ProductService();
        List<Article_Category> list = service.getListArCategory();
        request.setAttribute("listAr", list);
        //Lay ra danh sach loai sp de chen vao header
        List<Product_type> listType = productService.getAllProduct_type();
        request.setAttribute("listType", listType);
        //Lay ra thong tin de chen vao footer
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);
        if(user == null){
            RequestDispatcher rd = request.getRequestDispatcher("views/web/404-change-password.jsp");
            rd.forward(request, response);
            return;
        }
        if(user.getEnable() == 0) {
            RequestDispatcher rd = request.getRequestDispatcher("views/web/verify.jsp");
            rd.forward(request, response);
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("views/web/verified.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

