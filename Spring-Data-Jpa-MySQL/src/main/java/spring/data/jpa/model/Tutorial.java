package spring.data.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table (name = "TUTORIAL")
public class Tutorial {
	@Id
	@GeneratedValue 
private Long id ;
	@Column ( name = "title")
private String title;
	@Column  (name = "description")
private String description;
	@Column (name = "published")
private Boolean published;
}
