package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosConsulta(
		
		long id,
		
		long idMedico,
		
		@NotBlank(message="O campo paciente é obrigatório.")
		long idPaciente,
		
		@Future(message="O campo data não pode ter data retorativa.")
		@NotBlank(message="O campo data é obrigatório.")
		LocalDateTime data
		
		) {

}
