package org.example.api;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.example.api.services.PetStoreService;

@Getter
@Accessors(fluent = true)
public class RestAssuredWrapper {

    private final PetStoreService petStoreService;

    public RestAssuredWrapper() {
        petStoreService = new PetStoreService();
    }
}
