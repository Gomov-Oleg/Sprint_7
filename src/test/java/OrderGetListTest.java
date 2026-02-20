import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.gettingListOrders;

public class OrderGetListTest extends BaseApiTest{

    @Test
    @DisplayName("Успешное получение списка заказов")
    @Description("Проверяем, что при запросе возвращается список заказов")
    public void successGetListOrders() {

        // Получаем список заказов
        gettingListOrders()
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .assertThat().body("orders", notNullValue());
    }
}
