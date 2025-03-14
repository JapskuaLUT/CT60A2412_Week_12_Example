package com.example.week12example1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HockeyTeam implements Serializable, Cloneable {
    private String teamName;
    private int foundingYear;
    private List<Player> players;
    private int wins;
    private int losses;

    public HockeyTeam(String teamName, int foundingYear) {
        this.teamName = teamName;
        this.foundingYear = foundingYear;
        this.players = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }

    // Getters and setters
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getFoundingYear() { return foundingYear; }
    public void setFoundingYear(int foundingYear) { this.foundingYear = foundingYear; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    public int getLosses() { return losses; }
    public void setLosses(int losses) { this.losses = losses; }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public String toString() {
        return teamName + " (" + foundingYear + "): " +
                wins + "W-" + losses + "L, " +
                players.size() + " players";
    }

    // Reference copy is just the default assignment in Java
    // team2 = team1; // This creates a reference copy

    // Shallow copy implementation
    @Override
    public HockeyTeam clone() {
        try {
            HockeyTeam cloned = (HockeyTeam) super.clone();
            // Create a new ArrayList but with the same Player objects
            cloned.players = new ArrayList<>(this.players);
            return cloned;
        } catch (CloneNotSupportedException e) {
            // This shouldn't happen since we're Cloneable
            return null;
        }
    }

    // Deep copy implementation
    public HockeyTeam deepCopy() {
        HockeyTeam copy = new HockeyTeam(this.teamName, this.foundingYear);
        copy.wins = this.wins;
        copy.losses = this.losses;

        // Deep copy of players list
        for (Player player : this.players) {
            copy.players.add(player.clone());
        }

        return copy;
    }
}
