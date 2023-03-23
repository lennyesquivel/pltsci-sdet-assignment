package org.example;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private int sizeX;
    private int sizeY;
    private List<Integer[]> patches;

    public Room() {

    }

    public Room(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public List<Integer[]> getPatches() {
        return patches;
    }

    public void addPatch(int x, int y) {
        if (patches == null) {
            patches = new ArrayList<Integer[]>();
        }
        patches.add(new Integer[]{x,y});
    }

    public JsonArray getJsonRoomSize() {
        JsonArray roomSize = new JsonArray();
        roomSize.add(getSizeX());
        roomSize.add(getSizeY());
        return roomSize;
    }

    public JsonArray getJsonPatches() {
        JsonArray patches = new JsonArray();
        for (Integer[] patch : getPatches()) {
            JsonArray jsonPatch = new JsonArray();
            jsonPatch.add(patch[0]);
            jsonPatch.add(patch[1]);
            patches.add(jsonPatch);
        }
        return patches;
    }
}
