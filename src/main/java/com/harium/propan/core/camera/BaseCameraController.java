package com.harium.propan.core.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public abstract class BaseCameraController implements CameraController {

    protected Camera camera;

    protected float speed = 0.05f;
    protected float turnSpeed = 1;

    protected boolean right = false;
    protected boolean left = false;
    protected boolean forward = false;
    protected boolean backward = false;

    protected boolean turnRight = false;
    protected boolean turnLeft = false;
    protected boolean turnUp = false;
    protected boolean turnDown = false;

    protected boolean fly = false;
    protected boolean dive = false;

    public BaseCameraController() {
        super();
    }

    public BaseCameraController(Camera camera) {
        super();
        this.camera = camera;
    }

    public void update(long now) {
        if (right) {
            Vector3 right = new Vector3(camera.direction).crs(camera.up).nor();
            right.scl(speed);
            camera.position.add(right);
            camera.update();
        }

        if (left) {
            Vector3 left = new Vector3(camera.direction).crs(camera.up).nor();
            left.scl(-speed);
            camera.position.add(left);
            camera.update();
        }

        if (forward) {
            Vector3 forward = new Vector3(camera.direction).nor();
            forward.scl(speed);
            camera.position.add(forward);
            camera.update();
        }

        if (backward) {
            Vector3 backward = new Vector3(camera.direction).nor();
            backward.scl(-speed);
            camera.position.add(backward);
            camera.update();
        }

        if (turnRight) {
            camera.direction.rotate(camera.up, -turnSpeed);
            camera.update();
        }
        if (turnLeft) {
            camera.direction.rotate(camera.up, turnSpeed);
            camera.update();
        }
        if (turnUp) {
            Vector3 sideAxis = new Vector3(camera.direction).crs(camera.up).nor();
            camera.direction.rotate(sideAxis, turnSpeed);
            camera.update();
        }
        if (turnDown) {
            Vector3 sideAxis = new Vector3(camera.direction).crs(camera.up).nor();
            camera.direction.rotate(sideAxis, -turnSpeed);
            camera.update();
        }

        if (fly) {
            Vector3 direction = new Vector3(camera.up).nor();
            direction.scl(speed);
            camera.position.add(direction);
            camera.update();
        }

        if (dive) {
            Vector3 direction = new Vector3(camera.up).nor();
            direction.scl(-speed);
            camera.position.add(direction);
            camera.update();
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
