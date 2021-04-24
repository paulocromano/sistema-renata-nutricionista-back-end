package br.com.renatanutricionista.endereco.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.renatanutricionista.endereco.model.EnderecoAPIViaCEP;


@FeignClient(url = "https://viacep.com.br/ws/", name = "cep")
public interface CEPService {
	
	@GetMapping("{cep}/json")
	EnderecoAPIViaCEP buscarEnderecoPorCEP(@PathVariable String cep);
}
