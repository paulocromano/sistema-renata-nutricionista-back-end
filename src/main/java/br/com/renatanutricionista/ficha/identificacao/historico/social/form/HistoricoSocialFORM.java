package br.com.renatanutricionista.ficha.identificacao.historico.social.form;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.form.PatologiaPacienteFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezesConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicasConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarroConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiureseConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiureseConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivilConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinalConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menopausa.Menopausa;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menopausa.MenopausaConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menstruacao.normal.MenstruacaoNormal;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menstruacao.normal.MenstruacaoNormalConversao;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial.HistoricoSocialBuilder;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricoSocialFORM {

	@NotEmpty(message = "O campo Profissão não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Profissão deve ter no máximo {max} caracteres!")
	private String profissao;
	
	@NotEmpty(message = "O campo Estado Civil não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código do Estado Civil deve ter somente {max} caracter!")
	private String codigoEstadoCivil;
	
	@NotEmpty(message = "O campo Composição Familiar não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Composição Familiar deve ter no máximo {max} caracteres!")
	private String composicaoFamiliar;
	
	@NotEmpty(message = "O campo Local Refeições não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Local Refeições deve ter no máximo {max} caracteres!")
	private String localRefeicoes;
	
	@NotEmpty(message = "O campo Consumo de Bebidas Alcoólicas não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código da Frequência de Consumo de Bebidas Alcoólicas deve ter somente {max} caracter!")
	private String codigoFrequenciaConsumoBebidasAlcoolicas;
	
	@NotEmpty(message = "O campo Consumo de Cigarro não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código de Consumo de Cigarros deve ter somente {max} caracter!")
	private String codigoConsumoCigarro;
	
	private Integer quantidadeCigarrosPorDia;
	
	@NotEmpty(message = "O campo Hábito Intestinal não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código do Hábito Intestinal deve ter somente {max} caracter!")
	private String codigoHabitoIntestinal;
	
	@NotEmpty(message = "O campo Consistência das Fezes não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código da Consistência das Fezes deve ter somente {max} caracter!")
	private String codigoConsistenciaFezes;
	
	@NotEmpty(message = "O campo Frequência da Diurese não pode ser nulo/vazio!")
	@Size(max = 1, message = "O campo Código da Frequência da Diurese deve ter somente {max} caracter!")
	private String codigoFrequenciaDiurese;
	
	@NotEmpty(message = "O campo Coloração da Diurese não pode ser nulo/vazio!")
	@Size(max = 2, message = "O campo Código da Coloração da Diurese deve ter no máximo {max} caracteres!")
	private String codigoColoracaoDiurese;
	
	@Valid
	private Set<PatologiaPacienteFORM> patologiasPaciente;
	
	@NotNull(message = "O campo Horas de Sono não pode ser nulo!")
	private Integer horasSono;
	
	@Size(max = 1, message = "O campo Código da Menstruação Normal deve ter somente {max} caracter!")
	private String codigoMenstruacaoNormal;
	
	@Size(max = 200, message = "O campo Motivo Anormalidade da Menstruação deve ter no máximo {max} caracteres!")
	private String motivoAnormalidadeMenstruacao;

	@Size(max = 1, message = "O campo Código da Menopausa deve ter somente {max} caracter!")
	private String codigoMenopausa;
	
	private Integer quantosAnosEstaNaMenopausa;
	
	
	public HistoricoSocial converterParaHistoricoSocial(Paciente paciente) {
		validarCamposDoFormulario(paciente);
		
		HistoricoSocialBuilder historicoSocialBuilder =  new HistoricoSocial.HistoricoSocialBuilder()
				.profissao(profissao)
				.estadoCivil(new EstadoCivilConversao().convertToEntityAttribute(codigoEstadoCivil))
				.composicaoFamiliar(composicaoFamiliar)
				.localRefeicoes(localRefeicoes)
				.frequenciaConsumoBebidasAlcoolicas(new ConsumoBebidasAlcoolicasConversao().convertToEntityAttribute(codigoFrequenciaConsumoBebidasAlcoolicas))
				.consumoCigarro(new ConsumoCigarroConversao().convertToEntityAttribute(codigoConsumoCigarro))
				.quantidadeCigarrosPorDia(quantidadeCigarrosPorDia)
				.habitoIntestinal(new HabitoIntestinalConversao().convertToEntityAttribute(codigoHabitoIntestinal))
				.consistenciaFezes(new ConsistenciaFezesConversao().convertToEntityAttribute(codigoConsistenciaFezes))
				.frequenciaDiurese(new FrequenciaDiureseConversao().convertToEntityAttribute(codigoFrequenciaDiurese))
				.coloracaoDiurese(new ColoracaoDiureseConversao().convertToEntityAttribute(codigoColoracaoDiurese))
				.horasSono(horasSono)
				.dataUltimaAtualizacaoDadosDoHistoricoSocial(LocalDateTime.now())
				.paciente(paciente);
		
		if (paciente.getSexo().equals(SexoUtils.FEMININO)) {
			historicoSocialBuilder.menstruacaoNormal(new MenstruacaoNormalConversao().convertToEntityAttribute(codigoMenstruacaoNormal))
					.motivoAnormalidadeMenstruacao(motivoAnormalidadeMenstruacao)
					.menopausa(new MenopausaConversao().convertToEntityAttribute(codigoMenopausa))
					.quantosAnosEstaNaMenopausa(quantosAnosEstaNaMenopausa);
		}
		
		return historicoSocialBuilder.criarHistoricoSocial();
	}
	
	
	public List<PatologiaPaciente> gerarListaPatologiasPaciente(HistoricoSocial historicoSocial) {
		 return patologiasPaciente.stream().map(patologiaFORM -> 
		 		 new PatologiaPaciente(patologiaFORM.getQuantosAnosPossuiPatologia(), historicoSocial, patologiaFORM.getIdPatologia()))
				 .collect(Collectors.toList());
	}
	
	
	private void validarCamposDoFormulario(Paciente paciente) {
		validarQuantidadeConsumoCigarrosPorDia();
		
		if (paciente.getSexo().equals(SexoUtils.FEMININO)) {
			validarMotivoMenstruacaoAnormal();
			validarTempoPacienteEstaNaMenopausa();
		}
		
	}
	
	private void validarQuantidadeConsumoCigarrosPorDia() {
		if (!codigoConsumoCigarro.equals(ConsumoCigarro.NUNCA_FUMOU.getCodigo()) && Objects.isNull(quantidadeCigarrosPorDia))
			throw new PacienteException("A quantidade de Cigarros consumidos por dia não pode estar nula!");
	}
	
	private void validarMotivoMenstruacaoAnormal() {
		if (codigoMenstruacaoNormal.equals(MenstruacaoNormal.NAO.getCodigo()) && Objects.isNull(motivoAnormalidadeMenstruacao))
			throw new PacienteException("O Motivo da Anormalidade da Menstruação não pode estar nula!");
	}
	
	private void validarTempoPacienteEstaNaMenopausa() {
		if (codigoMenopausa.equals(Menopausa.SIM.getCodigo()) && Objects.isNull(quantosAnosEstaNaMenopausa))
			throw new PacienteException("O campo de Quantos Anos a Paciente está na Menopausa não "
					+ "não pode estar nulo!");
	}
}
