package spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.data.jpa.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
