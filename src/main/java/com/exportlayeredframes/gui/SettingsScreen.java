package com.exportlayeredframes.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;


@Environment(EnvType.CLIENT)
public class SettingsScreen extends Screen {

	public SettingsScreen() {
		super(Text.translatable("exportlayeredframes.settingsmenu.title"));
	}

	public ButtonWidget button1;

	@Override
	protected void init() {
		button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {})
			.dimensions(width/2 - 205, 20, 200, 20)
			.tooltip(Tooltip.of(Text.literal("Tooltip :D")))
			.build();
		addDrawableChild(button1);
	}
}
