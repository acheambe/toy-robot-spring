package com.ach.challenge.puzzle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ach.challenge.puzzle.exception.ToyRobotException;
import com.ach.challenge.puzzle.model.Command;
import com.ach.challenge.puzzle.model.Direction;
import com.ach.challenge.puzzle.model.Position;
import com.ach.challenge.puzzle.model.SquareTable;
import com.ach.challenge.puzzle.model.ToyRobot;

/**
 * Implements the Robot Service Interface
 * 
 * @author Alice Cheambe
 *
 */
@Service
public class ToyRobotService implements RobotService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToyRobotService.class);


	@Autowired
	private SquareTable squareTable;
	@Autowired
	private ToyRobot robot;

	@Override
	public boolean isValidPosition(Position position) {
		if(robot.isPlaced())
			return squareTable.isValidPosition(position);
		else
			return true;
	}

	@Override
	public String place(Position position) {
		robot.setPosition(position);
		return String.valueOf(robot.getPosition().toString()); 
	}

	@Override
	public void move(Position position) {
		robot.move(position);
	}

	@Override
	public void rotateLeft() {
		robot.rotateLeft();
	}

	@Override
	public void rotateRight() {
		robot.rotateRight();
	}

	@Override
	public String report() {
		if(robot == null || robot.getPosition() == null || !robot.isPlaced())
			return "ROBOT MISSING";
		return String.valueOf(robot.getPosition());
	}

	@Override
	public Position getNextPosition() {
		if(robot.isPlaced())
		{
			try {
				return robot.getPosition().getNextPosition();
			} catch (ToyRobotException e) {
				LOGGER.debug("Invalide next position");
				return null;
			}
		}
		return null;

	}

}
