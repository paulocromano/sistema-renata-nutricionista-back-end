package br.com.renatanutricionista.ficha.identificacao.historico.patologia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.social.model.HistoricoSocial;
import br.com.renatanutricionista.patologia.model.Patologia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "patologia_paciente", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "historicoSocial")
public class PatologiaPaciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantos_anos_possui_patologia")
	@NotNull(message = "O campo de Quantos Anos que o Paciente possui a Patologia não pode estar nulo!")
	private Integer quantosAnosPosssuiPatologia;
	
	@ManyToOne
	@JoinColumn(name = "historico_social_paciente_id")
	private HistoricoSocial historicoSocial;
	
	@ManyToOne
	@JoinColumn(name = "patologia_id", referencedColumnName = "id")
	@NotNull(message = "O objeto Patologia não pode estar vazio!")
	private Patologia patologia;

	
	public PatologiaPaciente(Integer quantosAnosPosssuiPatologia, HistoricoSocial historicoSocial, Integer idPatologia) {
		this.quantosAnosPosssuiPatologia = quantosAnosPosssuiPatologia;
		this.historicoSocial = historicoSocial;
		
		patologia = new Patologia();
		patologia.setId(idPatologia);
	}
}
