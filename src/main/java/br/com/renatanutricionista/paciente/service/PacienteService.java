package br.com.renatanutricionista.paciente.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form.AtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.repository.AtividadeFisicaRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.repository.PatologiaPacienteRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.HistoricoSocialRepository;
import br.com.renatanutricionista.medicamento.model.Medicamento;
import br.com.renatanutricionista.medicamento.repository.MedicamentoRepository;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.patologia.repository.PatologiaRepository;
import br.com.renatanutricionista.utils.VerificacaoUtils;


@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private HistoricoSocialRepository historicoSocialRepository;
	
	@Autowired
	private PatologiaPacienteRepository patologiaPacienteRepository;
	
	@Autowired
	private PatologiaRepository patologiaRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private AtividadeFisicaRepository atividadeFisicaRepository;
	
//	@Autowired
//	private HistoricoAlimentarRepository historicoAlimentarRepository;
	
	
	public ResponseEntity<List<PacienteDTO>> listarTodosPacientes() {	
		return ResponseEntity.ok().body(PacienteDTO.converterParaListaPacienteDTO(pacienteRepository.findAll()));
	}
	
	
	public ResponseEntity<Void> cadastrarPacienteEndereco(PacienteFORM pacienteFORM) {
		pacienteRepository.save(pacienteFORM.converterParaPaciente());
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> atualizarPacienteEndereco(Long idPaciente, AtualizacaoPacienteFORM atualizacaoPaciente) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		
		atualizacaoPaciente.atualizarPacienteEndereco(paciente);
		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.ok().build();
	}
	
	
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(Long idPaciente, HistoricoSocialFORM historicoSocialFORM) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		
		HistoricoSocial historicoSocial = historicoSocialRepository.save(historicoSocialFORM.converterParaHistoricoSocial(paciente));
		validarSalvarListaPatologiasDoPaciente(historicoSocialFORM, historicoSocial);
		
		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private void validarSalvarListaPatologiasDoPaciente(HistoricoSocialFORM historicoSocialFORM, HistoricoSocial historicoSocial) {		
		if (!historicoSocialFORM.getPatologiasPaciente().isEmpty()) {			
			List<PatologiaPaciente> patologiasPaciente = historicoSocialFORM.gerarListaPatologiasPaciente(historicoSocial);
			
			List<Patologia> patologiasEncontradas = patologiaRepository.findByIdIn(historicoSocialFORM.getPatologiasPaciente().stream()
					.map(patologia -> patologia.getIdPatologia())
					.collect(Collectors.toList()));
			
			if (historicoSocialFORM.getPatologiasPaciente().size() != patologiasEncontradas.size()) 
				throw new IllegalArgumentException("Existe(m) Patologia(s) inválido(s) na lista de Patologias "
						+ "do Paciente informada pelo Usuário!");
			
			patologiaPacienteRepository.saveAll(patologiasPaciente);
		}
	}
	
	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, AtividadeFisicaFORM atividadeFisicaFORM) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		atividadeFisicaRepository.save(atividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
//	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(Long idPaciente, HistoricoAlimentarFORM historicoAlimentarFORM) {
//		Paciente paciente = verificarSePacienteExiste(idPaciente);
//		List<Medicamento> medicamentos = validarListaMedicamentosDoPaciente(historicoAlimentarFORM.getIdMedicamentos());
//		HistoricoAlimentar historicoAlimentar = historicoAlimentarFORM.converterParaHistoricoAlimentar(medicamentos, paciente);
//		
//		//historicoAlimentarRepository.save(historicoAlimentar);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).build();
//	}
	
	
	private List<Medicamento> validarListaMedicamentosDoPaciente(List<Integer> idMedicamentos) {
		if (!idMedicamentos.isEmpty()) {
			List<Medicamento> medicamentosEncontrados = medicamentoRepository.findByIdIn(idMedicamentos);
			
			if (idMedicamentos.size() != medicamentosEncontrados.size())
				throw new IllegalArgumentException("Existe(m) ID(s) inválido(s) na lista de Medicamentos informada pelo Usuário!");
			
			return medicamentosEncontrados;
		}
		
		return new ArrayList<>();
	}
	
	
	public ResponseEntity<Void> removerPaciente(Long idPaciente) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		pacienteRepository.delete(paciente);
		
		return ResponseEntity.noContent().build();
	}
	
	
	private void atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(Paciente paciente) {
		paciente.setDataUltimaAtualizacaoDadosDoPaciente(LocalDateTime.now());
		pacienteRepository.saveAndFlush(paciente);
	}
}
