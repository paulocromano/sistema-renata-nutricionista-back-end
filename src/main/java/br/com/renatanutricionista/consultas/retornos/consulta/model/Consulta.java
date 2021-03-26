package br.com.renatanutricionista.consultas.retornos.consulta.model;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.calendario.agendamento.paciente.model.CalendarioAgendamentoPaciente;
import br.com.renatanutricionista.consultas.retornos.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.FormaPagamento;
import br.com.renatanutricionista.consultas.retornos.consulta.enums.SituacaoConsulta;
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
	
	@Column(name = "porcentagem_desconto")
	private Integer porcentagemDesconto;
	
	@Column(name = "motivo")
	@NotEmpty(message = "O campo Motivo da Consulta não pode estar nulo/vazio!")
	@Size(max = 250, message = "O campo Motivo da Consulta deve ter no máximo {max} caracteres!")
	private String motivoConsulta;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	@NotNull(message = "O objeto Paciente não pode estar nulo!")
	private Paciente paciente;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "calendario_agendamento_paciente_id")
	@NotNull(message = "O objeto Período de Agendamento da Consulta não pode estar nulo!")
	private CalendarioAgendamentoPaciente periodoAgendamentoConsulta;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "avaliacao_consumo_habitual_id")
	private AvaliacaoConsumoHabitual avaliacaoConsumoHabitual;

	
	private Consulta(SituacaoConsulta situacaoConsulta, String motivoConsulta,
			Paciente paciente, CalendarioAgendamentoPaciente periodoAgendamentoConsulta) {
		
		this.situacaoConsulta = situacaoConsulta;
		this.motivoConsulta = motivoConsulta;
		this.paciente = paciente;
		this.periodoAgendamentoConsulta = periodoAgendamentoConsulta;
	}
	
	
	public static class ConsultaBuilder {
		
		private SituacaoConsulta situacaoConsulta;
		private String motivoConsulta;
		private Paciente paciente;
		private CalendarioAgendamentoPaciente periodoAgendamentoConsulta;
		
		
		public ConsultaBuilder situacaoConsulta(SituacaoConsulta situacaoConsulta) {
			this.situacaoConsulta = situacaoConsulta;
			return this;
		}
		
		public ConsultaBuilder motivoConsulta(String motivoConsulta) {
			this.motivoConsulta = motivoConsulta;
			return this;
		}
		
		public ConsultaBuilder paciente(Paciente paciente) {
			this.paciente = paciente;
			return this;
		}
		
		public ConsultaBuilder periodoAgendamentoConsulta(CalendarioAgendamentoPaciente periodoAgendamentoConsulta) {
			this.periodoAgendamentoConsulta = periodoAgendamentoConsulta;
			return this;
		}
		
		
		public Consulta criarConsulta() {
			return new Consulta(situacaoConsulta, motivoConsulta, paciente, periodoAgendamentoConsulta);
		}
	}
}
