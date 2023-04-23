package service.API_LOGISTIC;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transport {
    String id;
    String created_at;
    String leadTime;

    public Transport(String id, String created_at, String leadTime) {
        this.id = id;
        this.created_at = created_at;
        this.leadTime = leadTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id='" + id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", leadTime='" + leadTime + '\'' +
                '}';
    }
}

