package com.harium.propan.core.camera;

import com.badlogic.gdx.graphics.Camera;
import com.harium.etyl.commons.event.KeyEvent;

public class FPSCameraController extends BaseCameraController {

    public FPSCameraController(Camera camera) {
        super(camera);
    }

    @Override
    public void updateKeyboard(KeyEvent event) {
        if (event.isKeyDown(KeyEvent.VK_D)) {
            right = true;
        } else if (event.isKeyUp(KeyEvent.VK_D)) {
            right = false;
        }
        if (event.isKeyDown(KeyEvent.VK_A)) {
            left = true;
        } else if (event.isKeyUp(KeyEvent.VK_A)) {
            left = false;
        }
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

        if (event.isKeyDown(KeyEvent.VK_UP_ARROW)) {
            turnUp = true;
        } else if (event.isKeyUp(KeyEvent.VK_UP_ARROW)) {
            turnUp = false;
        }
        if (event.isKeyDown(KeyEvent.VK_DOWN_ARROW)) {
            turnDown = true;
        } else if (event.isKeyUp(KeyEvent.VK_DOWN_ARROW)) {
            turnDown = false;
        }
        if (event.isKeyDown(KeyEvent.VK_RIGHT_ARROW)) {
            turnRight = true;
        } else if (event.isKeyUp(KeyEvent.VK_RIGHT_ARROW)) {
            turnRight = false;
        }
        if (event.isKeyDown(KeyEvent.VK_LEFT_ARROW)) {
            turnLeft = true;
        } else if (event.isKeyUp(KeyEvent.VK_LEFT_ARROW)) {
            turnLeft = false;
        }
        if (event.isKeyDown(KeyEvent.VK_SPACE)) {
            fly = true;
        } else if (event.isKeyUp(KeyEvent.VK_SPACE)) {
            fly = false;
        }
        if (event.isKeyDown(KeyEvent.VK_N)) {
            dive = true;
        } else if (event.isKeyUp(KeyEvent.VK_N)) {
            dive = false;
        }
    }

}
