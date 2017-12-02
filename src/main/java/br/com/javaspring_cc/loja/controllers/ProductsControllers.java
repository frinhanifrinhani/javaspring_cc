package br.com.javaspring_cc.loja.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.javaspring_cc.loja.daos.ProductDAO;
import br.com.javaspring_cc.loja.models.Product;

@Controller
@Transactional
public class ProductsControllers {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/produtos/form")
	public String form() {
		return "products/form";
	}
	
	@RequestMapping("produtos")
	public String save(Product product) {
		productDAO.save(product);
		return "products/ok";
	}
	
	

}
