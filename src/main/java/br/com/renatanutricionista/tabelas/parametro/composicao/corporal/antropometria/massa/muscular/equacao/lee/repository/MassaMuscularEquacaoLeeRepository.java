package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.model.MassaMuscularEquacaoLee;


@Transactional(readOnly = true)
public interface MassaMuscularEquacaoLeeRepository extends JpaRepository<MassaMuscularEquacaoLee, Integer> {

}
