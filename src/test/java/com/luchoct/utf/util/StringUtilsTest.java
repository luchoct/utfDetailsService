package com.luchoct.utf.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

	@Test
	public void padToLengthAddsPadding() {
		assertEquals("aaaahello", StringUtils.getInstance().padToLength("hello", 'a', 9));
	}

	@Test
	public void padToLengthReturnsInput() {
		assertEquals("hello", StringUtils.getInstance().padToLength("hello", 'a', 2));
	}

	@Test
	public void split() {
		assertEquals("he", StringUtils.getInstance().split("hello", 2)[0]);
		assertEquals("ll", StringUtils.getInstance().split("hello", 2)[1]);
		assertEquals("o", StringUtils.getInstance().split("hello", 2)[2]);
	}
}
