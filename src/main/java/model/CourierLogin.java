package model;

// Класс уже созданного курьера с полями: логин и пароль
public class CourierLogin {

    private String login;
    private String password;

    // Конструктор с параметрами: логин и пароль
    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Конструктор без параметров
    public CourierLogin(){}

    // Геттеры и сеттеры
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
