package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderCreate;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

// Шаги для заказа
public class OrderSteps {

    // Шаг, который создаёт заказ и возвращает track заказа
    @Step("Создаём заказ")
    public static Response createOrder(OrderCreate orderCreate) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(orderCreate)
                .when()
                .post(CREATE_ORDER_PATH)
                .then()
                .extract().response();
    }

    // Шаг, который получает список заказов
    @Step("Получаем список заказов")
    public static Response gettingListOrders() {
            return given()
                .log().all()
                .get(GETTING_LIST_ORDERS_PATH)
                .then()
                .extract().response();
    }
}
