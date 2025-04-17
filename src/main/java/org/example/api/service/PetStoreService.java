package org.example.api.service;

public class PetStoreService extends BasePetService {

    public PetStoreService() {
        super();
    }

    @Override
    public String pathToResource() {
        return "/store";
    }
}
