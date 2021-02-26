package br.com.zup.propostacartao.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.zup.propostacartao.validation.annotation.FormatoBase64;

public class FormatoBase64Validator implements ConstraintValidator<FormatoBase64, Object> {
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String valorBase64 = (String) value;

		return Base64.isBase64(valorBase64.getBytes());
	}

}
