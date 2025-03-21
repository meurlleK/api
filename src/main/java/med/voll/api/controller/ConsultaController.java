package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendamentoConsulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

	@Autowired
	AgendamentoConsulta agendamentoConsulta = new AgendamentoConsulta();

	@PostMapping
	@Transactional
	public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dadosDoAgendamento = agendamentoConsulta.agendar(dados);
		return ResponseEntity.ok(dadosDoAgendamento);
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dados) {
		agendamentoConsulta.cancelar(dados);
		return ResponseEntity.noContent().build();
	}

}
