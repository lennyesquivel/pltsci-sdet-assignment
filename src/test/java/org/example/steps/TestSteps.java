package org.example.steps;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.ParseException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Room;
import org.example.Roomba;
import org.example.utils.ApiUtils;
import org.example.utils.PropertiesUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class TestSteps {

    private static Logger log = LoggerFactory.getLogger(TestSteps.class);

    Roomba roomba;
    Room room;
    Response response;

    @Given("Roomba is in position {string},{string}")
    public void roombaSetPos(String x, String y) {
        if (roomba == null) {
            roomba = new Roomba(x, y);
            log.info(String.format("Roomba created with position (%s,%s).", x, y));
        } else {
            Assert.assertEquals("Roomba is not in expected (X) position", x, roomba.getPosX());
            Assert.assertEquals("Roomba is not in expected (Y) position", y, roomba.getPosY());
        }
    }

    @And("Room size is {string} by {string}")
    public void setRoomSize(String x, String y) {
        if (room == null) {
            room = new Room(x, y);
        } else {
            room.setSizeX(x);
            room.setSizeY(y);
        }
    }

    @When("Room has dirt patches in positions")
    public void setDirtPatches(DataTable table) {
        List<Map<String, String>> data = table.asMaps();
        log.info(data.size() + " patches of dirt will be added.");
        for (Map<String, String> form : data) {
            room.addPatch(form.get("x"), form.get("y"));
        }
    }

    @And("Roomba follows the directions")
    public void setRoombaDirections(List<String> directions) {
        roomba.setInstructions(directions);
    }

    @When("Request is sent")
    public void sendRequest() {
        log.info("Sending request to " + RestAssured.baseURI + PropertiesUtils.props.getProperty("cleaning.url"));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("roomSize", room.getJsonRoomSize());
        jsonObject.add("coords", roomba.getJsonCoords());
        jsonObject.add("patches", room.getJsonPatches());
        jsonObject.add("instructions", roomba.getInstruccionsString());
        log.info("Request body: " + jsonObject);
        response = ApiUtils.post(PropertiesUtils.props.getProperty("cleaning.url"), jsonObject);
        Assert.assertNotNull(response);
    }

    @Then("Response has {string} code")
    public void checkResponseCode(String codeType) throws Exception {
        if (!codeType.equalsIgnoreCase("successful") &&
                !codeType.equalsIgnoreCase("error")) {
            log.error("Error in step syntax, available options are ['successful', 'error']");
            throw new Exception("Error in step syntax, available options are ['successful', 'error']");
        }
        //Just taking into consideration success, redirects and all other status codes will be marked as errors
        if (codeType.equalsIgnoreCase("successful")) {
            JsonObject jsonRes = JsonObject.readFrom(response.asString());
            log.info(String.format("Response code %s with body: %s", response.getStatusCode(), jsonRes.toString()));
            Assert.assertEquals(200, response.getStatusCode());
            roomba.setPatchesCleaned(jsonRes.getInt("patches", 0));
            roomba.setNewJsonCoords((JsonArray) jsonRes.get("coords"));
        } else {
            try {
                JsonObject jsonRes = JsonObject.readFrom(response.asString());
                log.info(String.format("Response code %s with body: %s", response.getStatusCode(), jsonRes.toString()));
            } catch (ParseException ignored) {
                log.info(String.format("Response code: %s", response.getStatusCode()));
            }
            Assert.assertTrue(response.getStatusCode() > 299);
        }
    }

    @And("Roomba cleaned {int} patches")
    public void checkPatchesCleaned(int patches) {
        Assert.assertEquals(patches, roomba.getPatchesCleaned());
    }

}
