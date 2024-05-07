package com.pieropan.propostaapp.scheduled;

import com.pieropan.propostaapp.repository.PropostaRepository;
import com.pieropan.propostaapp.service.NotificacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PropostaSemIntegracao {

	private final PropostaRepository propostaRepository;

	private final NotificacaoService notificacaoRabbitService;

	private final String exchange;


	public PropostaSemIntegracao(PropostaRepository propostaRepository,
								 NotificacaoService notificacaoRabbitService,
								 @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		this.propostaRepository = propostaRepository;
		this.notificacaoRabbitService = notificacaoRabbitService;
		this.exchange = exchange;
	}

	@Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
	public void buscarPropostasSemIntegracao() {
		propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
			try {
				notificacaoRabbitService.notificar(proposta.toResponseDTO(), exchange);
				propostaRepository.atualizarStatusIntegrada(proposta.getId(), true);
			} catch (RuntimeException ex) {
				log.error(ex.getMessage());
			}
		});
	}
}