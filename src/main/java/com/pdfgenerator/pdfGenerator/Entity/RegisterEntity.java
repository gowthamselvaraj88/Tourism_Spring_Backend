package com.pdfgenerator.pdfGenerator.Entity;

public class RegisterEntity {

    private Long userId;
    private String userName;
    private String address;
    private Integer mobile;
    private Integer panNumber;
    private String email;

    public RegisterEntity() {
    }

    public RegisterEntity(Long userId, String userName, String address, Integer mobile, Integer panNumber, String email, String userPassword) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.mobile = mobile;
        this.panNumber = panNumber;
        this.email = email;
        this.userPassword = userPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Integer getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(Integer panNumber) {
        this.panNumber = panNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private String userPassword;

}
