package med.voll.api.domain.consulta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoAgendamentoDuplicadoMedico implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private ConsultaRepository consultaRepository;

	public void validador(DadosAgendamentoConsulta dados) {
		
		var retornoConsultaMedico = consultaRepository.existeConsultaDuplicadaMedico(dados.idMedico(),dados.data());
		boolean existeConsultaDuplicada = retornoConsultaMedico > 0 ;
		
		if(existeConsultaDuplicada) {
			throw new ValidacaoException("Médico já ocupado neste horário e data.");
		}
	}

}
