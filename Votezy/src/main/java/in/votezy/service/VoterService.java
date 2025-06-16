package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.entity.Voter;
import in.votezy.exception.DuplicateResourseException;
import in.votezy.exception.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoterRepository;
@Service
public class VoterService {
	@Autowired
	private VoterRepository voterRepository;
	@Autowired
	private CandidateRepository candidateRepository;
	
	public Voter addVoter(Voter voter) {
		if(voterRepository.existsByEmail(voter.getEmail()))
			throw new DuplicateResourseException("Voter with email id:"+voter.getEmail()+" already exists");
		return voterRepository.save(voter);
	}
	
	public Voter updateVoter(Long id, Voter updateVoter) {
		Voter voter=voterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Voter with id:"+id+" not found"));
        if(updateVoter.getEmail()!=null) {
        	voter.setEmail(updateVoter.getEmail());
        }
        if(updateVoter.getName()!=null) {
        	voter.setName(updateVoter.getName());
        }
        
		return voterRepository.save(voter);
	 }
	
	
	public List<Voter> getAllVoters(){
		return voterRepository.findAll();
	}
	
	public Voter getVoterById(Long id) {
		Voter voter=voterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Voter with id:"+id+" not found"));
        return voter;
	}
	
	
	public void removeVoter(Long id) {
		Voter voter=voterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Voter with id:"+id+" not found"));
		Vote vote=voter.getVote();
		if(vote!=null) {
			Candidate candidate=vote.getCondidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
	
	

}
