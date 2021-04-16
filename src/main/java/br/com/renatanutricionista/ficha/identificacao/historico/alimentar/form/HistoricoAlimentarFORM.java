package br.com.renatanutricionista.ficha.identificacao.historico.alimentar.form;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.form.SuplementoPacienteFORM;
import br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model.SuplementoPaciente;
import br.com.renatanutricionista.medicamento.model.Medicamento;
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
	
	private Set<Integer> idMedicamentos;
	
	@Valid
	private Set<SuplementoPacienteFORM> suplementosPaciente;
	
	
	public HistoricoAlimentar converterParaHistoricoAlimentar(Paciente paciente, Set<Medicamento> medicamentosPaciente) {
		return new HistoricoAlimentar.Builder()
				.intoleranciaAlergiaAlimentosPaciente(intoleranciaAlergiaAlimentosPaciente)
				.preferenciaAlimentarPaciente(preferenciaAlimentarPaciente)
				.alimentosPacienteNaoGosta(alimentosPacienteNaoGosta)
				.alteracoesGastrointestinal(alteracoesGastrointestinal)
				.consumoAgua(consumoAgua)
				.medicamentos(medicamentosPaciente)
				.paciente(paciente)
				.dataHoraCadastroHistoricoAlimentar(LocalDateTime.now())
				.build();
	}
	
	
	public Set<SuplementoPaciente> gerarSetSuplementosPaciente(HistoricoAlimentar historicoAlimentar) {
		return suplementosPaciente.stream().map(suplementoPaciente -> new SuplementoPaciente(suplementoPaciente.getDose(), 
				suplementoPaciente.getFormaPreparo(), historicoAlimentar, suplementoPaciente.getIdSuplemento()))
				
				.collect(Collectors.toSet());
	}
}
