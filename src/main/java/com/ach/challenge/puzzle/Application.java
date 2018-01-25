package com.ach.challenge.puzzle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/***
 * Coding puzzle Toy Robot 
 * 
 * Code problem details:  
 * 		- The application is a simulation of a toy robot moving on a square table top, of dimensions 5 x 5 units.  
 * 		- There are no other obstructions on the table surface.  
 * 		- The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. 
 * 
 * Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.
 * 
 * Create an application that can read in commands of the following form: 
 * 		- PLACE X,Y,F  
 *		- MOVE 
 *		- LEFT 
 *		- RIGHT 
 *		- REPORT 
 * 
 * PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. 
 * The origin (0,0) can be considered to be the SOUTH WEST most corner.  
 * 
 * MOVE will move the toy robot one unit forward in the direction it is currently facing.  
 * 
 * LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.  
 * 
 * REPORT will announce the X,Y and F of the toy robot. 
 * 
 * @author Alice Cheambe
 *
 */
@Configuration
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class Application 
{	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main( String[] args )
	{
		LOGGER.info("Starting the application");
		SpringApplication.run(Application.class, args);
	}
}
