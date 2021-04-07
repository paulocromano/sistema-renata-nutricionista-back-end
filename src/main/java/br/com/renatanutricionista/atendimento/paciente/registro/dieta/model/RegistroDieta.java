package br.com.renatanutricionista.atendimento.paciente.registro.dieta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "registro_dieta", schema = "sistema_nutricionista")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistroDieta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_registro_dieta")
	@NotNull(message = "O campo do Tipo de Registro da Dieta não pode estar nulo!")
	private TipoRegistroDieta tipoRegistroDieta;
	
	@Column(name = "alimentos_desjejum")
	@NotEmpty(message = "O campo Alimentos no Desjejum não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Desjejum deve ter no máximo {max} caracteres!")
	private String alimentosDesjejum;
	
	@Column(name = "quantidade_medida_caseira_alimentos_desjejum")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Desjejum não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Desjejum deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosDesjejum;
	
	@Column(name = "alimentos_lanche_manha")
	@NotEmpty(message = "O campo Alimentos no Lanche da Manhã não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Lanche da Manhã deve ter no máximo {max} caracteres!")
	private String alimentosLancheManha;
	
	@Column(name = "quantidade_medida_caseira_alimentos_lanche_manha")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Manhã não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Manhã deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosLancheManha;
	
	@Column(name = "alimentos_almoco")
	@NotEmpty(message = "O campo Alimentos no Almoço não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Almoço deve ter no máximo {max} caracteres!")
	private String alimentosAlmoco;
	
	@Column(name = "quantidade_medida_caseira_alimentos_almoco")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Almoço não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Almoço deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosAlmoco;
	
	@Column(name = "alimentos_lanche_tarde")
	@NotEmpty(message = "O campo Alimentos no Lanche da Tarde não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Lanche da Tarde deve ter no máximo {max} caracteres!")
	private String alimentosLancheTarde;
	
	@Column(name = "quantidade_medida_caseira_alimentos_lanche_tarde")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Tarde não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Tarde deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosLancheTarde;
	
	@Column(name = "alimentos_janta")
	@NotEmpty(message = "O campo Alimentos na Janta não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos na Janta deve ter no máximo {max} caracteres!")
	private String alimentosJanta;
	
	@Column(name = "quantidade_medida_caseira_alimentos_janta")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos na Janta não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos na Janta deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosJanta;
	
	@Column(name = "alimentos_ceia")
	@NotEmpty(message = "O campo Alimentos na Ceia não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos na Ceia deve ter no máximo {max} caracteres!")
	private String alimentosCeia;
	
	@Column(name = "quantidade_medida_caseira_alimentos_ceia")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos na Ceia não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos na Ceia deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosCeia;
	
	@Column(name = "alimento_final_semana")
	@NotEmpty(message = "O campo Alimentos no Final de Semana  não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Final de Semana  deve ter no máximo {max} caracteres!")
	private String alimentosFinalDeSemana;
	
	@Column(name = "quantidade_medida_caseira_alimentos_final_semana")
	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Final de Semana não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Final de Semana  deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosFinalDeSemana;
	
	
	public static class Builder {
		
		private TipoRegistroDieta tipoRegistroDieta;
		private String alimentosDesjejum;
		private String quantidadeMedidaCaseiraAlimentosDesjejum;
		private String alimentosLancheManha;
		private String quantidadeMedidaCaseiraAlimentosLancheManha;
		private String alimentosAlmoco;
		private String quantidadeMedidaCaseiraAlimentosAlmoco;
		private String alimentosLancheTarde;
		private String quantidadeMedidaCaseiraAlimentosLancheTarde;
		private String alimentosJanta;
		private String quantidadeMedidaCaseiraAlimentosJanta;
		private String alimentosCeia;
		private String quantidadeMedidaCaseiraAlimentosCeia;
		private String alimentosFinalDeSemana;
		private String quantidadeMedidaCaseiraAlimentosFinalDeSemana;
		
		
		public Builder tipoRegistroDieta(TipoRegistroDieta tipoRegistroDieta) {
			this.tipoRegistroDieta = tipoRegistroDieta;
			return this;
		}
		
		public Builder alimentosDesjejum(String alimentosDesjejum) {
			this.alimentosDesjejum = alimentosDesjejum;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosDesjejum(String quantidadeMedidaCaseiraAlimentosDesjejum) {
			this.quantidadeMedidaCaseiraAlimentosDesjejum = quantidadeMedidaCaseiraAlimentosDesjejum;
			return this;
		}
		
		public Builder alimentosLancheManha(String alimentosLancheManha) {
			this.alimentosLancheManha = alimentosLancheManha;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosLancheManha(String quantidadeMedidaCaseiraAlimentosLancheManha) {
			this.quantidadeMedidaCaseiraAlimentosLancheManha = quantidadeMedidaCaseiraAlimentosLancheManha;
			return this;
		}
		
		public Builder alimentosAlmoco(String alimentosAlmoco) {
			this.alimentosAlmoco = alimentosAlmoco;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosAlmoco(String quantidadeMedidaCaseiraAlimentosAlmoco) {
			this.quantidadeMedidaCaseiraAlimentosAlmoco = quantidadeMedidaCaseiraAlimentosAlmoco;
			return this;
		}
		
		public Builder alimentosLancheTarde(String alimentosLancheTarde) {
			this.alimentosLancheTarde = alimentosLancheTarde;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosLancheTarde(String quantidadeMedidaCaseiraAlimentosLancheTarde) {
			this.quantidadeMedidaCaseiraAlimentosLancheTarde = quantidadeMedidaCaseiraAlimentosLancheTarde;
			return this;
		}
		
		public Builder alimentosJanta(String alimentosJanta) {
			this.alimentosJanta = alimentosJanta;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosJanta(String quantidadeMedidaCaseiraAlimentosJanta) {
			this.quantidadeMedidaCaseiraAlimentosJanta = quantidadeMedidaCaseiraAlimentosJanta;
			return this;
		}
		
		public Builder alimentosCeia(String alimentosCeia) {
			this.alimentosCeia = alimentosCeia;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosCeia(String quantidadeMedidaCaseiraAlimentosCeia) {
			this.quantidadeMedidaCaseiraAlimentosCeia = quantidadeMedidaCaseiraAlimentosCeia;
			return this;
		}
		
		public Builder alimentosFinalDeSemana(String alimentosFinalDeSemana) {
			this.alimentosFinalDeSemana = alimentosFinalDeSemana;
			return this;
		}
		
		public Builder quantidadeMedidaCaseiraAlimentosFinalDeSemana(String quantidadeMedidaCaseiraAlimentosFinalDeSemana) {
			this.quantidadeMedidaCaseiraAlimentosFinalDeSemana = quantidadeMedidaCaseiraAlimentosFinalDeSemana;
			return this;
		}
		
		
		public RegistroDieta build() {
			return new RegistroDieta(null, tipoRegistroDieta, alimentosDesjejum, quantidadeMedidaCaseiraAlimentosDesjejum, 
					alimentosLancheManha, quantidadeMedidaCaseiraAlimentosLancheManha, alimentosAlmoco, 
					quantidadeMedidaCaseiraAlimentosAlmoco, alimentosLancheTarde, quantidadeMedidaCaseiraAlimentosLancheTarde, 
					alimentosJanta, quantidadeMedidaCaseiraAlimentosJanta, alimentosCeia, quantidadeMedidaCaseiraAlimentosCeia, 
					alimentosFinalDeSemana, quantidadeMedidaCaseiraAlimentosFinalDeSemana);
		}
	}
}
