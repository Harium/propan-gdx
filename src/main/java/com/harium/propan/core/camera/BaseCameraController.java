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
            strafe(speed);
        }

        if (left) {
            strafe(-speed);
        }

        if (forward) {
            moveAhead(speed);
        }

        if (backward) {
            moveAhead(-speed);
        }

        if (turnRight) {
            turnYaw(-turnSpeed);
        }
        if (turnLeft) {
            turnYaw(turnSpeed);
        }
        if (turnUp) {
            turnPitch(turnSpeed);
        }
        if (turnDown) {
            turnPitch(-turnSpeed);
        }

        if (fly) {
            moveUp(speed);
        }

        if (dive) {
            moveUp(-speed);
        }
    }

    public void strafe(float amount) {
        Vector3 right = new Vector3(camera.direction).crs(camera.up).nor();
        right.scl(amount);
        camera.position.add(right);
        camera.update();
    }

    public void moveUp(float amount) {
        Vector3 direction = new Vector3(camera.up).nor();
        direction.scl(amount);
        camera.position.add(direction);
        camera.update();
    }

    public void turnPitch(float degrees) {
        Vector3 sideAxis = new Vector3(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(sideAxis, degrees);
        camera.update();
    }

    public void turnYaw(float degrees) {
        camera.direction.rotate(camera.up, degrees);
        camera.update();
    }

    public void moveAhead(float amount) {
        Vector3 backward = new Vector3(camera.direction).nor();
        backward.scl(amount);
        camera.position.add(backward);
        camera.update();
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
