package br.com.renatanutricionista.ficha.identificacao.historico.suplemento.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.alimentar.model.HistoricoAlimentar;
import br.com.renatanutricionista.suplemento.model.Suplemento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "suplemento_paciente", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "historicoAlimentar" })
public class SuplementoPaciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 100, message = "O campo Dose deve ter no máximo {max} caracteres!")
	private String dose;
	
	@Size(max = 250, message = "O campo Forma de Preparo deve ter no máximo {max} caracteres!")
	private String formaPreparo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "historico_alimentar_id")
	private HistoricoAlimentar historicoAlimentar;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "suplemento_id")
	@NotNull(message = "O objeto Suplemento não pode estar nulo!")
	private Suplemento suplemento;
	

	public SuplementoPaciente(String dose, String formaPreparo, HistoricoAlimentar historicoAlimentar, Integer idSuplemento) {
		this.dose = dose;
		this.formaPreparo = formaPreparo;
		this.historicoAlimentar = historicoAlimentar;
		
		suplemento = new Suplemento();
		suplemento.setId(idSuplemento);
	}
}
