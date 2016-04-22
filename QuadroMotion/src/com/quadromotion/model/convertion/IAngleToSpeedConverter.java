package com.quadromotion.model.convertion;

public interface IAngleToSpeedConverter {

	// methods	
	public float expConverter(float inputValue);

	public float linearConverter(float inputValue);
	
	public float HeavySideConverter(float inputValue);
	
	public float LogarithmConverter(float inputValue);

}
