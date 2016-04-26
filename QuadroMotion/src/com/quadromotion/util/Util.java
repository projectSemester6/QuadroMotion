package com.quadromotion.util;

/**
 * This class contains several usable util
 * @author Gabriel
 *
 */
public class Util {
	
	public float randomWithRange(float min, float max) {
		float range = Math.abs(max - min);
		return (float) (Math.random() * range) + (min <= max ? min : max);
	}

	public float limit(float i, float min, float max) {
		if (i < min)
			return min;
		else if (i > max)
			return max;
		return i;
	}

}
