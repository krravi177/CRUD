package CRUD.CRUD.TRH.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateEntryException(DuplicateEntryException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT); // HTTP 409 Conflict
	}

}
