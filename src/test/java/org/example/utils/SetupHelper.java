package org.example.utils;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetupHelper {

    private static Logger log = LoggerFactory.getLogger(SetupHelper.class);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = PropertiesUtils.props.getProperty("base.uri");
        log.info("Base URI: " + RestAssured.baseURI);
    }

}
