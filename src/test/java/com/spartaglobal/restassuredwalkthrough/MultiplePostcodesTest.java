package com.spartaglobal.restassuredwalkthrough;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MultiplePostcodesTest {

    private static Response json;

    @BeforeClass
    public static void setup(){
        baseURI = "https://api.postcodes.io/";
        basePath = "postcodes/";
        json = given()
                .contentType(ContentType.JSON)
                .body("{\"postcodes\":[\"se120nb\", \"SE96RJ\"]}")
                .when()
                .post();
    }

    @Test
    public void testMultiple(){
        given()
                .contentType(ContentType.JSON)
                .body("{\"postcodes\":[\"se120nb\", \"SE96RJ\"]}")
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("result[0].query", equalTo("se120nb"));
    }

    @Test
    public void testCountry(){
        json
                .then()
                .statusCode(200)
                .body("result[1].result.country", equalTo("England"));
    }

    @Test
    public void testNuts(){
        json
                .then()
                .statusCode(200)
                .body("result[1].result.nuts",equalTo("Bexley and Greenwich"));
    }


}
