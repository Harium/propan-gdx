package com.badlogic.gdx.backends.lwjgl;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.harium.etyl.util.PathHelper;
import com.harium.propan.core.loader.MeshLoader;
import com.harium.util.loader.NativeLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.badlogic.gdx.utils.SharedLibraryLoader.*;

public class GdxTestHelper {

    private static boolean loaded = false;

    private static LwjglApplication application;

    public static void dispose() {
        // Attempt to close the frame
        Gdx.app.exit();
        destroyWindow();
    }

    public static void init() {
        if (loaded) {
            return;
        }
        loaded = true;
        loadNatives();
        initLoaders();

        LwjglApplicationConfiguration.disableAudio = true;
        application = new EmptyApplication();
        Gdx.graphics.setVSync(false);

        Gdx.app = application;
        Gdx.gl20 = new LwjglGL20();
        Gdx.gl30 = new LwjglGL30();
        Gdx.gl = Gdx.gl20;
        Gdx.files = application.getFiles();
        createWindow();
    }

    private static void loadNatives() {
        GdxNativesLoader.disableNativesLoading = true;

        NativeLoader.load("libs", "gdx");

        // Code from LwjglNativesLoader
        SharedLibraryLoader loader = new SharedLibraryLoader();
        File nativesDir = null;
        try {
            if (isWindows) {
                nativesDir = loader.extractFile(is64Bit ? "lwjgl64.dll" : "lwjgl.dll", null).getParentFile();
                if (!LwjglApplicationConfiguration.disableAudio)
                    loader.extractFileTo(is64Bit ? "OpenAL64.dll" : "OpenAL32.dll", nativesDir);
            } else if (isMac) {
                nativesDir = loader.extractFile("liblwjgl.dylib", null).getParentFile();
                if (!LwjglApplicationConfiguration.disableAudio) loader.extractFileTo("openal.dylib", nativesDir);
            } else if (isLinux) {
                nativesDir = loader.extractFile(is64Bit ? "liblwjgl64.so" : "liblwjgl.so", null).getParentFile();
                if (!LwjglApplicationConfiguration.disableAudio)
                    loader.extractFileTo(is64Bit ? "libopenal64.so" : "libopenal.so", nativesDir);
            }
        } catch (Throwable ex) {
            throw new GdxRuntimeException("Unable to extract LWJGL natives.", ex);
        }
        System.setProperty("org.lwjgl.librarypath", nativesDir.getAbsolutePath());
    }

    private static void initLoaders() {
        // Init Loaders
        String path = PathHelper.currentDirectory() + "assets/";
        MeshLoader.getInstance().setUrl(path);
    }

    private static void createWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(1, 1));
            Display.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(GdxTestHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void destroyWindow() {
        Display.destroy();
    }

    static class EmptyApplication extends LwjglApplication {
        public EmptyApplication() {
            super(APPLICATION_LISTENER, "Test Window", 200, 60);
        }

        @Override
        void mainLoop() {
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

}
