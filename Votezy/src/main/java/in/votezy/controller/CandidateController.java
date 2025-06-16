package in.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.votezy.entity.Candidate;
import in.votezy.service.CandidateService;
import jakarta.validation.Valid;
@RequestMapping("/api/candidate")
@RestController
@CrossOrigin
public class CandidateController {
	@Autowired
	private CandidateService candidateService;
	
	@PostMapping("/add")
	public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
		Candidate candidate2=candidateService.register(candidate);
		return new ResponseEntity<Candidate>(candidate2,HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Candidate>> getAllCandidate(){
		return new ResponseEntity<List<Candidate>>(candidateService.getAllCandidates(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate>  getCandidate(@PathVariable long id){
		return new ResponseEntity<Candidate>(candidateService.getCandidate(id),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Candidate> updateCandidate(@RequestParam long id,@RequestBody Candidate updateCandidate){
		return new ResponseEntity<Candidate>(candidateService.update(id,updateCandidate), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable long id){
		candidateService.remove(id);
		return new ResponseEntity<>("Candidate delete successfully with id:"+id, HttpStatus.OK);
	}
}
