package org.example.api.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;
import static org.example.config.ConfigurationManager.configuration;

public abstract class BasePetService {

    protected final RequestSpecification requestSpecification;

    public BasePetService() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(configuration().baseUri())
                .setBasePath(pathToResource())
                .build();
    }

    public abstract String pathToResource();
}
