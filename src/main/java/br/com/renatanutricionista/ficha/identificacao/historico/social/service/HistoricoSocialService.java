package br.com.renatanutricionista.ficha.identificacao.historico.social.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.renatanutricionista.exception.custom.ObjectNotFoundException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.form.PatologiaPacienteFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.repository.PatologiaPacienteRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.HistoricoSocialDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.dto.InformacoesPreviasHistoricosSociaisDTO;
import br.com.renatanutricionista.ficha.identificacao.historico.social.form.HistoricoSocialFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ColoracaoDiuresePaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.ImagemColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.ColoracaoDiuresePacienteRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.HistoricoSocialRepository;
import br.com.renatanutricionista.ficha.identificacao.historico.social.repository.ImagemColoracaoDiureseRepository;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.paciente.service.PacienteService;
import br.com.renatanutricionista.patologia.model.Patologia;
import br.com.renatanutricionista.patologia.repository.PatologiaRepository;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.service.AtendimentoPacienteParametroService;


@Service
public class HistoricoSocialService {

	@Autowired
	private HistoricoSocialRepository historicoSocialRepository;
	
	@Autowired
	private PatologiaPacienteRepository patologiaPacienteRepository;
	
	@Autowired
	private PatologiaRepository patologiaRepository;
	
	@Autowired
	private ColoracaoDiuresePacienteRepository coloracaoDiuresePacienteRepository;
	
	@Autowired
	private ImagemColoracaoDiureseRepository imagemColoracaoDiureseRepository;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AtendimentoPacienteParametroService atendimentoPacienteParametroService;
	
	
	
	public ResponseEntity<InformacoesPreviasHistoricosSociaisDTO> buscarInformacoesPreviasHistoricosSociaisDoPaciente(Long idPaciente) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		AtendimentoPacienteParametro atendimentoPacienteParametro = atendimentoPacienteParametroService.buscarAtendimentoPacienteParametro();

		return ResponseEntity.ok().body(new InformacoesPreviasHistoricosSociaisDTO(paciente, atendimentoPacienteParametro));
	}
	
	
	public ResponseEntity<HistoricoSocialDTO> buscarHistoricoSocialDoPaciente(Long idHistoricoSocial) {
		return ResponseEntity.ok().body(new HistoricoSocialDTO(verificarSeHistoricoSocialExiste(idHistoricoSocial)));
	} 
	
	
	public ResponseEntity<Void> cadastrarHistoricoSocialDoPaciente(Long idPaciente, HistoricoSocialFORM historicoSocialFORM) {
		Paciente paciente = pacienteService.verificarSePacienteExiste(idPaciente);
		HistoricoSocial historicoSocial = historicoSocialRepository.save(historicoSocialFORM.converterParaHistoricoSocial(paciente));
		validarPatologiasDoFormulario(historicoSocialFORM.getPatologiasPaciente());
		
		Set<ImagemColoracaoDiurese> imagensColoracaoDiurese = validarColoracoesDiureseDoFormulario(historicoSocialFORM.getColoracoesDiurese());
		
		coloracaoDiuresePacienteRepository.saveAll(converterParaColoracaoDiuresePaciente(historicoSocial, imagensColoracaoDiurese));;
		patologiaPacienteRepository.saveAll(historicoSocialFORM.gerarSetPatologiasPaciente(historicoSocial));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	private Set<ColoracaoDiuresePaciente> converterParaColoracaoDiuresePaciente(HistoricoSocial historicoSocial,
			Set<ImagemColoracaoDiurese> imagensColoracaoDiurese) {
		
		return imagensColoracaoDiurese.stream()
				.map(imagem -> new ColoracaoDiuresePaciente(imagem, historicoSocial))
				.collect(Collectors.toSet());
	}
	
	
	private Set<ImagemColoracaoDiurese> validarColoracoesDiureseDoFormulario(Set<Integer> coloracoesDiurese) {
		Set<ImagemColoracaoDiurese> imagensColoracaoDiurese = imagemColoracaoDiureseRepository.findByIdIn(coloracoesDiurese);
		
		System.out.println(coloracoesDiurese.size());
		System.out.println(imagensColoracaoDiurese.size());
		if (coloracoesDiurese.size() != imagensColoracaoDiurese.size())
			throw new IllegalArgumentException("Existe uma ou mais Colorações da Diurese inválidas!");
		
		return imagensColoracaoDiurese;
	}
	
	
	private void validarPatologiasDoFormulario(Set<PatologiaPacienteFORM> patologiasPaciente) {
		if (Objects.nonNull(patologiasPaciente) && patologiasPaciente.size() > 0) {
			List<Integer> idPatologiasPaciente = Arrays.stream(patologiasPaciente
					.stream().mapToInt(PatologiaPacienteFORM::getIdPatologia).toArray())
					.boxed()
					.collect(Collectors.toList());
			
			List<Patologia> patologiasEncontradas = patologiaRepository.findByIdIn(idPatologiasPaciente);
			
			if (patologiasPaciente.size() != patologiasEncontradas.size())
				throw new IllegalArgumentException("Existe uma ou mais Patologias inválidas!");
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
