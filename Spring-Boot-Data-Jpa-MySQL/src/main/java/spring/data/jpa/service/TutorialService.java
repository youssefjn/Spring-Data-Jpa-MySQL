package spring.data.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.data.jpa.model.Tutorial;
import spring.data.jpa.repository.TutorialRepository;
@Service
public class TutorialService {
	@Autowired
	private TutorialRepository tutorialRepository;


	public List<Tutorial> getallTutorials(){
		return tutorialRepository.findAll();	
	}

	public Tutorial createTutorial (Tutorial tutorial) {
		tutorialRepository.save(tutorial);
		return tutorial;
	}

	public Tutorial getTutorialById(Long id) {
		Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()-> 
		new IllegalStateException("Tutorial with id "+ id + " does not exist"));
		return tutorial;
	}
	
	public void deleteTutorial(Long id) {
		if ( !tutorialRepository.existsById(id)) {
			throw new IllegalStateException("id " + id +" doesn't exist");}
		tutorialRepository.deleteById(id);
	}
	@Transactional
	public Tutorial updateTutorial (Long id , Tutorial tutorial) {
		Optional<Tutorial> tutorialData = Optional.of(tutorialRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id "+ id + " does not exist")));;

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			if ( tutorial.getTitle() != null && tutorial.getTitle().length()> 0)
			_tutorial.setTitle(tutorial.getTitle());
			if (tutorial.getDescription() != null && tutorial.getDescription().length()> 0)
			_tutorial.setDescription(tutorial.getDescription());
			if ( tutorial.getPublished() != null )
			_tutorial.setPublished(tutorial.getPublished());
				return _tutorial;
				}
		return null;
				
		
	
	}

}
