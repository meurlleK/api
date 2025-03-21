package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosMedico(
		
		boolean ativo,
		
		@NotBlank(message="O campo nome é obrigatório.")
		String nome, 
		
		@NotBlank(message="O campo e-mail é obrigatório.")	
		@Email(message="Formato do email é inválido")
		String email, 
		
		@NotBlank(message="O campo telefone é obrigatório.")	
		String telefone, 	
		
		@NotBlank(message="O campo crm é obrigatório.")
		@Pattern(regexp = "\\d{4,6}")
		String crm, 
		
		@NotNull
		Especialidade especialidade,
		
		@NotNull
		@Valid
		DadosEndereco endereco
		) {

}
