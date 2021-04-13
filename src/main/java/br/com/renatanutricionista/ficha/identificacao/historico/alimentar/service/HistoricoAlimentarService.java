package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form.HistoricoAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.repository.HistoricoAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.repository.SuplementoPacienteRepository;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.utils.PacienteUtils;


@Service
public class HistoricoAlimentarService {

	@Autowired
	private HistoricoAlimentarRepository historicoAlimentarRepository;
	
	@Autowired
	private SuplementoPacienteRepository suplementoPacienteRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private PacienteUtils pacienteUtils;
	
	
	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(Long idPaciente, HistoricoAlimentarFORM historicoAlimentarFORM) {
		Paciente paciente = pacienteUtils.verificarSePacienteExiste(idPaciente);
		
		Set<Medicamento> medicamentosPaciente = validarListaMedicamentosPaciente(historicoAlimentarFORM.getIdMedicamentos());
		HistoricoAlimentar historicoAlimentar = historicoAlimentarFORM.converterParaHistoricoAlimentar(paciente, medicamentosPaciente);
		
		historicoAlimentarRepository.save(historicoAlimentar);
		
		try {
			suplementoPacienteRepository.saveAll(historicoAlimentarFORM.gerarSetSuplementosPaciente(historicoAlimentar));
		}
		catch (Exception e) {
			throw new IntegrityConstraintViolationException("Existe(m) Suplemento(s) inválido(s) na lista de Suplementos "
					+ "do Paciente informada pelo Usuário");

		}
		
		pacienteUtils.atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private Set<Medicamento> validarListaMedicamentosPaciente(Set<Integer> idMedicamentos) {
		if (!idMedicamentos.isEmpty()) {
			Set<Medicamento> medicamentosPaciente = medicamentoRepository.findByIdIn(idMedicamentos);
			
			if (medicamentosPaciente.size() != idMedicamentos.size())
				throw new IllegalArgumentException("Existe(m) Medicamento(s) inválido(s) na lista de Medicamentos "
						+ "do Paciente informada pelo Usuário!");
			
			return medicamentosPaciente;
		}

		return new HashSet<>();
	}
}
