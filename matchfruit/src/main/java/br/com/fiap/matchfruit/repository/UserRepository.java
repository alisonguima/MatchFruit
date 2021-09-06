package br.com.fiap.matchfruit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.matchfruit.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Page<User> findByNameLikeIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);
}
