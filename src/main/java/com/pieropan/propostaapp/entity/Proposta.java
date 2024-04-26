package com.pieropan.propostaapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proposta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double valorSolicitado;
	private int prazoPagamento;
	private Boolean aprovada;
	private boolean integrada;
	private String observacao;

	@OneToOne()
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
}
