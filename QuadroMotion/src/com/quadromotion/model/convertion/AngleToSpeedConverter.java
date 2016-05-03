package com.quadromotion.model.convertion;

// converts the input angle value to the output speed 
public class AngleToSpeedConverter  {

	private float maxAngle;
	private float maxSpeed;
	private float speedOffset;
	private float angleOffset;
	private float functionExp;

	/**
	 * 
	 * @param maxAngle
	 * @param maxSpeed
	 * @param speedOffset
	 * @param angleOffset
	 * @param functionExp
	 */
	public AngleToSpeedConverter(float maxAngle, float maxSpeed, float speedOffset, float angleOffset,
			float functionExp) {
		super();
		this.maxAngle = maxAngle;
		this.maxSpeed = maxSpeed;
		this.speedOffset = speedOffset;
		this.angleOffset = angleOffset;
		this.functionExp = functionExp;
	}


	public float expConverter(float inputValue) {
		// TODO exponentielle umrechnung

		boolean inputSign = false;

		float functionSpeed = 0;
		float functionMaxAngle = 0;


		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)
		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, functionExp); // y(x)
		// =
		// ((x-d)/fMA)^p
		functionSpeed = functionSpeed * (maxSpeed - speedOffset);// y(x) =
		// y(x)*a
		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**
		 * changement de signe
		 */

		if (inputSign == true) {
			outputValue = -outputValue;// y(x) = -y(x)
		}

		if(inputValue < angleOffset) {
			outputValue = 0;
		}

		return outputValue;
	}


	public float linearConverter(float inputValue) {
		// TODO lineare umrechnung

		float slope = 0;
		float intercept = 0;
		boolean inputSign = false;

		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}
		inputValue = Math.abs(inputValue);

		slope = (maxSpeed - speedOffset) / (maxAngle - angleOffset); // a
		intercept = speedOffset - slope * angleOffset; // b

		float outputValue = slope * inputValue + intercept;// y = a*x + b;

		if(inputValue < angleOffset){
			outputValue = 0;
		}
		if(inputSign == true){
			outputValue = -outputValue; // on adapte le signe en fonction de l'angle
		}
		return outputValue;

	}


	public float HeavySideConverter(float inputValue) {

		float outputValue = 0;
		boolean inputSign = false;

		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}
		inputValue = Math.abs(inputValue);

		if ((inputValue > angleOffset) && (inputSign == true)) {
			outputValue = -maxSpeed;
		} 
		if ((inputValue > angleOffset) && (inputSign == false)) {
			outputValue = maxSpeed;
		}
		if(inputValue < angleOffset){
			outputValue = 0;
		}
		return outputValue;

	}


	public float LogarithmConverter(float inputValue) {

		boolean inputSign = false;

		float functionSpeed = 0;
		float functionMaxAngle = 0;

		functionExp = 1/functionExp;

		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)
		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, functionExp); // y(x)
		// =
		// ((x-d)/fMA)^p
		functionSpeed = functionSpeed * (maxSpeed - speedOffset);// y(x) =
		// y(x)*a
		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**
		 * changement de signe
		 */

		if (inputSign == true) {
			outputValue = -outputValue;// y(x) = -y(x)
		}

		if(inputValue < angleOffset) {
			outputValue = 0;
		}

		return outputValue;
	}

	public float getMaxAngle() {
		return maxAngle;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

}
