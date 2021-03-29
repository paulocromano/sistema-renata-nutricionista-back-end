package br.com.renatanutricionista.consultas.retornos.retorno.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renatanutricionista.consultas.retornos.retorno.service.RetornoConsultaService;


@RestController
@RequestMapping("/retorno-consulta-paciente")
public class RetornoConsultaResource {

	@Autowired
	private RetornoConsultaService retornoConsultaService;
}
