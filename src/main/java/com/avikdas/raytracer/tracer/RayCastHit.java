package com.avikdas.raytracer.tracer;

import com.avikdas.raytracer.scene.SceneObject;
import lombok.Value;

@Value
class RayCastHit {
    SceneObject object;
    float t;
}
