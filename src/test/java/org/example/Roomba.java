package org.example;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;

import java.util.List;

public class Roomba {

    private int posX;
    private int posY;
    private List<String> instructions;
    private int patchesCleaned;

    public Roomba() {

    }

    public Roomba(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
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
        setPosX(jsonArray.get(0).asInt());
        setPosY(jsonArray.get(1).asInt());
    }
}
