package com.avikdas.raytracer.scene;

import com.avikdas.raytracer.image.ImageColor;
import com.avikdas.raytracer.image.ReadableImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.IOException;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Texture {
    ReadableImage image;

    public static Texture fromResource(String resource) throws IOException {
        return new Texture(ReadableImage.fromResource(resource));
    }

    Color colorAtUV(float u, float v) {
        ImageColor color = image.readPixel(
                (int) Math.floor(u * image.getWidth()),
                (int) Math.floor(v * image.getHeight())
        );

        return new Color(
                (float) color.getR() / ImageColor.MAX,
                (float) color.getG() / ImageColor.MAX,
                (float) color.getB() / ImageColor.MAX
        );
    }
}
