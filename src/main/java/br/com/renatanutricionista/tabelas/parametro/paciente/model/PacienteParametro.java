package br.com.renatanutricionista.tabelas.parametro.paciente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "paciente_parametro", catalog = "sistema_nutricionista_parametro")
@Getter
@Setter
public class PacienteParametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tempo_meses_atualizar_historico_social")
	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Social não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoSocial;
	
	@Column(name = "tempo_meses_atualizar_historico_familiar")
	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Familiar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoFamiliar;
	
	@Column(name = "tempo_meses_atualizar_historico_atividade_fisica")
	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico de Atividade Física não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoAtividadeFisica;
	
	@Column(name = "tempo_meses_atualizar_historico_alimentar")
	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico Alimentar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoAlimentar;
	
	@Column(name = "tempo_meses_atualizar_historico_frequencia_alimentar")
	@NotNull(message = "O campo do Tempo em Meses para Atualizar o Histórico da Frequência Alimentar não pode ser nulo!")
	private Integer tempoMesesAtualizarHistoricoFrequenciaAlimentar;
}
