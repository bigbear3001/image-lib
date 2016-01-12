package com.perhab.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;

import lombok.Getter;

/**
 * Small helper utility to read a SVG and transcode it into a AWT Image.
 * @author bigbear3001
 *
 */
public class SVGImageIO {
	/**
	 * Transcoding hints for SVG.
	 */
	private static TranscodingHints hints = initTranscodingHints();

	/**
	 * Read SVG from input stream and transocde into image.
	 * @param stream - input stream to read the SVG from
	 * @return AWT Image transcoded from SVG.
	 */
	public static Image read(InputStream stream) {
		TranscoderInput input = new TranscoderInput(stream);
		SVGImageTranscoder t = new SVGImageTranscoder();
		t.setTranscodingHints(hints);
		try {
			t.transcode(input, null);
			return t.getImage();
		} catch (TranscoderException e) {
			throw new SVGTranscoderException("Error while transcoding SVG", e);
		}
	}
	
	/**
	 * Initialize the TranscodingHints needed for transcoding an SVG to a image.
	 * @return transcoding hints for SVG.
	 */
	private static TranscodingHints initTranscodingHints() {
		TranscodingHints hints = new TranscodingHints();
		hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
		hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
		hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
		return hints;
	}

	/**
	 * SVG to Image Transcoder holding a BufferedImage after transcoding.
	 * @author bigbear3001
	 *
	 */
	private static class SVGImageTranscoder extends ImageTranscoder {
		@Getter
		BufferedImage image;
		
		@Override
		public BufferedImage createImage(int width, int height) {
			return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}

		@Override
		public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {
			image = img;
		}
	}
	
	/**
	 * Runtime exception in case there is an error transcoding the SVG into an image.
	 * @author bigbear3001
	 */
	private static class SVGTranscoderException extends RuntimeException {

		/**
		 * unique serilialization version id.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Initialize a new SVGTranscoderException.
		 * @param message - error message
		 * @param cause - cause of the error
		 */
		public SVGTranscoderException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
