package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacao.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.consulta.validacao.ValidadorCancelamentoDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendamentoConsulta {
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoDeConsultas> validadores;
	
	
	@Autowired
	private List<ValidadorCancelamentoDeConsultas> validadoresCancelamento;
	
	
	public DadosDetalhamentoConsulta agendar (DadosAgendamentoConsulta dados) {
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!!");
		}
		
		if(dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico informado não existe!!");
		}
		
		var medico = buscaMedico(dados);
		
		if (medico == null) {
		    throw new ValidacaoException("Não existe médico disponível nessa data!");
		}
		
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		
		validadores.forEach(v -> v.validador(dados));
		
		var consulta = new Consulta(null, medico, paciente, dados.data(), null, null);
		
		consultaRepository.save(consulta);
			
		return new DadosDetalhamentoConsulta(consulta);
	}
	
	public void cancelar( DadosCancelamentoConsulta dados) {
		if (!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("A consulta informada não existe.");
		}
		
		var consultaNaoEstaCancelada = consultaRepository.consultaNaoCancelada(dados.idConsulta()) == null ? true : false;
		
		if(consultaRepository.existsById(dados.idConsulta()) && consultaNaoEstaCancelada == false) {
			throw new ValidacaoException("A consulta informada já esta cancelada.");
		}
		
		validadoresCancelamento.forEach(v -> v.validadorCancelar(dados));
		
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
	    consulta.cancelar(dados.motivo(), dados.dataCancelamento());		
	}


	private Medico buscaMedico(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null && dados.especialidade() == null) {
			throw new ValidacaoException("Campo especialidade é obrigatório caso o medico não seja informado.");
		}
		
		if(dados.idMedico() != null) {			
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		return medicoRepository.buscaMedicoAleatorioDisponivelNaData(dados.especialidade(), dados.data());
	}

}
