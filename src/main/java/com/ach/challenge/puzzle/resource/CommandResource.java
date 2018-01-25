package com.ach.challenge.puzzle.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON representation object of Command
 * 
 * @author Alice Cheambe
 *
 */
public class CommandResource {
	
	String action;
	
	public CommandResource(@JsonProperty("action") String command) {
		this.action = command;
	}

	public String getCommand() {
		return action;
	}
	

}
