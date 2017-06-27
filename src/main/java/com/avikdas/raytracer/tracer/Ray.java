package com.avikdas.raytracer.tracer;

import com.avikdas.raytracer.scene.Vector3;
import lombok.Value;

@Value
public class Ray {
    Vector3 origin;
    Vector3 direction;
}
