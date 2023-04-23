package model;


import dao.DBConnection;
import service.API_LOGISTIC.Login_API;
import service.API_LOGISTIC.RegisterTransport;
import service.API_LOGISTIC.Transport;
import service.OrderService;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Order {
    public int oder_id;
    public String user_name;
    public long total_money;
    public int fee;
    public Date date_order;
    public String payment;
    public String transport;
    public int status;
    public String address;
    public String note;
    public String phoneNum;

    public Order(int oder_id, String user_name, long total_money, int fee, Date date_order, String payment, String transport, int status, String address, String note, String phoneNum) {
        this.oder_id = oder_id;
        this.user_name = user_name;
        this.total_money = total_money;
        this.fee = fee;
        this.date_order = date_order;
        this.payment = payment;
        this.transport = transport;
        this.status = status;
        this.address = address;
        this.note = note;
        this.phoneNum = phoneNum;
    }

    public int getOder_id() {
        return oder_id;
    }

    public void setOder_id(int oder_id) {
        this.oder_id = oder_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getTotal_money() {
        return total_money;
    }

    public void setTotal_money(long total_money) {
        this.total_money = total_money;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Date getDate_order() {
        return date_order;
    }

    public void setDate_order(Date date_order) {
        this.date_order = date_order;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getDateCurrent() {
        Date current = Date.valueOf(LocalDate.now());
        return current;
    }

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "oder_id=" + oder_id +
                ", user_name='" + user_name + '\'' +
                ", total_money=" + total_money +
                ", fee=" + fee +
                ", date_order=" + date_order +
                ", payment='" + payment + '\'' +
                ", transport='" + transport + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                '}';


    }
    public String getFullName(String user_name){
        String sql = "Select  full_name from users where user_name = "+ "user_name";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String fullName = null;
        try {
            ps = DBConnection.getConnection().prepareStatement(sql);

            rs = ps.executeQuery(sql);
            while (rs.next()) {
                 fullName = rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullName;
    }
    public String formatCurrency(double amount) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(amount);
    }
    public String statusOrder(int id){
        String nameStatus = "Lỗi";
        switch (id){
            case 0:
                nameStatus = "Chờ xác nhận";
                break;
            case 1:
                nameStatus = "Đang vận chuyển";
                break;
            case 2:
                nameStatus = "Đã giao";
                break;
            case 3:
                nameStatus = "Đã huỷ đơn hàng";
                break;
            case 4:
                nameStatus = "Giao hàng thất bại";
                break;
        }
        return nameStatus;
    }

    public static void main(String[] args) throws IOException {
        Order o = new Order();
        System.out.println(o.statusOrder(1));


        int oid = 26;
        OrderService orderService = new OrderService();
        String from_district_id = "2264";
        String from_ward_id = "90816";
        String to_district_id = "2270";
        String to_ward_id = "231013";

        Order order = new Order();
        order.setOder_id(oid);
        Login_API login_api = new Login_API();
        String API_KEY = login_api.login();
        System.out.println(API_KEY);
        RegisterTransport register = new RegisterTransport();
        Transport transport = register.registerTransport(API_KEY, order, from_district_id, from_ward_id, to_district_id, to_ward_id);
        orderService.addTransport(transport);
    }
}