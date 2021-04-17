package br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.repository.HistoricoPatologiaFamiliaresRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.form.HistoricoPatologiaFamiliaresPorDataFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.repository.HistoricoPatologiaFamiliaresPorDataRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;


@Service
public class HistoricoPatologiaFamiliaresPorDataService {

	@Autowired
	private HistoricoPatologiaFamiliaresPorDataRepository historicoPatologiaFamiliaresPorDataRepository;
	
	@Autowired
	private HistoricoPatologiaFamiliaresRepository historicoPatologiaFamiliaresRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	
	public ResponseEntity<Void> cadastrarHistoricoPatologiaFamiliaresPorData(Long idPaciente,
			HistoricoPatologiaFamiliaresPorDataFORM historicoPatologiaFamiliaresPorDataFORM) {
		
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		
		HistoricoPatologiaFamiliaresPorData historico = historicoPatologiaFamiliaresPorDataRepository.save(
				historicoPatologiaFamiliaresPorDataFORM.converterParaHistoricoPatologiaFamiliaresPorData(paciente));
		
		historicoPatologiaFamiliaresRepository.saveAll(historicoPatologiaFamiliaresPorDataFORM.gerarSetHistoricoPatologiaFamiliares(historico));
		pacienteService.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
