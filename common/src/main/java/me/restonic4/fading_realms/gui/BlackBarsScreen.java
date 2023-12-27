package me.restonic4.fading_realms.gui;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import me.restonic4.fading_realms.util.Camera.Cutscene.Easing;
import me.restonic4.restapi.RestApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.MouseSettingsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BlackBarsScreen extends FixedSceen {
    private float partialTick;
    private long startTime;
    private float durationSeconds;
    private boolean isShown;
    private boolean wantsToClose;

    public BlackBarsScreen() {
        super(Component.literal("Cutscene"));
        reset();
    }

    public void reset() {
        this.partialTick = 0;
        this.startTime = -1;
        this.durationSeconds = 2;
        this.isShown = false;
        this.wantsToClose = false;
    }

    public ImageWidget topBar;
    public ImageWidget downBar;

    public int currentBarHeight;

    @Override
    protected void init() {
        int referenceHeight = 1080;
        int referenceBarHeight = 132;

        currentBarHeight = (height * referenceBarHeight) / referenceHeight;

        topBar = new ImageWidget(0, -currentBarHeight, 1920, currentBarHeight, new ResourceLocation("fading_realms", "textures/gui/BlackBar.png"));
        downBar = new ImageWidget(0, height, 1920, currentBarHeight, new ResourceLocation("fading_realms", "textures/gui/BlackBar.png"));

        addRenderableWidget(topBar);
        addRenderableWidget(downBar);

        reset();
    }

    public BlackBarsScreen setClosing() {
        this.wantsToClose = true;
        this.partialTick = 0;
        this.startTime = -1;
        return this;
    }

    public void update() {
        if (topBar == null || downBar == null) {
            return;
        }

        if (startTime == -1) {
            startTime = System.currentTimeMillis();
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        partialTick = (float) elapsedTime / 1000.0f;

        if (partialTick >= durationSeconds) {
            if (!isShown) {
                topBar.setPosition(0, 0);
                downBar.setPosition(0, height - currentBarHeight);
            }
            else if (wantsToClose) {
                topBar.setPosition(0, -currentBarHeight);
                downBar.setPosition(0, height);
            }

            isShown = true;
        }

        float t = partialTick / durationSeconds;
        t = (float) Easing.quadInOutEasing(t);

        if (!isShown) {
            topBar.setPosition(0, (int) lerp(topBar.getY(), 0, t));
            downBar.setPosition(0, (int) lerp(downBar.getY(), height - currentBarHeight, t));
        }
        else if (wantsToClose) {
            topBar.setPosition(0, (int) lerp(topBar.getY(), -currentBarHeight, t));
            downBar.setPosition(0, (int) lerp(downBar.getY(), height, t));
        }
    }

    private double lerp(double start, double end, double t) {
        return start + t * (end - start);
    }

    public boolean isFinished() {
        return (partialTick >= durationSeconds) && wantsToClose;
    }
}
