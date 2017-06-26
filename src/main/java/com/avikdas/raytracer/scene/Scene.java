package com.avikdas.raytracer.scene;

import lombok.Value;

import java.util.List;

@Value
public class Scene {
    Vector3 camera;
    ImagePlane ImagePlane;
    Color ambientLight;
    List<Light> lights;
    List<SceneObject> objects;
}
