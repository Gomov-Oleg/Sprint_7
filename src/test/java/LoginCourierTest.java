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
import static org.hamcrest.CoreMatchers.instanceOf;
import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.loginCourier;

public class LoginCourierTest extends BaseApiTest{

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
    @DisplayName("Курьер может войти в систему")
    @Description("Проверяем, что курьер может войти в систему, если передать валидную пару: логин-пароль")
    public void courierCanLogIn() {

        // Создаём курьера
        createCourier(courier);

        // Передаём валидные логин и пароль созданного курьера
        loginCourier(courierLogin)
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .assertThat().body("id", instanceOf(Integer.class));
    }

    @Test
    @DisplayName("Ошибка, если при авторизации не передать логин")
    @Description("Проверяем, что появляется ошибка, если не передать логин")
    public void errorLogInCourierWithoutLogin() {

        // Создаём объект без логина
        CourierLogin courierLogin = new CourierLogin(null, PASSWORD);

        // Создаём курьера
        createCourier(courier);

        // Передаём данные без логина
        loginCourier(courierLogin)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка, если при авторизации не передать пароль")
    @Description("Проверяем, что появляется ошибка, если не передать пароль")
    public void errorLogInCourierWithoutPassword() {

        // Создаём объект без пароля
        CourierLogin courierLogin = new CourierLogin(LOGIN, null);

        // Создаём курьера
        createCourier(courier);

        // Передаём данные без пароля
        loginCourier(courierLogin)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка, если при авторизации передать неверный логин")
    @Description("Проверяем, что появляется ошибка, если написать некорректный логин")
    public void errorLogInCourierIncorrectLogin() {

        // Создаём объект, в котором искажаем настоящий логин
        CourierLogin courierLogin = new CourierLogin(LOGIN + 15, PASSWORD);

        // Создаём курьера
        createCourier(courier);

        // Передаём данные с некорректным логином
        loginCourier(courierLogin)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Ошибка, если при авторизации передать неверный пароль")
    @Description("Проверяем, что появляется ошибка, если написать некорректный пароль")
    public void errorLogInCourierIncorrectPassword() {

        // Создаём объект, в котором искажаем настоящий пароль
        CourierLogin courierLogin = new CourierLogin(LOGIN, PASSWORD + 1);

        // Создаём курьера
        createCourier(courier);

        // Передаём данные с некорректным паролем
        loginCourier(courierLogin)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    // После каждого теста удаляем созданного курьера
    @After
    public void tearDown() {
        courierSteps.cleanUp (courierLogin);
    }
}
