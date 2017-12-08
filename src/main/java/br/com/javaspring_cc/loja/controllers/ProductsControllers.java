package br.com.javaspring_cc.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.javaspring_cc.loja.daos.ProductDAO;
import br.com.javaspring_cc.loja.models.Price.BookType;
import br.com.javaspring_cc.loja.models.Product;
import br.com.javaspring_cc.loja.validation.ProductValidator;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsControllers {	
	
	@Autowired
	private ProductDAO productDAO;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("/products/form");
		modelAndView.addObject("types",BookType.values());
		return modelAndView;
	}
	
	//save com redirecionamento
//	@RequestMapping(method=RequestMethod.POST)
//	public String save(Product product) {
//		productDAO.save(product);
//		return "redirect:produtos";
//	}
	
	//save com redirecionamento e paramentro atraves do addObject
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView save(Product product) {
//		productDAO.save(product);
//		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
//		modelAndView.addObject("sucesso","Produto cadastro com sucesso");
//		
//		return modelAndView;
//	}
	
//	//save com redirecionamento e fleshAttribute
//	@RequestMapping(method=RequestMethod.POST)
//	public String save(@Valid Product product, BindingResult bindingResult ,RedirectAttributes redirectAttributes) {
//		
//		if(bindingResult.hasErrors()) {
//			return "produtos/form";
//		}
//		
//		productDAO.save(product);
//		redirectAttributes.addFlashAttribute("sucesso","Produto Cadastrado com sucesso");
//		return "redirect:produtos";
//	}
	
	//save com redirecionamento e fleshAttribute, valiação
	@RequestMapping(method=RequestMethod.POST) //,name="saveProduct"
	public ModelAndView save(@Valid Product product, BindingResult bindingResult ,RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return form();
		}
		
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso","Produto Cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products",productDAO.list());
		
		return modelAndView;
	}
	
	

}
