package controller.web;

import model.*;
import service.ArticleService;
import service.IntroService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import  java.util.List;

@WebServlet(name = "DetailArticle", value = "/detail_article")
public class DetailArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String id = request.getParameter("pid");
        int aid = Integer.parseInt(id);
        ArticleService service = new ArticleService();
        Article article = service.getArticleById(aid);
        request.setAttribute("ar", article);

        List<Article_Category> list = service.getListArCategory();
        request.setAttribute("listAr", list);

        List<Article> newA = service.getNewArticle();
        request.setAttribute("newest", newA);

        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);
        //Loai sp
        ProductService productService = new ProductService();
        List<Product_type> listType = productService.getAllProduct_type();
        request.setAttribute("listType",listType);

        request.getRequestDispatcher("/views/web/blog-detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
