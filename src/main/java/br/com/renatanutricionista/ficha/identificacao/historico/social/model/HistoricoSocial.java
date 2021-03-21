package br.com.renatanutricionista.ficha.identificacao.historico.social.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.model.PatologiaPaciente;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.consistencia.fezes.ConsistenciaFezes;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.frequencia.FrequenciaDiurese;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.estado.civil.EstadoCivil;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.habito.intestinal.HabitoIntestinal;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menopausa.Menopausa;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.mulher.menstruacao.normal.MenstruacaoNormal;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.bebidas.alcoolicas.UsoBebidasAlcoolicas;
import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.uso.cigarro.UsoCigarro;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_social_paciente", schema = "sistema_patologia")
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
	@NotNull(message = "O campo Uso de Bebidas Alcoólicas não pode ser nulo!")
	private UsoBebidasAlcoolicas frequenciaUsoBebidasAlcoolicas;
	
	@Column(name = "uso_cigarro")
	@NotNull(message = "O campo Uso de Cigarro não pode ser nulo!")
	private UsoCigarro usoCigarro;
	
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
	
	@Column(name = "coloracao_diurese")
	@NotNull(message = "O campo Coloração da Diurese não pode ser nulo!")
	private ColoracaoDiurese coloracaoDiurese;
	
	@OneToMany(mappedBy = "historicoSocial", cascade = CascadeType.REMOVE)
	private List<PatologiaPaciente> patologiasPaciente;
	
	@Column(name = "horas_sono")
	@NotNull(message = "O campo Horas de Sono não pode ser nulo!")
	private Integer horasSono;
	
	@Column(name = "menstruacao_normal")
	private MenstruacaoNormal menstruacaoNormal;
	
	@Column(name = "motivo_anormalidade_menstruacao")
	@Size(max = 200, message = "O campo Motivo Anormalidade da Menstruação deve ter no máximo {max} caracteres!")
	private String motivoAnormalidadeMenstruacao;
	
	private Menopausa menopausa;
	
	@Column(name = "inicio_menopausa")
	@DateTimeFormat(pattern = "MM/yyyy")
	private LocalDate inicioMenopausa;
	
	@Column(name = "data_ultima_atualizacao_dados_historico_social")
	@NotNull(message = "A Data da Última Atualização dos Dados do Histórico Social não pode estar nula!")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataUltimaAtualizacaoDadosDoHistoricoSocial;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O Paciente do Histórico Social não deve estar nulo!")
	private Paciente paciente;
	
	
	private HistoricoSocial(String profissao, EstadoCivil estadoCivil, String composicaoFamiliar, String localRefeicoes,
			UsoBebidasAlcoolicas frequenciaUsoBebidasAlcoolicas,UsoCigarro usoCigarro, Integer quantidadeCigarrosPorDia,
			HabitoIntestinal habitoIntestinal, ConsistenciaFezes consistenciaFezes, FrequenciaDiurese frequenciaDiurese,
			ColoracaoDiurese coloracaoDiurese, Integer horasSono, MenstruacaoNormal menstruacaoNormal, 
			String motivoAnormalidadeMenstruacao, Menopausa menopausa, LocalDate inicioMenopausa, 
			LocalDateTime dataUltimaAtualizacaoDadosDoHistoricoSocial, Paciente paciente) {
		
		this.profissao = profissao;
		this.estadoCivil = estadoCivil;
		this.composicaoFamiliar = composicaoFamiliar;
		this.localRefeicoes = localRefeicoes;
		this.frequenciaUsoBebidasAlcoolicas = frequenciaUsoBebidasAlcoolicas;
		this.usoCigarro = usoCigarro;
		this.quantidadeCigarrosPorDia = quantidadeCigarrosPorDia;
		this.habitoIntestinal = habitoIntestinal;
		this.consistenciaFezes = consistenciaFezes;
		this.frequenciaDiurese = frequenciaDiurese;
		this.coloracaoDiurese = coloracaoDiurese;
		this.horasSono = horasSono;
		this.menstruacaoNormal = menstruacaoNormal;
		this.motivoAnormalidadeMenstruacao = motivoAnormalidadeMenstruacao;
		this.menopausa = menopausa;
		this.inicioMenopausa = inicioMenopausa;
		this.paciente = paciente;
		this.dataUltimaAtualizacaoDadosDoHistoricoSocial = dataUltimaAtualizacaoDadosDoHistoricoSocial;
	}

	
	public static class HistoricoSocialBuilder {
		
		private String profissao;
		private EstadoCivil estadoCivil;
		private String composicaoFamiliar;
		private String localRefeicoes;
		private UsoBebidasAlcoolicas frequenciaUsoBebidasAlcoolicas;
		private UsoCigarro usoCigarro;
		private Integer quantidadeCigarrosPorDia;
		private HabitoIntestinal habitoIntestinal;
		private ConsistenciaFezes consistenciaFezes;
		private FrequenciaDiurese frequenciaDiurese;
		private ColoracaoDiurese coloracaoDiurese;
		private Integer horasSono;
		private MenstruacaoNormal menstruacaoNormal;
		private String motivoAnormalidadeMenstruacao;
		private Menopausa menopausa;
		private LocalDate inicioMenopausa;
		private LocalDateTime dataUltimaAtualizacaoDadosDoHistoricoSocial;
		private Paciente paciente;
		
		
		public HistoricoSocialBuilder profissao(String profissao) {
			this.profissao = profissao;
			return this;
		}
		
		public HistoricoSocialBuilder estadoCivil(EstadoCivil estadoCivil) {
			this.estadoCivil = estadoCivil;
			return this;
		}
		
		public HistoricoSocialBuilder composicaoFamiliar(String composicaoFamiliar) {
			this.composicaoFamiliar = composicaoFamiliar;
			return this;
		}
		
		public HistoricoSocialBuilder localRefeicoes(String localRefeicoes) {
			this.localRefeicoes = localRefeicoes;
			return this;
		}
		
		public HistoricoSocialBuilder frequenciaUsoBebidasAlcoolicas(UsoBebidasAlcoolicas frequenciaUsoBebidasAlcoolicas) {
			this.frequenciaUsoBebidasAlcoolicas = frequenciaUsoBebidasAlcoolicas;
			return this;
		}
		
		public HistoricoSocialBuilder usoCigarro(UsoCigarro usoCigarro) {
			this.usoCigarro = usoCigarro;
			return this;
		}
		
		public HistoricoSocialBuilder quantidadeCigarrosPorDia(Integer quantidadeCigarrosPorDia) {
			this.quantidadeCigarrosPorDia = quantidadeCigarrosPorDia;
			return this;
		}
		
		public HistoricoSocialBuilder habitoIntestinal(HabitoIntestinal habitoIntestinal) {
			this.habitoIntestinal = habitoIntestinal;
			return this;
		}
		
		public HistoricoSocialBuilder consistenciaFezes(ConsistenciaFezes consistenciaFezes) {
			this.consistenciaFezes = consistenciaFezes;
			return this;
		}
		
		public HistoricoSocialBuilder frequenciaDiurese(FrequenciaDiurese frequenciaDiurese) {
			this.frequenciaDiurese = frequenciaDiurese;
			return this;
		}
		
		public HistoricoSocialBuilder coloracaoDiurese(ColoracaoDiurese coloracaoDiurese) {
			this.coloracaoDiurese = coloracaoDiurese;
			return this;
		}
		
		public HistoricoSocialBuilder horasSono(Integer horasSono) {
			this.horasSono = horasSono;
			return this;
		}
		
		public HistoricoSocialBuilder menstruacaoNormal(MenstruacaoNormal menstruacaoNormal) {
			this.menstruacaoNormal = menstruacaoNormal;
			return this;
		}
		
		public HistoricoSocialBuilder motivoAnormalidadeMenstruacao(String motivoAnormalidadeMenstruacao) {
			this.motivoAnormalidadeMenstruacao = motivoAnormalidadeMenstruacao;
			return this;
		}
		
		public HistoricoSocialBuilder menopausa(Menopausa menopausa) {
			this.menopausa = menopausa;
			return this;
		}
		
		public HistoricoSocialBuilder inicioMenopausa(LocalDate inicioMenopausa) {
			this.inicioMenopausa = inicioMenopausa;
			return this;
		}
		
		public HistoricoSocialBuilder dataUltimaAtualizacaoDadosDoHistoricoSocial(LocalDateTime dataUltimaAtualizacaoDadosDoHistoricoSocial) {
			this.dataUltimaAtualizacaoDadosDoHistoricoSocial = dataUltimaAtualizacaoDadosDoHistoricoSocial;
			return this;
		}
		
		public HistoricoSocialBuilder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		
		public HistoricoSocial criarHistoricoSocial() {
			return new HistoricoSocial(profissao, estadoCivil, composicaoFamiliar, localRefeicoes, 
					frequenciaUsoBebidasAlcoolicas, usoCigarro, quantidadeCigarrosPorDia, habitoIntestinal, 
					consistenciaFezes, frequenciaDiurese, coloracaoDiurese, horasSono, menstruacaoNormal, 
					motivoAnormalidadeMenstruacao, menopausa, inicioMenopausa, 
					dataUltimaAtualizacaoDadosDoHistoricoSocial, paciente);
		}
	}
}
