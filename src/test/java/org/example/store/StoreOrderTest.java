package org.example.store;

import io.qameta.allure.Feature;
import org.example.BaseTest;
import org.example.api.RestAssuredResponse;
import org.example.api.dto.Order;
import org.testng.annotations.Test;

import static java.time.OffsetDateTime.now;
import static org.apache.http.HttpStatus.SC_OK;

@Feature("Store Order")
public class StoreOrderTest extends BaseTest {

    @Test(description = "Verify POST request to '/store/order' places an order for a pet")
    void verifyPlaceOrderForPet() {
        final Order order = Order.builder()
                .id(0L)
                .petId(0L)
                .quantity(0)
                .shipDate(now())
                .status("placed")
                .isCompleted(true)
                .build();

        final RestAssuredResponse<Order> createdOrderResponse = api.petStoreService().createOrder(order);

        createdOrderResponse.validate().statusCode(SC_OK);

        final Order createdOrder = createdOrderResponse.extract();
        softly.assertThat(createdOrder)
                .usingRecursiveComparison()
                .ignoringFields("id", "shipDate")
                .isEqualTo(order);
        softly.assertThat(createdOrder.id())
                .isNotNull()
                .isNotZero()
                .isPositive();
        softly.assertThat(createdOrder.shipDate())
                .isNotNull();
        softly.assertAll();
    }
}
