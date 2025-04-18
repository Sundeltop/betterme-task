# This project is created as a test task for BetterMe

## Tech Stack

+ Java 21
+ Maven
+ RestAssured
+ TestNG
+ Allure
+ AssertJ

## Configuration

You can change base.uri in **config.properties**:

`base.uri=YOUR URI`

or by passing as variable in Maven:

`-Dbase.uri=YOUR URI`

## How to run

You can run this project by running Maven command:

`mvn clean test`

or run selected testng suite file under `src/test/resources/suites/`

To generate Allure Report you can use Allure Maven plugin:

`mvn allure:serve`