package com.harium.propan;

import com.harium.etyl.BaseEngine;
import com.harium.etyl.core.Engine;
import com.harium.etyl.loader.FontLoader;
import com.harium.etyl.loader.MultimediaLoader;
import com.harium.etyl.loader.image.ImageLoader;
import com.harium.propan.core.CoreGL;
import com.harium.propan.core.context.ApplicationGL;
import com.harium.propan.core.loader.MeshLoader;

public abstract class Propan extends BaseEngine<CoreGL> implements Engine<ApplicationGL> {

    private ApplicationGL application;

    public Propan(int w, int h) {
        super(w, h);
        addLoader(ImageLoader.getInstance());
        addLoader(FontLoader.getInstance());
        addLoader(MultimediaLoader.getInstance());
        addLoader(MeshLoader.getInstance());
    }

    public void init() {
        initialSetup("");

        application = startApplication();
        application.setLoaded(false);
        core.setApplication(application);

        super.init();
    }

    protected CoreGL initCore() {
        return new CoreGL(w, h);
    }

}
