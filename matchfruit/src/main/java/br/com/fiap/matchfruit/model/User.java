package br.com.fiap.matchfruit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="TB_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_TB_USER", allocationSize = 1)
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
	@Column(name="id_user")
	public Long id;
	
	@Column(name="ds_name")
	@NotBlank(message = "O nome é obrigatório. Digite um nome.")
	public String name;
	
	@Column(name="TB_USER", unique = true)
	@Email(message = "Precisa ser um email válido")
	public String email;
	
	@Column(name="ds_password")
	@NotBlank(message = "Digite uma senha válida.")
	@JsonProperty(access = Access.WRITE_ONLY)
	public String pass;
	
	@ManyToMany
	@JoinTable(name = "TB_FAVORITES", joinColumns = @JoinColumn(name="id_user"), inverseJoinColumns = @JoinColumn(name="id_fruit"))
	private List<Fruit> fruits;
	
	public User (Long id, String name, String email, String pass) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
	}
	
	public User (String name, String email, String pass) {
		this.name = name;
		this.email = email;
		this.pass = pass;
	}
}