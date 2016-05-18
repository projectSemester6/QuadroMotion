package com.quadromotion.model.convertion;

// converts the input angle value to the output speed 
public class Converter implements IConverter {

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
	public Converter(float maxAngle, float maxSpeed, float speedOffset, float angleOffset, float functionExp) {
		super();
		this.maxAngle = maxAngle;
		this.maxSpeed = maxSpeed;
		this.speedOffset = speedOffset;
		this.angleOffset = angleOffset;
		this.functionExp = functionExp;

	}

	@Override
	public float expConverter(float inputValue) {

		if (Math.abs(inputValue) < angleOffset)
			return 0f;
		if (inputValue > maxAngle)
			inputValue = maxAngle;

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
																	//
		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**
		 * changement de signe
		 */

		if (outputValue > maxSpeed)
			outputValue = maxSpeed;

		if (inputSign) {
			return -outputValue;
			// outputValue = -outputValue;// y(x) = -y(x)
		}

		return outputValue;
	}

	@Override
	public float linearConverter(float inputValue) {
		// TODO lineare umrechnung
		if (Math.abs(inputValue) < angleOffset)
			return 0;
		if (inputValue > maxAngle)
			inputValue = maxAngle;
		boolean inputSign = false;
		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}
		float slope = 0;
		float intercept = 0;

		slope = (maxSpeed - speedOffset) / (maxAngle - angleOffset); // a
		intercept = speedOffset - slope * angleOffset; // b

		float outputValue = slope * Math.abs(inputValue) + intercept;// y = a*x
																		// + b;
		if (outputValue > maxSpeed)
			outputValue = maxSpeed;
		if (inputSign)
			return -outputValue;
		return outputValue;

	}

	@Override
	public float heavySideConverter(float inputValue) {

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
		if (inputValue < angleOffset) {
			outputValue = 0;
		}
		return outputValue;
	}

	@Override
	public float logarithmConverter(float inputValue) {

		boolean inputSign = false;

		float functionSpeed = 0;
		float functionMaxAngle = 0;

		/**
		 * Ce bordel est a v�rifi� Math�matiquement
		 */
		float _functionExp = 1 / functionExp;

		if (inputValue < 0) {
			inputSign = true; // signe n�gatif
		}

		inputValue = Math.abs(inputValue);

		functionMaxAngle = maxAngle - angleOffset; // fMA = (b-d)
		functionSpeed = (float) Math.pow((inputValue - angleOffset) / functionMaxAngle, _functionExp); // y(x)
		// =
		// ((x-d)/fMA)^p
		functionSpeed = functionSpeed * (maxSpeed - speedOffset);// y(x) =
																	// y(x)*a
		float outputValue = functionSpeed + speedOffset; // y(x) = y(x) + c

		/**
		 * changement de signe
		 */

		if (inputSign) {
			outputValue = -outputValue;// y(x) = -y(x)
		}

		if (inputValue < angleOffset)
			outputValue = 0;

		return outputValue;
	}
}
