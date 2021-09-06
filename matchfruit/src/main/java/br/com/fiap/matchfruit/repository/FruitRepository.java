package br.com.fiap.matchfruit.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.matchfruit.model.Fruit;

public interface FruitRepository  extends JpaRepository<Fruit, Long>{
	Page<Fruit> findByNameLikeIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);
}
