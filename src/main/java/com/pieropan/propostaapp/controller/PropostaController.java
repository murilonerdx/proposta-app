package com.pieropan.propostaapp.controller;

import com.pieropan.propostaapp.dto.PropostaRequestDTO;
import com.pieropan.propostaapp.dto.PropostaResponseDTO;
import com.pieropan.propostaapp.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/proposta")
@RequestMapping("/proposta") //Tive que mudar porque o cara fez o front apenas com /proposta
@RequiredArgsConstructor
public class PropostaController {
	private final PropostaService propostaService;

	@PostMapping
	public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) throws Exception {
		PropostaResponseDTO propostaResponseDTO = propostaService.criarProposta(propostaRequestDTO);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/{id}")
						.buildAndExpand(propostaResponseDTO.getId())
						.toUri()
				)
				.body(propostaResponseDTO);
	}


	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponseDTO> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(propostaService.obterPorId(id));
	}

	@GetMapping()
	public ResponseEntity<List<PropostaResponseDTO>> obterTodasPropostas() {
		return ResponseEntity.ok().body(propostaService.obterTodasPropostas());
	}

}
