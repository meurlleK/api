package med.voll.api.domain.paciente;

public record ListaPaciente(Long id, String nome, String email, String cpf) {
	
	public ListaPaciente (Paciente paciente){
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
