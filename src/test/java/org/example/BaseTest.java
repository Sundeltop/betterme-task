package org.example;

import com.github.javafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.example.api.RestAssuredWrapper;

public abstract class BaseTest {

    protected final RestAssuredWrapper api = new RestAssuredWrapper();
    protected final SoftAssertions softly = new SoftAssertions();
    protected final Faker faker = new Faker();
}
