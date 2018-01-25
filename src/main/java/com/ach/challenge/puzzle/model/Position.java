package com.ach.challenge.puzzle.model;

import org.springframework.stereotype.Component;

import com.ach.challenge.puzzle.exception.ToyRobotException;

/**
 *  States:
 *   placed     boolean     Has the robot been placed on the board?
 *   x          int         X-position on the board
 *   y          int         Y-position on the board
 *   facing     Direction   Enum representing direction of the robot
 *
 * 	Behaviors:
 * 
 *  - PLACE X,Y,F  
 *	- MOVE 
 *	- LEFT 
 *	- RIGHT 
 *	- REPORT
 *
 * @author Alice Cheambe
 *
 */
public class Position {
    int x;
    int y;
    Direction direction;
    
    public Position() {
    	this.x = 0;
    	this.y = 0;
    	this.direction = Direction.SOUTH;
    }
    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void change(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public Position getNextPosition() throws ToyRobotException {
        if (this.direction == null)
            throw new ToyRobotException("Invalid robot direction");

        // new position to validate
        Position newPosition = new Position(this);
        switch (this.direction) {
            case NORTH:
                newPosition.change(0, 1);
                break;
            case EAST:
                newPosition.change(1, 0);
                break;
            case SOUTH:
                newPosition.change(0, -1);
                break;
            case WEST:
                newPosition.change(-1, 0);
                break;
        }
        return newPosition;
    }
    
    @Override
    public String toString() {
    	
    	return String.format("(%s, %s, %s)", 
				getX(), 
				getY(),
				getDirection().toString());
    	
    }
}
