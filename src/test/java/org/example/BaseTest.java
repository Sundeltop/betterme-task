package org.example;

import org.example.api.RestAssuredWrapper;

public abstract class BaseTest {

    protected final RestAssuredWrapper api = new RestAssuredWrapper();
}
