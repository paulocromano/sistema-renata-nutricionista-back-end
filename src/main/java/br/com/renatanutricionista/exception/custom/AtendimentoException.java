package br.com.renatanutricionista.exception.custom;

public class AtendimentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public AtendimentoException(String message) {
		super(message);
	}
}
