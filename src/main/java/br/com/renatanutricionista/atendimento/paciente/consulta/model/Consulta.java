package br.com.renatanutricionista.atendimento.paciente.consulta.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.forma.pagamento.FormaPagamento;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.model.RegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
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
	
	@NotNull(message = "O campo Data da Consulta não pode estar nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull(message = "O campo Horário da Consulta não pode ser nulo!")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
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
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "registro_dieta_habitual_id")
	private RegistroDieta registroDietaHabitual;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "retorno_consulta_id")
	private RetornoConsulta retornoConsulta;

	
	private Consulta(SituacaoConsulta situacaoConsulta, LocalDate data, LocalTime horario, String motivoConsulta, Paciente paciente) {
		this.situacaoConsulta = situacaoConsulta;
		this.data = data;
		this.horario = horario;
		this.motivoConsulta = motivoConsulta;
		this.paciente = paciente;
	}


	public static class Builder {
		
		private SituacaoConsulta situacaoConsulta;
		private LocalDate data;
		private LocalTime horario;
		private String motivoConsulta;
		private Paciente paciente;
		
		
		public Builder situacaoConsulta(SituacaoConsulta situacaoConsulta) {
			this.situacaoConsulta = situacaoConsulta;
			return this;
		}
		
		public Builder data(LocalDate data) {
			this.data = data;
			return this;
		}
		
		public Builder horario(LocalTime horario) {
			this.horario = horario;
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
		
		
		public Consulta build() {
			return new Consulta(situacaoConsulta, data, horario, motivoConsulta, paciente);
		}
	}
}
