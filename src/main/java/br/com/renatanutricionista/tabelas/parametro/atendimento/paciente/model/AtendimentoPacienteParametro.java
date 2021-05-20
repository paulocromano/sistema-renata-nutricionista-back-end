package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "atendimento_paciente_parametro", catalog = "sistema_nutricionista_parametro")
@Getter
@Setter
@NoArgsConstructor
public class AtendimentoPacienteParametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "quantidade_parcelas")
	@NotNull(message = "O campo Quantidade de Parcelas não pode ser nulo!")
	private Integer quantidadeParcelas;
	
	@Column(name = "tempo_meses_geracao_automatica_horarios_atendimento")
	@NotNull(message = "O campo Tempo em Meses para Geração Automática de Horários de Atendimento não pode ser nulo!")
	private Integer tempoMesesGeracaoAutomaticaHorariosAtendimento;
	
	@Column(name = "intervalo_minutos_entre_atendimentos")
	@NotNull(message = "O campo Minutos do Intervalo entre Atendimentos não pode ser nulo!")
	private Integer intervaloMinutosEntreAtendimentos;
	
	@Column(name = "intervalo_dias_entre_primeira_consulta_retorno")
	@NotNull(message = "O campo Intervalo de Dias entre Primeira Consulta/Retorno não pode ser nulo!")
	private Integer intervaloDiasEntrePrimeiraConsultaRetorno;
	
	@Column(name = "intervalo_dias_entre_consulta_retorno")
	@NotNull(message = "O campo Intervalo de Dias entre Consulta/Retorno não pode ser nulo!")
	private Integer intervaloDiasEntreConsultaRetorno;
	
	@Column(name = "intervalo_dias_retorno_consulta")
	@NotNull(message = "O campo Intervalo de Dias entre Retorno/Consulta não pode ser nulo!")
	private Integer intervaloDiasEntreRetornoConsulta;
	
	@Column(name = "preco_consulta")
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Preço da Consulta deve ser no mínimo R$ {value}")
	@NotNull(message = "O campo Preco da Consulta não pode estar nulo!")
	private BigDecimal precoConsulta;
}
