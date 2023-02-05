package controller.admin;

import model.CategoryProModel;
import service.CategoryProService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/data-category")
public class CategoryControllerAdmin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        if(request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        List<CategoryProModel> listCate;
        if(action.equals("add")){
            String name = request.getParameter("cate");
            CategoryProService.addCate(name);
            listCate = CategoryProService.findAll();
            request.setAttribute("listCate",listCate);
            request.setAttribute("success","Thêm thành công");
            request.getRequestDispatcher("views/admin/category-product.jsp").forward(request,response);
            return;
        }
        if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            CategoryProService.deleteCate(id);
            listCate = CategoryProService.findAll();
            request.setAttribute("listCate",listCate);
            request.setAttribute("success","Xoá thành công");
            request.getRequestDispatcher("views/admin/category-product.jsp").forward(request,response);
            return;
        }
        if(action.equals("edit")){
            String name = request.getParameter("cate");
            int id = Integer.parseInt(request.getParameter("id"));
            if(name == null){
                CategoryProModel cate = CategoryProService.findById(id);
                request.setAttribute("cate",cate);
                request.getRequestDispatcher("views/admin/edit-category-product.jsp").forward(request,response);
                return;
            }
            CategoryProService.updateCate(id,name);
            CategoryProModel cate = CategoryProService.findById(id);
            request.setAttribute("cate",cate);
            request.setAttribute("success","Sửa thành công");
            request.getRequestDispatcher("views/admin/edit-category-product.jsp").forward(request,response);
            return;
        }
        listCate = CategoryProService.findAll();
        request.setAttribute("listCate",listCate);
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/category-product.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
