package br.com.renatanutricionista.relatorio.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.renatanutricionista.relatorio.service.RelatorioService;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/relatorio")
public class RelatorioResource {

	@Autowired
	private RelatorioService relatorioService;
}
