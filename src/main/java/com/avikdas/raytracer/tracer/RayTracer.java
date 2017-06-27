package com.avikdas.raytracer.tracer;

import com.avikdas.raytracer.scene.Color;
import com.avikdas.raytracer.scene.Scene;
import com.avikdas.raytracer.scene.Vector3;
import lombok.Value;

import java.util.Optional;

@Value
public class RayTracer {
    Scene scene;
    int w;
    int h;

    public Color tracedValueAtPixel(int x, int y) {
        float xt = ((float) x) / w;
        float yt = ((float) h - y - 1) / h;

        Vector3 top = Vector3.lerp(
                scene.getImagePlane().getTopLeft(),
                scene.getImagePlane().getTopRight(),
                xt
        );

        Vector3 bottom = Vector3.lerp(
                scene.getImagePlane().getBottomLeft(),
                scene.getImagePlane().getBottomRight(),
                xt
        );

        Vector3 point = Vector3.lerp(bottom, top, yt);
        Ray ray = new Ray(
                point,
                point.minus(scene.getCamera())
        );

        return colorFromAnyObjectHit(ray);
    }

    private Color colorFromAnyObjectHit(Ray ray) {
        return scene
                .getObjects()
                .stream()
                .map(obj -> obj
                        .earliestIntersection(ray)
                        .map(t -> new RayCastHit(obj, t)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min((h0, h1) -> (int) Math.signum(h0.getT() - h1.getT()))
                .map(hit -> hit.getObject().getMaterial().getKDiffuse())
                .orElse(Color.BLACK);
    }
}
