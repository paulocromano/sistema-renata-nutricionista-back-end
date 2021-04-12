package br.com.renatanutricionista.atendimento.paciente.retorno.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.form.AvaliacaoComposicaoCorporalFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.form.AvaliacaoConsumoHabitualFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.form.AvaliacaoMassaMuscularCorporeaFORM;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.form.CondutaNutricionalFORM;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.form.RegistroDietaFORM;
import br.com.renatanutricionista.atendimento.paciente.retorno.enums.SituacaoRetorno;
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
	
	@Valid
	@NotNull(message = "O Registro de Dieta Habitual não pode ser nulo!")
	private RegistroDietaFORM registroDietaHabitual;
	
	@Valid
	@NotNull(message = "O Registro de Dieta 24 Horas não pode ser nulo!")
	private RegistroDietaFORM registroDieta24Horas;
	
	@Valid
	@NotNull(message = "A Avaliação do Consumo Habitual não pode ser nula!")
	private AvaliacaoConsumoHabitualFORM avaliacaoConsumoHabitual;
	
	@Valid
	@NotNull(message = "A Avaliação da Composição Corporal não pode ser nula!")
	private AvaliacaoComposicaoCorporalFORM avaliacaoComposicaoCorporal;
	
	@Valid
	@NotNull(message = "A Avaliação da Massa Corporal não pode ser nula!")
	private AvaliacaoMassaMuscularCorporeaFORM avaliacaoMassaMuscularCorporea;
	
	@Valid
	@NotNull(message = "A Conduta Nutricional não pode ser nula!")
	private CondutaNutricionalFORM condutaNutricional;
	
	
	public void atualizarInformacoesRetornoConsulta(RetornoConsulta retornoConsulta) {
		retornoConsulta.setDificuldadesParaSeguirOrientacoes(dificuldadesParaSeguirOrientacoes);
		retornoConsulta.setAlteracoesSintomas(alteracoesSintomas);
		retornoConsulta.setAlteracoesQueimacoes(alteracoesQueimacoes);
		retornoConsulta.setAlteracoesMedicamentos(alteracoesMedicamentos);
		
		retornoConsulta.setRegistroDietaHabitual(registroDietaHabitual.converterParaRegistroDieta(TipoRegistroDieta.HABITUAL));
		retornoConsulta.setRegistroDieta24Horas(registroDieta24Horas.converterParaRegistroDieta(TipoRegistroDieta.PERIODO_24H));
		retornoConsulta.setAvaliacaoConsumoHabitual(avaliacaoConsumoHabitual.converterParaAvaliacaoConsumoHabitual());
		retornoConsulta.setAvaliacaoComposicaoCorporal(avaliacaoComposicaoCorporal.converterParaAvaliacaoComposicaoCorporal(
				retornoConsulta.getConsulta().getPaciente().getSexo()));
		retornoConsulta.setAvaliacaoMassaMuscularCorporeaAntropometrica(avaliacaoMassaMuscularCorporea.converterParaAvaliacaoMassaMuscularCorporea());
		retornoConsulta.setCondutaNutricional(condutaNutricional.converterParaCondutaNutricional());
		
		retornoConsulta.setSituacaoRetorno(SituacaoRetorno.RETORNO_FINALIZADO);
	}
}
