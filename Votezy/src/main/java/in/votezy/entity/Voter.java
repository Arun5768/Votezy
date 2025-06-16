package in.votezy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Voter {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "name must be required")
	private String name;
	@NotBlank(message = "email is required")
	@Email(message = "invlaid Email formate")
	private String email;
	private Boolean hasVoted=false;
	@OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
	private Vote vote;
	
}
