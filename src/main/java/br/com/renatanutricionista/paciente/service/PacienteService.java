package br.com.renatanutricionista.paciente.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.IntegrityConstraintViolationException;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form.AtividadeFisicaFORM;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.repository.AtividadeFisicaRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form.HistoricoAlimentarFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.repository.HistoricoAlimentarRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.repository.PatologiaPacienteRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.HistoricoSocialRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.repository.SuplementoPacienteRepository;
import br.com.renatanutricionista.paciente.dto.PacienteDTO;
import br.com.renatanutricionista.paciente.form.AtualizacaoPacienteFORM;
import br.com.renatanutricionista.paciente.form.PacienteFORM;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.repository.PacienteRepository;
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
	private AtividadeFisicaRepository atividadeFisicaRepository;
	
	@Autowired
	private HistoricoAlimentarRepository historicoAlimentarRepository;
	
	@Autowired
	private SuplementoPacienteRepository suplementoPacienteRepository;
	
	
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
		
		try {
			patologiaPacienteRepository.saveAll(historicoSocialFORM.gerarListaPatologiasPaciente(historicoSocial));
		}
		catch (Exception e) {
			throw new IntegrityConstraintViolationException("Existe(m) Patologia(s) inválida(s) na lista de Patologias " + 
											" do Paciente informada pelo Usuário!");
		}

		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	
	public ResponseEntity<Void> cadastrarAtividadeFisicaDoPaciente(Long idPaciente, AtividadeFisicaFORM atividadeFisicaFORM) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		atividadeFisicaRepository.save(atividadeFisicaFORM.converterParaAtividadeFisica(paciente));
		
		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	public ResponseEntity<Void> cadastrarHistoricoAlimentarDoPaciente(Long idPaciente, HistoricoAlimentarFORM historicoAlimentarFORM) {
		Paciente paciente = VerificacaoUtils.verificarSePacienteExiste(idPaciente, pacienteRepository);
		HistoricoAlimentar historicoAlimentar = historicoAlimentarFORM.converterParaHistoricoAlimentar(paciente);
		
		historicoAlimentarRepository.save(historicoAlimentar);
		
		try {
			suplementoPacienteRepository.saveAll(historicoAlimentarFORM.gerarListaSuplementosPaciente(historicoAlimentar));
		}
		catch (Exception e) {
			throw new IntegrityConstraintViolationException("Existe(m) suplemento(s) inválidos na lista de Suplementos "
					+ "do Paciente informada pelo Usuário");
		}
		
		atualizarDataHoraUltimaAlteracaoNosDadosDoPaciente(paciente);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
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
