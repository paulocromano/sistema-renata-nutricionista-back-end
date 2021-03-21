package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Size;

import br.com.renatanutricionista.exception.custom.PacienteException;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums.FrequenciaAtividadeFisica;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums.FrequenciaAtividadeFisicaConversao;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtividadeFisicaFORM {

	@Size(max = 100, message = "O campo Atividades Praticadas deve ter no máximo {max} caracteres!")
	private String atividadePraticada;
	
	@Size(max = 1, message = "O campo Código da Frequência deve ter somente {max} caracter!")
	private String codigoFrequencia;
	
	@Size(max = 5, message = "O campo Duração deve ter no máximo {max} caracteres!")
	private String duracao;
	
	
	public AtividadeFisica converterParaAtividadeFisica(Paciente paciente) {
		validarCamposParaSalvarAtividadeFisica();
		
		FrequenciaAtividadeFisica frequenciaAtividadeFisica = (Objects.nonNull(codigoFrequencia)) 
				? new FrequenciaAtividadeFisicaConversao().convertToEntityAttribute(codigoFrequencia)
				: FrequenciaAtividadeFisica.NAO_PRATICA;

		return new AtividadeFisica.AtividadeFisicaBuilder()
				.atividadePraticada(atividadePraticada)
				.frequencia(frequenciaAtividadeFisica)
				.duracao(duracao)
				.paciente(paciente)
				.dataUltimaAtualizacaoDadosDaAtividadeFisica(LocalDateTime.now())
				.criarAtividadeFisica();
	}
	
	
	private void validarCamposParaSalvarAtividadeFisica() {
		
		if (Objects.nonNull(atividadePraticada) && Objects.isNull(duracao))
			throw new PacienteException("O campo Duração não pode estar nulo/vazio!");
		
		if (Objects.isNull(atividadePraticada) && Objects.nonNull(duracao))
			throw new PacienteException("O campo da  Atividade Praticada não pode estar nulo/vazio!");
			
		if (Objects.nonNull(duracao))
			ConversaoUtils.converterStringParaLocalTime(duracao);
	}
}
