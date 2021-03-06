package com.tamasdober.demo.helpers;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class RestAssuredHelpers {

    // Verifies response code and sends back the REST-assured Response object
    public static Response sendGetAllReturnResponse(String targetUrl, Integer expectedStatusCode) {

        return given().
            when().get(targetUrl).
            then().statusCode(expectedStatusCode).
            log().all().and().
            extract().response();
    }

    public static String getFirstIdFromGetAllResponse(Response response) {

        return response.jsonPath().getString("id[0]");
    }


    public static String[] getAllIdsFromGetAllResponse(Response response) {

        final String id = response.jsonPath().getString("id");
        return id.substring(1, id.length() - 1).split(",");
    }

    public static Integer getAllIdsListSizeFromGetAllResponse(Response response) {

        final List<Object> list = response.jsonPath().getList("$");
        return list.size();
    }

    public static void verifyEmptyResponse(Response response) {

        assertEquals("[]", response.asString());
    }

    // Verifies the content of the response JSON in case of a unsuccessful request
    public static void verifyErrorJsonResponse(Response response, ExpectedErrors expectedErrors) {

        assertEquals(expectedErrors.getStatus(), (response.body().path("status")));
        assertEquals(expectedErrors.getError(), (response.body().path("error")));
        assertEquals(expectedErrors.getMessage(), (response.body().path("messsage")));
    }

    // To verify headers in the response
    public static void testResponseHeadersGet(Response response) {

        assertEquals("application/json", response.getHeader("Content-Type"));
        // Test other headers like Transfer-Encoding, Keep-Alive, Connection
        // ...
    }


    public static Response sendPostReturnResponse(String targetUrl, ContentType contentType, String requestBody,
        Integer expectedStatusCode) {

        return given().contentType(contentType).
            when().body(requestBody).post(targetUrl).
            then().statusCode(expectedStatusCode).contentType(ContentType.JSON).
            log().all().and().extract().response();
    }

    public static String createRequestBody(String name) {

        return "{\"name\":\"" + name + "\"}";

    }

    public static String createIncorrectRequestBody(String name) {

        return "{\"name\":" + name + "}";

    }

    public static void verifySuccessfulPostResponse(Response response) {

        assertEquals("1", response.asString());
    }

    public static void deleteById(String targetUrl, String id, Integer expectedStatusCode) {

        given().
            when().delete(targetUrl + "/" + id).
            then().statusCode(expectedStatusCode).
            log().all();
    }

}
