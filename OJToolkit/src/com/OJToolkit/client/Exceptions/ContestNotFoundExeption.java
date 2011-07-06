package com.OJToolkit.client.Exceptions;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ContestNotFoundExeption extends Exception implements Serializable {
	public ContestNotFoundExeption() {
		super();
	}
	
	ContestNotFoundExeption(String message){
		super(message);
	}
}
