package me.rayll.mercadolivreorangetalent.tratamentodeexcessao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class TratamentoDeExcessaoController {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> tratarExcessaoDeArgumentos(MethodArgumentNotValidException ex) {

		List<String> listaDeErros = new ArrayList<>();

		for (ObjectError error : ex.getAllErrors()) {
			listaDeErros.add(error.getDefaultMessage());
		}
		return listaDeErros;
	}
	
	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)	
	public List<String> tratarSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		
		List<String> listaDeErros = new ArrayList<>();
		String message = "Não foi possível salvar, verifique a requisição.";
		listaDeErros.add(message);		

		return listaDeErros;
	}

	@ExceptionHandler(value = ResponseStatusException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public List<String> tratarExcessaoResponseStatus(ResponseStatusException ex) {

		List<String> listaDeErros = new ArrayList<>();

		listaDeErros.add(ex.getMessage());

		return listaDeErros;

	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> tratarExcessaoMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {

		List<String> listaDeErros = new ArrayList<>();

		String message = "Tipo de parâmetro inválido na requisição!";

		listaDeErros.add(ex.getMessage());
		listaDeErros.add(message);

		return listaDeErros;
	}

	@ExceptionHandler(value = IllegalStateException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public List<String> tratarIllegalStateException(IllegalStateException ex) {

		List<String> listaDeErros = new ArrayList<>();

		listaDeErros.add(ex.getMessage());

		return listaDeErros;
	}

}
