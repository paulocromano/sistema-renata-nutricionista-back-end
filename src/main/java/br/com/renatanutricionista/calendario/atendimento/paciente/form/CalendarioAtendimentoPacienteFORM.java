package br.com.renatanutricionista.calendario.atendimento.paciente.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.utils.ConversaoUtils;
import br.com.renatanutricionista.utils.RegexUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CalendarioAtendimentoPacienteFORM {

	@NotEmpty(message = "O campo da Data Inicial não pode estar vazia/nula!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data Inicial para cadastro de períodos é inválida!")
	private String dataInicial;
	
	@NotEmpty(message = "O campo da Data Final não pode estar vazia/nula!")
	@Pattern(regexp = RegexUtils.DATA, message = "O formato da Data Final para cadastro de períodos é inválida!")
	private String dataFinal;
	
	
	public void validarDataInicialFinal(int tempoMesesGeracaoPeriodos) {
		LocalDate dataInicial = ConversaoUtils.converterStringParaLocalDate(this.dataInicial);
		LocalDate dataFinal = ConversaoUtils.converterStringParaLocalDate(this.dataFinal);
		
		if (dataInicial.isAfter(dataFinal))
			throw new AtendimentoException("A Data Inicial não pode proceder a Data Final!");
		
		LocalDate hoje = LocalDate.now();
		
		if (!dataInicial.isAfter(hoje))
			throw new AtendimentoException("A Data Inicial deve proceder a Data Atual!");
		
		LocalDate dataLimiteParaGeracaoDePeriodos = hoje.plusMonths(tempoMesesGeracaoPeriodos).withDayOfMonth(dataInicial.getDayOfMonth());
		
		if (dataFinal.isAfter(dataLimiteParaGeracaoDePeriodos))
			throw new AtendimentoException("A Data Final permita deve ser até " 
				+ ConversaoUtils.converterLocalDateParaString(dataLimiteParaGeracaoDePeriodos) + "!");
	}
}
