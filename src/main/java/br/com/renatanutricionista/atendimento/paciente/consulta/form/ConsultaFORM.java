package br.com.renatanutricionista.atendimento.paciente.consulta.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.renatanutricionista.atendimento.paciente.avaliacao.composicao.corporal.form.AvaliacaoComposicaoCorporalFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.consumo.habitual.form.AvaliacaoConsumoHabitualFORM;
import br.com.renatanutricionista.atendimento.paciente.avaliacao.massa.muscular.corporea.antropometrica.form.AvaliacaoMassaMuscularCorporeaFORM;
import br.com.renatanutricionista.atendimento.paciente.conduta.nutricional.form.CondutaNutricionalFORM;
import br.com.renatanutricionista.atendimento.paciente.consulta.enums.situacao.consulta.SituacaoConsulta;
import br.com.renatanutricionista.atendimento.paciente.consulta.model.Consulta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.form.RegistroDietaFORM;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ConsultaFORM {

	@Valid
	@NotNull(message = "O Registro de Dieta Habitual não pode ser nulo!")
	private RegistroDietaFORM registroDietaHabitual;
	
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
	
	
	public void atualizarInformacoesDaConsulta(Consulta consulta) {
		consulta.setRegistroDietaHabitual(registroDietaHabitual.converterParaRegistroDieta(TipoRegistroDieta.HABITUAL));
		consulta.setAvaliacaoConsumoHabitual(avaliacaoConsumoHabitual.converterParaAvaliacaoConsumoHabitual());
		consulta.setAvaliacaoComposicaoCorporal(avaliacaoComposicaoCorporal.converterParaAvaliacaoComposicaoCorporal(consulta.getPaciente().getSexo()));
		consulta.setAvaliacaoMassaMuscularCorporeaAntropometrica(avaliacaoMassaMuscularCorporea.converterParaAvaliacaoMassaMuscularCorporea());
		consulta.setCondutaNutricional(condutaNutricional.converterParaCondutaNutricional());
		consulta.setSituacaoConsulta(SituacaoConsulta.CONSULTA_FINALIZADA);
	}
}
