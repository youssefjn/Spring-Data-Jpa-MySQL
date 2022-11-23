package spring.data.jpa;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import spring.data.jpa.model.Tutorial;
import spring.data.jpa.service.TutorialService;

@WebMvcTest 

public class TutorialControllerTests {

	@MockBean
	private TutorialService tutorialService;

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@Test
	void shouldCreateTutorial() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "Spring Boot @WebMvcTest", "Description", true);
		mockMvc.perform(post("/api/tutorial")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tutorial)))
				.andExpect(status()
				.isCreated())
				.andDo(print());
	}
	
	@Test
	void shouldReturnTutorial() throws Exception {
		Long id = 1L;
		Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", true);
		when(tutorialService.getTutorialById(id)).thenReturn(tutorial);
		mockMvc.perform(get("/api/tutorials/{id}", id)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(id))
		.andExpect(jsonPath("$.title").value(tutorial.getTitle()))
		.andExpect(jsonPath("$.description").value(tutorial.getDescription()))
		.andExpect(jsonPath("$.published").value(tutorial.getPublished()))
		.andDo(print());
	}
	  
	  @Test
	  void shouldReturnListOfTutorials() throws Exception {
	    List<Tutorial> tutorials = new ArrayList<>(
	        Arrays.asList(new Tutorial(1L, "Spring Boot @WebMvcTest 1", "Description 1", true),
	            new Tutorial(2L, "Spring Boot @WebMvcTest 2", "Description 2", true),
	            new Tutorial(3L, "Spring Boot @WebMvcTest 3", "Description 3", true)));

	    when(tutorialService.getallTutorials()).thenReturn(tutorials);
	    mockMvc.perform(get("/api/tutorials"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.size()").value(tutorials.size()))
	        .andDo(print());
	  }

	  
	  @Test
	  void shouldReturnNotFoundtutorials() throws Exception {
		  List<Tutorial> tutorials = new ArrayList<>();
		  when(tutorialService.getallTutorials()).thenReturn(tutorials);
		    mockMvc.perform(get("/api/tutorials"))
		        .andExpect(status().isNoContent())
		        .andDo(print());
	  }
	  @Test
	  void shouldDeleteTutorial() throws Exception {
	    long id = 1L;
	   
	    doNothing().when(tutorialService).deleteTutorial(id);
	    mockMvc.perform(delete("/api/tutorials/{id}", id))
	         .andExpect(status().isOk())
	         .andDo(print());
	  }
	  
	  @Test
	  void shouldUpdateTutorial() throws Exception {
		  long id = 1L;

		    Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", false);
		    Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);
		    when(tutorialService.getTutorialById(id)).thenReturn(tutorial);
		    when(tutorialService.updateTutorial(id, tutorial)).thenReturn(updatedtutorial);

		    mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
		        .content(objectMapper.writeValueAsString(updatedtutorial)))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.title").value(updatedtutorial.getTitle()))
		        .andExpect(jsonPath("$.description").value(updatedtutorial.getDescription()))
		        .andExpect(jsonPath("$.published").value(updatedtutorial.getPublished()))
		        .andDo(print());
		  }


}
