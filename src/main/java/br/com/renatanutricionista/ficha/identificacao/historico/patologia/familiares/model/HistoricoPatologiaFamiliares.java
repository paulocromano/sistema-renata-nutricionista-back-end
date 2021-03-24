package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.FamiliarTemPatologia;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.por.data.model.HistoricoPatologiaFamiliaresPorData;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "historico_patologia_familiares", schema = "sistema_nutricionista")
@Getter
@Setter
@JsonIgnoreProperties(value = "historicoPatologiaFamiliaresPorData")
public class HistoricoPatologiaFamiliares {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "patologia")
	private PatologiaFamiliares patologiaFamiliares;
	
	private FamiliarTemPatologia pai;
	private FamiliarTemPatologia mae;
	
	@Column(name = "avos_masculinos")
	private FamiliarTemPatologia avosMasculinos;
	
	@Column(name = "avos_femininos")
	private FamiliarTemPatologia avosFemininos;
	
	private FamiliarTemPatologia tios;
	private FamiliarTemPatologia tias;
	
	@ManyToOne
	@JoinColumn(name = "historico_patologia_familiares_por_data_id")
	private HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData;
}
