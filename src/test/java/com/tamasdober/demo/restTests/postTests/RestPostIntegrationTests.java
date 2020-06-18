package com.tamasdober.demo.restTests.postTests;


import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.tamasdober.demo.helpers.ExpectedErrors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.tamasdober.demo.helpers.RestAssuredHelpers.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class RestPostIntegrationTests {

    final String targetUrl = "http://localhost:8080/api/v1/person";

    @Test
    public void tesValidPost200() {

        String name = "Janet";
        String body = createRequestBody(name);

        final Response response = sendPostReturnResponse(targetUrl, ContentType.JSON, body, 200);
        testResponseHeadersGet(response);
        verifySuccessfulPostResponse(response);
    }

    @Test
    public void tesInvalidContentTypePost415() {

        String name = "Janet";
        String body = createRequestBody(name);

        final Response response = sendPostReturnResponse(targetUrl, ContentType.TEXT, body, 415);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void tesInvalidRequestBodyPost400() {

        String name = "Janet";
        String body = createIncorrectRequestBody(name);

        final Response response = sendPostReturnResponse(targetUrl, ContentType.JSON, body, 400);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.BAD_REQUEST);
    }

    @Test
    public void testEmptyNamePost200() {

        String name = "";
        String body = createRequestBody(name);

        final Response response = sendPostReturnResponse(targetUrl, ContentType.JSON, body, 200);
        testResponseHeadersGet(response);
        verifySuccessfulPostResponse(response);
    }

    @Test
    public void testNullNamePost200() {

        String name = null;
        String body = createRequestBody(name);

        final Response response = sendPostReturnResponse(targetUrl, ContentType.JSON, body, 200);
        testResponseHeadersGet(response);
        verifySuccessfulPostResponse(response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullBodyPost200() {

        String body = null;

        final Response response = sendPostReturnResponse(targetUrl, ContentType.JSON, body, 200);
        testResponseHeadersGet(response);
        verifySuccessfulPostResponse(response);
    }

}
