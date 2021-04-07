package br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto;

import br.com.renatanutricionista.atendimento.paciente.registro.dieta.model.RegistroDieta;
import lombok.Getter;


@Getter
public class RegistroDietaDTO {

	private Long id;
	private String tipoRegistroDieta;
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
	
	
	public RegistroDietaDTO(RegistroDieta registroDieta) {
		id = registroDieta.getId();
		tipoRegistroDieta = registroDieta.getTipoRegistroDieta().getDescricao();
		alimentosDesjejum = registroDieta.getAlimentosDesjejum();
		quantidadeMedidaCaseiraAlimentosDesjejum = registroDieta.getQuantidadeMedidaCaseiraAlimentosDesjejum();
		alimentosLancheManha = registroDieta.getAlimentosLancheManha();
		quantidadeMedidaCaseiraAlimentosLancheManha = registroDieta.getQuantidadeMedidaCaseiraAlimentosLancheManha();
		alimentosAlmoco = registroDieta.getAlimentosAlmoco();
		quantidadeMedidaCaseiraAlimentosAlmoco = registroDieta.getQuantidadeMedidaCaseiraAlimentosAlmoco();
		alimentosLancheTarde = registroDieta.getAlimentosLancheTarde();
		quantidadeMedidaCaseiraAlimentosLancheTarde = registroDieta.getQuantidadeMedidaCaseiraAlimentosLancheTarde();
		alimentosJanta = registroDieta.getAlimentosJanta();
		quantidadeMedidaCaseiraAlimentosJanta = registroDieta.getQuantidadeMedidaCaseiraAlimentosJanta();
		alimentosCeia = registroDieta.getAlimentosCeia();
		quantidadeMedidaCaseiraAlimentosCeia = registroDieta.getQuantidadeMedidaCaseiraAlimentosCeia();
		alimentosFinalDeSemana = registroDieta.getAlimentosFinalDeSemana();
		quantidadeMedidaCaseiraAlimentosFinalDeSemana = registroDieta.getQuantidadeMedidaCaseiraAlimentosFinalDeSemana();
	}
}
