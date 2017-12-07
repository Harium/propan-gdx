package com.harium.etyl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.harium.etyl.core.Core;
import com.harium.etyl.ui.GDXWindow;

public abstract class DesktopEngine<T extends Core> {

    protected int w;
    protected int h;
    protected String icon;

    protected Core core;
    private LwjglApplicationConfiguration configuration;

    public DesktopEngine(int w, int h) {
        super();

        this.w = w;
        this.h = h;

        configuration = buildConfiguration();
        core = initCore();
        core.getSession().put(Etyl.WINDOW, new GDXWindow());
    }

    public void init() {
        configuration.width = w;
        configuration.height = h;

        new LwjglApplication(core, configuration);
    }

    protected Core initCore() {
        return new Core(w, h);
    }

    public Core getCore() {
        return core;
    }

    protected LwjglApplicationConfiguration buildConfiguration() {
        return new LwjglApplicationConfiguration();
    }

    public void setTitle(String title) {
        configuration.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void enableFullScreen() {
        configuration.fullscreen = true;
    }

    public void disableFullScreen() {
        configuration.fullscreen = false;
    }
}
