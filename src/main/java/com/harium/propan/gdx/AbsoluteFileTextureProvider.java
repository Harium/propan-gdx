package com.harium.propan.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;

public class AbsoluteFileTextureProvider implements TextureProvider {
    private Texture.TextureFilter minFilter, magFilter;
    private Texture.TextureWrap uWrap, vWrap;
    private boolean useMipMaps;

    public AbsoluteFileTextureProvider () {
        minFilter = magFilter = Texture.TextureFilter.Linear;
        uWrap = vWrap = Texture.TextureWrap.Repeat;
        useMipMaps = false;
    }

    public AbsoluteFileTextureProvider (Texture.TextureFilter minFilter, Texture.TextureFilter magFilter, Texture.TextureWrap uWrap,
                                       Texture.TextureWrap vWrap, boolean useMipMaps) {
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        this.uWrap = uWrap;
        this.vWrap = vWrap;
        this.useMipMaps = useMipMaps;
    }

    @Override
    public Texture load (String fileName) {
        Texture result = new Texture(Gdx.files.absolute(fileName), useMipMaps);
        result.setFilter(minFilter, magFilter);
        result.setWrap(uWrap, vWrap);
        return result;
    }
}
