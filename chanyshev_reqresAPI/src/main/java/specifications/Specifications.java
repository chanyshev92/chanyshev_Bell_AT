package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Класс со спецификациями запросов и ответов
 */
public class Specifications {

    /**
     * Спецификация запроса. Установка URL страницы. Установка типа JSON
     * @return Объект спецификации
     */
    public  static RequestSpecification requestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
    }

    /**
     * Спецификация "Успешного" ответа. Установка статус кода 200.
     * @return Объект спецификации
     */
    public static ResponseSpecification responseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    /**
     * Спецификация "Провального" ответа. Установка статус кода 400.
     * @return Объект спецификации
     */
    public static ResponseSpecification responseBadSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    /**
     * Установка спецификации запроса
     * @param requestSpec Входная спецификация запроса
     */
    public static void installSpec(RequestSpecification requestSpec){
        RestAssured.requestSpecification = requestSpec;
    }

    /**
     * Установка спецификации ответа
     * @param responseSpec Входная спецификация ответа
     */
    public static void installSpec(ResponseSpecification responseSpec){
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Установка спецификации запроса и ответа
     * @param requestSpec Входная спецификация запроса
     * @param responseSpec Входная спецификация ответа
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Обнуление спецификации
     */
    public static void deleteSpec(){
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
