package br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtendimentoPacienteParametroFORM {

	@NotNull(message = "O campo Quantidade de Parcelas não pode ser nulo!")
	private Integer quantidadeParcelas;

	@NotNull(message = "O campo Tempo em Meses para Geração Automática de Horários de Atendimento não pode ser nulo!")
	private Integer tempoMesesGeracaoAutomaticaHorariosAtendimento;

	@NotNull(message = "O campo Minutos do Intervalo entre Atendimentos não pode ser nulo!")
	private Integer intervaloMinutosEntreAtendimentos;

	@NotNull(message = "O campo Intervalo de Dias entre Primeira Consulta/Retorno não pode ser nulo!")
	private Integer intervaloDiasEntrePrimeiraConsultaRetorno;

	@NotNull(message = "O campo Intervalo de Dias entre Consulta/Retorno não pode ser nulo!")
	private Integer intervaloDiasEntreConsultaRetorno;

	@NotNull(message = "O campo Intervalo de Dias entre Retorno/Consulta não pode ser nulo!")
	private Integer intervaloDiasEntreRetornoConsulta;
	
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Preço da Consulta deve ser no mínimo R$ {value}")
	@NotNull(message = "O campo Preco da Consulta não pode estar nulo!")
	private BigDecimal precoConsulta;
	
	
	public void atualizarInformacoesDosParametrosParaAtendimentoDePaciente(AtendimentoPacienteParametro atendimentoPacienteParametro,
			Integer quantidadeMaximaParcelas, Integer tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento) {
		validarCampos(quantidadeMaximaParcelas, tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento);
		
		atendimentoPacienteParametro.setQuantidadeParcelas(quantidadeParcelas);
		atendimentoPacienteParametro.setTempoMesesGeracaoAutomaticaHorariosAtendimento(tempoMesesGeracaoAutomaticaHorariosAtendimento);
		atendimentoPacienteParametro.setIntervaloMinutosEntreAtendimentos(intervaloMinutosEntreAtendimentos);
		atendimentoPacienteParametro.setIntervaloDiasEntrePrimeiraConsultaRetorno(intervaloDiasEntrePrimeiraConsultaRetorno);
		atendimentoPacienteParametro.setIntervaloDiasEntreConsultaRetorno(intervaloDiasEntreConsultaRetorno);
		atendimentoPacienteParametro.setIntervaloDiasEntreRetornoConsulta(intervaloDiasEntreRetornoConsulta);
		atendimentoPacienteParametro.setPrecoConsulta(precoConsulta);
	}
	
	
	private void validarCampos(Integer quantidadeMaximaParcelas, Integer tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento) {
		if (quantidadeParcelas > quantidadeMaximaParcelas) 
			throw new AtendimentoException("O número máximo de Parcelas permitido é 12!");

		if (tempoMesesGeracaoAutomaticaHorariosAtendimento > tempoMaximoEmMesesGeracaoAutomaticaHorariosAtendimento) 
			throw new AtendimentoException("O tempo máximo para geração automática de Horários de "
					+ "Atendimento é de 5 meses!");
	}
}
