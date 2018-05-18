package com.harium.propan.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.context.load.GenericLoadApplication;
import com.harium.etyl.commons.context.load.LoadApplication;
import com.harium.etyl.core.GDXCore;
import com.harium.propan.commons.context.application.DefaultLoadApplicationGL;
import com.harium.propan.commons.context.application.LoadApplicationGLAdapter;
import com.harium.propan.core.context.ApplicationGL;
import com.harium.propan.gdx.GDX3DGraphics;


public class CoreGL extends GDXCore<ApplicationGL> {

    protected GDX3DGraphics graphics3D;
    protected DefaultLoadApplicationGL loadContext;

    private boolean loadingGL = false;

    public CoreGL(int w, int h) {
        super(w, h);
    }

    @Override
    public void create() {
        super.create();
        graphics3D = new GDX3DGraphics(w, h);
    }

    @Override
    public void render() {
        createFirstApplication();
        long now = System.currentTimeMillis();
        update(now);

        updateLoading(loadContext);

        renderContext(context);
        drawModules(graphics);

        graphics.flush();
    }

    @Override
    protected void renderContext(Context context) {
        ApplicationGL applicationGL = ((ApplicationGL) context);

        if (applicationGL.isClearBeforeDraw()) {
            float r = ((applicationGL.getBackgroundColor() >> 16) & 0xff) / 0xff;
            float g = ((applicationGL.getBackgroundColor() >> 8) & 0xff) / 0xff;
            float b = ((applicationGL.getBackgroundColor()) & 0xff) / 0xff;

            Gdx.gl.glClearColor(r, g, b, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        }

        // Setup 2D
        // Pre drawing
        if (applicationGL.shouldPreDrawing()) {
            setup2D();
            applicationGL.preDraw(graphics);
        }

        applicationGL.preDisplay(graphics3D);
        applicationGL.display(graphics3D);

        // Setup 2D
        setup2D();
        applicationGL.draw(graphics);
    }

    @Override
    protected void buildLoadContext() {
        ApplicationGL applicationGL = (ApplicationGL) application;

        if (applicationGL.getLoadApplicationGL() != null) {
            loadContext = applicationGL.getLoadApplicationGL();
        } else {
            // Load compatibility LoadApplication
            if (applicationGL.getLoadApplication() != null) {
                loadContext = new LoadApplicationGLAdapter(applicationGL.getLoadApplication());
            } else {
                loadContext = new LoadApplicationGLAdapter(new GenericLoadApplication(w, h));
            }
        }

        // Force load the loading screen
        loadContext.setSession(session);
        loadContext.setCamera(orthoCamera);
        loadContext.load();

        // Assync load
        assetManager.finishLoading();
        assetManager.checkResources();

        context = loadContext;
    }

    protected void updateLoading(LoadApplication loadContext) {
        if (loadingGL && !assetManager.update()) {
            float loading = application.getLoading();
            loadContext.onChangeLoad(loading);
            if (loading == 100) {
                loadingGL = false;
            }
            assetManager.checkResources();
        } else if (!changed && !loadingGL) {
            assetManager.checkResources();
            context = application;
            context.initGraphics(graphics);
            initContext = true;
            changed = true;
        } else {
            // When Application is already loaded, but it want to load something on demand
            if (!assetManager.update()) {
                context.setLoaded(false);
                assetManager.checkResources();
            } else if (!context.isLoaded()) {
                context.setLoaded(true);
                assetManager.checkResources();
            }
        }
    }

    private void setup2D() {
        orthoCamera.update();
        graphics.setProjectionMatrix(orthoCamera.combined);
        viewport.apply();
    }

    @Override
    protected void loadApplication() {
        super.loadApplication();

        loadingGL = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {

                        ApplicationGL applicationGL = (ApplicationGL) application;
                        applicationGL.init(graphics3D);
                        if (applicationGL.getCamera() == null) {
                            applicationGL.setCamera(orthoCamera);
                        }
                        loadingGL = false;
                    }
                });
            }
        }).start();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        application.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        ((ApplicationGL) application).pause(graphics3D);
    }

    @Override
    public void resume() {
        super.resume();
        ((ApplicationGL) application).resume(graphics3D);
    }

    @Override
    public void dispose() {
        super.dispose();
        ((ApplicationGL) application).dispose(graphics3D);
    }

}
