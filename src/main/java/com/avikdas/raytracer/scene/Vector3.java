package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class Vector3 {
    float x;
    float y;
    float z;

    private Vector3 times(float scalar) {
        return new Vector3(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }

    private Vector3 plus(Vector3 other) {
        return new Vector3(
                x + other.x,
                y + other.y,
                z + other.z
        );
    }

    public Vector3 minus(Vector3 other) {
        return new Vector3(
                x - other.x,
                y - other.y,
                z - other.z
        );
    }

    float dot(Vector3 other) {
        return x * other.x +
                y * other.y +
                z * other.z;
    }

    public static Vector3 lerp(Vector3 start, Vector3 end, float t) {
        return start.times(1 - t).plus(end.times(t));
    }
}
