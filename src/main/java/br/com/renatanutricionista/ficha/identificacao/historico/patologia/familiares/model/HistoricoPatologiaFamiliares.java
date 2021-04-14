package br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.model;

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

import br.com.renatanutricionista.ficha.identificacao.historico.patologia.familiares.enums.PatologiaFamiliares;
import br.com.renatanutricionista.ficha.identificacao.historico.patologia.por.data.familiares.model.HistoricoPatologiaFamiliaresPorData;
import br.com.renatanutricionista.utils.enums.resposta.RespostaUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "historico_patologia_familiares", schema = "sistema_nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(value = "historicoPatologiaFamiliaresPorData")
public class HistoricoPatologiaFamiliares {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "patologia")
	@NotNull(message = "O campo Patologia dos Familiares não pode estar nulo!")
	private PatologiaFamiliares patologiaFamiliares;
	
	@NotNull(message = "O campo Pai não pode estar nulo!")
	private RespostaUtils pai;
	
	@NotNull(message = "O campo Mãe não pode estar nulo!")
	private RespostaUtils mae;
	
	@Column(name = "avos_masculinos")
	@NotNull(message = "O campo Avôs não pode estar nulo!")
	private RespostaUtils avosMasculinos;
	
	@Column(name = "avos_femininos")
	@NotNull(message = "O campo Avós não pode estar nulo!")
	private RespostaUtils avosFemininos;
	
	@NotNull(message = "O campo Tios não pode estar nulo!")
	private RespostaUtils tios;
	
	@NotNull(message = "O campo Tias não pode estar nulo!")
	private RespostaUtils tias;
	
	@ManyToOne
	@JoinColumn(name = "historico_patologia_familiares_por_data_id")
	private HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData;


	public static class Builder {
		
		private PatologiaFamiliares patologiaFamiliares;
		private RespostaUtils pai;
		private RespostaUtils mae;
		private RespostaUtils avosMasculinos;
		private RespostaUtils avosFemininos;
		private RespostaUtils tios;
		private RespostaUtils tias;
		private HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData;
		
		
		public Builder patologiaFamiliares(PatologiaFamiliares patologiaFamiliares) {
			this.patologiaFamiliares = patologiaFamiliares;
			return this;
		}
		
		public Builder pai(RespostaUtils pai) {
			this.pai = pai;
			return this;
		}
		
		public Builder mae(RespostaUtils mae) {
			this.mae = mae;
			return this;
		}
		
		public Builder avosMasculinos(RespostaUtils avosMasculinos) {
			this.avosMasculinos = avosMasculinos;
			return this;
		}
		
		public Builder avosFemininos(RespostaUtils avosFemininos) {
			this.avosFemininos = avosFemininos;
			return this;
		}
		
		public Builder tios(RespostaUtils tios) {
			this.tios = tios;
			return this;
		}
		
		public Builder tias(RespostaUtils tias) {
			this.tias = tias;
			return this;
		}
		
		public Builder historicoPatologiaFamiliaresPorData(HistoricoPatologiaFamiliaresPorData historicoPatologiaFamiliaresPorData) {
			this.historicoPatologiaFamiliaresPorData = historicoPatologiaFamiliaresPorData;
			return this;
		}
		
		
		public HistoricoPatologiaFamiliares build() {
			return new HistoricoPatologiaFamiliares(null, patologiaFamiliares, pai, mae, avosMasculinos, 
					avosFemininos, tios, tias, historicoPatologiaFamiliaresPorData);
		}
	}
}
