package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.repository.HistoricoPatologiaFamiliaresRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.HistoricoPatologiaFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.dto.InformacoesPreviasHistoricosFamiliaresPorDataDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.form.HistoricoPatologiaFamiliaresPorDataFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.repository.HistoricoPatologiaFamiliaresPorDataRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;


@Service
public class HistoricoPatologiaFamiliaresPorDataService {

	@Autowired
	private HistoricoPatologiaFamiliaresPorDataRepository historicoPatologiaFamiliaresPorDataRepository;
	
	@Autowired
	private HistoricoPatologiaFamiliaresRepository historicoPatologiaFamiliaresRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<InformacoesPreviasHistoricosFamiliaresPorDataDTO> buscarInformacoesPreviasHistoricosPatologiasFamiliaresPorDataDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		AtendimentoPacienteParametro atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		
		return ResponseEntity.ok().body(new InformacoesPreviasHistoricosFamiliaresPorDataDTO(paciente, atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<HistoricoPatologiaFamiliaresPorDataDTO> buscarHistoricoPatologiaFamiliaresPorDataDoPaciente(Long idPatologiaFamiliares) {
		return ResponseEntity.ok().body(new HistoricoPatologiaFamiliaresPorDataDTO(verificarSeHistoricoPatologiaFamiliaresPorDataExiste(idPatologiaFamiliares)));
	}
	
	
	public ResponseEntity<Void> cadastrarHistoricoPatologiaFamiliaresPorData(Long idPaciente,
			HistoricoPatologiaFamiliaresPorDataFORM historicoPatologiaFamiliaresPorDataFORM) {
		
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		
		HistoricoPatologiaFamiliaresPorData historico = historicoPatologiaFamiliaresPorDataRepository.save(
				historicoPatologiaFamiliaresPorDataFORM.converterParaHistoricoPatologiaFamiliaresPorData(paciente));
		
		historicoPatologiaFamiliaresRepository.saveAll(historicoPatologiaFamiliaresPorDataFORM.gerarSetHistoricoPatologiaFamiliares(historico));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> excluirHistoricoPatologiaFamiliaresPorData(Long idHistoricoPatologiaFamiliaresPorData) {
		HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData = verificarSeHistoricoPatologiaFamiliaresPorDataExiste(idHistoricoPatologiaFamiliaresPorData);
		historicoPatologiaFamiliaresPorDataRepository.delete(historicoPatologiaFamiliaresPorData);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HistoricoPatologiaFamiliaresPorData verificarSeHistoricoPatologiaFamiliaresPorDataExiste(Long idHistoricoPatologiaFamiliaresPorData) {
		if (Objects.isNull(idHistoricoPatologiaFamiliaresPorData))
			throw new NullPointerException("O ID do Hist??rico de Patologia dos Familiares por Data n??o pode ser nulo!");
		
		Optional<HistoricoPatologiaFamiliaresPorData> historicoPatologiaFamiliaresPorData = 
				historicoPatologiaFamiliaresPorDataRepository.findById(idHistoricoPatologiaFamiliaresPorData);
		
		if (historicoPatologiaFamiliaresPorData.isEmpty())
			throw new ObjectNotFoundException("Hist??rico de Patologia dos Familiares por Data n??o encontrado!");
		
		return historicoPatologiaFamiliaresPorData.get();
	}
}
