package com.paulorpc.aquarium.api.response;

import java.util.ArrayList;

import org.springframework.validation.BindingResult;


public class Response<T> {
	
	private T data;
	private ArrayList<String> issues;
	
	
	public Response() {
		issues = new ArrayList<>();
	}
	
	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ArrayList<String> getIssues() {
		return issues;
	}

	public void setIssues(ArrayList<String> errors) {
		this.issues = errors;
	}

	/***
	 * Adiciona os erros do result para a lista do response.
	 * @param result resulado da validação
	 */
	public void setIssuesFromResultErrors(BindingResult result) {
		result.getAllErrors().forEach(e->getIssues().add(e.getDefaultMessage()));
	}
	
	
}
