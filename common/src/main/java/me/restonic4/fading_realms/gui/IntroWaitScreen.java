package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class IntroWaitScreen extends Screen {
    public IntroWaitScreen() {
        super(Component.literal("IntroWait"));
    }

    public ImageWidget background;
    public ImageWidget title;
    public ImageWidget waitingText;

    @Override
    protected void init() {
        int title_w = (int)((1024f / 2) / 1.5f);
        int title_h = (int)((256f / 2) / 1.5f);

        int title_x = (int)((width / 2f) - (title_w / 2));
        int title_y = (int)(0f + (title_h / 2));

        int waitingText_w = (int)((1024f / 2) / 2.5f);
        int waitingText_h = (int)((256f / 2) / 2.5f);

        int waitingText_x = (int)((width / 2f) - (waitingText_w / 2));
        int waitingText_y = (int)(height - waitingText_h - (waitingText_h / 2f));

        background = new ImageWidget(0, 0, width, height, new ResourceLocation("fading_realms", "textures/gui/WaitIntroBackground.png"));
        title = new ImageWidget(title_x, title_y, title_w, title_h, new ResourceLocation("fading_realms", "textures/gui/title.png"));
        waitingText = new ImageWidget(waitingText_x, waitingText_y, waitingText_w, waitingText_h, new ResourceLocation("fading_realms", "textures/gui/waitingText.png"));

        addRenderableWidget(background);
        addRenderableWidget(title);
        addRenderableWidget(waitingText);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean handleComponentClicked(@Nullable Style style) {
        return false;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
