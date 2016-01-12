package com.perhab.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Image;
import java.io.InputStream;

import org.junit.Test;

public class SVGImageIOTest {
	@Test
	public void testSVGtoImage() {
		InputStream stream = SVGImageIOTest.class.getResourceAsStream("test.svg");
		Image image = SVGImageIO.read(stream);
		assertNotNull("Didn't get an image from svg input stream", image);
		assertEquals("Image should have been 128 pixels high.", 128, image.getHeight(null));
		assertEquals("Image should have been 128 pixels wide.", 128, image.getWidth(null));
	}
}
