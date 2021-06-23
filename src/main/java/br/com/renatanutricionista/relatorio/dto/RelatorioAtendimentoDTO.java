package br.com.renatanutricionista.relatorio.dto;

import lombok.Getter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Getter
public class RelatorioAtendimentoDTO {

	private String titulo;
	private JRBeanCollectionDataSource atendimentos;
	private String dataHorarioGeracaoRelatorio;
}
