package com.pieropan.propostaapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String telefone;
	private Double renda;

	@OneToOne(mappedBy = "usuario")
	@JoinColumn(name="proposta_id")
	private Proposta proposta;

	public Usuario(Long id, String nome, String sobrenome, String cpf, String telefone, Double renda) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.renda = renda;
	}


}
