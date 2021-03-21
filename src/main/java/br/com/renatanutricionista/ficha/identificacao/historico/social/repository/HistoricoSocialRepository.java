package br.com.renatanutricionista.ficha.identificacao.historico.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;

public interface HistoricoSocialRepository extends JpaRepository<HistoricoSocial, Long> {

}
