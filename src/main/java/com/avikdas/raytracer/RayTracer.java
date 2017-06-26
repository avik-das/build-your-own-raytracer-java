package com.avikdas.raytracer;

import com.avikdas.raytracer.image.Image;
import com.avikdas.raytracer.image.ImageColor;
import com.avikdas.raytracer.scene.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class RayTracer {
    private static final int W = 1920;
    private static final int H = 1080;

    private static final Scene SCENE = new Scene(
            new Vector3(0, 0, 2),
            new ImagePlane(
                    new Vector3(-1.92f,  1.08f, -0.5f),
                    new Vector3( 1.92f,  1.08f, -0.5f),
                    new Vector3(-1.92f, -1.08f, -0.5f),
                    new Vector3( 1.92f, -1.08f, -0.5f)
            ),
            new Color(0.5f, 0.5f, 0.5f),
            Arrays.asList(
                    new Light(
                            new Vector3(-5, 1, 0.5f),
                            new Color(0.8f, 0.8f, 0.8f),
                            new Color(0.8f, 0.8f, 0.8f)
                    ),
                    new Light(
                            new Vector3(5, -1, 0.5f),
                            new Color(0.7f, 0.7f, 0.7f),
                            new Color(0.8f, 0.8f, 0.8f)
                    )
            ),
            Arrays.asList(
                    new Sphere(
                            new Vector3(0, 0, -1.2f),
                            0.4f,
                            new Material(
                                    new Color(0.2f, 0.1f, 0.1f),
                                    new Color(0.4f, 0.1f, 0.1f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.9f, 0.5f, 0.5f),
                                    100
                            )
                    ),
                    new Sphere(
                            new Vector3(-1, 0, -0.8f),
                            0.2f,
                            new Material(
                                    new Color(0.1f, 0.2f, 0.1f),
                                    new Color(0.5f, 0.9f, 0.5f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.3f, 0.5f, 0.2f),
                                    25
                            )
                    ),
                    new Sphere(
                            new Vector3(1, 0f, -0.8f),
                            0.2f,
                            new Material(
                                    new Color(0.1f, 0.1f, 0.2f),
                                    new Color(0.5f, 0.5f, 0.9f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.2f, 0.3f, 0.5f),
                                    50
                            )
                    ),
                    new Sphere(
                            new Vector3(0, 0.7f, -0.8f),
                            0.2f,
                            new Material(
                                    new Color(0.1f, 0.1f, 0.2f),
                                    new Color(0.9f, 0.5f, 0.5f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.5f, 0.2f, 0.3f),
                                    50
                            )
                    ),
                    new Sphere(
                            new Vector3(0, -0.7f, -0.8f),
                            0.2f,
                            new Material(
                                    new Color(0.1f, 0.1f, 0.2f),
                                    new Color(0.9f, 0.5f, 0.9f),
                                    new Color(0.7f, 0.7f, 0.7f),
                                    new Color(0.5f, 0.2f, 0.5f),
                                    50
                            )
                    )
            )
    );

    public static void main(String [] args) throws IOException {
        com.avikdas.raytracer.tracer.RayTracer tracer =
                new com.avikdas.raytracer.tracer.RayTracer(SCENE, W, H);

        try (Image image = new Image(W, H)) {
            for (int x = 0; x < W; x++)
                for (int y = 0; y < H; y++) {
                    Color color = tracer.tracedValueAtPixel(x, y);
                    image.plotPixel(x, y, colorToImageColor(color));
                }

            image.save(Paths.get(args[0]));
        }
    }

    private static ImageColor colorToImageColor(Color color) {
        return new ImageColor(
                (int) (color.getR() * ImageColor.MAX),
                (int) (color.getG() * ImageColor.MAX),
                (int) (color.getB() * ImageColor.MAX)
        );
    }
}
