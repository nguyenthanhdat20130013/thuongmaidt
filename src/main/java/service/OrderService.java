package service;

import dao.DBConnection;
import model.Order;
import model.Order_detail;
import service.API_LOGISTIC.Transport;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> getAllOder() {
        List<Order> od = new ArrayList<>();
        Order order = null;
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT  order_id,  user_name,  total_money,  fee,  date_order,  payment,  transport,  status,  address,  note, phoneNum FROM `orders`";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                od.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return od;
    }

    public List<Order> getAllOderNotCheck() {
        List<Order> od = new ArrayList<>();
        Order order = null;
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT  order_id,  user_name,  total_money,  fee,  date_order,  payment,  transport,  status,  address,  note, phoneNum FROM `orders` where status = 0 ";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                od.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return od;
    }

    public int getNumOrderNotCheck() {
        ResultSet rs;
        int result = 0;
        PreparedStatement ps;
        String sql = "SELECT COUNT(order_id) FROM `orders` where status = 0 ";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Order> getOderByUname(String uname) {
        List<Order> od = new ArrayList<>();
        Order order = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "SELECT order_id,  user_name,  total_money,  fee,  date_order,  payment,  transport,  status,  address,  note, phoneNum FROM `orders` WHERE user_name like ?";
            pst = DBConnection.getConnection().prepareStatement(sql);
            pst.setString(1, uname);
            rs = pst.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                od.add(order);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return od;
    }

    public Order getOderById(int id) {

        Order order = null;
        ResultSet rs;
        PreparedStatement pst;
        String sql;
        try {
            sql = "SELECT order_id,  user_name,  total_money,  fee,  date_order,  payment,  transport,  status,  address,  note, phoneNum FROM `orders` WHERE order_id = " + id;
            pst = DBConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return order;
    }

    public void addOder(Order o) {
        String sql = "INSERT INTO `orders` (order_id, user_name, total_money, fee, date_order, payment, transport, status, address, note, phoneNum) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, o.getOder_id());
            ps.setString(2, o.getUser_name());
            ps.setInt(3, (int) o.getTotal_money());
            ps.setInt(4, o.getFee());
            ps.setDate(5, o.getDate_order());
            ps.setString(6, o.getPayment());
            ps.setString(7, o.getTransport());
            ps.setInt(8, o.getStatus());
            ps.setString(9, o.getAddress());
            ps.setString(10, o.getNote());
            ps.setString(11, o.getPhoneNum());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOrderDetail(Order_detail detail) {
        String sql = "INSERT INTO order_detail (id_oder, id_product, price, amount, fee, total) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, detail.getOrder().getOder_id());
            ps.setInt(2, detail.getId_product());
            ps.setLong(3, detail.getPrice());
            ps.setInt(4, detail.getAmount());
            ps.setInt(5, detail.getFee());
            ps.setLong(6, detail.getTotal());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Order_detail> getOrderDById(int id) {
        List<Order_detail> od = new ArrayList<>();
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT id_product, price, amount, fee, total FROM order_detail WHERE id_oder = " + id;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order_detail orderDetail = new Order_detail(0, new Order(1, "u", 1, 1, Date.valueOf(LocalDate.now()), "t", "1", 1, "grgr", "fg", "phone"), rs.getInt(1), rs.getLong(2), rs.getInt(3), rs.getInt(4), rs.getLong(5));
                od.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return od;
    }

    public int getMaxMHD() {
        ResultSet rs;
        int result = 0;
        PreparedStatement ps;
        String sql = "SELECT MAX(order_id) FROM `orders`";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result + 1;
    }

    public void updateStatus(int order_id, int status) {
        String sql = "UPDATE `orders` SET `status` = ? WHERE `order_id` = ?";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, order_id);
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTransport(Transport transport) {
        String sql = "INSERT INTO transports (id, id_order, created_at, leadTime) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, transport.getId());
            ps.setInt(2, transport.getOrder().getOder_id());
            ps.setString(3, transport.getCreated_at());
            ps.setString(4, transport.getLeadTime());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumTrans(int id) {
        ResultSet rs;
        int result = 0;
        PreparedStatement ps;
        String sql = "SELECT COUNT(*) FROM `transports` WHERE id_order =" + id;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        OrderService os = new OrderService();
        Order o = new Order();
//        o.setOder_id(26);
//        Transport transport = new Transport("0", o,"now","then");
//        os.addTransport(transport);
        System.out.println(os.getNumTrans(26));
    }
}
