package com.harium.propan.core.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.harium.etyl.commons.event.KeyEvent;

public class FlyView {

    protected Camera camera;

    protected float speed = 0.5f;
    protected float turnSpeed = 1f;

    protected boolean up = false;
    protected boolean down = false;
    protected boolean left = false;
    protected boolean right = false;

    protected boolean forward = false;
    protected boolean backward = false;
    protected boolean turnLeft = false;
    protected boolean turnRight = false;

    public FlyView(Camera camera) {
        this.camera = camera;
    }

    public void update(long now) {
        if (up && !down) {
            camera.position.y += speed;
            camera.update();
        }
        if (down && !up) {
            camera.position.y -= speed;
            camera.update();
        }
        if (left && !right) {
            Vector3 ortho = new Vector3(Vector3.Y);
            ortho.crs(camera.direction);
            ortho.nor();
            ortho.scl(speed);

            camera.position.add(ortho);
            camera.update();
        }

        if (right && !left) {
            Vector3 ortho = new Vector3(Vector3.Y);
            ortho.crs(camera.direction);
            ortho.nor();
            ortho.scl(-speed);

            camera.position.add(ortho);
            camera.update();
        }

        if (turnRight && !turnLeft) {
            camera.rotate(Vector3.Y, -turnSpeed);
            camera.update();
        }

        if (turnLeft && !turnRight) {
            camera.rotate(Vector3.Y, turnSpeed);
            camera.update();
        }

        if (forward && !backward) {
            Vector3 d = new Vector3(camera.direction);
            d.nor();
            d.scl(speed);
            camera.position.add(d);
            camera.update();
        }
        if (backward && !forward) {
            Vector3 d = new Vector3(camera.direction);
            d.nor();
            d.scl(-speed);
            camera.position.add(d);
            camera.update();
        }
    }

    public void updateKeyboard(KeyEvent event) {
        if (event.isKeyDown(KeyEvent.VK_W)) {
            forward = true;
        } else if (event.isKeyUp(KeyEvent.VK_W)) {
            forward = false;
        }
        if (event.isKeyDown(KeyEvent.VK_S)) {
            backward = true;
        } else if (event.isKeyUp(KeyEvent.VK_S)) {
            backward = false;
        }
        if (event.isKeyDown(KeyEvent.VK_A)) {
            left = true;
        } else if (event.isKeyUp(KeyEvent.VK_A)) {
            left = false;
        }
        if (event.isKeyDown(KeyEvent.VK_D)) {
            right = true;
        } else if (event.isKeyUp(KeyEvent.VK_D)) {
            right = false;
        }

        if (event.isKeyDown(KeyEvent.VK_UP)) {
            up = true;
        } else if (event.isKeyUp(KeyEvent.VK_UP)) {
            up = false;
        }
        if (event.isKeyDown(KeyEvent.VK_DOWN)) {
            down = true;
        } else if (event.isKeyUp(KeyEvent.VK_DOWN)) {
            down = false;
        }
        if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
            turnRight = true;
        } else if (event.isKeyUp(KeyEvent.VK_RIGHT)) {
            turnRight = false;
        }
        if (event.isKeyDown(KeyEvent.VK_LEFT)) {
            turnLeft = true;
        } else if (event.isKeyUp(KeyEvent.VK_LEFT)) {
            turnLeft = false;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getTurnSpeed() {
        return turnSpeed;
    }

    public void setTurnSpeed(float turnSpeed) {
        this.turnSpeed = turnSpeed;
    }
}
