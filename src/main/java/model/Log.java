package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Log {
    int id;
    int level;
    int userId;
    String src;
    String content;
    Date creatAt;
    int status;

    static Map<Integer, String> levelMapping = new HashMap<>();

    static {
        levelMapping.put(0, "INFO");
        levelMapping.put(1, "ALERT");
        levelMapping.put(2, "WARNING");
        levelMapping.put(3, "DANGER");
    }

    public static int INFO = 0;
    public static int ALERT = 1;
    public static int WARNING = 2;
    public static int DANGER = 3;

    public Log() {

    }

    public Log( int level,int userId, String src,  String content, Date creatAt, int status) {
        this.level = level;
        this.src = src;
        this.userId = userId;
        this.content = content;
        this.creatAt = creatAt;
        this.status = status;
    }
    public Log( int level,int userId, String src,  String content,int status) {
        this.level = level;
        this.src = src;
        this.userId = userId;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelWithName() {
        return levelMapping.get(levelMapping.containsKey(this.level) ? this.level : 0);
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
