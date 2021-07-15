package br.com.renatanutricionista.ficha.identificacao.historico.social.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "coloracao_diurese_paciente", schema = "sistema_nutricionista")
@Getter
@Setter
@JsonIgnoreProperties(value = "historicoSocial")
public class ColoracaoDiuresePaciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "imagem_coloracao_diurese_id")
	@NotNull(message = "O campo Coloração da Diurese não pode ser nulo!")
	private ImagemColoracaoDiurese coloracaoDiurese;
	
	@ManyToOne	
	@JoinColumn(name = "historico_social_id", referencedColumnName = "id")
	@NotNull(message = "O objeto Histórico Social não pode ser nulo!")
	private HistoricoSocial historicoSocial;
}
