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

import br.com.fiap.matchfruit.model.Fruit;
import br.com.fiap.matchfruit.repository.FruitRepository;

@RestController
@RequestMapping("/api/fruit")
public class ApiFruitController {

	@Autowired
	private FruitRepository repository;

	@GetMapping()
	@Cacheable("fruits")
	public Page<Fruit> index(@RequestParam(required = false) String name,
	@PageableDefault(size = 20) Pageable pageable) {

		if (name == null) {
			return repository.findAll(pageable);	
		}
		return repository.findByNameLikeIgnoreCase("%" + name + "%", pageable);
	}

	@PostMapping()
	@CacheEvict(value = "fruits", allEntries = true)
	public ResponseEntity<Fruit> create(@RequestBody Fruit fruit, UriComponentsBuilder uriBuilder) {
		repository.save(fruit);
		URI uri = uriBuilder.path("/api/fruit/{id}").buildAndExpand(fruit.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("{id}")
	public ResponseEntity<Fruit> get(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Fruit> delete(@PathVariable Long id) {
		Optional<Fruit> fruit = repository.findById(id);
		if (fruit.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Fruit> update(@Valid @RequestBody Fruit newFruit, @PathVariable Long id) {
		Optional<Fruit> optional = repository.findById(id);
		if (optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Fruit fruit = optional.get();
		fruit.setName(newFruit.getName());
		fruit.setInformations(newFruit.getInformations());
		fruit.setBenefits(newFruit.getBenefits());
		fruit.setSeasons(newFruit.getSeasons());

		repository.save(fruit);

		return ResponseEntity.ok(fruit);
	}
}