package org.example.api;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.example.api.service.PetStoreService;

@Getter
@Accessors(fluent = true)
public class RestWrapper {

    private final PetStoreService petStoreService;

    public RestWrapper() {
        petStoreService = new PetStoreService();
    }
}
