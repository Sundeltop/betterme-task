package org.example.api.services;

import lombok.extern.log4j.Log4j2;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

@Log4j2
public class PetStoreService extends BasePetService {

    public PetStoreService() {
        super();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Integer> getStoreInventory() {
        log.info("Send GET request to /store/inventory");
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
