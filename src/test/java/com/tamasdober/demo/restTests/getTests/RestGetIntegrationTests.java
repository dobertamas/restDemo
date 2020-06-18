package com.tamasdober.demo.restTests.getTests;

import com.jayway.restassured.response.Response;
import com.tamasdober.demo.helpers.ExpectedErrors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.tamasdober.demo.helpers.RestAssuredHelpers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestGetIntegrationTests {

    final String targetUrl = "http://localhost:8080/api/v1/person";

    final String invalidTargetUrlVersion = "http://localhost:8080/api/v2/person";

    final String invalidTargetUrlPath = "http://localhost:8080/api/v2/person2";

    @Test
    public void testGetAllResponse200() {

        Response response = sendGetReturnResponse(targetUrl, 200);
        testResponseHeadersGet(response);
        // Only after deleteAll
        //verifyEmptyResponse(response);
    }

    @Test
    public void testGetAllInvalidUrlVersionExpected404() {

        Response response = sendGetReturnResponse(invalidTargetUrlVersion, 404);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.NOT_FOUND);
    }

    @Test
    public void testGetAllInvalidUrlPathExpected404() {

        Response response = sendGetReturnResponse(invalidTargetUrlPath, 404);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.NOT_FOUND);
    }

    @Test
    public void testGetAllFindFirst200() {

        Response response = sendGetReturnResponse(targetUrl, 200);
        testResponseHeadersGet(response);
        final String firstIdFromResponse = getFirstIdFromResponse(response);
        System.out.println(firstIdFromResponse);
        // Only after deleteAll
        //verifyEmptyResponse(response);
    }

}
