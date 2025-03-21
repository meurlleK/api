package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
		
		Long id,
		
		@NotNull
		Long idConsulta,
		
		@NotNull
		MotivoCancelamento motivo,	
		
		@NotNull		
		LocalDateTime dataCancelamento
		
		) {

}
