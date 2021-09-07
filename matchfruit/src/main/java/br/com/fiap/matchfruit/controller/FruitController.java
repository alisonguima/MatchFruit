package br.com.fiap.matchfruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.matchfruit.model.Fruit;
import br.com.fiap.matchfruit.repository.FruitRepository;

@Controller
public class FruitController {

	@Autowired
	private FruitRepository repository;
	
	@GetMapping("/fruit")
	public ModelAndView index() {
		List<Fruit> fruits = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("fruits");
		modelAndView.addObject("fruits", fruits);
		return modelAndView;
	}
	
	@RequestMapping("/fruit/new")
	public String create(Fruit fruit) {
		return "fruit-form";
	}
}
