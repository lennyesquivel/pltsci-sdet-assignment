package org.example.models;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;

import java.util.List;

public class Roomba {

    private String posX;
    private String posY;
    private List<String> instructions;
    private int patchesCleaned;

    public Roomba() {

    }

    public Roomba(String x, String y) {
        this.posX = x;
        this.posY = y;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public int getPatchesCleaned() {
        return patchesCleaned;
    }

    public void setPatchesCleaned(int patchesCleaned) {
        this.patchesCleaned = patchesCleaned;
    }

    public JsonArray getJsonCoords() {
        JsonArray coords = new JsonArray();
        coords.add(getPosX());
        coords.add(getPosY());
        return coords;
    }

    public String getInstruccionsString() {
        String instructions = "";
        for (String ins : getInstructions()) {
            instructions += ins;
        }
        return instructions;
    }

    public void setNewJsonCoords(JsonArray jsonArray) {
        setPosX(String.valueOf(jsonArray.get(0).asInt()));
        setPosY(String.valueOf(jsonArray.get(1).asInt()));
    }
}
