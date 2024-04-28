package com.pieropan.propostaapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pieropan.propostaapp.entity.Proposta;
import com.pieropan.propostaapp.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropostaRequestDTO {
	@NotEmpty(message="Nome não pode estar null")
	private String nome;
	@NotEmpty(message="Digite um sobrenome")
	private String sobrenome;
	private String telefone;

	@CPF(message="Digite um CPF valido!")
	private String cpf;

	@Size(min=1000)
	private Double renda;
	private Double valorSolicitado;
	private int prazoPagamento;

	public Proposta toModel() {
		return new Proposta(
				valorSolicitado,
				prazoPagamento,
				new Usuario(null, nome, sobrenome, telefone, cpf, renda)
		);
	}
}
