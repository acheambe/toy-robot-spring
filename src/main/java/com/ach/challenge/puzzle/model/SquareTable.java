package com.ach.challenge.puzzle.model;

import org.springframework.stereotype.Component;
/**
 * Implement class of the Table interface with equal sides (squared)
 * 
 * @author Alice Cheambe
 *
 */
@Component
public class SquareTable implements Table {

    int rows;
    int columns;

    public SquareTable() {
    	this.rows = 5;
    	this.columns = 5;
    }
    public SquareTable(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public boolean isValidPosition(Position position) {
        return !(
                position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0
        );
    }
}
