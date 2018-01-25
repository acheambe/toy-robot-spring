package com.ach.challenge.puzzle.controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ach.challenge.puzzle.resource.CommandResource;
import com.ach.challenge.puzzle.service.RobotService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ToyRobotController.class, secure = false)
public class ToyRobotControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RobotService robotService;

	@Test
	public void shouldReturnEmptyStringOnPlace() throws Exception {
		String jsonString = "{\"action\": \"PLACE 0,0,NORTH\"}";;
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();
		assertEquals("", result.getResponse().getContentAsString());
	}

	@Test
	public void shouldReturnEmptyStringOnLeft() throws Exception {
		String jsonString = "{\"action\": \"LEFT\"}";;
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();
		assertEquals("", result.getResponse().getContentAsString());
	}

	@Test
	public void shouldReturnEmptyStringOnRight() throws Exception {
		String jsonString = "{\"action\": \"RIGHT\"}";;
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();
		assertEquals("", result.getResponse().getContentAsString());
	}

	@Test
	public void shouldPlace() throws Exception {

		String jsonString = "{\"action\": \"PLACE 0,0,NORTH\"}";

		MvcResult result =  mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful())
				.andReturn();
		String body = result.getResponse().getContentAsString();
		assertEquals("(0, 0, NORTH)", robotService.report());
	}

	@Test
	public void shouldPlaceAndMove() throws Exception {

		String jsonString = "{\"action\": \"PLACE 0,0,NORTH\"}";
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful())
				.andReturn();

		jsonString = "{\"action\": \"MOVE\"}";
		result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		//		jsonString = "{\"action\": \"REPORT\"}"new CommandResource("REPORT");
		//		result = mockMvc.perform(
		//				post("/toyrobot/command")
		//				.contentType(MediaType.APPLICATION_JSON)
		//				.content(asJsonString(command)))
		//		.andExpect(status().is2xxSuccessful()).andReturn();

		assertEquals("(0, 1, NORTH)",  robotService.report());
	}

	@Test
	public void shouldPlaceAndRotate() throws Exception {

		String jsonString = "{\"action\": \"PLACE 0,0,NORTH\"}";
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		jsonString = "{\"action\": \"LEFT\"}";
		result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		assertEquals("(0, 0, WEST)", robotService.report());
	}

	@Test
	public void shouldPlaceAndReplace() throws Exception {

		String jsonString = "{\"action\": \"PLACE 0,0,NORTH\"}";
		MvcResult result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		jsonString = "{\"action\": \"MOVE\"}";

		result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		jsonString = "{\"action\": \"PLACE 3,4,SOUTH\"}";
		result = mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(status().is2xxSuccessful()).andReturn();

		assertEquals("(3, 4, SOUTH)", robotService.report());
	}

	@Test
	public void shouldMoveAndRotate() throws Exception {

		String jsonString = "{\"action\": \"PLACE 1,2,EAST\"}";
		mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		.andExpect(status().is2xxSuccessful());

		jsonString = "{\"action\": \"MOVE\"}";
		mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		.andExpect(status().is2xxSuccessful());

		mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		.andExpect(status().is2xxSuccessful());

		jsonString = "{\"action\": \"LEFT\"}";

		mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		.andExpect(status().is2xxSuccessful());

		jsonString = "{\"action\": \"MOVE\"}";
		mockMvc.perform(
				post("/toyrobot/command")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
		.andExpect(status().is2xxSuccessful());

		assertEquals("(3, 3, NORTH)", robotService.report());
	}
}
