package com.pieropan.propostaapp.service;

import com.pieropan.propostaapp.dto.PropostaRequestDTO;
import com.pieropan.propostaapp.dto.PropostaResponseDTO;
import com.pieropan.propostaapp.entity.Proposta;
import com.pieropan.propostaapp.entity.Usuario;
import com.pieropan.propostaapp.repository.PropostaRepository;
import com.pieropan.propostaapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropostaService {
	private final PropostaRepository propostaRepository;
	private final UsuarioRepository usuarioRepository;

	public PropostaResponseDTO criarProposta(PropostaRequestDTO prd) throws Exception {
		Proposta proposta = prd.toModel();
		Optional<Usuario> usuario = usuarioRepository.findByCpf(proposta.getUsuario().getCpf());

		if(usuario.isPresent() && usuario.get().getProposta().getAprovada()){
			throw new Exception("Esse CPF já tem uma proposta");
		}

		return propostaRepository.save(proposta).toResponseDTO();
	}
}
