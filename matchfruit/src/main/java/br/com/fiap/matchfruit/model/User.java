package br.com.fiap.matchfruit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name="ds_email", unique = true)
	@Email(message = "Precisa ser um email válido")
	public String email;
	
	@Column(name="ds_password")
	@NotBlank(message = "Digite uma senha válida.")
	@JsonProperty(access = Access.WRITE_ONLY)
	public String pass;

	@ManyToMany
	@JoinTable(
			name = "tb_favorites", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "fruit_id"))
	private List<Fruit> favorites;

}
