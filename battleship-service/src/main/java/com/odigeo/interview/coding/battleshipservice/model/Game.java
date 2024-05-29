package com.odigeo.interview.coding.battleshipservice.model;


import javafx.scene.control.Cell;

import java.time.Instant;

public class Game {

    private String id;
    private String playerOneId;
    private String playerTwoId;
    private Integer playerTurn;

    private Instant createdAt;
    private Instant startedAt;
    private Instant finishedAt;
    private String winner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerOneId() {
        return playerOneId;
    }

    public void setPlayerOneId(String playerOneId) {
        this.playerOneId = playerOneId;
    }

    public String getPlayerTwoId() {
        return playerTwoId;
    }

    public void setPlayerTwoId(String playerTwoId) {
        this.playerTwoId = playerTwoId;
    }

    public Integer getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Integer playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setNextPlayerTurn() {
        setPlayerTurn((getPlayerTurn() % 2) + 1);
    }

    public boolean isPlayerTurn(String playerId) {
        if (playerId.equals(getPlayerOneId())) {
            return isPlayerTurn(1);
        } else if (playerId.equals(getPlayerTwoId())) {
            return isPlayerTurn(2);
        } else {
            throw new IllegalArgumentException(String.format("Player %s does not exist in the game.", playerId));
        }
    }

    public boolean isPlayerTurn(int playerNumber) {
        return getPlayerTurn() != null && getPlayerTurn() == playerNumber;
    }

    public boolean isFinished() {
        return getFinishedAt() != null;
    }

    public boolean playersReady() {
        return getPlayerOneId() != null
                && getPlayerTwoId() != null;
    }

    public boolean playerReady(String playerId) {
        // dummy implementation
        return true;
    }
}
