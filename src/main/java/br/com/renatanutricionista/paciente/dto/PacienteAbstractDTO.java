package br.com.renatanutricionista.paciente.dto;

import br.com.renatanutricionista.endereco.dto.EnderecoDTO;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
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
	protected EnderecoDTO endereco;
	protected String dataCadastro;
	
	
	protected PacienteAbstractDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		dataNascimento = ConversaoUtils.converterLocalDateParaString(paciente.getDataNascimento());
		sexo = paciente.getSexo().getDescricao();
		etnia = paciente.getEtnia().getDescricao();
		telefone = paciente.getTelefone();
		telefoneRecado = paciente.getTelefoneRecado();
		endereco = new EnderecoDTO(paciente.getEndereco());
		dataCadastro = ConversaoUtils.converterLocalDateParaString(paciente.getDataCadastro());
	}
}
