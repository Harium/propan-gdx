package com.harium.propan.gdx;

import com.badlogic.gdx.Gdx;
import com.harium.propan.core.graphics.Graphics3D;

public class GDX3DGraphics implements Graphics3D {

    int w, h;
    float scaleW, scaleH;

    public GDX3DGraphics(int w, int h) {
        this.w = w;
        this.h = h;

        int sw = Gdx.graphics.getWidth();
        int sh = Gdx.graphics.getHeight();

        scaleW = sw / (float) w;
        scaleH = sh / (float) h;
    }

    @Override
    public void glBegin(int mode) {

    }

    @Override
    public void glEnd() {

    }

    @Override
    public void glColor3d(double red, double green, double blue) {

    }

    @Override
    public void glLoadIdentity() {

    }

    @Override
    public int[] getViewPort() {
        return new int[0];
    }

    @Override
    public void glMatrixMode(int mode) {

    }

    @Override
    public void glClear(int mask) {

    }

    @Override
    public void glClearColor(float red, float green, float blue, float alpha) {
        Gdx.gl.glClearColor(red, green, blue, alpha);
    }

    @Override
    public void glClearDepth(float depth) {
        Gdx.gl.glClearDepthf(depth);
    }

    @Override
    public void glEnable(int cap) {
        Gdx.gl.glEnable(cap);
    }

    @Override
    public void glDepthFunc(int func) {

    }

    @Override
    public void glHint(int target, int mode) {

    }

    @Override
    public void glViewport(int x, int y, int width, int height) {
        int nx = (int) (x * scaleW);
        int ny = (int) (y * scaleH);

        int viewportW = (int) (width * scaleW);
        int viewportH = (int) (height * scaleH);

        Gdx.gl.glViewport(nx, h - ny, viewportW, viewportH);
    }

    @Override
    public void glFlush() {

    }
}
