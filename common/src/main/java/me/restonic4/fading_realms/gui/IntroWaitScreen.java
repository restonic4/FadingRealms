package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

    /*public int titleWidth = (int)(2047f/3f);
    public int titleHeight = (int)(868f/3f);*/
    public int titleWidth = 256;
    public int titleHeight = 44;

    public int title_X = (int)((width / 2f) - (titleWidth / 2f));
    public int title_Y = titleHeight;

    /*public int waitingTextWidth = (int)(2005f/3f);
    public int waitingTextHeight = (int)(282f/3f);*/
    public int waitingTextWidth = 334;
    public int waitingTextHeight = 47;

    public int waitingText_X = (int)((width / 2f) - (waitingTextWidth / 2f));
    public int waitingText_Y = height - waitingTextHeight;

    @Override
    protected void init() {
        background = new ImageWidget(0, 0, width, height, new ResourceLocation("fading_realms", "textures/gui/WaitIntroBackground.png"));
        title = new ImageWidget(title_X, title_Y, titleWidth, titleHeight, new ResourceLocation("fading_realms", "textures/gui/title.png"));
        waitingText = new ImageWidget(waitingText_X, waitingText_Y, waitingTextWidth, waitingTextHeight, new ResourceLocation("fading_realms", "textures/gui/ElegidoText.png"));

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
