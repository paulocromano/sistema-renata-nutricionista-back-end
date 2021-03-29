package br.com.renatanutricionista.ficha.identificacao.atividade.fisica.form;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.exception.custom.AtendimentoException;
import br.com.renatanutricionista.ficha.identificacao.atividade.fisica.enums.FrequenciaAtividadeFisica;
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
	
	@NotNull(message = "O campo de Frequência de Atividade Física não pode ser nulo!")
	private FrequenciaAtividadeFisica frequenciaAtividadeFisica;
	
	@Size(max = 5, message = "O campo Duração deve ter no máximo {max} caracteres!")
	private String duracao;
	
	
	public AtividadeFisica converterParaAtividadeFisica(Paciente paciente) {
		validarCamposParaSalvarAtividadeFisica();

		return new AtividadeFisica.Builder()
				.atividadePraticada(atividadePraticada)
				.frequencia(frequenciaAtividadeFisica)
				.duracao(duracao)
				.paciente(paciente)
				.dataHoraUltimaAtualizacaoDadosDaAtividadeFisica(LocalDateTime.now())
				.build();
	}
	
	
	private void validarCamposParaSalvarAtividadeFisica() {
		
		if (Objects.nonNull(atividadePraticada) && Objects.isNull(duracao))
			throw new AtendimentoException("O campo Duração não pode estar nulo/vazio!");
		
		if (Objects.isNull(atividadePraticada) && Objects.nonNull(duracao))
			throw new AtendimentoException("O campo da  Atividade Praticada não pode estar nulo/vazio!");
			
		if (Objects.nonNull(duracao))
			ConversaoUtils.converterStringParaLocalTimeHoraMinuto(duracao);
	}
}
