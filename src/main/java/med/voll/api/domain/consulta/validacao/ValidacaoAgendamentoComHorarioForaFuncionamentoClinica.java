package med.voll.api.domain.consulta.validacao;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoAgendamentoComHorarioForaFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{
	
	public void validador(DadosAgendamentoConsulta dados) {
		var dataConsulta = dados.data();
		var horarioAntesDoExpediente = dataConsulta.getHour() < 7;
		var horarioDepoisDoExpediente = dataConsulta.getHour() > 18 ; 
		var isSunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
				
		if(horarioAntesDoExpediente || horarioDepoisDoExpediente || isSunday) {
			throw new ValidacaoException("Não há expediente para a data e horario informado");
		}
				
	}

}
