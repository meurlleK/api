package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.AtualizarDadosPaciente;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.ListaPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.detalhamentoListaPaciente;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
	
	@Autowired
	PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosPaciente dados, UriComponentsBuilder uriBuilder) {
		Paciente paciente = new Paciente(dados);
		repository.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();;
		
		return ResponseEntity.created(uri).body(new detalhamentoListaPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<ListaPaciente>> listarPaciente (@PageableDefault(size=10,sort= {"nome"}) Pageable paginas ){
		var lista = repository.findAllByAtivoTrue(paginas).map(ListaPaciente::new);
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarPaciente(@RequestBody @Valid AtualizarDadosPaciente dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarCadastroPaciente(dados);
		return ResponseEntity.ok(new detalhamentoListaPaciente(paciente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirPaciente(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.desativarPaciente();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity detalharPaciente(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		return ResponseEntity.ok(new detalhamentoListaPaciente(paciente));
	}
		
}
