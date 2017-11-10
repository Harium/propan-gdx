package com.harium.propan.core.animation;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.harium.etyl.core.animation.script.SingleIntervalAnimation;

public class ScaleUniform3D extends SingleIntervalAnimation {

    private ModelInstance target;

    public ScaleUniform3D(long time) {
        super(time);
    }

    public ScaleUniform3D(long delay, long time) {
        super(delay, time);
    }

    public ScaleUniform3D(ModelInstance target, long time) {
        super(time);
        this.target = target;
    }

    @Override
    public void update(double value) {
        Vector3 scale = new Vector3((float) value, (float) value, (float) value);
        target.transform.scl(scale);
    }
}
