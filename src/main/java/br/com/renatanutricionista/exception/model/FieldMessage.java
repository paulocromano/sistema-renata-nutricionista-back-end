package br.com.renatanutricionista.exception.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter 
@AllArgsConstructor
public class FieldMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
}
