package org.example.api.services;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.api.RestAssuredResponse;
import org.example.api.dto.Order;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Log4j2
public class PetStoreService extends BasePetService {

    public PetStoreService() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Step("Send GET request to '/store/inventory'")
    public RestAssuredResponse<Map<String, Integer>> getStoreInventory() {
        log.info("Send GET request to '/store/inventory'");
        return new RestAssuredResponse<Map<String, Integer>>(
                given().spec(requestSpecification).get("/inventory"),
                r -> r.as(Map.class)
        );
    }

    @Step("Send POST request to '/store/order' to create order")
    public RestAssuredResponse<Order> createOrder(Order order) {
        log.info("Send POST request to '/store/order' to create order");
        return new RestAssuredResponse<>(
                given().spec(requestSpecification).body(order).post("/order"),
                r -> r.as(Order.class)
        );
    }

    @Step("Send GET request to '/store/order/{orderId}' to get order by 'orderId'")
    public RestAssuredResponse<Order> getOrderById(Long orderId) {
        log.info("Send GET request to '/store/order/{}' to get order by 'orderId'", orderId);
        return new RestAssuredResponse<>(
                given().spec(requestSpecification).get("/order/{orderId}", orderId),
                r -> r.as(Order.class)
        );
    }

    @Step("Send DELETE request to '/store/order/{orderId}' to delete order by 'orderId'")
    public RestAssuredResponse<Void> deleteOrderById(Long orderId) {
        log.info("Send DELETE request to '/store/order/{}' to delete order by 'orderId'", orderId);
        return new RestAssuredResponse<>(
                given().spec(requestSpecification).delete("/order/{orderId}", orderId),
                null
        );
    }

    @Override
    public String pathToResource() {
        return "/store";
    }
}
