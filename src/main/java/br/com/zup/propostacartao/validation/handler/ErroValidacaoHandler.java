package br.com.zup.propostacartao.validation.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.propostacartao.validation.dto.ErroValidacaoDTO;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErroValidacaoDTO erroValidacaoHandler(MethodArgumentNotValidException exception) {
		List<ObjectError> errosGlobais = exception.getBindingResult().getGlobalErrors();
		List<FieldError> camposComErro = exception.getBindingResult().getFieldErrors();
		
		return respostaComErroBuilder(errosGlobais, camposComErro);
	}
	
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(BindException.class)
//	public ErroValidacaoDTO erroValidacaoHandler(BindException exception) {
//		List<ObjectError> errosGlobais = exception.getBindingResult().getGlobalErrors();
//		List<FieldError> camposComErro = exception.getBindingResult().getFieldErrors();
//		
//		return respostaComErroBuilder(errosGlobais, camposComErro);
//	}
	
	private ErroValidacaoDTO respostaComErroBuilder(List<ObjectError> errosGlobais, List<FieldError> camposComErro) {
		ErroValidacaoDTO erro = new ErroValidacaoDTO();
		
		errosGlobais.forEach(e -> erro.adicionarErrosGlobais(mensagemDeErroBuilder(e)));
		
		camposComErro.forEach(e-> {
			String mensagemDeErro = mensagemDeErroBuilder(e);
			erro.adicionarCamposComErro(e.getField(), mensagemDeErro);
		});
		
		return erro;
	}
	
	private String mensagemDeErroBuilder(ObjectError erro) {
		return messageSource.getMessage(erro, LocaleContextHolder.getLocale());
	}
	
}
