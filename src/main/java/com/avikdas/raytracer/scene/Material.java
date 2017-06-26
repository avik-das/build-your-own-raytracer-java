package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class Material {
    Color kAmbient;
    Color kDiffuse;
    Color kSpecular;
    Color kReflection;
    int alpha;
}
