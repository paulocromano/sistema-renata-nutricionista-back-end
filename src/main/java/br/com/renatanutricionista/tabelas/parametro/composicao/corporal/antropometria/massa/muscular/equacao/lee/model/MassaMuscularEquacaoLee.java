package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.massa.muscular.equacao.lee.enums.MassaMuscularPelaIdade;
import lombok.Getter;


@Entity
@Table(name = "massa_muscular_equacao_lee", catalog = "sistema_nutricionista_parametro")
@Getter
public class MassaMuscularEquacaoLee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "classificacao_pela_idade")
	private MassaMuscularPelaIdade classificacaoPelaIdade;
	
	@Column(name = "mulheres_porcentagem_maior")
	private Integer mulheresPorcentagemMaior;
	
	@Column(name = "homens_porcentagem_maior")
	private Integer homensPorcentagemMaior;
}
