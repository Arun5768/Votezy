package in.votezy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class ElectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @NotBlank(message = "election name must be required")
	private String electionName;
	@OneToOne
	@JoinColumn(name = "winner_id")
	private Candidate candidate;
	
	private int totalVote;
}
