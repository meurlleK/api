package med.voll.api.domain.consulta.validacao;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoMinutosAntecedentes  implements ValidadorAgendamentoDeConsultas{

	public void validador(DadosAgendamentoConsulta dados) {

		var tempoRegistroDaConsulta = dados.data();
		var tempoRegistroNesteMomento = LocalDateTime.now();
		var diferencaEmMinutosDoRegistro = Duration.between(tempoRegistroNesteMomento, tempoRegistroDaConsulta)
				.toMinutes();

		if (diferencaEmMinutosDoRegistro < 30) {
			throw new ValidacaoException("A consulta precisa ser com no mínimo de 30 minutos de antecedência.");
		}

	}

}
