package com.badlogic.gdx.backends.lwjgl;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.harium.etyl.util.PathHelper;
import com.harium.propan.core.loader.MeshLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GLContext;

public class GdxTestHelper {

    private static boolean loaded = false;

    private static LwjglApplication application;

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

        application = new LwjglApplication(APPLICATION_LISTENER, "Test Window", 200, 60);

        Gdx.app = application;
        Gdx.gl20 = new LwjglGL20();
        Gdx.gl30 = new LwjglGL30();
        Gdx.gl = Gdx.gl20;
        Gdx.files = application.getFiles();
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
