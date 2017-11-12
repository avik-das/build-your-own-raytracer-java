package com.avikdas.raytracer.image;

import lombok.RequiredArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequiredArgsConstructor
public class ReadableImage {
    private final BufferedImage image;

    public static ReadableImage fromResource(String resource) throws IOException {
        BufferedImage image = ImageIO.read(
                ReadableImage
                        .class
                        .getResourceAsStream(resource)
        );

        return new ReadableImage(image);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public ImageColor readPixel(int x, int y) {
        int color = image.getRGB(x, y);
        int r = (color & 0x00ff0000) >> 16;
        int g = (color & 0x0000ff00) >> 8;
        int b =  color & 0x000000ff;

        return new ImageColor(r, g, b);
    }
}
