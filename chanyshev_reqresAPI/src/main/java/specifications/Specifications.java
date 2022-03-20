package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public  static RequestSpecification requestSpec(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
        return requestSpecification;
    }

    public static ResponseSpecification responseSpec(){
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        return responseSpec;
    }
    public static ResponseSpecification responseBadSpec(){
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        return responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec){
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installSpec(ResponseSpecification responseSpec){
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
    public static void deleteSpec(){
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
