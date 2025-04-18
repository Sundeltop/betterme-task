package org.example.api.store;

import io.qameta.allure.Feature;
import org.example.api.BaseTest;
import org.example.api.RestAssuredResponse;
import org.testng.annotations.Test;

import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Store Inventory")
public class StoreInventoryTest extends BaseTest {

    @Test(description = "Verify GET request to '/store/inventory' returns a map of status codes to quantities")
    void verifyReturnMapOfInventoryStatusCodes() {
        final String[] defaultStatuses = new String[]{"available", "pending", "sold"};
        final RestAssuredResponse<Map<String, Integer>> storeInventory = api.petStoreService().getStoreInventory();

        storeInventory.validate().statusCode(SC_OK);

        assertThat(storeInventory.extract())
                .isNotEmpty()
                .containsKeys(defaultStatuses);
    }
}
