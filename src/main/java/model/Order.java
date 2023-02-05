package model;


import java.sql.Date;
import java.time.LocalDate;

public class Order {
    public int oder_id;
    public String user_name;
    public String payment;
    public long total_money;
    public int fee;
    public Date date_order;
    public String transport;
    public int status;

    public Order(int oder_id, String user_name, String payment, long total_money, int fee, Date date_order, String transport, int status) {
        this.oder_id = oder_id;
        this.user_name = user_name;
        this.payment = payment;
        this.total_money = total_money;
        this.fee = fee;
        this.date_order = date_order;
        this.transport = transport;
        this.status = status;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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
                ", payment='" + payment + '\'' +
                ", total_money=" + total_money +
                ", fee=" + fee +
                ", date_order=" + date_order +
                ", transport='" + transport + '\'' +
                ", status=" + status +
                '}';
    }

    public static void main(String[] args) {
        Order o = new Order();
        System.out.println(o.getDateCurrent());
    }
}