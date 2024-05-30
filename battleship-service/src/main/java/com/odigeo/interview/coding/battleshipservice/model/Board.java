package com.odigeo.interview.coding.battleshipservice.model;

import com.odigeo.interview.coding.battleshipapi.contract.DeployShipsCommand;
import com.odigeo.interview.coding.battleshipservice.model.vessels.Vessel;

import java.util.List;

public final class Board {
    private final char[][] board; // Char A agua V vessel

    public Board() {
        board = new char[10][10];
    }

    public char[][] getBoard() {
        return board;
    }

    public void putShipsOnBoard(List<Vessel> ships, DeployShipsCommand command) {
        //Recorrer lista y poner cada barco en el board
    }
}
