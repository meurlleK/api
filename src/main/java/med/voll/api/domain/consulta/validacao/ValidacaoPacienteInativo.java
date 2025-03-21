package med.voll.api.domain.consulta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidacaoPacienteInativo  implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private PacienteRepository pacienteRepository;

	public void validador(DadosAgendamentoConsulta dados) {
		
		var retornoRepository = pacienteRepository.pacienteAtivo(dados.idPaciente());

		boolean pacienteAtivo = retornoRepository != null ? retornoRepository.isAtivo() : false;
		
		if(!pacienteAtivo) {
			throw new ValidacaoException("Paciente não esta ativo na Clínica.");
		}
	}

}
