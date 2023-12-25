package me.restonic4.fading_realms.mixin;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.logging.LogUtils;
import me.restonic4.fading_realms.gui.BlackBarsScreen;
import me.restonic4.fading_realms.util.IMinecraftMixin;
import net.minecraft.SharedConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Minecraft.class, priority = 1001)
public abstract class MinecraftMixin implements IMinecraftMixin {
    @Final @Shadow private static final Logger LOGGER = LogUtils.getLogger();
    @Shadow private Thread gameThread;
    @Shadow @Nullable public Screen screen;
    @Shadow @Nullable public ClientLevel level;
    @Shadow @Nullable public LocalPlayer player;
    @Shadow public final MouseHandler mouseHandler;
    @Shadow private final Window window;
    @Shadow public boolean noRender;
    @Shadow private final SoundManager soundManager;

    @Shadow public abstract void updateTitle();

    public MinecraftMixin(MouseHandler mouseHandler, Window window, SoundManager soundManager) {
        this.mouseHandler = mouseHandler;
        this.window = window;
        this.soundManager = soundManager;
    }

    /**
     * @author restonic4
     * @reason Cutscene black bars cursor thingy fix
     */
    @Overwrite
    public void setScreen(@Nullable Screen screen) {
        if (SharedConstants.IS_RUNNING_IN_IDE && Thread.currentThread() != this.gameThread) {
            LOGGER.error("setScreen called from non-game thread");
        }
        if (this.screen != null) {
            this.screen.removed();
        }
        if (screen == null && this.level == null) {
            screen = new TitleScreen();
        } else if (screen == null && this.player.isDeadOrDying()) {
            if (this.player.shouldShowDeathScreen()) {
                screen = new DeathScreen(null, this.level.getLevelData().isHardcore());
            } else {
                this.player.respawn();
            }
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.added();
        }
        BufferUploader.reset();
        if (screen != null) {
            if (!screen.getTitle().getString().toLowerCase().contains("cutscene")) {
                this.mouseHandler.releaseMouse();
                KeyMapping.releaseAll();
            }
            screen.init(Minecraft.getInstance(), this.window.getGuiScaledWidth(), this.window.getGuiScaledHeight());
            this.noRender = false;
        } else {
            this.soundManager.resume();
            this.mouseHandler.grabMouse();
        }
        this.updateTitle();
    }
}
