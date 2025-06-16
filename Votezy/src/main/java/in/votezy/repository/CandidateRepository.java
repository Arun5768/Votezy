package in.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.votezy.entity.Candidate;

public interface CandidateRepository  extends JpaRepository<Candidate, Long>{
//List<Candidate> findByAllOrOrderByVoteCountDesc();
	Boolean existsByName(String name);
}
