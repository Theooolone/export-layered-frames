package com.exportlayeredframes;

// My own classes
import com.exportlayeredframes.client.LayeredScreenshotHandler;
import com.exportlayeredframes.gui.SettingsScreen;
// Fabric classes
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
// Minecraft classes
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

public class ExportLayeredFrames implements ClientModInitializer {

	private static MinecraftClient client = MinecraftClient.getInstance();

	// F7 (default) to take layered screenshot
	private static final KeyBinding SCREENSHOT_KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.exportlayeredframes.layered_screenshot", // key for en_us.json file
			InputUtil.Type.KEYSYM, // I dont even know man
			GLFW.GLFW_KEY_F7, // the key u press
			"key.categories.exportlayeredframes" // the category in the controls page
	));

	// F8 (default) to take layered video
	private static final KeyBinding VIDEO_KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.exportlayeredframes.layered_video",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_F8,
			"key.categories.exportlayeredframes"
	));

	// K (default) to open settings
	private static final KeyBinding SETTINGS_KEY_BINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.exportlayeredframes.settings_menu",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_K,
			"key.categories.exportlayeredframes"
	));
	
	@Override
	public void onInitializeClient() {
		
		LayeredScreenshotHandler.onInitialize();
		
		// Check for key presses at end of each tick
		ClientTickEvents.END_CLIENT_TICK.register(context -> {
			// Listen for screenshot taken event
			if (SCREENSHOT_KEY_BINDING.wasPressed()) {
				LayeredScreenshotHandler.singleScreenshot(true);
			}
			if (VIDEO_KEY_BINDING.wasPressed()) {
				LayeredScreenshotHandler.singleScreenshot(true);
			}
			// Listen for settins menu event
			if (SETTINGS_KEY_BINDING.wasPressed()) {
				client.setScreen(new SettingsScreen());
			}
		});
		
	}
}