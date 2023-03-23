package org.example.utils;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

        public static Response post(String url, JsonObject body) {
                RequestSpecification request = RestAssured.given();
                request.header("Content-Type", "application/json");
                request.body(body.toString());
                return request.post(url);
        }

}
