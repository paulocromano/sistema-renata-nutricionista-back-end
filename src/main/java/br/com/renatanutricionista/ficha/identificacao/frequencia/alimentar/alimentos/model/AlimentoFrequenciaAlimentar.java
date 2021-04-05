package br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.ficha.identificacao.frequencia.alimentar.alimentos.enums.PossuiTipoModoConsumo;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alimento_questionario_frequencia_alimentar", schema = "sistema_nutricionista")
@Setter
@Getter
public class AlimentoFrequenciaAlimentar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descricao;
	
	@Column(name = "possui_tipo_modo_consumo")
	private PossuiTipoModoConsumo possuiTipoModoConsumo;
}
