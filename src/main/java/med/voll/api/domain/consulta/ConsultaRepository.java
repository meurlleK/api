package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	@Query("""
			select COUNT(c) from Consulta c
			where
			c.medico.id = :id
			and
			c.dataCancelamento = null
			and
			c.data = :data
			""")
	Long existeConsultaDuplicadaMedico(Long id, LocalDateTime data);

	@Query("""
			    select COUNT(c) FROM Consulta c
			    where
			    c.paciente.id = :id
			    and
			    c.dataCancelamento = null
			    and
			    c.data
			    between
			    :inicioData AND :finalData
			""")
	Long existeConsultaJaAgendadaPaciente(Long id, LocalDateTime inicioData, LocalDateTime finalData);
		
	@Query("""
			select c.data from Consulta c
			where
			c.id = :id
			""")
	LocalDateTime consultaRegistrada(Long id);
	
	@Query("""
			select c.dataCancelamento from Consulta c
			where
			c.id = :id			
			""")
	LocalDateTime consultaNaoCancelada(Long id);

}
