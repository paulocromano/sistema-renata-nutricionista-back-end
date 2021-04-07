package br.com.renatanutricionista.atendimento.paciente.registro.dieta.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.renatanutricionista.atendimento.paciente.registro.dieta.enums.TipoRegistroDieta;
import br.com.renatanutricionista.atendimento.paciente.registro.dieta.model.RegistroDieta;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistroDietaFORM {

	@NotNull(message = "O campo do Tipo de Registro da Dieta não pode estar nulo!")
	private TipoRegistroDieta tipoRegistroDieta;
	
	@NotEmpty(message = "O campo Alimentos no Desjejum não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Desjejum deve ter no máximo {max} caracteres!")
	private String alimentosDesjejum;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Desjejum não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Desjejum deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosDesjejum;

	@NotEmpty(message = "O campo Alimentos no Lanche da Manhã não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Lanche da Manhã deve ter no máximo {max} caracteres!")
	private String alimentosLancheManha;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Manhã não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Manhã deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosLancheManha;

	@NotEmpty(message = "O campo Alimentos no Almoço não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Almoço deve ter no máximo {max} caracteres!")
	private String alimentosAlmoco;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Almoço não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Almoço deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosAlmoco;

	@NotEmpty(message = "O campo Alimentos no Lanche da Tarde não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Lanche da Tarde deve ter no máximo {max} caracteres!")
	private String alimentosLancheTarde;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Tarde não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Lanche da Tarde deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosLancheTarde;

	@NotEmpty(message = "O campo Alimentos na Janta não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos na Janta deve ter no máximo {max} caracteres!")
	private String alimentosJanta;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos na Janta não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos na Janta deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosJanta;

	@NotEmpty(message = "O campo Alimentos na Ceia não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos na Ceia deve ter no máximo {max} caracteres!")
	private String alimentosCeia;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos na Ceia não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos na Ceia deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosCeia;

	@NotEmpty(message = "O campo Alimentos no Final de Semana  não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Alimentos no Final de Semana  deve ter no máximo {max} caracteres!")
	private String alimentosFinalDeSemana;

	@NotEmpty(message = "O campo Quantidade Medida Caseira de Alimentos no Final de Semana não pode estar nulo/vazio!")
	@Size(max = 500, message = "O campo Quantidade Medida Caseira de Alimentos no Final de Semana  deve ter no máximo {max} caracteres!")
	private String quantidadeMedidaCaseiraAlimentosFinalDeSemana;
	
	
	public RegistroDieta converterParaRegistroDieta() {
		return new RegistroDieta.Builder()
				.tipoRegistroDieta(tipoRegistroDieta)
				.alimentosDesjejum(alimentosDesjejum)
				.quantidadeMedidaCaseiraAlimentosDesjejum(quantidadeMedidaCaseiraAlimentosDesjejum)
				.alimentosLancheManha(alimentosLancheManha)
				.quantidadeMedidaCaseiraAlimentosLancheManha(quantidadeMedidaCaseiraAlimentosLancheManha)
				.alimentosAlmoco(alimentosAlmoco)
				.quantidadeMedidaCaseiraAlimentosAlmoco(quantidadeMedidaCaseiraAlimentosAlmoco)
				.alimentosLancheTarde(alimentosLancheTarde)
				.quantidadeMedidaCaseiraAlimentosLancheTarde(quantidadeMedidaCaseiraAlimentosLancheTarde)
				.alimentosJanta(alimentosJanta)
				.quantidadeMedidaCaseiraAlimentosJanta(quantidadeMedidaCaseiraAlimentosJanta)
				.alimentosCeia(alimentosCeia)
				.quantidadeMedidaCaseiraAlimentosCeia(quantidadeMedidaCaseiraAlimentosCeia)
				.alimentosFinalDeSemana(alimentosFinalDeSemana)
				.quantidadeMedidaCaseiraAlimentosFinalDeSemana(quantidadeMedidaCaseiraAlimentosFinalDeSemana)
				.build();
	}
}
