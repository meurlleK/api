package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {	
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	
	public Endereco(DadosEndereco dados) {
		this.logradouro = dados.logradouro();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
		this.bairro = dados.bairro();
		this.cep = dados.cep();
		this.cidade = dados.cidade();
		this.uf = dados.uf();
	}

	public void atualizarEnderecoMedico(DadosEndereco endereco) {
		if(endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}
		if(endereco.numero() != null) {
			this.numero = endereco.numero();
		}
		if(endereco.complemento() != null) {
			this.complemento = endereco.complemento();
		}
		if(endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}
		if(endereco.cep() != null) {
			this.cep = endereco.cep();
		}
		if(endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
		if(endereco.uf() != null) {
			this.uf = endereco.uf();
		}		
		
	}

	public void atualizarEnderecoPaciente(DadosEndereco endereco) {
		if(endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}
		if(endereco.numero() != null) {
			this.numero = endereco.numero();
		}
		if(endereco.complemento() != null) {
			this.complemento = endereco.complemento();
		}
		if(endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}
		if(endereco.cep() != null) {
			this.cep = endereco.cep();
		}
		if(endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
		if(endereco.uf() != null) {
			this.uf = endereco.uf();
		}		
		
	}
	
}
