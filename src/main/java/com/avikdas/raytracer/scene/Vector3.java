package com.avikdas.raytracer.scene;

import lombok.Value;

@Value
public class Vector3 {
    float x;
    float y;
    float z;

    public Vector3 times(float scalar) {
        return new Vector3(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }

    public Vector3 plus(Vector3 other) {
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

    public Vector3 inverted() {
        return this.times(-1);
    }

    public float dot(Vector3 other) {
        return x * other.x +
                y * other.y +
                z * other.z;
    }

    public float norm() {
        return (float) Math.sqrt(this.dot(this));
    }

    public Vector3 normalized() {
        return this.times(1 / norm());
    }

    public static Vector3 lerp(Vector3 start, Vector3 end, float t) {
        return start.times(1 - t).plus(end.times(t));
    }
}
