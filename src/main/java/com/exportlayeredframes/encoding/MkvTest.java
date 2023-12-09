package com.exportlayeredframes.encoding;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

//import com.github.kokorin.jaffree.ffmpeg.*;

public class MkvTest {
	
	public static ByteBuffer captureScreen() {
		Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
		int width = framebuffer.viewportWidth;
		int height = framebuffer.viewportHeight;

		// Create and configure byte buffer
		int totalBytes = width * height * 4;
		ByteBuffer pixelBuffer = BufferUtils.createByteBuffer(totalBytes);
		int pixelBufferPosition = 0;

		// Read pixels from the framebuffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer.fbo);
		GL30.glReadPixels(0, 0, width, height, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, pixelBuffer);
		pixelBuffer.position(pixelBufferPosition);

		return pixelBuffer;
	}

	public static BufferedImage buteBufferToBufferedImage(ByteBuffer buffer, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int idx = (y*width+x)*4;
				int r = buffer.get(idx) & 0xFF;
				int g = buffer.get(idx+1) & 0xFF;
				int b = buffer.get(idx+2) & 0xFF;
				int a = buffer.get(idx+3) & 0xFF;

				int argb = (a << 24)|(r << 16)|(g << 8)|b;
				image.setRGB(x,y,argb);
			}
		}

		return image;
	}

}
