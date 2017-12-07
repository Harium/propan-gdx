package com.harium.propan;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogamp.JoglAwtApplication;
import com.badlogic.gdx.backends.jogamp.JoglFiles;
import com.badlogic.gdx.backends.jogamp.JoglGL20;
import com.badlogic.gdx.backends.jogamp.JoglGL30;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.harium.etyl.util.PathHelper;
import com.harium.propan.core.loader.MeshLoader;

public class GdxTestHelper {

    private static boolean loaded = false;

    private static JoglAwtApplication application;

    public static void dispose() {
        // Attempt to close the frame
        Gdx.app.exit();
    }

    public static void init() {
        if (loaded) {
            return;
        }
        loaded = true;

        // Init Loaders
        String path = PathHelper.currentDirectory() + "assets/";
        MeshLoader.getInstance().setPath(path);

        // Init Gdx
        GdxNativesLoader.load();

        application = new JoglAwtApplication(APPLICATION_LISTENER, "Test Window", 200, 60);
        application.getGLCanvas().getContext().makeCurrent();

        Gdx.app = application;
        Gdx.gl20 = new JoglGL20();
        Gdx.gl30 = new JoglGL30();
        Gdx.gl = Gdx.gl20;
        Gdx.files = new JoglFiles();
    }

    private static final ApplicationListener APPLICATION_LISTENER = new ApplicationListener() {
        @Override
        public void create() {

        }

        @Override
        public void resize(int i, int i1) {

        }

        @Override
        public void render() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void dispose() {

        }
    };
}
