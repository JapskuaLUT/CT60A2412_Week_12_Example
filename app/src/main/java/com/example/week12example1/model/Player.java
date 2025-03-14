package com.example.week12example1.model;

import java.io.Serializable;

public class Player implements Serializable, Cloneable {
    private String name;
    private int jerseyNumber;
    private String position;
    private int goals;
    private int assists;

    public Player(String name, int jerseyNumber, String position) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.goals = 0;
        this.assists = 0;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getGoals() { return goals; }
    public void setGoals(int goals) { this.goals = goals; }

    public int getAssists() { return assists; }
    public void setAssists(int assists) { this.assists = assists; }

    public int getTotalPoints() { return goals + assists; }

    @Override
    public String toString() {
        return "#" + jerseyNumber + " " + name + " (" + position + "): " +
                goals + "G, " + assists + "A";
    }

    // Shallow copy implementation
    @Override
    public Player clone() {
        try {
            return (Player) super.clone();
        } catch (CloneNotSupportedException e) {
            // This shouldn't happen since we're Cloneable
            return new Player(name, jerseyNumber, position);
        }
    }
}
