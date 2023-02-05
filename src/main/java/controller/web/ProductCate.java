package controller.web;

import model.Introduce;
import model.Product;
import model.Product_type;
import service.IntroService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductCate", value = "/productCate")
public class ProductCate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("cid");
        int pid = Integer.parseInt(id);
        //phan trang
        // Goi service de thuc hien getAll
        ProductService service = new ProductService();
        //phan trang
        String indexPage = request.getParameter("index");
        if(indexPage == null){
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);


        List<Product> list= service.pagingProductBType(index,pid);
        int count = service.getTotalProductType(pid);
        int endPage = count/5; //moi trang 3 bai
        if(count % 5 != 0){
            endPage ++;
        }
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("num", count);
        request.setAttribute("indexPage",indexPage);

        List<Product_type> listType = service.getAllProduct_type();

        Product_type name = service.getNameType(pid);
        request.setAttribute("typeName" , name);

        request.setAttribute("listType",listType);
        request.setAttribute("list",list);

        //Lay ra thong tin de chen vao footer
        IntroService intr = new IntroService();
        Introduce intro = intr.getIntro();
        request.setAttribute("info", intro);


        request.getRequestDispatcher("/views/web/table.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
