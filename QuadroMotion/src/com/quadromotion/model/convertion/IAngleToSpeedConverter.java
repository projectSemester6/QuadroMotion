package com.quadromotion.model.convertion;

public interface IAngleToSpeedConverter {

	

	// methods	
	public double expConverter(double inputValue);

	public double linearConverter(double inputValue);
	
	public double HeavySideConverter(double inputValue);

	// getter & setter
	public double getInputValue();

	public void setInputValue(int inputValue);

	public double getOutputValue();
}
