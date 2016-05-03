package com.quadromotion.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quadromotion.config.OffsetConfig;
import com.quadromotion.model.convertion.Converter;

public class ConverterTest {

	float input = OffsetConfig.MAX_ANGLE_X;
	
	@Test
	public void testExpConverter() {
		Converter convert = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		assertEquals("exponentiell", 50, convert.expConverter(input), 0.5);
	}

	@Test
	public void testLinConverter() {
		Converter convert = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		assertEquals("linear", 50, convert.linearConverter(input), 0.5);
	}

	@Test
	public void testHeavySideConverter() {
		Converter convert = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		assertEquals("heavyside", OffsetConfig.MAX_SPEED_X, convert.heavySideConverter(input), 0);
	}

	@Test
	public void testLogConverter() {
		Converter convert = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		assertEquals("logarithmisch", 50, convert.logarithmConverter(input), 0.5);
	}

}
