package service;

import dao.DBConnection;
import model.CancelOrder;
import model.Order;
import model.Order_detail;
import service.API_LOGISTIC.Transport;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                Timestamp timestamp = rs.getTimestamp("date_order");

                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;
                if (timestamp != null) {
                    dateOrder = timestamp.toLocalDateTime();
                }
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), dateOrder, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
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
                Timestamp timestamp = rs.getTimestamp("date_order");

                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;
                if (timestamp != null) {
                    dateOrder = timestamp.toLocalDateTime();
                }
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), dateOrder, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                od.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return od;
    }
    //check don hang dang van chuyen
    public List<Order> getAllOderShip() {
        List<Order> od = new ArrayList<>();
        Order order = null;
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT order_id, user_name, total_money, fee, date_order, payment, transport, status, address, note, phoneNum FROM `orders` WHERE status IN (7, 8)";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("date_order");
                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;
                if (timestamp != null) {
                    dateOrder = timestamp.toLocalDateTime();
                }
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), dateOrder, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
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
                Timestamp timestamp = rs.getTimestamp("date_order");

                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;
                if (timestamp != null) {
                    dateOrder = timestamp.toLocalDateTime();
                }
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), dateOrder, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
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
                Timestamp timestamp = rs.getTimestamp("date_order");

                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;
                if (timestamp != null) {
                    dateOrder = timestamp.toLocalDateTime();
                }
                order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), dateOrder, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));

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
            // Chuyển đổi LocalDateTime thành Timestamp
            ps.setTimestamp(5, Timestamp.valueOf(o.getDate_order()));
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


                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime dateOrder = null;

                Order_detail orderDetail = new Order_detail(0, new Order(1, "u", 1, 1, dateOrder, "t", "1", 1, "grgr", "fg", "phone"), rs.getInt(1), rs.getLong(2), rs.getInt(3), rs.getInt(4), rs.getLong(5));
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

    public static void updateStatus(int order_id, int status) {
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
    public Transport getTransportId(int id){
        ResultSet rs;
        Transport transport = null;
        PreparedStatement ps;
        String sql = "SELECT id, id_order, created_at, leadTime FROM `transports` WHERE id_order =" + id;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOder_id(rs.getInt(2));
               transport = new Transport(rs.getString(1), order,rs.getString(3), rs.getString(4));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transport;
    }
    //
//    public void updateOrderStatusByTransportLeadTime() {
//        ResultSet rs;
//        PreparedStatement ps;
//        String sql = "UPDATE `orders` SET `status` = 2 WHERE `order_id` IN "
//                + "(SELECT `id_order` FROM `transports` WHERE DATEDIFF(NOW(), STR_TO_DATE(`leadTime`, '%d/%m/%Y')) >= 0) AND `status` != 2";
//        try {
//            ps = DBConnection.getConnection().prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void updateOrderStatusByTransportLeadTime() {
//        ResultSet rs;
//        PreparedStatement ps;
//        String sql = "UPDATE `orders` SET `status` = CASE " +
//                "WHEN `status` != 2 AND `order_id` IN " +
//                "(SELECT `id_order` FROM `transports` WHERE `leadTime` <> 'Chờ vận chuyển' AND DATE(`leadTime`) <= CURDATE()) THEN 2 " +
//                "WHEN `status` != 8 AND `order_id` IN " +
//                "(SELECT `id_order` FROM `transports` WHERE `leadTime` <> 'Chờ vận chuyển' AND DATE(`leadTime`) <= CURDATE()) THEN 8 " +
//                "ELSE `status` END";
//        try {
//            ps = DBConnection.getConnection().prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void updateOrderStatusByTransportLeadTime() {
//        PreparedStatement ps;
//        String sql = "UPDATE `orders` SET `status` = CASE " +
//                "WHEN `status` NOT IN (2, 8) AND `order_id` IN " +
//                "(SELECT `id_order` FROM `transports` WHERE `leadTime` <> 'Chờ vận chuyển' AND DATE(`leadTime`) <= CURDATE()) " +
//                "THEN CASE " +
//                "WHEN `status` != 2 THEN 2 " +
//                "WHEN `status` != 8 THEN 8 " +
//                "END " +
//                "ELSE `status` END";
//        try {
//            ps = DBConnection.getConnection().prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public static void updateOrderStatusByTransportLeadTime() {
        PreparedStatement ps;
        String sql = "UPDATE `orders` SET `status` = CASE " +
                "WHEN `status` NOT IN (2, 8) AND `order_id` IN " +
                "(SELECT `id_order` FROM `transports` WHERE `leadTime` <> 'Chờ vận chuyển' AND STR_TO_DATE(`leadTime`, '%Y/%m/%d %H:%i:%s') <= NOW()) " +
                "THEN CASE " +
                "WHEN `status` != 2 THEN 2 " +
                "WHEN `status` != 8 THEN 8 " +
                "END " +
                "ELSE `status` END";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        OrderService os = new OrderService();
//        Order o = new Order();
//        System.out.println(os.getOderById(26));os
//        os.updateOrderStatusByTransportLeadTime();
        List<Order> orderList = os.getAllOderShip();
        for(Order o : orderList){
            System.out.println(o.toString());
        }
        updateOrderStatusByTransportLeadTime();
    }

    public void cancelOrder(int orderId, int uid, String reason) {
        updateStatus(orderId, 3);
        String sql = "INSERT INTO cancel_order (user_id, order_id, reason, statuss, createdAt) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, orderId);
            ps.setString(3, reason);
            ps.setString(4, "Huỷ đơn hàng");
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CancelOrder> getAllOderCancel() {
        List<CancelOrder> od = new ArrayList<>();
        CancelOrder order = null;
        ResultSet rs;
        PreparedStatement ps;
        String sql = "SELECT cancel_id, user_id, order_id, reason, statuss, createdAt FROM `cancel_order`";
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("createdAt");
                // Chuyển đổi Timestamp thành LocalDateTime
//                LocalDateTime dateOrder = null;
//                if (timestamp != null) {
//                    dateOrder = timestamp.toLocalDateTime();
//                }
                order = new CancelOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3),  rs.getString(4), rs.getString(5), timestamp);
                od.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return od;
    }
}
