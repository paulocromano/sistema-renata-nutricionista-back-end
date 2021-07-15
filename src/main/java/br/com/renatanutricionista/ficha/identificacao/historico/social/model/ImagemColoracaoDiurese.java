package br.com.renatanutricionista.ficha.identificacao.historico.social.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.ficha.identificacao.historico.social.enums.diurese.coloracao.ColoracaoDiurese;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "imagem_coloracao_diurese_historico_social", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ImagemColoracaoDiurese {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "coloracao_diurese", unique = true)
	@NotNull(message = "A Coloração da Diurese não pode ser nula!")
	private ColoracaoDiurese coloracaoDiurese;
	
	@Column(name = "uuid_imagem")
	@NotEmpty(message = "O UUID da imagem da cor da diurese não pode ser nulo/vazio!")
	@Size(max = 8000, message = "O UUID da imagem da cor da diurese deve ter no máximo {max} caractere!")
	private String uuidImagem;

	
	public ImagemColoracaoDiurese(ColoracaoDiurese coloracaoDiurese, String uuidImagem) {
		this.coloracaoDiurese = coloracaoDiurese;
		this.uuidImagem = uuidImagem;
	}
}
