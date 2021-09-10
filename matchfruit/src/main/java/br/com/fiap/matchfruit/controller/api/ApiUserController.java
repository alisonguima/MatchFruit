package br.com.fiap.matchfruit.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.matchfruit.model.User;
import br.com.fiap.matchfruit.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired
	private UserRepository repository;

	@GetMapping()
	@Cacheable("users")
	public Page<User> index(@RequestParam(required = false) String name,
			@PageableDefault(size = 20) Pageable pageable) {

		if (name == null) {
			return repository.findAll(pageable);
		}
		return repository.findByNameLikeIgnoreCase("%" + name + "%", pageable);
	}

	@PostMapping()
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
		repository.save(user);
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<User> update(@Valid @RequestBody User newUser, @PathVariable Long id) {
		Optional<User> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = optional.get();
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		user.setPass(newUser.getPass());

		repository.save(user);

		return ResponseEntity.ok(user);
	}

	@PutMapping("/{userId}/fruit/{fruitId}")
	public ResponseEntity<User> addFruit(@PathVariable Long userId, @PathVariable Long fruitId) {
		Optional<User> optUser = userRepo.findById(userId);
		Optional<Fruit> optFruit = fruitRepo.findById(fruitId);
		
		if (optUser.isEmpty() || optFruit.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		User user = optUser.get();
		Fruit fruit = optFruit.get();
		List<Fruit> favorites = user.getFavorites();
		favorites.add(fruit);
		user.setFavorites(favorites);
		
		System.out.println("Lista de favoritos:");
		for (Fruit item : user.getFavorites()) {
			System.out.println(item.getName());
			System.out.println(item.getInformations());
			System.out.println(item.getBenefits());
		}
		
		userRepo.save(user);
		
		return ResponseEntity.ok(user);
	}
}
