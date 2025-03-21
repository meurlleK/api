package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record detalhamentoListaPaciente( String nome, String email,String telefone,String cpf, Endereco endereco) {
	
	public detalhamentoListaPaciente(Paciente paciente) {
		this(paciente.getNome(),paciente.getEmail(),paciente.getTelefone(),paciente.getCpf(),paciente.getEndereco());
	}
}
