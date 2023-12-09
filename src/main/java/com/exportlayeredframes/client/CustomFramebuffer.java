package com.exportlayeredframes.client;

import net.minecraft.client.gl.Framebuffer;

public class CustomFramebuffer extends Framebuffer {

	public CustomFramebuffer(boolean useDepth) {
		super(useDepth);
	}
	
	public static CustomFramebuffer copyFramebuffer(Framebuffer originalFramebuffer) {
		int width = originalFramebuffer.viewportWidth;
		int height = originalFramebuffer.viewportHeight;
		boolean useDepthAttachment = (originalFramebuffer.getDepthAttachment() != -1);

		// Create a new CustomFramebuffer instance with the depthAttachment setting
		CustomFramebuffer copiedFramebuffer = new CustomFramebuffer(useDepthAttachment);

		// Properly initialize the copied framebuffer with the required dimensions
		copiedFramebuffer.resize(width, height, false);

		copiedFramebuffer.setClearColor(0,0,0,0);
		copiedFramebuffer.setTexFilter(originalFramebuffer.texFilter);

		return copiedFramebuffer;
	}

}
