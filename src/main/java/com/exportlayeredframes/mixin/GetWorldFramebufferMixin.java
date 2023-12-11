package com.exportlayeredframes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.LightmapTextureManager;
import org.joml.Matrix4f;


@Mixin(GameRenderer.class)
public class GetWorldFramebufferMixin implements AutoCloseable {
	// Intended result: puts the injectMethod() function after the line containing the WorldRenderer.render(...) function.
	@Inject(method = "renderWorld(FJLnet/minecraft/client/util/math/MatrixStack;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/WorldRenderer;render("
			+ "Lnet/minecraft/client/util/math/MatrixStack;FJZ"
			+ "Lnet/minecraft/client/render/Camera;"
			+ "Lnet/minecraft/client/render/GameRenderer;"
			+ "Lnet/minecraft/client/render/LightmapTextureManager;"
			+ "Lorg/joml/Matrix4f;"
			+ ")V",
			shift = At.Shift.AFTER
		)
	)
	private void injectedMethod(CallbackInfo ci) {
		//TODO: cry
		//TODO: okay now i have finally injected a method AFTER TWO DAYS now i gotta figure out how to get the frame buffer or whatever the hell
		System.out.println("TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST");
	}
}