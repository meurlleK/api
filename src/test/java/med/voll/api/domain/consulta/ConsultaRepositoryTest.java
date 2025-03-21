package med.voll.api.domain.consulta;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.DadosMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ConsultaRepositoryTest {
	
	@Autowired
	ConsultaRepository consultaRepository; 
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	@DisplayName("Verifica se existe consulta cadastrada para o Médico com mesma data.")
	void testExisteConsultaDuplicada() {
		
		var medico = cadastrarMedico(true ,"Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente(true, "Paciente", "paciente@email.com", "00000000000");
		var paciente2 = cadastrarPaciente(true, "Paciente2", "paciente2@email.com", "00000000002");
				
		var dataConsulta1 = LocalDateTime.now().plusDays(4);
		var dataConsulta2 = LocalDateTime.now().plusDays(5);
		
		var consulta1 = cadastrarConsulta(null, medico, paciente, dataConsulta1, null, null);					
		var consulta2 = cadastrarConsulta(null, medico, paciente2, dataConsulta1, null, null);		
		var consulta3 = cadastrarConsulta(null, medico, paciente, dataConsulta2, null, null);	
		
		if((consulta1).equals(consulta2) ) {			
			
			var existeConsulta = consultaRepository.existeConsultaDuplicadaMedico(medico.getId() , dataConsulta1) > 0;			
			assertThat(existeConsulta).isTrue();	
		} else 	
		if((consulta1).equals(consulta3) ) {			
				
			var existeConsulta = consultaRepository.existeConsultaDuplicadaMedico(medico.getId() , dataConsulta2) > 0;			
			assertThat(existeConsulta).isFalse();	
		}			
	}
	
	@Test
	@DisplayName("Verifica se existe consulta cadastrada para o Paciente com mesma data.")
	void testExisteConsultaJaAgendadaPaciente() {
		
		var medico = cadastrarMedico(true ,"Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
		var medico2 = cadastrarMedico(true ,"Medico2", "medico2@voll.med", "123457", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente(true, "Paciente", "paciente@email.com", "00000000000");
						
		var dataConsulta1 = LocalDateTime.now().plusDays(10);
		var dataConsulta2 = LocalDateTime.now().plusDays(11);
				
		var consulta1 = cadastrarConsulta(null, medico, paciente, dataConsulta1, null, null);					
		var consulta2 = cadastrarConsulta(null, medico2, paciente, dataConsulta1, null, null);		
		var consulta3 = cadastrarConsulta(null, medico, paciente, dataConsulta2, null, null);

	if((consulta1).equals(consulta2) ) {			
			
			var existeConsulta = consultaRepository.existeConsultaJaAgendadaPaciente(paciente.getId(), dataConsulta1.withHour(7), dataConsulta1.withHour(18))>0;			
			assertThat(existeConsulta).isTrue();	
		} else 	
		if((consulta1).equals(consulta3) ) {			
				
			var existeConsulta = consultaRepository.existeConsultaJaAgendadaPaciente(paciente.getId(), dataConsulta1.withHour(7), dataConsulta1.withHour(18))>0;	;			
			assertThat(existeConsulta).isFalse();	
		}
	}
	
	
	private Consulta cadastrarConsulta(Long id,Medico medico, Paciente paciente, LocalDateTime data, LocalDateTime dataCancela, MotivoCancelamento motCancela) {
	   	return em.merge(new Consulta(id, medico, paciente, data, dataCancela, motCancela));
	}

	private Medico cadastrarMedico(boolean ativo, String nome, String email, String crm, Especialidade especialidade) {
	    var medico = new Medico(dadosMedico(ativo, nome, email, crm, especialidade));
	    em.persist(medico);
	    return medico;
	}

	private Paciente cadastrarPaciente(boolean ativo, String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(ativo, nome, email, cpf));
	    em.persist(paciente);
	    return paciente;
	}
	
	

	private DadosMedico dadosMedico(boolean ativo, String nome, String email, String crm, Especialidade especialidade) {
	    return new DadosMedico(
	    		ativo,
	            nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	private DadosPaciente dadosPaciente(boolean ativo,String nome, String email, String cpf) {
	    return new DadosPaciente(
	    		ativo,
	            nome,
	            email,
	            "61999999999",
	            cpf,
	            dadosEndereco()
	    );
	}
		
	private DadosEndereco dadosEndereco() {
	    return new DadosEndereco(
	            "rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
	    );
	}
	
		
}

/*

@Test
void testConsultaRegistrada() {
	
}

@Test
void testConsultaNaoCancelada() {
	
}*/


