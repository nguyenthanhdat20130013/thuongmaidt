package controller.web;

import beans.Cart;
import model.Article_Category;
import model.Introduce;
import model.Product_type;
import model.UserModel;
import service.ArticleService;
import service.IntroService;
import service.ProductService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ProductCheckOutController", value = "/checkout")
public class ProductCheckOutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay ra danh sach loai bai viet

//
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

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        UserModel user = (UserModel)request.getSession().getAttribute("user");


        if(Objects.isNull(user)){
            response.sendRedirect("/login");
        } else if (Objects.isNull(cart)) {
            response.sendRedirect("/home");

        } else if(!Objects.isNull(user)) {
            UserModel userModel = UserService.findById(user.getId());
            request.setAttribute("user",userModel);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/product-checkout.jsp");
            rd.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
