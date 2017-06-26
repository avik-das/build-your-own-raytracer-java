package com.avikdas.raytracer;

import com.avikdas.raytracer.image.Image;
import com.avikdas.raytracer.image.ImageColor;

import java.io.IOException;
import java.nio.file.Paths;

public class RayTracer {
    private static final int W = 256;
    private static final int H = 192;

    public static void main(String [] args) throws IOException {
        try (Image image = new Image(W, H)) {
            for (int x = 0; x < W; x++)
                for (int y = 0; y < H; y++) {
                    int r = (int) (((float) x) / W * ImageColor.MAX);
                    int g = (int) (((float) y) / H * ImageColor.MAX);
                    int b = 0x88;

                    image.plotPixel(x, y, new ImageColor(r, g, b));
                }

            image.save(Paths.get(args[0]));
        }
    }
}
