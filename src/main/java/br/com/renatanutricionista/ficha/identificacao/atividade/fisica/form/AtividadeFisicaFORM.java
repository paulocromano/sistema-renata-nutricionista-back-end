package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums.FrequenciaAtividadeFisicaConversao;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.model.AtividadeFisica;
import br.com.renatanutricionista.paciente.model.Paciente;
import br.com.renatanutricionista.utils.ConversaoUtils;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AtividadeFisicaFORM {

	@Size(max = 100, message = "O campo Atividades Praticadas deve ter no máximo {150} caracteres!")
	private String atividadePraticada;
	
	@NotNull(message = "O Código da Frequência de Atividade Física não pode estar nulo!")
	private String codigoFrequencia;
	
	@Size(max = 5, message = "O campo Duração deve ter no máximo {max} caracteres!")
	private String duracao;
	
	
	public AtividadeFisica converterParaAtividadeFisica(Paciente paciente) {
		if (Objects.nonNull(duracao))
			ConversaoUtils.converterStringParaLocalTime(duracao);
			
		return new AtividadeFisica.AtividadeFisicaBuilder()
				.atividadePraticada(atividadePraticada)
				.frequencia(new FrequenciaAtividadeFisicaConversao().convertToEntityAttribute(codigoFrequencia))
				.duracao(duracao)
				.paciente(paciente)
				.dataUltimaAtualizacaoDadosDaAtividadeFisica(LocalDateTime.now())
				.criarAtividadeFisica();
	}
}
