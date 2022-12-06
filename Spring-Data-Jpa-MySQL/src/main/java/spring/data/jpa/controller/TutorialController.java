package spring.data.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.data.jpa.model.Tutorial;
import spring.data.jpa.service.TutorialService;

@RestController
@RequestMapping ( "/api")
public class TutorialController {

	private TutorialService tutorialService;
	@Autowired
	public TutorialController(TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	@GetMapping ("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			tutorials = tutorialService.getallTutorials();
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}	
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorial/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable ("id") Long id ){
		try {
			Tutorial tutorial = tutorialService.getTutorialById(id);
			return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);

		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorial")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
		try {
			tutorialService.createTutorial(tutorial);
			return new ResponseEntity<Tutorial>(tutorial, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorial/{id}")
	public ResponseEntity<Void> deleteTutorial(@PathVariable ("id") Long id){
		try {
			tutorialService.deleteTutorial(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch ( Exception e ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/tutorial/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable ("id") Long id , @RequestBody Tutorial tutorial){
		try {
			tutorialService.updateTutorial(id , tutorial);
			return new ResponseEntity<>(tutorial , HttpStatus.OK);
		}catch ( Exception e ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}


}

