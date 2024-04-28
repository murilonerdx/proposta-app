package com.pieropan.propostaapp.controller;

import com.pieropan.propostaapp.dto.PropostaRequestDTO;
import com.pieropan.propostaapp.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/proposta")
@RequiredArgsConstructor
public class PropostaController {
	private final PropostaService propostaService;

	@PostMapping
	public ResponseEntity<?> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) throws Exception {
		return ResponseEntity.ok().body(propostaService.criarProposta(propostaRequestDTO));
	}

}
