package br.com.renatanutricionista.ficha.identificacao.historico.social.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.repository.PatologiaPacienteRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.HistoricoSocialDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.HistoricoSocialRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;


@Service
public class HistoricoSocialService {

	@Autowired
	private HistoricoSocialRepository historicoSocialRepository;
	
	@Autowired
	private PatologiaPacienteRepository patologiaPacienteRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	
	public ResponseEntity<HistoricoSocialDTO> buscarHistoricoSocialDoPaciente(Long idHistoricoSocial) {
		return ResponseEntity.ok().body(new HistoricoSocialDTO(verificarSeHistoricoSocialExiste(idHistoricoSocial)));
	} 
	
	
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(Long idPaciente, HistoricoSocialFORM historicoSocialFORM) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		validarOpcaoConsumoBebidasAlcoolicasConsumoCigarro(paciente, historicoSocialFORM);
		
		HistoricoSocial historicoSocial = historicoSocialRepository.save(historicoSocialFORM.converterParaHistoricoSocial(paciente));
		
		try {
			patologiaPacienteRepository.saveAll(historicoSocialFORM.gerarSetPatologiasPaciente(historicoSocial));
		}
		catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new IntegrityConstraintViolationException("Existe(m) Patologia(s) inválida(s) na lista de Patologias " + 
						" do Paciente informada pelo Usuário!");
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void validarOpcaoConsumoBebidasAlcoolicasConsumoCigarro(Paciente paciente, HistoricoSocialFORM historicoSocial) {
		Optional<HistoricoSocial> historicoPaciente;
		
		if (historicoSocial.getFrequenciaConsumoBebidasAlcoolicas().equals(ConsumoBebidasAlcoolicas.NUNCA) 
				&& historicoSocial.getConsumoCigarro().equals(ConsumoCigarro.NUNCA_FUMOU)) {
			
			historicoPaciente = historicoSocialRepository.findFirstByPacienteAndFrequenciaConsumoBebidasAlcoolicasNotAndConsumoCigarroNot(
				paciente, ConsumoBebidasAlcoolicas.NUNCA, ConsumoCigarro.NUNCA_FUMOU);
			
			if (historicoPaciente.isPresent()) 
				throw new PacienteException("Opção inválida! O Paciente possui antecedentes de consumo de "
						+ "bebidas alcoólicas e cigarros!");
		}
		
		else if (historicoSocial.getFrequenciaConsumoBebidasAlcoolicas().equals(ConsumoBebidasAlcoolicas.NUNCA)) {
			historicoPaciente = historicoSocialRepository.findFirstByPacienteAndFrequenciaConsumoBebidasAlcoolicasNot(
					paciente, ConsumoBebidasAlcoolicas.NUNCA);
			
			if (historicoPaciente.isPresent()) 
				throw new PacienteException("Opção inválida! O Paciente possui antecedente de consumo de "
						+ "bebidas alcoólicas!");
		}
		
		else if (historicoSocial.getConsumoCigarro().equals(ConsumoCigarro.NUNCA_FUMOU)) {
			historicoPaciente = historicoSocialRepository.findFirstByPacienteAndConsumoCigarroNot(
					paciente, ConsumoCigarro.NUNCA_FUMOU);
			
			if (historicoPaciente.isPresent())
				throw new PacienteException("Opção inválida! O Paciente possui antecedente de consumo de cigarros!");
		}
	}
	
	
	public ResponseEntity<Void> excluirHistoricoSocial(Long idHistoricoSocial) {
		HistoricoSocial historicoSocial = verificarSeHistoricoSocialExiste(idHistoricoSocial);
		historicoSocialRepository.delete(historicoSocial);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HistoricoSocial verificarSeHistoricoSocialExiste(Long idHistoricoSocial) {
		if (Objects.isNull(idHistoricoSocial))
			throw new NullPointerException("O ID do Histórico Social não pode ser nulo!");
		
		Optional<HistoricoSocial> historicoSocial = historicoSocialRepository.findById(idHistoricoSocial);
		
		if (historicoSocial.isEmpty())
			throw new ObjectNotFoundException("Histórico Social não encontrado!");
		
		return historicoSocial.get();
	}
}
