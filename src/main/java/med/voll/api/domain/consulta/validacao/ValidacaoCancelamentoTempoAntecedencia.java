package med.voll.api.domain.consulta.validacao;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

@Component
public class ValidacaoCancelamentoTempoAntecedencia implements ValidadorCancelamentoDeConsultas {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validadorCancelar(DadosCancelamentoConsulta dados) {
		var tempoRegistroDaConsulta = consultaRepository.consultaRegistrada(dados.idConsulta());
		var tempoRegistroDoCancelamento = dados.dataCancelamento();
		var diferencaEmHorasDoCancelamento = Duration.between(tempoRegistroDoCancelamento, tempoRegistroDaConsulta)
				.toHours();

		if (diferencaEmHorasDoCancelamento < 24) {
			throw new ValidacaoException("O cancelamento da consulta precisa ser com no mínimo de 24 horas de antecedência.");
		}

	}
}
