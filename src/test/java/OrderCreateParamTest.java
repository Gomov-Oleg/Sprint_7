import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderCreate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;


import static java.net.HttpURLConnection.HTTP_CREATED;

import static org.hamcrest.CoreMatchers.instanceOf;
import static steps.OrderSteps.createOrder;


@RunWith(Parameterized.class)
public class OrderCreateParamTest extends BaseApiTest{

    private final List<String> color;

    public OrderCreateParamTest(List<String> color) {
        this.color = color;
    }

    // Передаём в параметризованный тест цвет самоката
    @Parameterized.Parameters (name = "color: {0}")
    public static Object[][] getColor() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }

    @Test
    @DisplayName("Успешное создание заказа")
    @Description("Проверяем, что можно создать заказ, если указать различные варианты цвета самоката")
    public void successCreateOrder() {

        // Создаём объект, в который будем передавать цвет самоката
        OrderCreate orderCreate = new OrderCreate(color);

        // Создаём заказ
        createOrder(orderCreate)
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .assertThat().body("track", instanceOf(Integer.class));
    }
}
