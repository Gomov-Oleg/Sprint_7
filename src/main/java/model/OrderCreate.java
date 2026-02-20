package model;

import java.util.List;

// Класс создания заказа
public class OrderCreate {

    private String firstName = "Вася";
    private String lastName = "Иванов";
    private String address = "г. Москва, ул. Ленина, д. 25, кв. 81";
    private int metroStation = 4;
    private String phone = "+7 999 000 01 01";
    private int rentTime = 5;
    private String deliveryDate = "2026-02-28";
    private String comment = "Позвоните, пожалуйста, за 15 минут до приезда";
    private List<String> color;

    // Конструктор с параметром цвет самоката
    public OrderCreate(List<String> color) {
        this.color = color;
    }

    // Конструктор без параметров
    public OrderCreate(){}

    // Геттеры и сеттеры
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
