package com.exportlayeredframes.client;

import net.minecraft.client.MinecraftClient;

import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import net.minecraft.client.gl.Framebuffer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class LayeredScreenshotHandler {

	private static MinecraftClient client = MinecraftClient.getInstance();

	private static Boolean scrStart = false;
	private static Boolean scrAfterSetup = false;
	private static Boolean scrBeforeEntities = false;
	private static Boolean scrAfterEntities = false;
	private static Boolean scrBeforeDebugRender = false;
	private static Boolean scrAfterTranslucent = false;
	private static Boolean scrLast = false;
	private static Boolean scrEnd = false;
	

	private static String screenshotName;

	private static void renderClient(MinecraftClient client, String name, Boolean bool) {
		if (bool) {
				Framebuffer fb = client.getFramebuffer();
				InitialiseRenderlayer(name, fb);
			}
	}

	// Takes a screenshot midway thru the render process
	public static void onInitialize() {
		WorldRenderEvents.START.register(context ->              {renderClient(context.gameRenderer().getClient(), "start", scrStart); scrStart = false;});
		WorldRenderEvents.AFTER_SETUP.register(context ->        {renderClient(context.gameRenderer().getClient(), "after-setup", scrAfterSetup); scrAfterSetup = false;});
		WorldRenderEvents.BEFORE_ENTITIES.register(context ->    {renderClient(context.gameRenderer().getClient(), "before-entities", scrBeforeEntities); scrBeforeEntities = false;});
		WorldRenderEvents.AFTER_ENTITIES.register(context ->     {renderClient(context.gameRenderer().getClient(), "after-entities", scrAfterEntities); scrAfterEntities = false;});
		WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context ->{renderClient(context.gameRenderer().getClient(), "before-debug-render", scrBeforeDebugRender); scrBeforeDebugRender = false;});
		WorldRenderEvents.AFTER_TRANSLUCENT.register(context ->  {renderClient(context.gameRenderer().getClient(), "after-translucent", scrAfterTranslucent); scrAfterTranslucent = false;});
		WorldRenderEvents.LAST.register(context ->               {renderClient(context.gameRenderer().getClient(), "last", scrLast); scrLast = false;});
		WorldRenderEvents.END.register(context ->                {renderClient(context.gameRenderer().getClient(), "end", scrEnd); scrEnd = false;});
	}

	private static void InitialiseRenderlayer(String type, Framebuffer f) {
		framebufferScreenshot(client.getFramebuffer(), type);
	}


	// Takes a single layered screenshot
	public static void singleScreenshot(Boolean chatmsg) {
		screenshotName = "_" + getFormattedDateTime() + ".webp";

		scrStart = true;
		scrAfterSetup = true;
		scrBeforeEntities = true;
		scrAfterEntities = true;
		scrBeforeDebugRender = true;
		scrAfterTranslucent = true;
		scrLast = true;
		scrEnd = true;

		if (chatmsg) {
			client.player.sendMessage(Text.translatable("exportlayeredframes.screenshot"));
			screenshotmsg("start");
			screenshotmsg("after-setup");
			screenshotmsg("before-entities");
			screenshotmsg("after-entities");
			screenshotmsg("before-debug-render");
			screenshotmsg("after-translucent");
			screenshotmsg("last");
			screenshotmsg("end");
		}
	}

	
	// Returns the date and time in the same format as vanilla screenshot filenames
	private static String getFormattedDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");
		LocalDateTime currentTime = LocalDateTime.now();
		return currentTime.format(formatter);
	}

	// Sends a message in chat containing a link to the file with the filename being type+screenshotName
	private static void screenshotmsg(String type) {
		client.player.sendMessage(Text.literal(type+screenshotName).setStyle(
			Style.EMPTY.withFormatting(Formatting.UNDERLINE).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "screenshots/"+type+screenshotName))
		));
	}

	// Uses ScreenshotRecorder to save the current framebuffer
	private static void framebufferScreenshot(Framebuffer framebuffer, String type) {
		ScreenshotRecorder.saveScreenshot(client.runDirectory, type+screenshotName, framebuffer, (text) -> {});
	}
}