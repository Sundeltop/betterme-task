package org.example.store;

import io.qameta.allure.Feature;
import org.example.BaseTest;
import org.example.api.RestAssuredResponse;
import org.example.api.dto.Order;
import org.testng.annotations.Test;

import static java.time.OffsetDateTime.now;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

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

    @Test(description = "Verify GET request to '/store/order/{orderId}' finds purchase order by ID")
    void verifyFindPurchaseOrderById() {
        final Long existingOrderId = 1L;
        createOrderWithId(existingOrderId);

        final RestAssuredResponse<Order> getOrderResponse = api.petStoreService().getOrderById(existingOrderId);

        getOrderResponse.validate().statusCode(SC_OK);

        assertThat(getOrderResponse.extract())
                .hasNoNullFieldsOrProperties()
                .extracting(Order::id)
                .isEqualTo(existingOrderId);
    }

    @Test(description = "Verify DELETE request to '/store/order/{orderId}' deletes purchase order by ID")
    void verifyDeletePurchaseOrderById() {
        final Long existingOrderId = faker.number().numberBetween(90000L, 99999L);
        createOrderWithId(existingOrderId);

        api.petStoreService()
                .deleteOrderById(existingOrderId)
                .validate()
                .statusCode(SC_OK);
    }

    @Test(description = "Verify DELETE request to '/store/order/{orderId}' returns error if purchase order doesn't exist")
    void verifyDeleteNotExistingPurchaseOrderById() {
        final Long notExistingOrderId = faker.number().numberBetween(90000L, 99999L);
        deleteOrderWithId(notExistingOrderId);

        api.petStoreService()
                .deleteOrderById(notExistingOrderId)
                .validate()
                .statusCode(SC_NOT_FOUND);
    }

    private Order createOrderWithId(Long orderId) {
        final Order order = getOrderWithId(orderId);

        final RestAssuredResponse<Order> createdOrderResponse = api.petStoreService().createOrder(order);
        createdOrderResponse.validate().statusCode(SC_OK);

        return createdOrderResponse.extract();
    }

    private void deleteOrderWithId(Long orderId) {
        api.petStoreService()
                .deleteOrderById(orderId)
                .validate()
                .statusCode(anyOf(is(SC_OK), is(SC_NOT_FOUND)));
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
