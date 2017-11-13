package com.harium.propan.core.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.harium.etyl.loader.Loader;
import com.harium.etyl.util.PathHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MeshLoader extends Loader {

    private static MeshLoader instance = null;

    private Map<String, ModelLoader> loaders = new HashMap<>();

    public static final String OBJ = "obj";

    public static MeshLoader getInstance() {
        if (instance == null) {
            instance = new MeshLoader();
        }

        return instance;
    }

    private MeshLoader() {
        super();
        setFolder("models/");

        loaders.put(OBJ, new ObjLoader());
    }

    public Model loadModel(String path) {
        String extension = PathHelper.getExtension(path).toLowerCase();
        ModelLoader loader = loaders.get(extension);
        return loader.loadModel(Gdx.files.internal(getFolder() + path));
    }

    public ModelLoader getLoader(String extension) {
        return loaders.get(extension);
    }

    public void addLoader(String extension, ModelLoader loader) {
        loaders.put(extension, loader);
    }

    public Set<String> supportedExtensions() {
        return loaders.keySet();
    }

}