package br.com.zup.propostacartao.validation.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.propostacartao.validation.annotation.ValorUnico;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

	private String campo;
	private Class<?> dominio;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void initialize(ValorUnico constraintAnnotation) {
		campo = constraintAnnotation.campo();
		dominio = constraintAnnotation.dominio();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String queryString = "SELECT 1 FROM " + dominio.getSimpleName() + " WHERE " + campo + "=:valor";
		
		Query query = entityManager.createQuery(queryString);
		query.setParameter("valor", value);
		
		List<?> listaDeRegistros = query.getResultList();
		
		return listaDeRegistros.isEmpty();
	}

}
