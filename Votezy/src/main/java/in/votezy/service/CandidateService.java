package in.votezy.service;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.exception.DuplicateResourseException;
import in.votezy.exception.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;

@Service
public class CandidateService {
	@Autowired
	private CandidateRepository candidateRepository;
	
	public Candidate register(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	public Candidate update(Long id, Candidate updateCandidate) {
		Candidate candidate=candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id:"+id+" not found"));
		if(updateCandidate.getName()!=null) {
			candidate.setName(updateCandidate.getName());
		}
		if(updateCandidate.getParty()!=null) {
			candidate.setParty(updateCandidate.getParty());
		}
		return candidateRepository.save(candidate);
	}
	
	public Candidate getCandidate(long id) {
		Candidate candidate=candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id:"+id+" not found"));
		return candidate;
	}
	
	public List<Candidate> getAllCandidates(){
		return candidateRepository.findAll();
	}
	
	public void remove(long id) {
		Candidate candidate=candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Candidate with id:"+id+" not found"));
		List<Vote> votes=candidate.getVotes();
		for(Vote vote:votes) {
			vote.setCondidate(null);
		}
	votes.clear();
	candidateRepository.delete(candidate);
	}

}
