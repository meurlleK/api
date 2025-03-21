package med.voll.api.domain.consulta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoAgendamentoUnicoPorPacienteMesmoDia implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validador(DadosAgendamentoConsulta dados) {
			
		var inicioDia = dados.data().withHour(7);
		var finalDia = dados.data().withHour(18);
		var retornoConsultaPaciente = consultaRepository.existeConsultaJaAgendadaPaciente(dados.idPaciente(), inicioDia, finalDia);
		boolean existeConsultaJaAgendada = retornoConsultaPaciente > 0;
			
		if(existeConsultaJaAgendada) {
			throw new ValidacaoException("Só pode ser agendada uma consulta por paciente no dia.");
		}
	}

}
