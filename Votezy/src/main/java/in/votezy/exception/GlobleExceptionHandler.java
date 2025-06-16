package in.votezy.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobleExceptionHandler {
  
	@ExceptionHandler(DuplicateResourseException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorResponse handleDuplicateResourceException(DuplicateResourseException exception) {
		ErrorResponse response=new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
		return response;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
		ErrorResponse response=new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( VoteNotAllowedException.class)
	public ResponseEntity<ErrorResponse> handleVoteNoteAllowedException(VoteNotAllowedException exception) {
		ErrorResponse response=new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		BindingResult bindingResult=exception.getBindingResult();
		Map<String,String> response=new HashMap<>();
		List<FieldError> errorList=bindingResult.getFieldErrors();
		
		for(FieldError fieldError:errorList) {
			response.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);	
	}
}
