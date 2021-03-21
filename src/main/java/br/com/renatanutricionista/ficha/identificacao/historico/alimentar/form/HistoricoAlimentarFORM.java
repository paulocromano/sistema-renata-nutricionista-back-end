package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.paciente.model.Paciente;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HistoricoAlimentarFORM {

	@Size(max = 250, message = "O campo Intolerância e/ou Alergia de Alimentos do Paciente deve ter no máximo {max} caracteres!")
	private String intoleranciaAlergiaAlimentosPaciente;
	
	@Size(max = 250, message = "O campo Preferência Alimentar do Paciente deve ter no máximo {max} caracteres!")
	private String preferenciaAlimentarPaciente;
	
	@Size(max = 250, message = "O campo Alimentos que o Paciente não Gosta deve ter no máximo {max} caracteres!")
	private String alimentosPacienteNaoGosta;
	
	@Size(max = 250, message = "O campo Alterações Gastrointestinal deve ter no máximo {max} caracteres!")
	private String alteracoesGastrointestinal;
	
	@NotEmpty(message = "O campo Consumo de Água não pode estar vazio/nulo!")
	@Size(message = "O campo Consumo de Água deve ter no máximo {max} caracteres!")
	private String consumoAgua;
	
	@Size(max = 500, message = "O campo Medicamentos deve ter no máximo {max} caracteres!")
	private String medicamentos;
	
	
	public HistoricoAlimentar converterParaHistoricoAlimentar(Paciente paciente) {
		return new HistoricoAlimentar.HistoricoAlimentarBuilder()
				.intoleranciaAlergiaAlimentosPaciente(intoleranciaAlergiaAlimentosPaciente)
				.preferenciaAlimentarPaciente(preferenciaAlimentarPaciente)
				.alimentosPacienteNaoGosta(alimentosPacienteNaoGosta)
				.alteracoesGastrointestinal(alteracoesGastrointestinal)
				.consumoAgua(consumoAgua)
				.medicamentos(medicamentos)
				.paciente(paciente)
				.criarHistoricoAlimentar();
	}
}
