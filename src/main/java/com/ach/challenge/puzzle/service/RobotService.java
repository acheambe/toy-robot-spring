package com.ach.challenge.puzzle.service;

import com.ach.challenge.puzzle.model.Position;

/**
 * Robot Service Interface
 * 
 * @author Alice Cheambe
 *
 */

public interface RobotService {
	
	public boolean isValidPosition(Position position);
	
	public String place(Position position); 
	
	public void move(Position position);
	
	public void rotateLeft();
	
	public void rotateRight();
	
	public String report();
	
	public Position getNextPosition();
}
