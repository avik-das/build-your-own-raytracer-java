package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class Material {
    Color kAmbient;
    Color kDiffuse;
    Color kSpecular;
    Color kReflection;
    int alpha;
    Texture texture;

    public Color getDiffuseColor(Vector3 normal) {
        if (texture == null) {
            return kDiffuse;
        }

        float x = normal.getX();
        float y = normal.getY();
        float z = normal.getZ();
        float u = (float) (0.5f + Math.atan2(z, x) / (2 * Math.PI));
        float v = (float) (0.5f - Math.asin(y) / Math.PI);

        return texture.colorAtUV(u, v);
    }
}
