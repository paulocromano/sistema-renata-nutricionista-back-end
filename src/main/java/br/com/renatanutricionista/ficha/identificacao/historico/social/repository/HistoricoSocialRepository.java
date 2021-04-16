package br.com.renatanutricionista.ficha.identificacao.historico.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.paciente.model.Paciente;

public interface HistoricoSocialRepository extends JpaRepository<HistoricoSocial, Long> {

	Optional<HistoricoSocial> findFirstByPacienteAndFrequenciaConsumoBebidasAlcoolicasNotAndConsumoCigarroNot(
			Paciente paciente, ConsumoBebidasAlcoolicas consumoBebidasAlcoolicas, ConsumoCigarro consumoCigarro);

	Optional<HistoricoSocial> findFirstByPacienteAndFrequenciaConsumoBebidasAlcoolicasNot(Paciente paciente,
			ConsumoBebidasAlcoolicas consumoBebidasAlcoolicas);

	Optional<HistoricoSocial> findFirstByPacienteAndConsumoCigarroNot(Paciente paciente, ConsumoCigarro consumoCigarro);
}
