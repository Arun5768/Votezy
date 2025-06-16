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

import in.votezy.entity.Voter;
import in.votezy.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
	@Autowired
	private VoterService voterService;
    
	@PostMapping("/register")
	public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter){
	  return new ResponseEntity<>(voterService.addVoter(voter), HttpStatus.CREATED);
	}
	@PutMapping("/updateVoter")
	public ResponseEntity<Voter> updateVoter(@RequestParam long id, @RequestBody Voter updateVoter){
		return new ResponseEntity<>(voterService.updateVoter(id, updateVoter),HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Voter>> getAllVoter(){
		List<Voter> voterList=voterService.getAllVoters();
		return new ResponseEntity<>(voterList,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Voter> getVoter(@PathVariable Long id){
		Voter voter=voterService.getVoterById(id);
		return new ResponseEntity<>(voter,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeVoter(@PathVariable long id){
		voterService.removeVoter(id);
		return  new ResponseEntity<String>("Voter deleted with id:"+id, HttpStatus.OK);
	}
}
