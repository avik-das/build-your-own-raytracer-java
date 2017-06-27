package com.avikdas.raytracer.tracer;

import com.avikdas.raytracer.scene.SceneObject;
import com.avikdas.raytracer.scene.Vector3;
import lombok.Value;

@Value
class RayCastHit {
    SceneObject object;
    float t;
    Vector3 normal;
}
