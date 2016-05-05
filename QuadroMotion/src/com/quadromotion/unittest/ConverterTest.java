package com.quadromotion.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quadromotion.config.OffsetConfig;
import com.quadromotion.model.convertion.Converter;

public class ConverterTest {

	float input = OffsetConfig.MAX_ANGLE_X;

	@Test
	public void testExpConverter() {
		Converter convert = new Converter(70, 50, 10, 0, 2.1f);
		assertEquals("exponentiell", 50, convert.expConverter(80), 0.5);
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
		assertEquals("heavyside", OffsetConfig.MAX_SPEED_X,
				convert.heavySideConverter(OffsetConfig.ANGLE_OFFSET_X + 10), 0);
	}

	@Test
	public void testLogConverter() {
		Converter convert = new Converter(OffsetConfig.MAX_ANGLE_X, OffsetConfig.MAX_SPEED_X,
				OffsetConfig.SPEED_OFFSET_X, OffsetConfig.ANGLE_OFFSET_X, OffsetConfig.FUNCTION_EXP_X);
		assertEquals("logarithmisch", 50, convert.logarithmConverter(input), 0.5);
	}

}
