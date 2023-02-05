package model;

public class UserModel {
    int id;
    String userName;
    String passWord;
    int role;
    String fullName;
    String phoneNum;
    String email;
    String address;

    String gender;
    int enable;

    public UserModel(int id, String userName, String passWord, int role, String fullName, String phoneNum, String email, String address, int enable,String gender ) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.enable = enable;
    }

    public UserModel(int id, String userName, String passWord, int role, int enable) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.enable = enable;
    }

    public UserModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
    public boolean checkRole(int role) {
        return this.role >= role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
