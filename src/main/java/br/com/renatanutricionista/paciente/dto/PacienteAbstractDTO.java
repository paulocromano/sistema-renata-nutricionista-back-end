package br.com.renatanutricionista.paciente.dto;

import lombok.Getter;

@Getter
public abstract class PacienteAbstractDTO {

	protected Long id;
	protected String nome;
	protected String dataNascimento;
	protected String sexo;
	protected String etnia;
	protected String telefone;
	protected String telefoneRecado;
	protected String dataHoraUltimaAtualizacaoDadosDoPaciente;
}
