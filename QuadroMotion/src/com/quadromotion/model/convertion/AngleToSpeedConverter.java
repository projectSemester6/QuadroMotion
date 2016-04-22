package com.quadromotion.model.convertion;

// converts the input angle value to the output speed 
public class AngleToSpeedConverter implements IAngleToSpeedConverter{

	private double maxAngle = 0;
	private double maxSpeed = 0;
	private double speedOffset = 0;
	private double angleOffset = 0;
	private double functionExp = 0;

	private double inputValue;
	private double outputValue;

	/**
	 * 
	 * @param maxAngle
	 * @param maxSpeed
	 * @param speedOffset
	 * @param angleOffset
	 * @param functionExp
	 */
	public AngleToSpeedConverter(double maxAngle, double maxSpeed, double speedOffset, double angleOffset, double functionExp) {
		super();
		this.maxAngle = maxAngle;
		this.maxSpeed = maxSpeed;
		this.speedOffset = speedOffset;
		this.angleOffset = angleOffset;
		this.functionExp = functionExp;
	}



	@Override
	public double expConverter(double inputValue) {
		// TODO exponentielle umrechnung

		boolean inputSign = false;		

		double functionSpeed = 0;
		double functionMaxAngle = 0;

		if(inputValue < 0){
			inputSign = true; // signe négatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)		
		functionSpeed = Math.pow((inputValue - angleOffset)/functionMaxAngle, functionExp); // y(x) = ((x-d)/fMA)^p
		functionSpeed = functionSpeed*(maxSpeed - speedOffset);// y(x) = y(x)*a
		outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**changement de signe
		 * */

		if(inputSign){
			outputValue = -outputValue;// y(x) = -y(x)
		}
		return outputValue;
	}

	@Override
	public double linearConverter(double inputValue) {
		// TODO lineare umrechnung

		double slope = 0;
		double intercept = 0;

		slope = (maxSpeed - speedOffset)/(maxAngle - angleOffset); // a
		intercept = speedOffset - slope*angleOffset; // b

		outputValue = slope*inputValue + intercept;// y = a*x + b;

		return outputValue;

	}

	@Override
	public double HeavySideConverter(double inputValue){

		if(inputValue > angleOffset){
			outputValue = maxSpeed;			
		}else{
			outputValue = 0;
		}
		return outputValue;

	}

	public double LogarithmConverter(double inputValue){

		boolean inputSign = false;		

		double functionSpeed = 0;
		double functionMaxAngle = 0;
		
		/**
		 * Ce bordel est a vérifié Mathématiquement
		 */
		functionExp = 1/functionExp;
		
		if(inputValue < 0){
			inputSign = true; // signe négatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)		
		functionSpeed = Math.pow((inputValue - angleOffset)/functionMaxAngle, functionExp); // y(x) = ((x-d)/fMA)^p
		functionSpeed = functionSpeed*(maxSpeed - speedOffset);// y(x) = y(x)*a
		outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**changement de signe
		 * */

		if(inputSign){
			outputValue = -outputValue;// y(x) = -y(x)
		}
		return outputValue;
	}

	@Override
	public double getInputValue() {
		return inputValue;
	}

	@Override
	public void setInputValue(int inputValue) {
		this.inputValue = inputValue;
	}

	@Override
	public double getOutputValue() {
		// TODO Auto-generated method stub
		return outputValue;
	}

	public double getMaxAngle() {
		return maxAngle;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

}
