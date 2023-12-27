package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class WhiteScreen extends FixedSceen {
    public WhiteScreen() {
        super(Component.literal("White"));
    }

    @Override
    protected void init() {
        ImageWidget image = new ImageWidget(0, 0, width, height, new ResourceLocation("fading_realms", "textures/gui/white.png"));

        addRenderableWidget(image);
    }
}
