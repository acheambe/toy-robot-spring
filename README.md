# Coding Puzzle Toy Robot (Spring)

This is a spring boot application that simulates a robot
on a configurable table grid

## Description

- The application is a simulation of a toy robot moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  robot falling from the table must be prevented, however further valid movement
  commands must still be allowed.
 
The application can read in commands via a REST interface of the following form:

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y
  and facing NORTH, SOUTH, EAST or WEST.
- The origin (0,0) can be considered to be the SOUTH WEST most corner.
- MOVE will move the toy robot one unit forward in the direction it is currently
  facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot.

## Constraints
- The application must be a Spring-Boot-Application
- Input must be realized over the REST-API, take care when designing the REST-API 
- The robot that is not on the table can choose the ignore the MOVE, LEFT, RIGHT and REPORT commands
- The robot must not fall off the table during movement. This also includes the initial placement of the toy robot
- Any move that would cause the robot to fall must be ignored.
- It is not required to provide any graphical output showing the movement of the toy robot.

## Example Input and Output:
    
a)

	PLACE 0,0,NORTH
    MOVE
    REPORT

	Output: 0,1,NORTH

b)

	PLACE 0,0,NORTH
	LEFT
	REPORT
	
	Output: 0,0,WEST

c)

	PLACE 1,2,EAST
	MOVE
	MOVE
	LEFT
	MOVE
	REPORT

	Output: 3,3,NORTH
	
d)

	MOVE
	REPORT

	Output: ROBOT MISSING


## Requirements

- Java 8

- JUnit and Mockito for testing

- Maven as dependency package manager 


## Compile, Test, Run and Packaging

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn spring-boot:run`

- Packaging: `mvn package`, compiled jar (toy-robot-spring.jar) in *target/* folder

- Test Client: Postman

## Outlook
Currently the API offers a single end point for controlling the toy robot. Alternatively, it could be separated into 4 end points

- POST toyrobot/place which consumes Position (int: x, int: y, string: facing)
- POST toyrobot/move
- POST toyrobot/rotate which consumes Direction (string: left | string: right)
- GET toyrobot/report  which produces Position (int: x, int: y, string: facing)