package org.example.steps;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

    @Given("Roomba is in position {int},{int}")
    public void roombaSetPos(int x, int y) {
        if (roomba == null) {
            roomba = new Roomba(x, y);
            log.info(String.format("Roomba created with position (%s,%s).", x, y));
        } else {
            Assert.assertEquals("Roomba is not in expected (X) position", x, roomba.getPosX());
            Assert.assertEquals("Roomba is not in expected (Y) position", y, roomba.getPosY());
        }
    }

    @And("Room size is {int} by {int}")
    public void setRoomSize(int x, int y) {
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
            int x = Integer.parseInt(form.get("x"));
            int y = Integer.parseInt(form.get("y"));
            room.addPatch(x, y);
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
        Response res = ApiUtils.post(PropertiesUtils.props.getProperty("cleaning.url"), jsonObject);
        Assert.assertEquals(200, res.getStatusCode());
        JsonObject jsonRes = JsonObject.readFrom(res.asString());
        log.info(String.format("Response code %s with body: %s", res.getStatusCode(), jsonRes.toString()));
        roomba.setPatchesCleaned(jsonRes.getInt("patches", 0));
        roomba.setNewJsonCoords((JsonArray) jsonRes.get("coords"));
    }

    @And("Roomba cleaned {int} patches")
    public void checkPatchesCleaned(int patches) {
        Assert.assertEquals(patches, roomba.getPatchesCleaned());
    }

}
