package br.com.renatanutricionista.ficha.identificacao.historico.social.form;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.form.PatologiaPacienteFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezes;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivil;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinal;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial.Builder;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import br.com.renatanutricionista.utils.enums.sexo.SexoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricoSocialFORM {

	@NotEmpty(message = "O campo Profissão não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Profissão deve ter no máximo {max} caracteres!")
	private String profissao;
	
	@NotNull(message = "O campo do Estado Civil não pode ser nulo!")
	private EstadoCivil estadoCivil;
	
	@NotEmpty(message = "O campo Composição Familiar não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Composição Familiar deve ter no máximo {max} caracteres!")
	private String composicaoFamiliar;
	
	@NotEmpty(message = "O campo Local Refeições não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Local Refeições deve ter no máximo {max} caracteres!")
	private String localRefeicoes;
	
	@NotNull(message = "O campo de Frequência de Consumo de Bebidas Alcoólicas não pode ser nulo!")
	private ConsumoBebidasAlcoolicas frequenciaConsumoBebidasAlcoolicas;
	
	@NotNull(message = "O campo de Consumo de Cigarro não pode ser nulo!")
	private ConsumoCigarro consumoCigarro;
	
	private Integer quantidadeCigarrosPorDia;
	
	@NotNull(message = "O campo do Hábito Intestinal não pode ser nulo!")
	private HabitoIntestinal habitoIntestinal;
	
	@NotNull(message = "O campo da Consistência das Fezes não pode ser nulo!")
	private ConsistenciaFezes consistenciaFezes;
	
	@NotNull(message = "O campo da Frequência da Diurese não pode ser nulo!")
	private FrequenciaDiurese frequenciaDiurese;
	
	@NotNull(message = "O campo da Coloração da Diurese não pode ser nulo!")
	private ColoracaoDiurese coloracaoDiurese;
	
	@Valid
	private Set<PatologiaPacienteFORM> patologiasPaciente;
	
	@NotNull(message = "O campo Horas de Sono não pode ser nulo!")
	private Integer horasSono;
	
	private RespostaUtils menstruacaoNormal;
	
	@Size(max = 200, message = "O campo Motivo Anormalidade da Menstruação deve ter no máximo {max} caracteres!")
	private String motivoAnormalidadeMenstruacao;

	private RespostaUtils menopausa;
	
	private Integer quantosAnosEstaNaMenopausa;
	
	
	public HistoricoSocial converterParaHistoricoSocial(Paciente paciente) {
		validarCamposDoFormulario(paciente);
		
		Builder historicoSocialBuilder = new HistoricoSocial.Builder()
				.profissao(profissao)
				.estadoCivil(estadoCivil)
				.composicaoFamiliar(composicaoFamiliar)
				.localRefeicoes(localRefeicoes)
				.frequenciaConsumoBebidasAlcoolicas(frequenciaConsumoBebidasAlcoolicas)
				.consumoCigarro(consumoCigarro)
				.quantidadeCigarrosPorDia(quantidadeCigarrosPorDia)
				.habitoIntestinal(habitoIntestinal)
				.consistenciaFezes(consistenciaFezes)
				.frequenciaDiurese(frequenciaDiurese)
				.coloracaoDiurese(coloracaoDiurese)
				.horasSono(horasSono)
				.dataHoraUltimaAtualizacaoDadosDoHistoricoSocial(LocalDateTime.now())
				.paciente(paciente);
		
		if (paciente.getSexo().equals(SexoUtils.FEMININO)) {
			historicoSocialBuilder.menstruacaoNormal(menstruacaoNormal)
					.motivoAnormalidadeMenstruacao(motivoAnormalidadeMenstruacao)
					.menopausa(menopausa)
					.quantosAnosEstaNaMenopausa(quantosAnosEstaNaMenopausa);
		}
		
		return historicoSocialBuilder.build();
	}
	
	
	public Set<PatologiaPaciente> gerarSetPatologiasPaciente(HistoricoSocial historicoSocial) {
		 return patologiasPaciente.stream().map(patologiaFORM -> 
		 		 new PatologiaPaciente(patologiaFORM.getQuantosAnosPossuiPatologia(), historicoSocial, patologiaFORM.getIdPatologia()))
				 .collect(Collectors.toSet());
	}
	
	
	private void validarCamposDoFormulario(Paciente paciente) {
		validarQuantidadeConsumoCigarrosPorDia();
		
		if (paciente.getSexo().equals(SexoUtils.FEMININO)) {
			validarMotivoMenstruacaoAnormal();
			validarTempoPacienteEstaNaMenopausa();
		}
		
	}
	
	private void validarQuantidadeConsumoCigarrosPorDia() {
		if (!consumoCigarro.equals(ConsumoCigarro.NUNCA_FUMOU) && Objects.isNull(quantidadeCigarrosPorDia))
			throw new PacienteException("A quantidade de Cigarros consumidos por dia não pode estar nula!");
	}
	
	private void validarMotivoMenstruacaoAnormal() {
		if (menstruacaoNormal.equals(RespostaUtils.NAO) && Objects.isNull(motivoAnormalidadeMenstruacao))
			throw new PacienteException("O Motivo da Anormalidade da Menstruação não pode estar nula!");
	}
	
	private void validarTempoPacienteEstaNaMenopausa() {
		if (menopausa.equals(RespostaUtils.SIM) && Objects.isNull(quantosAnosEstaNaMenopausa))
			throw new PacienteException("O campo de Quantos Anos a Paciente está na Menopausa não "
					+ "não pode estar nulo!");
	}
}
