package br.com.javaspring_cc.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.javaspring_cc.loja.models.Product;

public class ProductValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors){
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
		
		Product product = (Product) target;
		
		if(product.getPages() == 0) {
			errors.rejectValue("pages", "field.required");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

}
