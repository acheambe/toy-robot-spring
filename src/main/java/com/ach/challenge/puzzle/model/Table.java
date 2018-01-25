package com.ach.challenge.puzzle.model;

import org.springframework.stereotype.Component;

/**
 * Table Interface
 * 
 * @author Alice Cheambe
 *
 */
@Component
public interface Table {
	
	/**
	 * Checks if the given position on the table is valid
	 * @param position
	 * @return
	 */
    public boolean isValidPosition(Position position);

}