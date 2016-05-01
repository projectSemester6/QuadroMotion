package com.quadromotion.unittest;

import junit.framework.*;

import org.junit.Test;
import com.quadromotion.util.Util;

public class UtilTest extends TestCase {

	@Test
	public void testRandomWithRange() {
		Util u = new Util();
		int max = 50;
		int min = -50;
		float range = Math.abs(max - min);
		assertEquals("random",Math.random()*range,u.randomWithRange(min, max), 200);
	}

	@Test
	public void testLimit() {
		Util u = new Util();
		assertTrue(8 == u.limit(8, 2, 9));
	}

}
