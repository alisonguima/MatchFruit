package br.com.fiap.matchfruit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.matchfruit.model.User;
import br.com.fiap.matchfruit.repository.UserRepository;


@Controller
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping("/user")
	public ModelAndView index() {
		List<User> users = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("users");
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@PostMapping("/user")
	public String save(@Valid User user, BindingResult result) {
		if (result.hasErrors()) return "user-form";
		repository.save(user);
		return "users";
	}
	
	@RequestMapping("/user/new")
	public String create(User user) {
		return "user-form";
	}
}
