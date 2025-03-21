package med.voll.api.domain.consulta.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidacaoMedicoInativo  implements ValidadorAgendamentoDeConsultas{

	@Autowired
	private MedicoRepository medicoRepository;

	public void validador(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null) {
			return ;
		}
		
		var retornoRepository = medicoRepository.medicoAtivo(dados.idMedico());

		boolean medicoAtivo = (retornoRepository!= null) ? retornoRepository.isAtivo() : false ;
		
		if(!medicoAtivo) {
			throw new ValidacaoException("Médico não esta ativo na Clínica.");
		}
	}

}
