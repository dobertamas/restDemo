package com.tamasdober.demo.restTests.deleteTests;


import com.jayway.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.tamasdober.demo.helpers.RestAssuredHelpers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestDeleteIntegrationTests {

    final String targetUrl = "http://localhost:8080/api/v1/person";

    // Currently you have to run some Post tests beforehand so that the DB would not be empty
    @Test
    public void testDeletingById200() {

        Response response = sendGetAllReturnResponse(targetUrl, 200);
        // Getting the number of items in the database
        final Integer allIdsListSizeFromGetAllResponse = getAllIdsListSizeFromGetAllResponse(response);
        System.out.println(allIdsListSizeFromGetAllResponse);

        // Getting the actual names
        final String[] idsFromGetAllResponse = getAllIdsFromGetAllResponse(response);

        // Deleting the first item
        deleteById(targetUrl, idsFromGetAllResponse[0], 200);

        Response secondResponse = sendGetAllReturnResponse(targetUrl, 200);
        // Now getting number of items in the database again
        final Integer allIdsListSizeFromSecondResponse = getAllIdsListSizeFromGetAllResponse(secondResponse);
        System.out.println(allIdsListSizeFromGetAllResponse);
        // The number should have been decreased by one
        assertThat(allIdsListSizeFromSecondResponse, equalTo(allIdsListSizeFromGetAllResponse - 1));

    }

}
