package com.harium.propan.commons.context.application;

import com.harium.etyl.commons.context.load.DefaultLoadApplication;
import com.harium.etyl.commons.context.load.LoadApplication;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.propan.core.context.ApplicationGL;
import com.harium.propan.core.graphics.Graphics3D;

public class LoadApplicationGLAdapter extends DefaultLoadApplicationGL implements LoadApplication {

    private DefaultLoadApplication loadApplication;

    public LoadApplicationGLAdapter(DefaultLoadApplication loadApplication) {
        super(loadApplication.getW(), loadApplication.getH());
        this.loadApplication = loadApplication;
        shouldPreDrawing = false;
    }

    @Override
    public void load() {
        loadApplication.load();
    }

    @Override
    public void draw(Graphics g) {
        loadApplication.draw(g);
    }

    @Override
    public void onChangeText(String phrase) {
        loadApplication.onChangeText(phrase);
    }

    @Override
    public void onChangeLoad(float load) {
        loadApplication.onChangeLoad(load);
    }

    @Override
    public void init(Graphics3D g) {

    }

    @Override
    public void display(Graphics3D graphics3D) {

    }
}
