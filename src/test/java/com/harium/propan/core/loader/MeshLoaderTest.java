package com.harium.propan.core.loader;

import com.badlogic.gdx.graphics.g3d.Model;
import com.harium.propan.GdxTestHelper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

public class MeshLoaderTest {

    @BeforeClass
    public static void setUp() {
        GdxTestHelper.init();
    }

    @AfterClass
    public static void tearDown() {
        GdxTestHelper.dispose();
    }

    @Test
    public void init() {
        Assert.assertNotNull(MeshLoader.getInstance());
    }

    @Test
    public void testSupportedExtensions() {
        Set<String> supportedExtensions = MeshLoader.getInstance().supportedExtensions();

        Assert.assertEquals(1, supportedExtensions.size());
        Assert.assertTrue(supportedExtensions.contains(MeshLoader.OBJ));
    }

    @Test
    public void testAddLoader() {
        int loaders = MeshLoader.getInstance().supportedExtensions().size();
        MeshLoader.getInstance().addLoader("fbx", null);

        Assert.assertEquals(loaders + 1, MeshLoader.getInstance().supportedExtensions().size());
    }

    @Test
    public void testLoaderWithRightPath() {
        Model model = MeshLoader.getInstance().loadModel("stone/stone.obj");
        Assert.assertNotNull(model);
    }

}
