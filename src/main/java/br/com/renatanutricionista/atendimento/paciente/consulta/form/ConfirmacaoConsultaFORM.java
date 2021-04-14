package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.forma.pagamento.FormaPagamento;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ConfirmacaoConsultaFORM {

	@NotNull(message = "O campo Forma de Pagamento não pode estar nulo!")
	private FormaPagamento formaPagamento;
	
	private Integer numeroParcelas;
	
	@Digits(integer = 7, fraction = 2)
	@DecimalMin(value = "0.0", message = "O campo Valor da Consulta deve ter o valor mínimo {value}")
	@NotNull(message = "O campo do Valor da Consulta não pode estar nulo!")
	private BigDecimal valorConsulta;
	
	
	public void atualizarInformacoesConsulta(Consulta consulta) {
		validarPagamento();
		
		consulta.setSituacaoConsulta(SituacaoConsulta.AGUARDANDO_ATENDIMENTO);
		consulta.setFormaPagamento(formaPagamento);
		consulta.setNumeroParcelas(numeroParcelas);
		consulta.setValorConsulta(valorConsulta);
	}
	
	
	private void validarPagamento() {
		if (formaPagamento.equals(FormaPagamento.CREDITO)) {
			if (Objects.isNull(numeroParcelas) || numeroParcelas.equals(0))
				throw new NullPointerException("O Número de Parcelas não pode ser nulo ou 0");
		}
		else 
			numeroParcelas = null;
	}
}
