import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierLogin;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;

import static data.TestData.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourier;

public class CreateCourierTest extends BaseApiTest {

    CourierModel courier;
    CourierLogin courierLogin;
    CourierSteps courierSteps;

    @Before
    public void createTestCourier() {
        courier = new CourierModel(LOGIN, PASSWORD, FIRSTNAME);
        courierLogin = new CourierLogin(LOGIN, PASSWORD);
        courierSteps = new CourierSteps();
    }

    @Test
    @DisplayName("Успешное создание нового курьера")
    @Description("Проверяем, что курьера можно создать, если передать все необходимые поля")
    public void successCreateNewCourierTest() {

        // Создаём курьера
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Ошибка при создании двух одинаковых курьеров")
    @Description("Проверяем, что появляется ошибка при попытке создать двух одинаковых курьеров")
    public void errorCreatingTwoIdenticalCouriersTest() {

        // Создаём курьера
        createCourier(courier);

        // Передаём данные курьера, который уже создан
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без логина")
    @Description("Проверяем, что появляется ошибка при создании курьера, если не передать логин")
    public void errorCreatingCourierWithoutLoginTest() {

        // Создаём объект без логина
        CourierModel courier = new CourierModel(null, PASSWORD, FIRSTNAME);

        // Передаём данные курьера без логина
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без пароля")
    @Description("Проверяем, что появляется ошибка при создании курьера, если не передать пароль")
    public void errorCreatingCourierWithoutPasswordTest() {

        // Создаём объект без пароля
        CourierModel courier = new CourierModel(LOGIN, null, FIRSTNAME);

        // Передаём данные курьера без пароля
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    // После каждого теста удаляем созданного курьера
    @After
    public void tearDown() {
        courierSteps.cleanUp (courierLogin);
    }
}


