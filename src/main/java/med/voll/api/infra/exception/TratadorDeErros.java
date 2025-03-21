package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler(EntityNotFoundException.class )
	public ResponseEntity TratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity TratarErro400(MethodArgumentNotValidException exceptionErro) {
		var erro = exceptionErro.getFieldErrors();
		return ResponseEntity.badRequest().body(erro.stream().map(tratarErroValidacao::new).toList());		
	}
	
	@ExceptionHandler(ValidacaoException.class )
	public ResponseEntity ValidaErroValidacaoException(ValidacaoException erro) {
		return ResponseEntity.badRequest().body(erro.getMessage());
	}
	
	private record tratarErroValidacao(String campo, String mensagem) {
		public tratarErroValidacao(FieldError erro){
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
	

}
