package in.votezy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "name must be required")
	private String name;
	@NotBlank(message = "party must be required")
	private String party;
	private int voteCount=0;
	@JsonIgnore
	@OneToMany(mappedBy = "condidate",cascade = CascadeType.ALL )
	private List<Vote> votes;

}
