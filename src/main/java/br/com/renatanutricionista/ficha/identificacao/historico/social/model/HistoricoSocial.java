package br.com.renatanutricionista.ficha.identificacao.historico.social.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.paciente.model.PatologiaPaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezes;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.bebidas.alcoolicas.ConsumoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consumo.cigarro.ConsumoCigarro;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivil;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinal;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_social_paciente", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "paciente")
public class HistoricoSocial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O campo Profissão não pode estar nulo/vazio!")
	@Size(max = 60, message = "O campo Profissão deve ter no máximo {max} caracteres!")
	private String profissao;
	
	@Column(name = "estado_civil")
	@NotNull(message = "O campo Estado Civil não pode ser nulo!")
	private EstadoCivil estadoCivil;
	
	@Column(name = "composicao_familiar")
	@NotEmpty(message = "O campo Composição Familiar não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Composição Familiar deve ter no máximo {max} caracteres!")
	private String composicaoFamiliar;
	
	@Column(name = "local_refeicoes")
	@NotEmpty(message = "O campo Local Refeições não pode estar nulo/vazio!")
	@Size(max = 100, message = "O campo Local Refeições deve ter no máximo {max} caracteres!")
	private String localRefeicoes;
	
	@Column(name = "frequencia_uso_bebidas_alcoolicas")
	@NotNull(message = "O campo Consumo de Bebidas Alcoólicas não pode ser nulo!")
	private ConsumoBebidasAlcoolicas frequenciaConsumoBebidasAlcoolicas;
	
	@Column(name = "uso_cigarro")
	@NotNull(message = "O campo Consumo de Cigarro não pode ser nulo!")
	private ConsumoCigarro consumoCigarro;
	
	@Column(name = "quantidade_cigarros_por_dia")
	private Integer quantidadeCigarrosPorDia;
	
	@Column(name = "habito_intestinal")
	@NotNull(message = "O campo Hábito Intestinal não pode ser nulo!")
	private HabitoIntestinal habitoIntestinal;
	
	@Column(name = "consistencia_fezes")
	@NotNull(message = "O campo Consistência das Fezes não pode ser nulo!")
	private ConsistenciaFezes consistenciaFezes;
	
	@Column(name = "frequencia_diurese")
	@NotNull(message = "O campo Frequência da Diurese não pode ser nulo!")
	private FrequenciaDiurese frequenciaDiurese;
	
	@OneToMany(mappedBy = "historicoSocial", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<ColoracaoDiuresePaciente> coloracoesDiuresePaciente;
	
	@OneToMany(mappedBy = "historicoSocial", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<PatologiaPaciente> patologiasPaciente;
	
	@Column(name = "horas_sono")
	@NotNull(message = "O campo Horas de Sono não pode ser nulo!")
	private Integer horasSono;
	
	@Column(name = "menstruacao_normal")
	private RespostaUtils menstruacaoNormal;
	
	@Column(name = "motivo_anormalidade_menstruacao")
	@Size(max = 200, message = "O campo Motivo Anormalidade da Menstruação deve ter no máximo {max} caracteres!")
	private String motivoAnormalidadeMenstruacao;
	
	private RespostaUtils menopausa;
	
	@Column(name = "quantos_anos_menopausa")
	private Integer quantosAnosEstaNaMenopausa;
	
	@Column(name = "data_hora_cadastro_historico_social")
	@NotNull(message = "A Data e Hora do Cadastro do Histórico Social não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHoraCadastroHistoricoSocial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O Paciente do Histórico Social não deve estar nulo!")
	private Paciente paciente;
	
	
	private HistoricoSocial(String profissao, EstadoCivil estadoCivil, String composicaoFamiliar, String localRefeicoes,
			ConsumoBebidasAlcoolicas frequenciaConsumoBebidasAlcoolicas, ConsumoCigarro consumoCigarro, Integer quantidadeCigarrosPorDia,
			HabitoIntestinal habitoIntestinal, ConsistenciaFezes consistenciaFezes, FrequenciaDiurese frequenciaDiurese,
			Integer horasSono, RespostaUtils menstruacaoNormal, String motivoAnormalidadeMenstruacao, 
			RespostaUtils menopausa, Integer quantosAnosEstaNaMenopausa, 
			LocalDateTime dataHoraCadastroHistoricoSocial, Paciente paciente) {
		
		this.profissao = profissao;
		this.estadoCivil = estadoCivil;
		this.composicaoFamiliar = composicaoFamiliar;
		this.localRefeicoes = localRefeicoes;
		this.frequenciaConsumoBebidasAlcoolicas = frequenciaConsumoBebidasAlcoolicas;
		this.consumoCigarro = consumoCigarro;
		this.quantidadeCigarrosPorDia = quantidadeCigarrosPorDia;
		this.habitoIntestinal = habitoIntestinal;
		this.consistenciaFezes = consistenciaFezes;
		this.frequenciaDiurese = frequenciaDiurese;
		this.horasSono = horasSono;
		this.menstruacaoNormal = menstruacaoNormal;
		this.motivoAnormalidadeMenstruacao = motivoAnormalidadeMenstruacao;
		this.menopausa = menopausa;
		this.quantosAnosEstaNaMenopausa = quantosAnosEstaNaMenopausa;
		this.paciente = paciente;
		this.dataHoraCadastroHistoricoSocial = dataHoraCadastroHistoricoSocial;
	}

	
	public static class Builder {
		
		private String profissao;
		private EstadoCivil estadoCivil;
		private String composicaoFamiliar;
		private String localRefeicoes;
		private ConsumoBebidasAlcoolicas frequenciaConsumoBebidasAlcoolicas;
		private ConsumoCigarro consumoCigarro;
		private Integer quantidadeCigarrosPorDia;
		private HabitoIntestinal habitoIntestinal;
		private ConsistenciaFezes consistenciaFezes;
		private FrequenciaDiurese frequenciaDiurese;
		private Integer horasSono;
		private RespostaUtils menstruacaoNormal;
		private String motivoAnormalidadeMenstruacao;
		private RespostaUtils menopausa;
		private Integer quantosAnosEstaNaMenopausa;
		private LocalDateTime dataHoraCadastroHistoricoSocial;
		private Paciente paciente;
		
		
		public Builder profissao(String profissao) {
			this.profissao = profissao;
			return this;
		}
		
		public Builder estadoCivil(EstadoCivil estadoCivil) {
			this.estadoCivil = estadoCivil;
			return this;
		}
		
		public Builder composicaoFamiliar(String composicaoFamiliar) {
			this.composicaoFamiliar = composicaoFamiliar;
			return this;
		}
		
		public Builder localRefeicoes(String localRefeicoes) {
			this.localRefeicoes = localRefeicoes;
			return this;
		}
		
		public Builder frequenciaConsumoBebidasAlcoolicas(ConsumoBebidasAlcoolicas frequenciaConsumoBebidasAlcoolicas) {
			this.frequenciaConsumoBebidasAlcoolicas = frequenciaConsumoBebidasAlcoolicas;
			return this;
		}
		
		public Builder consumoCigarro(ConsumoCigarro consumoCigarro) {
			this.consumoCigarro = consumoCigarro;
			return this;
		}
		
		public Builder quantidadeCigarrosPorDia(Integer quantidadeCigarrosPorDia) {
			this.quantidadeCigarrosPorDia = quantidadeCigarrosPorDia;
			return this;
		}
		
		public Builder habitoIntestinal(HabitoIntestinal habitoIntestinal) {
			this.habitoIntestinal = habitoIntestinal;
			return this;
		}
		
		public Builder consistenciaFezes(ConsistenciaFezes consistenciaFezes) {
			this.consistenciaFezes = consistenciaFezes;
			return this;
		}
		
		public Builder frequenciaDiurese(FrequenciaDiurese frequenciaDiurese) {
			this.frequenciaDiurese = frequenciaDiurese;
			return this;
		}
		
		public Builder horasSono(Integer horasSono) {
			this.horasSono = horasSono;
			return this;
		}
		
		public Builder menstruacaoNormal(RespostaUtils menstruacaoNormal) {
			this.menstruacaoNormal = menstruacaoNormal;
			return this;
		}
		
		public Builder motivoAnormalidadeMenstruacao(String motivoAnormalidadeMenstruacao) {
			this.motivoAnormalidadeMenstruacao = motivoAnormalidadeMenstruacao;
			return this;
		}
		
		public Builder menopausa(RespostaUtils menopausa) {
			this.menopausa = menopausa;
			return this;
		}
		
		public Builder quantosAnosEstaNaMenopausa(Integer quantosAnosEstaNaMenopausa) {
			this.quantosAnosEstaNaMenopausa = quantosAnosEstaNaMenopausa;
			return this;
		}
		
		public Builder dataHoraCadastroHistoricoSocial(LocalDateTime dataHoraCadastroHistoricoSocial) {
			this.dataHoraCadastroHistoricoSocial = dataHoraCadastroHistoricoSocial;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		
		public HistoricoSocial build() {
			return new HistoricoSocial(profissao, estadoCivil, composicaoFamiliar, localRefeicoes, 
					frequenciaConsumoBebidasAlcoolicas, consumoCigarro, quantidadeCigarrosPorDia, habitoIntestinal, 
					consistenciaFezes, frequenciaDiurese, horasSono, menstruacaoNormal, 
					motivoAnormalidadeMenstruacao, menopausa, quantosAnosEstaNaMenopausa, 
					dataHoraCadastroHistoricoSocial, paciente);
		}
	}
}
