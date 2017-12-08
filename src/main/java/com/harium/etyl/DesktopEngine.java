package com.harium.etyl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.harium.etyl.core.Core;
import com.harium.etyl.loader.Loader;
import com.harium.etyl.ui.GDXWindow;
import com.harium.etyl.util.PathHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class DesktopEngine<T extends Core> {

    protected int w;
    protected int h;
    protected String icon;

    protected Core core;
    private LwjglApplicationConfiguration configuration;

    protected List<Loader> loaders;

    public DesktopEngine(int w, int h) {
        super();

        this.w = w;
        this.h = h;

        loaders = new ArrayList<>();

        configuration = buildConfiguration();
        core = initCore();
        core.getSession().put(Etyl.WINDOW, new GDXWindow());
    }

    public void init() {
        configuration.width = w;
        configuration.height = h;

        new LwjglApplication(core, configuration);

        initLoaders();
    }

    protected void initialSetup(String suffix) {
        String path = PathHelper.currentDirectory() + "assets" + File.separator + suffix;

        for (Loader loader : loaders) {
            loader.setPath(path);
        }
    }

    protected void initLoaders() {
        for (Loader loader : loaders) {
            loader.setAssets(core.getAssets());
        }
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

    public void addLoader(Loader loader) {
        loaders.add(loader);
    }
}
