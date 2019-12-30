package com.harium.propan.core.camera;

import com.harium.etyl.commons.event.KeyEvent;

public interface CameraController {

    void update(long now);

    void updateKeyboard(KeyEvent event);

}
