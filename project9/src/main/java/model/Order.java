package model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int accountId;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String content;
    private double totalMoney;
    private String signature;
    private String status;
    private Timestamp orderDate;

    // Constructors
    public Order() {}

    public Order(int orderId, int accountId, String name, String phoneNumber, String email,
                 String address, String content, double totalMoney, String signature,String status, Timestamp orderDate) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.content = content;
        this.totalMoney = totalMoney;
        this.signature = signature;
        this.status = status;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", accountId=" + accountId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", totalMoney=" + totalMoney +
                ", signature='" + signature + '\'' +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}

