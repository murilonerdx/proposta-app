package com.pieropan.propostaapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pieropan.propostaapp.dto.PropostaResponseDTO;
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

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Proposta(Double valorSolicitado, int prazoPagamento, Usuario usuario) {
		this.valorSolicitado = valorSolicitado;
		this.prazoPagamento = prazoPagamento;
		this.usuario = usuario;
	}

	@JsonIgnore
	public PropostaResponseDTO toResponseDTO(){
		return new PropostaResponseDTO(
				this.id,
				this.getUsuario().getNome(),
				this.getUsuario().getSobrenome(),
				this.getUsuario().getTelefone(),
				this.getUsuario().getCpf(),
				this.getUsuario().getRenda(),
				this.getValorSolicitado()
		);
	}
}
