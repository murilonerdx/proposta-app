package com.pieropan.propostaapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PropostaResponseDTO {
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String cpf;
	private Double renda;
	private String valorSolicitadoFmt;
	private int prazoPagamento;
	private Boolean aprovada;
}
