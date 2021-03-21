package br.com.renatanutricionista.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;


public final class Verificacao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final Object verificarSeEntidadeExiste(String nomeEntidade, JpaRepository jpaRepository, Object id) {
		Optional<Object> optionalObject = jpaRepository.findById(id);
			
		if (optionalObject.isEmpty()) 
			throw new ObjectNotFoundException(nomeEntidade + " não encontrado(a)!");
		
		return optionalObject.get();
	}
}
