package br.com.renatanutricionista.calendario.agendamento.paciente.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.renatanutricionista.calendario.agendamento.paciente.enums.PeriodoDisponivel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "calendario_agendamento_paciente", schema = "sistema_nutricionista")
@Setter
@Getter
public class CalendarioAgendamentoPaciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O campo da Data de Agendamento não pode estar nulo!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull(message = "O campo de Hora não pode estar nulo!")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horario;
	
	@Column(name = "periodo_disponivel")
	@NotNull(message = "O campo Período Disponível não pode estar nulo!")
	private PeriodoDisponivel periodoDisponivel;
}