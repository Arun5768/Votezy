package in.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.votezy.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter,Long> {
  Boolean existsByEmail(String email);
}
