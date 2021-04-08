package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.dto.HorarioAtendimentoDTO;
import br.com.renatanutricionista.tabelas.parametro.atendimento.horario.service.HorarioAtendimentoService;


@RestController
@RequestMapping("/horario-atendimento")
public class HorarioAtendimentoResource {

	@Autowired
	private HorarioAtendimentoService horarioAtendimentoService;
	
	
	@GetMapping
	public ResponseEntity<List<HorarioAtendimentoDTO>> listarHorariosAtendimento() {
		return horarioAtendimentoService.listarHorariosAtendimento();
	}
}
