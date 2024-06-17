package org.game.service;

import org.game.model.Cell;
import org.game.model.GameConfig;
import org.game.model.jump.Jump;
import org.game.model.stay.Stay;

import java.util.List;

public class Board {
    private Cell[][] cells;

    public Board(GameConfig config) {
        initializeCells(config.getBoardSize());
        addJumps(config.getSnakes());
        addJumps(config.getLadders());
        addJumps(config.getCrocodiles());
        addStays(config.getMines());
    }

    private void addStays(List<Stay> mines) {
        for (Stay stay : mines) {
            Cell cell = getCell(stay.getPosition());
            cell.setStay(stay);
        }
    }

    private void addJumps(List<Jump> jumps) {
        for (Jump jump : jumps) {
            Cell cell = getCell(jump.getStart());
            cell.setJump(jump);
        }
    }

    private void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }

    public Cell getCell(int position) {
        int boardRow = position / cells.length;
        int boardColumn = (position % cells.length);
        return cells[boardRow][boardColumn];
    }

    public int getSize() {
        return cells.length;
    }

}
