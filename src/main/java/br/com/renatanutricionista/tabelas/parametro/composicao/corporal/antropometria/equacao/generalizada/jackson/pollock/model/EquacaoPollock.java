package br.com.renatanutricionista.tabelas.parametro.composicao.corporal.antropometria.equacao.generalizada.jackson.pollock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.renatanutricionista.utils.enums.SexoUtils;
import lombok.Getter;


@Entity
@Table(name = "equacao_generalizada_jackson_pollock", schema = "sistema_nutricionista")
@Getter
public class EquacaoPollock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@Column(name = "sexo")
	private SexoUtils sexoPessoa;
	
	@Column(name = "gordura_essencial_minima")
	private Integer gorduraEssencialMinimaEmPorcentagem;
	
	@Column(name = "gordura_essencial_maxima")
	private Integer gorduraEssencialMaximaEmPorcentagem;
	
	@Column(name = "maioria_atletas_minima")
	private Integer maioriaAtletasMinimaEmPorcentagem;
	
	@Column(name = "maioria_atletas_maxima")
	private Integer maioriaAtletasMaximaEmPorcentagem;
	
	@Column(name = "saude_otima_minima")
	private Integer saudeOtimaMinimaEmPorcentagem;
	
	@Column(name = "saude_otima_maxima")
	private Integer saudeOtimaMaximaEmPorcentagem;
	
	@Column(name = "obesidade_limitrofe_minima")
	private Integer obesidadeLimitrofeMinimaEmPorcentagem;
	
	@Column(name = "obesidade_limitrofe_maxima")
	private Integer obesidadeLimitrofeMaximaEmPorcentagem;
}
