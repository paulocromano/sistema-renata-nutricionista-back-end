package br.com.renatanutricionista.exception.handle;

import java.time.DateTimeException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.exception.model.PadraoErro;
import br.com.renatanutricionista.exception.model.ValidationError;


@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<PadraoErro> illegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Valor inválido!", exception, request);
	}
	
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<PadraoErro> numberFormat(NumberFormatException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Erro ao converter valor para número!", exception, request);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<PadraoErro> nullPointer(NullPointerException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Valor nulo!", exception, request);
	}
	
	
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<PadraoErro> dateTime(DateTimeException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Erro ao converter Data e/ou Hora!", exception, request);
	}
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<PadraoErro> notFound(ObjectNotFoundException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.NOT_FOUND, "Objeto não encontrado!", exception, request);
	}
	
	
	@ExceptionHandler(PacienteException.class)
	public ResponseEntity<PadraoErro> paciente(PacienteException exception, HttpServletRequest request) {
		return erroPadronizado(HttpStatus.BAD_REQUEST, "Exceção da Regra de Negócio de Paciente!", exception, request);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<PadraoErro> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
		
		ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação de Campos!", 
				exception.getMessage(), request.getRequestURI());
		
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) 
			validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
	}


	private ResponseEntity<PadraoErro> erroPadronizado(HttpStatus httpStatus, String erro, Exception exception, HttpServletRequest request) {
		return ResponseEntity.status(httpStatus).body(new PadraoErro(System.currentTimeMillis(), httpStatus.value(), 
				erro, exception.getMessage(), request.getRequestURI()));
	}
}
