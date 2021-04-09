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
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "horario_atendimento", catalog = "sistema_nutricionista_parametro")
@Getter
@Setter
@NoArgsConstructor
public class HorarioAtendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dia_semana", unique = true)
	@NotNull(message = "O campo Dia da Semana não pode estar nulo!")
	private DayOfWeek diaDaSemana;

	@Column(name = "horario_entrada_antes_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "O campo Horário de Entrada antes do Almoço não pode ser nulo!")
	private LocalTime horarioEntradaAntesAlmoco;
	
	@Column(name = "horario_saida_antes_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "O campo Horário de Saída antes do Almoço não pode ser nulo!")
	private LocalTime horarioSaidaAntesAlmoco;
	
	@Column(name = "horario_entrada_depois_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "O campo Horário de Entrada depois do Almoço não pode ser nulo!")
	private LocalTime horarioEntradaDepoisAlmoco;
	
	@Column(name = "horario_saida_depois_almoco")
	@DateTimeFormat(pattern = "HH:mm")
	@NotNull(message = "O campo Horário de Saída depois do Almoço não pode ser nulo!")
	private LocalTime horarioSaidaDepoisAlmoco;

	
	private HorarioAtendimento(DayOfWeek diaDaSemana, LocalTime horarioEntradaAntesAlmoco, LocalTime horarioSaidaAntesAlmoco,
			LocalTime horarioEntradaDepoisAlmoco, LocalTime horarioSaidaDepoisAlmoco) {
		
		this.diaDaSemana = diaDaSemana;
		this.horarioEntradaAntesAlmoco = horarioEntradaAntesAlmoco;
		this.horarioSaidaAntesAlmoco = horarioSaidaAntesAlmoco;
		this.horarioEntradaDepoisAlmoco = horarioEntradaDepoisAlmoco;
		this.horarioSaidaDepoisAlmoco = horarioSaidaDepoisAlmoco;
	}
	

	public static class Builder {
		
		private DayOfWeek diaDaSemana;
		private LocalTime horarioEntradaAntesAlmoco;
		private LocalTime horarioSaidaAntesAlmoco;
		private LocalTime horarioEntradaDepoisAlmoco;
		private LocalTime horarioSaidaDepoisAlmoco;
		
		
		public Builder diaDaSemana(DayOfWeek diaDaSemana) {
			this.diaDaSemana = diaDaSemana;
			return this;
		}
		
		public Builder horarioEntradaAntesAlmoco(LocalTime horarioEntradaAntesAlmoco) {
			this.horarioEntradaAntesAlmoco = horarioEntradaAntesAlmoco;
			return this;
		}
		
		public Builder horarioSaidaAntesAlmoco(LocalTime horarioSaidaAntesAlmoco) {
			this.horarioSaidaAntesAlmoco = horarioSaidaAntesAlmoco;
			return this;
		}
		
		public Builder horarioEntradaDepoisAlmoco(LocalTime horarioEntradaDepoisAlmoco) {
			this.horarioEntradaDepoisAlmoco = horarioEntradaDepoisAlmoco;
			return this;
		}

		public Builder horarioSaidaDepoisAlmoco(LocalTime horarioSaidaDepoisAlmoco) {
			this.horarioSaidaDepoisAlmoco = horarioSaidaDepoisAlmoco;
			return this;
		}
		
		
		public HorarioAtendimento build() {
			return new HorarioAtendimento(diaDaSemana, horarioEntradaAntesAlmoco, horarioSaidaAntesAlmoco, 
					horarioEntradaDepoisAlmoco, horarioSaidaDepoisAlmoco);
		}
	}
}
