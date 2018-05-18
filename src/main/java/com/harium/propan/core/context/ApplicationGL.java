package com.harium.propan.core.context;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.harium.etyl.commons.context.Context;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.propan.commons.context.PropanApplication;
import com.harium.propan.commons.context.application.DefaultLoadApplicationGL;
import com.harium.propan.core.graphics.Graphics3D;


public abstract class ApplicationGL extends Context implements PropanApplication {

    protected Ray ray;
    protected Camera camera;
    protected Vector3 forwardVector = new Vector3();
    protected DefaultLoadApplicationGL loadApplicationGL;
    private Vector3 worldCoords = new Vector3();

    public ApplicationGL(int w, int h) {
        super(w, h);
        // clear calls should be handled by default
        clearBeforeDraw = false;
    }

    protected boolean shouldPreDrawing = true;

    @Override
    public void load() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void resize(int width, int height) {

    }

    public void preDraw(Graphics g) {

    }

    public void preDisplay(Graphics3D graphics3D) {
        // TODO Auto-generated method stub
    }

    public abstract void display(Graphics3D graphics3D);

    public void dispose(Graphics3D graphics3D) {
        // TODO Auto-generated method stub
    }

    public void pause(Graphics3D graphics3D) {
        // TODO Auto-generated method stub
    }

    public void resume(Graphics3D graphics3D) {
        // TODO Auto-generated method stub
    }

    public void reshape(Graphics3D graphics3D, int x, int y, int w, int h) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateMouse(PointerEvent event) {

    }

    @Override
    public void updateKeyboard(KeyEvent event) {

    }

    public boolean shouldPreDrawing() {
        return shouldPreDrawing;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setupCamera(float[] up, float[] lookAt) {
        camera.up.set(-up[0], up[2], up[1]);
        forwardVector.x = -lookAt[0];
        forwardVector.y = lookAt[2];
        forwardVector.z = lookAt[1];

        camera.lookAt(forwardVector);
        camera.update();

        ray = camera.getPickRay(w / 2, h / 2);
    }

    public void updateCamera(Matrix4 matrix) {
        camera.transform(matrix);
        camera.update();
        forwardVector = camera.direction;
        ray = camera.getPickRay(w / 2, h / 2);
    }

    protected Vector2 project(Vector3 worldCoords) {
        return project(worldCoords, new Vector2());
    }

    protected Vector2 project(Vector3 worldCoordinates, Vector2 out) {
        this.worldCoords.set(worldCoordinates);
        this.worldCoords.prj(camera.combined);
        out.x = w * (this.worldCoords.x + 1) / 2 + x;
        out.y = h * (this.worldCoords.y + 1) / 2 + y;
        return out;
    }

    public DefaultLoadApplicationGL getLoadApplicationGL() {
        return loadApplicationGL;
    }
}
