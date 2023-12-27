package me.restonic4.fading_realms.gui;

import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class FixedSceen extends Screen {
    public FixedSceen(Component component) {
        super(Component.literal("FixedScreen_" + component.getString()));
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

    @Override
    public boolean isMouseOver(double d, double e) {
        return false;
    }

    @Override
    public void setFocused(@Nullable GuiEventListener guiEventListener) {
        return;
    }

    @Override
    protected void setInitialFocus(GuiEventListener guiEventListener) {
        return;
    }

    @Override
    protected void changeFocus(ComponentPath componentPath) {
        ComponentPath componentPath1 = this.getCurrentFocusPath();
        if (componentPath1 != null) {
            componentPath1.applyFocus(false);
        }
    }

    @Override
    public void setFocused(boolean bl) {
        super.setFocused(false);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        return;
    }
}
