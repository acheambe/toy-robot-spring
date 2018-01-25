package com.ach.challenge.puzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ach.challenge.puzzle.exception.ToyRobotException;
import com.ach.challenge.puzzle.model.Command;
import com.ach.challenge.puzzle.model.Direction;
import com.ach.challenge.puzzle.model.Position;
import com.ach.challenge.puzzle.resource.CommandResource;
import com.ach.challenge.puzzle.service.RobotService;

/**
 * Rest end point for toy robot resource with exposed URL end-points
 * 
 * @author Alice Cheambe
 *
 */
@RestController
@RequestMapping("/toyrobot")
public class ToyRobotController {
	
	@Autowired
	private RobotService toyRobotService;


	@RequestMapping(value = "command" , method = RequestMethod.POST)
	public ResponseEntity<String> commandToyRobot(@RequestBody CommandResource commandResource) throws ToyRobotException {
		
		String[] args = commandResource.getCommand().split(" ");
		Command command;
		try {
			command = Command.valueOf(args[0]);
		} catch (IllegalArgumentException e) {
			throw new ToyRobotException("Invalid command");
		}
		if (command == Command.PLACE && args.length < 2) {
			throw new ToyRobotException("Invalid command");
		}

		String[] params;
		int x = 0;
		int y = 0;
		Direction commandDirection = null;
		if (command == Command.PLACE) {
			params = args[1].split(",");			
			try {
				x = Integer.parseInt(params[0]);
				y = Integer.parseInt(params[1]);
				commandDirection = Direction.valueOf(params[2]);
			} catch (Exception e) {				
				throw new ToyRobotException("Invalid command");
			}
		}

		switch (command) {
		case PLACE:
			Position position = new Position(x, y, commandDirection);
			if (!toyRobotService.isValidPosition(position))
				return new ResponseEntity<>("Invalid position", HttpStatus.BAD_REQUEST);
			toyRobotService.place(position);
			return new ResponseEntity<>("", HttpStatus.OK);                
		case MOVE:
			try {
				Position newPosition = toyRobotService.getNextPosition();
				if(newPosition == null)
					return new ResponseEntity<String>("", HttpStatus.OK);
				if(!toyRobotService.isValidPosition(newPosition))
					return new ResponseEntity<String>("Invalid position", HttpStatus.BAD_REQUEST);
				else {
					toyRobotService.move(newPosition);
					return new ResponseEntity<String>("", HttpStatus.OK);
				}
					
			} catch (Exception e) {
				return new ResponseEntity<String>("ROBOT MISSING", HttpStatus.BAD_REQUEST);
			}                   	
		case LEFT:                
			toyRobotService.rotateLeft();
			return new ResponseEntity<String>("", HttpStatus.OK);

		case RIGHT:                
			toyRobotService.rotateRight();
			return new ResponseEntity<String>("", HttpStatus.OK);

		case REPORT:
			return new ResponseEntity<String>(toyRobotService.report(), HttpStatus.OK);
		default:			
			return new ResponseEntity<String>("Invalid command", HttpStatus.BAD_REQUEST);		
		}
	}
}
