package br.com.renatanutricionista.atendimento.paciente.registro.dieta.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.renatanutricionista.atendimento.paciente.registro.dieta.model.RegistroDieta;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefeicaoDietaDTO {

	private String tipoRefeicao;
	private String alimentos;
	private String quantidadeMedidaCaseira;
	
	
	public static List<RefeicaoDietaDTO> gerarListaComAsRefeicoesDoRegistroDaDieta(RegistroDieta registroDieta) {
		List<RefeicaoDietaDTO> refeicoesRegistroDieta = new ArrayList<>();
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Desjejum", registroDieta.getAlimentosDesjejum(), registroDieta.getQuantidadeMedidaCaseiraAlimentosDesjejum()));
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Lanche da manhã", registroDieta.getAlimentosLancheManha(), 
				registroDieta.getQuantidadeMedidaCaseiraAlimentosLancheManha()));
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Almoço", registroDieta.getAlimentosAlmoco(), registroDieta.getQuantidadeMedidaCaseiraAlimentosAlmoco()));
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Lanche da tarde", registroDieta.getAlimentosLancheTarde(), 
				registroDieta.getQuantidadeMedidaCaseiraAlimentosLancheTarde()));
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Jantar", registroDieta.getAlimentosJanta(), registroDieta.getQuantidadeMedidaCaseiraAlimentosJanta()));
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Ceia", registroDieta.getAlimentosCeia(), registroDieta.getQuantidadeMedidaCaseiraAlimentosCeia()));
		
		refeicoesRegistroDieta.add(new RefeicaoDietaDTO("Final de semana", registroDieta.getAlimentosFinalDeSemana(), 
				registroDieta.getQuantidadeMedidaCaseiraAlimentosFinalDeSemana()));
		
		return refeicoesRegistroDieta;
	}
}
