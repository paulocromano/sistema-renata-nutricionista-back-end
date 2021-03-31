package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.retorno.model.RetornoConsulta;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RetornoConsultaFORM {

	@Size(max = 500, message = "O campo Dificuldades para Seguir Orientações deve ter no máximo {max} caracteres!")
	private String dificuldadesParaSeguirOrientacoes;

	@Size(max = 500, message = "O campo Alterações dos Sintomas deve ter no máximo {max} caracteres!")
	private String alteracoesSintomas;

	@Size(max = 500, message = "o campo Alterações das Queimacoes deve ter no máximo {max} caracteres!")
	private String alteracoesQueimacoes;

	@Size(max = 500, message = "O campo Alterações dos Medicamentos deve ter no máximo {max} caracteres!")
	private String alteracoesMedicamentos;
	
	
	public void atualizarInformacoesRetornoConsulta(RetornoConsulta retornoConsulta) {
		retornoConsulta.setDificuldadesParaSeguirOrientacoes(dificuldadesParaSeguirOrientacoes);
		retornoConsulta.setAlteracoesSintomas(alteracoesSintomas);
		retornoConsulta.setAlteracoesQueimacoes(alteracoesQueimacoes);
		retornoConsulta.setAlteracoesMedicamentos(alteracoesMedicamentos);
	}
}
