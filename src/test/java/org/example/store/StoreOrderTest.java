package org.example.store;

import io.qameta.allure.Feature;
import org.example.BaseTest;
import org.example.api.RestAssuredResponse;
import org.example.api.dto.Order;
import org.testng.annotations.Test;

import static java.time.OffsetDateTime.now;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Store Order")
public class StoreOrderTest extends BaseTest {

    @Test(description = "Verify POST request to '/store/order' places an order for a pet")
    void verifyPlaceOrderForPet() {
        final Order order = getOrderWithId(0L);

        final RestAssuredResponse<Order> createdOrderResponse = api.petStoreService().createOrder(order);

        createdOrderResponse.validate().statusCode(SC_OK);

        final Order createdOrder = createdOrderResponse.extract();
        softly.assertThat(createdOrder)
                .hasNoNullFieldsOrProperties()
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

    @Test(description = "Verify GET request to '/store/order/{orderId}' finds purchased order by ID")
    void verifyFindPurchasedOrderById() {
        final Long existingOrderId = 1L;
        final Order order = getOrderWithId(existingOrderId);

        api.petStoreService()
                .createOrder(order)
                .validate()
                .statusCode(SC_OK);

        final RestAssuredResponse<Order> getOrderResponse = api.petStoreService().getOrderById(existingOrderId);

        getOrderResponse.validate().statusCode(SC_OK);

        assertThat(getOrderResponse.extract())
                .hasNoNullFieldsOrProperties()
                .extracting(Order::id)
                .isEqualTo(existingOrderId);
    }

    private Order getOrderWithId(Long orderId) {
        return Order.builder()
                .id(orderId)
                .petId(0L)
                .quantity(0)
                .shipDate(now())
                .status("placed")
                .isCompleted(true)
                .build();
    }
}
