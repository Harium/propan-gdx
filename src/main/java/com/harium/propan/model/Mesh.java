package com.harium.propan.model;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;

public class Mesh extends ModelInstance {

    private final Vector3 center = new Vector3();
    private final Vector3 dimensions = new Vector3();
    private float radius;

    private final BoundingBox bounds = new BoundingBox();

    //Used in isVisible
    private final static Vector3 position = new Vector3();

    public Mesh(Model model) {
        super(model);
        init();
    }

    public Mesh(Model model, String nodeId, boolean mergeTransform) {
        super(model, nodeId, mergeTransform);
        init();
    }

    public Mesh(Model model, Matrix4 transform, String nodeId, boolean mergeTransform) {
        super(model, transform, nodeId, mergeTransform);
        init();
    }

    public Mesh(Model model, String nodeId, boolean parentTransform, boolean mergeTransform) {
        super(model, nodeId, parentTransform, mergeTransform);
    }

    public Mesh(Model model, Matrix4 transform, String nodeId, boolean parentTransform, boolean mergeTransform) {
        super(model, transform, nodeId, parentTransform, mergeTransform);
        init();
    }

    public Mesh(Model model, String nodeId, boolean recursive, boolean parentTransform, boolean mergeTransform) {
        super(model, nodeId, recursive, parentTransform, mergeTransform);
        init();
    }

    public Mesh(Model model, Matrix4 transform, String nodeId, boolean recursive, boolean parentTransform, boolean mergeTransform) {
        super(model, transform, nodeId, recursive, parentTransform, mergeTransform);
        init();
    }

    public Mesh(Model model, Matrix4 transform, String nodeId, boolean recursive, boolean parentTransform, boolean mergeTransform, boolean shareKeyframes) {
        super(model, transform, nodeId, recursive, parentTransform, mergeTransform, shareKeyframes);
        init();
    }

    public Mesh(Model model, String... rootNodeIds) {
        super(model, rootNodeIds);
        init();
    }

    public Mesh(Model model, Matrix4 transform, String... rootNodeIds) {
        super(model, transform, rootNodeIds);
        init();
    }

    public Mesh(Model model, Array<String> rootNodeIds) {
        super(model, rootNodeIds);
        init();
    }

    public Mesh(Model model, Matrix4 transform, Array<String> rootNodeIds) {
        super(model, transform, rootNodeIds);
        init();
    }

    public Mesh(Model model, Matrix4 transform, Array<String> rootNodeIds, boolean shareKeyframes) {
        super(model, transform, rootNodeIds, shareKeyframes);
        init();
    }

    public Mesh(Model model, Vector3 position) {
        super(model, position);
        init();
    }

    public Mesh(Model model, float x, float y, float z) {
        super(model, x, y, z);
        init();
    }

    public Mesh(Model model, Matrix4 transform) {
        super(model, transform);
        init();
    }

    public Mesh(ModelInstance copyFrom) {
        super(copyFrom);
        init();
    }

    public Mesh(ModelInstance copyFrom, Matrix4 transform) {
        super(copyFrom, transform);
        init();
    }

    public Mesh(ModelInstance copyFrom, Matrix4 transform, boolean shareKeyframes) {
        super(copyFrom, transform, shareKeyframes);
        init();
    }

    //Fixed Mesh
    private void init() {
        calculateBoundingBox(bounds);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        radius = dimensions.len() / 2f;
    }

    public boolean isVisible(final Camera cam) {
        transform.getTranslation(position);
        position.add(center);
        return cam.frustum.sphereInFrustum(position, radius);
    }

}
