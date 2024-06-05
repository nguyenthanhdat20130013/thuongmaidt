package controller.web;

import model.Review;
import model.UserModel;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddReviewServlet", value = "/addReview")
public class AddReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("review_text");
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        String userName = user != null ? user.getUserName() : "undefined";

        Review review = new Review(productId, userName, rating, reviewText);
        ProductService productService = new ProductService();
        productService.addReview(review);

        response.sendRedirect("product_detail?pid=" + productId);
    }
}
