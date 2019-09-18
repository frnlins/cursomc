package com.filipelins.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Integer status, String error, Long timeStamp) {
		super(status, error, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}
	
	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}

}
