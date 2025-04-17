package org.example.api;

import lombok.Getter;
import org.example.api.service.PetStoreService;

@Getter
public class RestWrapper {

    private final PetStoreService petStoreService;

    public RestWrapper() {
        petStoreService = new PetStoreService();
    }
}
