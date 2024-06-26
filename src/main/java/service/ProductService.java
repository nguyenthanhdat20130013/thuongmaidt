package service;

import dao.DBConnection;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    //lay ra tat ca san pham
    public static List<Product> getAllProduct() {
        List<Product> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "select * from products";
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getString(13));
                list.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;


    }

    public static List<Slider> getAllSlider() {
        List<Slider> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "select * from slideimg";
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
               Slider s= new Slider(rs.getInt(1),rs.getString(2),rs.getInt(3));
               list.add(s);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;


    }

    public static void updateQuantity(int id, int quantity) {
        PreparedStatement pst;
        String sql;
        try {
            sql = "UPDATE products  set quantity = (quantity + ?) where product_id = ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setInt(1,quantity);
            pst.setInt(2,id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getByIds(Product product) {
        List<Product> products = new ArrayList<>();
        for (int id:product.getIds()) {
            products.add(getProductById(id));
        }
        return products;
    }

    //lay ra hinh anh tuong ung
    public ArrayList<Image> getImage(int id) {
        ArrayList<Image> imgUrl = new ArrayList<>();
        Image img = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "select * from images where product_id = " + id;
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                String imageUrl = rs.getString(3);
                if (imageUrl == null) {
                    // Trả về URL thay thế nếu giá trị là null
                    imageUrl = "https://static.vecteezy.com/system/resources/previews/006/736/566/original/illustration-file-not-found-or-404-error-page-free-vector.jpg";
                }
                img = new Image(rs.getInt(1), rs.getInt(2), imageUrl);
                imgUrl.add(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;

    }

    //lay ra tat ca loai san pham
    public ArrayList<Product_type> getAllProduct_type() {
        ArrayList<Product_type> type_name = new ArrayList();
        String sql = "select type_id, type_name from product_type";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product_type t = new Product_type(rs.getInt(1), rs.getString(2));
                type_name.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return type_name;
    }

    //lay ra san pham theo id
    public static Product getProductById(int id) {
        Product pro = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "select * from products where product_id =" + id;
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                 pro = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getString(13));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pro;
    }

    //lay ra san pham theo loai
    public static List<Product> selectProductByType(int typeid) {
        List<Product> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "select * from product where product_type =" + typeid;
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getString(13));
                list.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //lay ra ten cua loai sp
    public Product_type getNameType(int id) {
        Product_type type_name = null;
        String sql = "select type_id , type_name from product_type where type_id = " + id;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                type_name = new Product_type(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return type_name;
    }

    //lay ra san pham tuong tu
    public static List<Product> selectSameProduct(int typeid) {
        List<Product> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "( select * from products where product_type = " + typeid + " ORDER BY product_id DESC ) LIMIT 3";
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getString(13));
                list.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    // lay ra san pham ban chay
    public List<Product> getBestSale(){
        List<Product> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT products.*, SUM(order_detail.amount) AS soLgDaBan FROM products" +
                    " INNER JOIN order_detail ON order_detail.id_product = products.product_id GROUP BY order_detail.id_product ORDER BY soLgDaBan DESC";
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(11), rs.getString(12));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    // lay ra san pham moi nhat
    public List<Product> getNewProduct(int n){
        List<Product> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        try {
            sql = "SELECT * FROM products p ORDER BY p.product_id DESC LIMIT " + n;
            ps = DBConnection.getConnection().prepareStatement(sql);
            list = new ArrayList<>();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(11), rs.getString(12));
                list.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //lay ra tong so san pham de phan trang
    public int getTotalProduct() {
        String sql = "Select  count(*) from products";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);

            rs = ps.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> pagingProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products\n" +
                "ORDER BY product_id LIMIT " + ((index - 1) * 15) + ",15";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(11), rs.getString(12));
                list.add(p);
            }

        } catch (Exception e) {

        }
        return list;
    }

    //dem sl san pham theo loai sp
    public int getTotalProductType(int type) {
        String sql = "Select  count(*) from products where product_type = " + type;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);

            rs = ps.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> pagingProductBType(int index, int typeid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products\n" + " WHERE product_type = " + typeid +
                " ORDER BY product_id LIMIT " + ((index - 1) * 10) + ",10";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(11), rs.getString(12));
                list.add(p);
            }

        } catch (Exception e) {

        }
        return list;
    }

    public void addProduct(Product p) {

        String sql = "INSERT INTO products(name, price, price_sell, info, code, brand, color, size, attribute, status, product_type, product_insurance) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getPrice_sell());
            ps.setString(4, p.getInfo());
            ps.setString(5, p.getCode());
            ps.setString(6, p.getBrand());
            ps.setString(7, p.getColor());
            ps.setString(8, p.getSize());
            ps.setString(9, p.getAttribute());
            ps.setInt(10, p.getStatus());
            ps.setInt(11, p.getProduct_type());
            ps.setString(12, p.getProduct_insurance());
            rs = ps.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void edit(Product p, int id) {

        String sql = "UPDATE products SET name = ?, price = ?, price_sell = ?, info = ?, code= ?, brand = ?, color = ?, size = ?, attribute = ?, status = ?, product_type = ?, product_insurance = ? WHERE product_id = " + id;
        PreparedStatement ps = null;

        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getPrice_sell());
            ps.setString(4, p.getInfo());
            ps.setString(5, p.getCode());
            ps.setString(6, p.getBrand());
            ps.setString(7, p.getColor());
            ps.setString(8, p.getSize());
            ps.setString(9, p.getAttribute());
            ps.setInt(10, p.getStatus());
            ps.setInt(11, p.getProduct_type());
            ps.setString(12, p.getProduct_insurance());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//            ProductService service = new ProductService();
//            System.out.println(service.getProductByName("ghế"));
//        String userName = "Nguyễn Thành Đạt";
//        int productId = 9;
//ProductService p = new ProductService();
//        boolean result = p.hasCustomerPurchasedProduct(userName, productId);
//        if (result) {
//            System.out.println("Khách hàng đã mua sản phẩm này.");
//        } else {
//            System.out.println("Khách hàng chưa mua sản phẩm này.");
//        }
    }

    public  List<Product> getProductByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT product_id,`name`,price,price_sell FROM products WHERE `name` LIKE ?";
        PreparedStatement ps ;
        ResultSet rs ;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1,"%" + txtSearch + "%");
            //ps.setObject(1, "%" + txtSearch + "%", Types.VARCHAR);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setProduct_id(rs.getInt("product_id"));
                p.setPrice(rs.getInt("price"));
                p.setPrice_sell(rs.getInt("price_sell"));
                p.setImg1(p.getImage(0));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<Review> getReviewsByProductId(int productId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE product_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("review_id"));
                review.setProductId(rs.getInt("product_id"));
                review.setUserName(rs.getString("user_name"));
                review.setRating(rs.getInt("rating"));
                review.setReviewText(rs.getString("review_text"));
                review.setCreatedAt(rs.getTimestamp("created_at"));
                reviews.add(review);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void addReview(Review review) {
        String sql = "INSERT INTO reviews (product_id, user_name, rating, review_text, created_at) VALUES (?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, review.getProductId());
            ps.setString(2, review.getUserName());
            ps.setInt(3, review.getRating());
            ps.setString(4, review.getReviewText());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean hasCustomerPurchasedProduct(String userName, int productId) {
        String sql = "SELECT od.id_product, od.price, od.amount, od.fee, od.total " +
                "FROM orders o " +
                "JOIN order_detail od ON o.order_id = od.id_oder " +
                "WHERE o.user_name = ? AND od.id_product = ? AND o.status = 2";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả trả về, nghĩa là khách hàng đã mua sản phẩm
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
