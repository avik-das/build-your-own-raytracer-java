package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class Sphere implements SceneObject {
    Vector3 center;
    float radius;
    Material material;
}
