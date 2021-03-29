package br.com.renatanutricionista.atendimento.paciente.retorno.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.model.AvaliacaoComposicaoCorporal;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.model.AvaliacaoConsumoHabitual;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.model.AvaliacaoMassaMuscularCorporea;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.model.CondutaNutricional;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
import br.com.renatanutricionista.calendario.atendimento.paciente.model.CalendarioAtendimentoPaciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "retorno_consulta_paciente", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "consulta" })
public class RetornoConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "situacao")
	@NotNull(message = "O campo Situação do Retorno não pode ser nulo!")
	private SituacaoRetorno situacaoRetorno;
	
	@Column(name = "dificuldades_seguir_orientacoes")
	@Size(max = 500, message = "O campo Dificuldades para Seguir Orientações deve ter no máximo {max} caracteres!")
	private String dificuldadesParaSeguirOrientacoes;
	
	@Column(name = "alteracoes_sintomas")
	@Size(max = 500, message = "O campo Alterações dos Sintomas deve ter no máximo {max} caracteres!")
	private String alteracoesSintomas;
	
	@Column(name = "alteracoes_queimacoes")
	@Size(max = 500, message = "o campo Alterações das Queimacoes deve ter no máximo {max} caracteres!")
	private String alteracoesQueimacoes;
	
	@Column(name = "alteracoes_medicamentos")
	@Size(max = 500, message = "O campo Alterações dos Medicamentos deve ter no máximo {max} caracteres!")
	private String alteracoesMedicamentos;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "calendario_atendimento_retorno_id")
	@NotNull(message = "O objeto Período do Retorno não pode estar nulo!")
	private CalendarioAtendimentoPaciente periodoRetorno;
	
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
	
	@OneToOne(mappedBy = "retornoConsulta")
	private Consulta consulta;
	
	
	public RetornoConsulta(SituacaoRetorno situacaoRetorno, CalendarioAtendimentoPaciente periodoRetorno) {
		this.situacaoRetorno = situacaoRetorno;
		this.periodoRetorno = periodoRetorno;
	}
}
