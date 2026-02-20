package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierId;
import model.CourierLogin;
import model.CourierModel;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

// Шаги для курьера
public class CourierSteps {

    // Шаг, который создаёт курьера
    @Step ("Создаём курьера")
    public static Response createCourier(CourierModel courier) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then()
                .extract().response();
    }

    // Шаг, который передаёт логин и пароль курьера и возвращает id курьера
    @Step("Передаём логин и пароль и получаем id курьера")
    public static Response loginCourier(CourierLogin courierLogin) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courierLogin)
                .when()
                .post(LOGIN_COURIER_PATH)
                .then()
                .extract().response();
    }

    // Шаг, который удаляет курьера
    @Step("Удаляем курьера")
    public static void deleteCourier(int id) {
         given()
                .log().all()
                .when()
                .delete(DELETE_COURIER_PATH + id);
    }

    // Шаг, который удаляет курьера после выполнения теста (получение id, удаление курьера)
    @Step("Удаляем курьера после выполнения теста (получаем его id, удаляем")
    public void cleanUp(CourierLogin courierLogin) {
        Response response = loginCourier(courierLogin);
        CourierId courierId = response.as(CourierId.class);
        int id = courierId.getId();
        deleteCourier(id);
    }
}

