package com.pieropan.propostaapp.service;

import com.pieropan.propostaapp.config.handler.BusinessException;
import com.pieropan.propostaapp.dto.PropostaRequestDTO;
import com.pieropan.propostaapp.dto.PropostaResponseDTO;
import com.pieropan.propostaapp.entity.Proposta;
import com.pieropan.propostaapp.entity.Usuario;
import com.pieropan.propostaapp.repository.PropostaRepository;
import com.pieropan.propostaapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropostaService {
	private final PropostaRepository propostaRepository;
	private final UsuarioRepository usuarioRepository;
	private final NotificacaoService notificacaoService;

	public PropostaResponseDTO criarProposta(PropostaRequestDTO prd) throws Exception {
		Proposta proposta = prd.toModel();
		Optional<Usuario> usuario = usuarioRepository.findByCpf(proposta.getUsuario().getCpf());

		if(usuario.isPresent() && usuario.get().getProposta().getAprovada()){
			throw new Exception("Esse CPF já tem uma proposta");
		}

		try{
			PropostaResponseDTO responseDTO = propostaRepository.save(proposta).toResponseDTO();

			notificacaoService.notificar(responseDTO, "proposta-pendente.ex");
			return responseDTO;
		}catch(Exception e){
			return null;
		}

	}

	public PropostaResponseDTO obterPorId(Long id) {
		return propostaRepository.findById(id).orElseThrow(() -> new BusinessException("Não foi possivel encontrar a proposta com id " + id)).toResponseDTO();
	}

	public List<PropostaResponseDTO> obterTodasPropostas() {
		return propostaRepository.findAll().stream().map(Proposta::toResponseDTO).collect(Collectors.toList());
	}
}
