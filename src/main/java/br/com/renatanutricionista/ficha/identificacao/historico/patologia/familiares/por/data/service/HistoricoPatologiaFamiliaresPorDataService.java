package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.form.HistoricoPatologiaFamiliaresPorDataFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.repository.HistoricoPatologiaFamiliaresPorDataRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.repository.HistoricoPatologiaFamiliaresRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class HistoricoPatologiaFamiliaresPorDataService {

	@Autowired
	private HistoricoPatologiaFamiliaresPorDataRepository historicoPatologiaFamiliaresPorDataRepository;
	
	@Autowired
	private HistoricoPatologiaFamiliaresRepository historicoPatologiaFamiliaresRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public ResponseEntity<Void> cadastrarHistoricoPatologiaFamiliaresPorData(Long idPaciente,
			HistoricoPatologiaFamiliaresPorDataFORM historicoPatologiaFamiliaresPorDataFORM) {
		
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		HistoricoPatologiaFamiliaresPorData historico = historicoPatologiaFamiliaresPorDataRepository.save(
				historicoPatologiaFamiliaresPorDataFORM.converterParaHistoricoPatologiaFamiliaresPorData(paciente));
		
		historicoPatologiaFamiliaresRepository.saveAll(historicoPatologiaFamiliaresPorDataFORM.gerarListaHistoricoPatologiaFamiliares(historico));
		pacienteUtils.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
