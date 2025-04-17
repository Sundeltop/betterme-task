package org.example.api.services;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class PetStoreService extends BasePetService {

    public PetStoreService() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Integer> getStoreInventory() {
        return given(requestSpecification)
                .when()
                .get("/inventory")
                .then()
                .statusCode(SC_OK)
                .extract()
                .as(Map.class);
    }

    @Override
    public String pathToResource() {
        return "/store";
    }
}
