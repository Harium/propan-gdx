package com.harium.propan.commons.context.application;

import com.harium.etyl.commons.context.load.LoadApplication;
import com.harium.propan.core.context.ApplicationGL;

public abstract class DefaultLoadApplicationGL extends ApplicationGL implements LoadApplication {

    protected String phrase = "Loading...";

    protected String percent = "0%";

    public DefaultLoadApplicationGL(int w, int h) {
        super(w, h);
    }
}
