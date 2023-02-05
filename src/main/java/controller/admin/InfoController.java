package controller.admin;

import model.Introduce;
import service.IntroService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InfoController", value = "/info_controller")
public class InfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IntroService intro = new IntroService();
        Introduce in = intro.getIntro();
        request.setAttribute("info",in);

        request.getRequestDispatcher("views/admin/info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String n = request.getParameter("pname");
        String intro = request.getParameter("pintroduce");
        String add = request.getParameter("paddress");
        String showr = request.getParameter("pshowroom");
        String time = request.getParameter("ptimework");
        String em = request.getParameter("pemail");
        String p = request.getParameter("pphone");
        String cap = request.getParameter("pcaption");

        IntroService se = new IntroService();
        se.editIntro(n,intro,add,showr,time,em,p,cap);
        response.sendRedirect("/info_controller");
    }
}
