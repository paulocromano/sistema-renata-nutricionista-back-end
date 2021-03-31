package br.com.renatanutricionista.exception.custom;

public class PacienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public PacienteException(String message) {
		super(message);
	}
}

