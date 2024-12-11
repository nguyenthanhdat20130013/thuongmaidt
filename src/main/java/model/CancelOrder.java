package model;

import java.sql.Timestamp;

public class CancelOrder {
    private int canecl_id;
    private int user_id;
    private int order_id;
    private String reason;
    private String status;
    private Timestamp createdAt;

    public CancelOrder(int canecl_id, int user_id, int order_id, String reason, String status, Timestamp createdAt) {
        this.canecl_id = canecl_id;
        this.user_id = user_id;
        this.order_id = order_id;
        this.reason = reason;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getCanecl_id() {
        return canecl_id;
    }

    public void setCanecl_id(int canecl_id) {
        this.canecl_id = canecl_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CancelOrder{" +
                "canecl_id=" + canecl_id +
                ", user_id=" + user_id +
                ", order_id=" + order_id +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
