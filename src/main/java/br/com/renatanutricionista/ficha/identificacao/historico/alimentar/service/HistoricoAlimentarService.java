package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.HistoricoAlimentarDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.dto.InformacoesPreviasHistoricosAlimentaresDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form.HistoricoAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.repository.HistoricoAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.repository.SuplementoPacienteRepository;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;


@Service
public class HistoricoAlimentarService {

	@Autowired
	private HistoricoAlimentarRepository historicoAlimentarRepository;
	
	@Autowired
	private SuplementoPacienteRepository suplementoPacienteRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	public ResponseEntity<InformacoesPreviasHistoricosAlimentaresDTO> buscarInformacoesPreviasHistoricosAlimentaresDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		AtendimentoPacienteParametro atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();
		
		return ResponseEntity.ok().body(new InformacoesPreviasHistoricosAlimentaresDTO(paciente, atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<HistoricoAlimentarDTO> buscarHistoricoAlimentarDoPaciente(Long idHistoricoAlimentar) {
		return ResponseEntity.ok().body(new HistoricoAlimentarDTO(verificarSeHistoricoAlimentarExiste(idHistoricoAlimentar)));
	}
	
	
	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(Long idPaciente, HistoricoAlimentarFORM historicoAlimentarFORM) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		
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
	
	
	public ResponseEntity<Void> excluirHistoricoAlimentar(Long idHistoricoAlimentar) {
		HistoricoAlimentar historicoAlimentar = verificarSeHistoricoAlimentarExiste(idHistoricoAlimentar);
		historicoAlimentarRepository.delete(historicoAlimentar);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private HistoricoAlimentar verificarSeHistoricoAlimentarExiste(Long idHistoricoAlimentar) {
		if (Objects.isNull(idHistoricoAlimentar))
			throw new NullPointerException("O ID do Histórico Alimentar não pode ser nulo!");
		
		Optional<HistoricoAlimentar> historicoAlimentar = historicoAlimentarRepository.findById(idHistoricoAlimentar);
		
		if (historicoAlimentar.isEmpty())
			throw new ObjectNotFoundException("Histórico Alimentar não encontrado!");
		
		return historicoAlimentar.get();
	}
}
