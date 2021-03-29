package br.com.renatanutricionista.consultas.retornos.consulta.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import br.com.renatanutricionista.consultas.retornos.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import br.com.renatanutricionista.consultas.retornos.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import br.com.renatanutricionista.consultas.retornos.conduta.nutricional.model.CondutaNutricional;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.FormaPagamento;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
import br.com.renatanutricionista.consultas.retornos.retorno.model.RetornoConsulta;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "consulta_paciente", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = "paciente")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "situacao")
	@NotNull(message = "O campo Situação da Consulta não pode estar nulo!")
	private SituacaoConsulta situacaoConsulta;
	
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@Column(name = "numero_parcelas")
	private Integer numeroParcelas;
	
	@Column(name = "valor")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Valor da Consulta deve ter o valor mínimo {value}")
	private BigDecimal valorConsulta;
	
	@Column(name = "motivo")
	@NotEmpty(message = "O campo Motivo da Consulta não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Motivo da Consulta deve ter no máximo {max} caracteres!")
	private String motivoConsulta;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "calendario_atendimento_consulta_id")
	@NotNull(message = "O objeto Período de Agendamento da Consulta não pode estar nulo!")
	private CalendarioAtendimentoPaciente periodoConsulta;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "avaliacao_consumo_habitual_id")
	private AvaliacaoConsumoHabitual avaliacaoConsumoHabitual;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "avaliacao_composicao_corporal_id")
	private AvaliacaoComposicaoCorporal avaliacaoComposicaoCorporal;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "avaliacao_massa_muscular_corporea_id")
	private AvaliacaoMassaMuscularCorporea avaliacaoMassaMuscularCorporeaAntropometrica;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "conduta_nutricional_id")
	private CondutaNutricional condutaNutricional;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "retorno_consulta_id")
	private RetornoConsulta retornoConsulta;

	
	private Consulta(SituacaoConsulta situacaoConsulta, String motivoConsulta,
			Paciente paciente, CalendarioAtendimentoPaciente periodoConsulta) {
		
		this.situacaoConsulta = situacaoConsulta;
		this.motivoConsulta = motivoConsulta;
		this.paciente = paciente;
		this.periodoConsulta = periodoConsulta;
	}
	
	
	public static class Builder {
		
		private SituacaoConsulta situacaoConsulta;
		private String motivoConsulta;
		private Paciente paciente;
		private CalendarioAtendimentoPaciente periodoConsulta;
		
		
		public Builder situacaoConsulta(SituacaoConsulta situacaoConsulta) {
			this.situacaoConsulta = situacaoConsulta;
			return this;
		}
		
		public Builder motivoConsulta(String motivoConsulta) {
			this.motivoConsulta = motivoConsulta;
			return this;
		}
		
		public Builder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		public Builder periodoConsulta(CalendarioAtendimentoPaciente periodoConsulta) {
			this.periodoConsulta = periodoConsulta;
			return this;
		}
		
		
		public Consulta build() {
			return new Consulta(situacaoConsulta, motivoConsulta, paciente, periodoConsulta);
		}
	}
}
