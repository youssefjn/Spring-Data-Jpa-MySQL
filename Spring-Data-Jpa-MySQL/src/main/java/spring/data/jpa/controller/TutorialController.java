package spring.data.jpa.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/api")
public class TutorialController {

	private TutorialService tutorialService;

	public TutorialController(TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {

		List<Tutorial> tutorials = new ArrayList<Tutorial>();
		tutorials = tutorialService.getallTutorials();
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);

	}

	@GetMapping("/tutorial/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {

		Tutorial tutorial = tutorialService.getTutorialById(id);
		return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);

	}

	@PostMapping("/tutorial")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {

		tutorialService.createTutorial(tutorial);
		return new ResponseEntity<Tutorial>(tutorial, HttpStatus.CREATED);

	}

	@DeleteMapping("/tutorial/{id}")
	public ResponseEntity<Void> deleteTutorial(@PathVariable("id") Long id) {

		tutorialService.deleteTutorial(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PutMapping("/tutorial/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorial) {

		tutorialService.updateTutorial(id, tutorial);
		return new ResponseEntity<>(tutorial, HttpStatus.OK);

	}

}
