package com.quadromotion.model.convertion;

public interface IConverter {

	// methods	
	public float expConverter(float inputValue);

	public float linearConverter(float inputValue);
	
	public float heavySideConverter(float inputValue);
	
	public float logarithmConverter(float inputValue);

}
