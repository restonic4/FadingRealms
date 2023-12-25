package me.restonic4.fading_realms.util.Camera;

public class CutsceneActionEntry {
    private final float time;
    private final CutsceneAction action;
    private boolean executed;

    public CutsceneActionEntry(float time, CutsceneAction action) {
        this.time = time;
        this.action = action;
        this.executed = false;
    }

    public float getTime() {
        return time;
    }

    public CutsceneAction getAction() {
        return action;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
}