package med.voll.api.domain.medico;

public record ListaMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {
	
	public ListaMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
