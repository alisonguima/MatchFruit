package br.com.fiap.matchfruit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_FRUIT")
@SequenceGenerator(name = "fruit", sequenceName = "SQ_TB_FRUIT", allocationSize = 1)
public class Fruit {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fruit")
	@Column(name="id_fruit")
	private Long id;
	
	@Column(name="ds_name", unique = true)
	@NotBlank(message = "O nome é obrigatório. Digite um nome.")
	@Size(max = 60, message = "O nome não pode ter mais de 60 caractéres.")
	private String name;
	
	@Column(name="ds_information")
	@NotBlank(message = "As informações são obrigatórias. Digite alguma informação.")
	@Size(max = 500, message = "As informações não pode ter mais de 500 caractéres.")
	private String informations;
	
	@Column(name="ds_benefits")
	@NotBlank(message = "Os benefícios são obrigatórios. Digite algum benefício.")
	@Size(max = 500, message = "Os benefícios não pode ter mais de 500 caractéres.")
	private String benefits;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_seasons")
	private Seasons seasons;
	
	@ManyToMany(mappedBy = "fruits")
	private List<User> users;
	
	public Fruit(Long id, String name, String informations, String benefits, Seasons seasons) {
		this.id = id;
		this.name = name;
		this.informations = informations;
		this.benefits = benefits;
		this.seasons = seasons;
	}
	
	public Fruit(String name, String informations, String benefits, Seasons seasons) {
		this.name = name;
		this.informations = informations;
		this.benefits = benefits;
		this.seasons = seasons;
	}
}
