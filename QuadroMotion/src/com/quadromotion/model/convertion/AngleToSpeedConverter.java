package com.quadromotion.model.convertion;

// converts the input angle value to the output speed 
public class AngleToSpeedConverter implements IAngleToSpeedConverter {

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

	@Override
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

		if (inputSign) {
			outputValue = -outputValue;// y(x) = -y(x)
		}
		return outputValue;
	}

	@Override
	public float linearConverter(float inputValue) {
		// TODO lineare umrechnung

		float slope = 0;
		float intercept = 0;

		slope = (maxSpeed - speedOffset) / (maxAngle - angleOffset); // a
		intercept = speedOffset - slope * angleOffset; // b

		float outputValue = slope * inputValue + intercept;// y = a*x + b;

		return outputValue;

	}

	@Override
	public float HeavySideConverter(float inputValue) {

		if (inputValue > angleOffset) {
			return maxSpeed;
		} 
		return 0;

	}

	@Override
	public float LogarithmConverter(float inputValue) {

		boolean inputSign = false;

		float functionSpeed = 0;
		float functionMaxAngle = 0;

		/**
		 * Ce bordel est a v�rifi� Math�matiquement
		 */
		functionExp = 1 / functionExp;

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

		if (inputSign) {
			outputValue = -outputValue;// y(x) = -y(x)
		}
		return  outputValue;
	}

	public float getMaxAngle() {
		return maxAngle;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

}
