package com.avikdas.raytracer.tracer;

import com.avikdas.raytracer.scene.*;
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

        return colorFromAnyObjectHit(ray).clamped();
    }

    private Color colorFromAnyObjectHit(Ray ray) {
        return scene
                .getObjects()
                .stream()
                .map(obj -> obj
                        .earliestIntersection(ray)
                        .map(t -> new RayCastHit(
                                obj,
                                t,
                                obj.normalAt(ray.at(t))
                        )))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min((h0, h1) -> (int) Math.signum(h0.getT() - h1.getT()))
                .map(hit -> phongLightingAtPoint(
                        scene,
                        hit.getObject(),
                        ray.at(hit.getT()),
                        hit.getNormal(),
                        ray.getDirection().inverted().normalized()
                ))
                .orElse(Color.BLACK);
    }

    private Color phongLightingAtPoint(
            Scene scene,
            SceneObject object,
            Vector3 point,
            Vector3 normal,
            Vector3 view) {
        Material material = object.getMaterial();

        Color lightContributions = scene
                .getLights()
                .stream()
                .filter(light ->
                        light.getPosition().minus(point).dot(normal) > 0)
                .map(light -> {
                    Vector3 l = light.getPosition().minus(point).normalized();
                    Vector3 r = normal.times(l.dot(normal) * 2).minus(l);

                    Color diffuse = light
                            .getIntensityDiffuse()
                            .times(material.getKDiffuse())
                            .times(l.dot(normal));

                    Color specular = light
                            .getIntensitySpecular()
                            .times(material.getKSpecular())
                            .times((float) Math.pow(
                                    r.dot(view), material.getAlpha()));

                    return diffuse.plus(specular);
                })
                .reduce(Color.BLACK, Color::plus);

        Color ambient = material
                .getKAmbient()
                .times(scene.getAmbientLight());

        return ambient.plus(lightContributions);
    }
}
