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
import med.voll.api.domain.medico.AtualizarDadosMedico;
import med.voll.api.domain.medico.DadosMedico;
import med.voll.api.domain.medico.DetalhamentoListaMedico;
import med.voll.api.domain.medico.ListaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosMedico dados, UriComponentsBuilder uriBuilder) {
		
		Medico medico = new Medico(dados);
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();;
		
		return ResponseEntity.created(uri).body(new DetalhamentoListaMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<ListaMedico>> exibirListaMedico( @PageableDefault(size=10,sort={"nome"}) Pageable paginas){
		var lista = repository.findAllByAtivoTrue(paginas).map(ListaMedico::new);
		return ResponseEntity.ok(lista);
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarMedico(@RequestBody @Valid AtualizarDadosMedico dados) {
		var medico  = repository.getReferenceById(dados.id());
		medico.atualizarCadastroMedico(dados);
		
		return ResponseEntity.ok(new DetalhamentoListaMedico(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirMedico(@PathVariable Long id) {
		var medico  = repository.getReferenceById(id);
				
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity detalharMedico(@PathVariable Long id) {
		var medico  = repository.getReferenceById(id);
				
		return ResponseEntity.ok(new DetalhamentoListaMedico(medico));
		
	}

}
