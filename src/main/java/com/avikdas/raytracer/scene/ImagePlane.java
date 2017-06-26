package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class ImagePlane {
    Vector3 topLeft;
    Vector3 topRight;
    Vector3 bottomLeft;
    Vector3 bottomRight;
}
