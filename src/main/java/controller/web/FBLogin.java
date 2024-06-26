package controller.web;

import model.Introduce;
import model.Post_Category;
import model.Product_type;
import service.IntroService;
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

@WebServlet(name = "FB", value = "/fb")
public class FBLogin extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("/views/web/fblogin.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
