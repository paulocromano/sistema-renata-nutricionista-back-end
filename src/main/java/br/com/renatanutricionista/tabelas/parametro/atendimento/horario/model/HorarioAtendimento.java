package br.com.renatanutricionista.tabelas.parametro.atendimento.horario.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "horario_atendimento", catalog = "sistema_nutricionista_parametro")
@Getter
@Setter
public class HorarioAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dia_semana")
	@NotNull(message = "O campo Dia da Semana não pode estar nulo!")
	private DayOfWeek diaDaSemana;

	@Column(name = "horario_entrada_antes_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horarioEntradaAntesAlmoco;
	
	@Column(name = "horario_saida_antes_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horarioSaidaAntesAlmoco;
	
	@Column(name = "horario_entrada_depois_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horarioEntradaDepoisAlmoco;
	
	@Column(name = "horario_saida_depois_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horarioSaidaDepoisAlmoco;
}
