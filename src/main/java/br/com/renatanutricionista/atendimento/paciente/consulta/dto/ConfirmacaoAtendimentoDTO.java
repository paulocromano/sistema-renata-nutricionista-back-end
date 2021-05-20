package br.com.renatanutricionista.atendimento.paciente.consulta.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.consulta.enums.forma.pagamento.FormaPagamento;
import br.com.renatanutricionista.tabelas.parametro.atendimento.paciente.model.AtendimentoPacienteParametro;
import br.com.renatanutricionista.utils.FormatacaoUtils;
import br.com.renatanutricionista.utils.conversao.enums.ConversaoDadosEnum;
import br.com.renatanutricionista.utils.conversao.enums.DadosEnum;
import lombok.Getter;


@Getter
public class ConfirmacaoAtendimentoDTO {

	private List<DadosEnum> formasPagamento;
	private List<Integer> quantidadeParcelas = new ArrayList<>();
	private String precoConsulta;
	
	
	public ConfirmacaoAtendimentoDTO(AtendimentoPacienteParametro atendimentoPacienteParametro) {
		formasPagamento = ConversaoDadosEnum.converterDadosEnum(FormaPagamento.values());
		precoConsulta = FormatacaoUtils.substituirPontoPorVirgula(atendimentoPacienteParametro.getPrecoConsulta());
		gerarListaComQuantidadeDeParcelas(atendimentoPacienteParametro.getQuantidadeParcelas());
	}
	
	
	private void gerarListaComQuantidadeDeParcelas(int quantidadeMaximaDeParcelas) {
		for (int indice = 0; indice < quantidadeMaximaDeParcelas; indice++) {
			quantidadeParcelas.add(indice + 1);
		}
	}
}
