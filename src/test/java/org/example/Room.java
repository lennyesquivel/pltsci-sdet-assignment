package org.example;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String sizeX;
    private String sizeY;
    private List<String[]> patches;

    public Room() {

    }

    public Room(String x, String y) {
        this.sizeX = x;
        this.sizeY = y;
    }

    public String getSizeX() {
        return sizeX;
    }

    public void setSizeX(String sizeX) {
        this.sizeX = sizeX;
    }

    public String getSizeY() {
        return sizeY;
    }

    public void setSizeY(String sizeY) {
        this.sizeY = sizeY;
    }

    public List<String[]> getPatches() {
        return patches;
    }

    public void addPatch(String x, String y) {
        if (patches == null) {
            patches = new ArrayList<String[]>();
        }
        patches.add(new String[]{x,y});
    }

    public JsonArray getJsonRoomSize() {
        JsonArray roomSize = new JsonArray();
        roomSize.add(getSizeX());
        roomSize.add(getSizeY());
        return roomSize;
    }

    public JsonArray getJsonPatches() {
        JsonArray patches = new JsonArray();
        for (String[] patch : getPatches()) {
            JsonArray jsonPatch = new JsonArray();
            jsonPatch.add(patch[0]);
            jsonPatch.add(patch[1]);
            patches.add(jsonPatch);
        }
        return patches;
    }
}
