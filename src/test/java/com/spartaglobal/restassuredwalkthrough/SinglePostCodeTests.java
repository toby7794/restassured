package com.spartaglobal.restassuredwalkthrough;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SinglePostCodeTests {

    private static Response json;

    @BeforeClass
    public static void setup(){
        baseURI = "https://api.postcodes.io/";
        basePath = "postcodes/";
        json = get("BN1 7JD");
    }

    @Test
    public void postcodeRequestIsSuccessful(){
        get("BN1 7JD").then().statusCode(200).body("status", equalTo(200));
    }

    @Test
    public void inCorrectpostcodeResponse(){
        get("BN1").then().statusCode(404);
    }

    @Test
    public void postcodeIsnhsha(){
        json.then().statusCode(200).body("result.nhs_ha", equalTo("South East Coast"));
    }

    @Test
    public void postcodeIsnhsha2(){
        json.then().statusCode(200).body("result.nhs_ha", equalTo("South East Coast"));
    }

}
