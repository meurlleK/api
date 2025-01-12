package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.AtualizarDadosPaciente;
import med.voll.api.paciente.DadosPaciente;
import med.voll.api.paciente.ListaPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
	PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid DadosPaciente dados) {
		repository.save(new Paciente(dados));
	}
	
	@GetMapping
	public Page<ListaPaciente> listarPaciente (@PageableDefault(size=10,sort= {"nome"}) Pageable paginas ){
		return repository.findAllByAtivoTrue(paginas).map(ListaPaciente::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizarPaciente(@RequestBody @Valid AtualizarDadosPaciente dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarCadastroPaciente(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluirPaciente(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.desativarPaciente();
	}
		
}
