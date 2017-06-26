package com.avikdas.raytracer.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

public class Image implements Closeable {
    private final BufferedImage image;
    private final Graphics2D graphics;

    public Image(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        graphics = image.createGraphics();
    }

    public void plotPixel(int x, int y, ImageColor color) {
        graphics.setPaint(
                new Color(
                        color.getR(),
                        color.getG(),
                        color.getB()
                )
        );

        graphics.fillRect(x, y, 1, 1);
    }

    public void save(Path file) throws IOException {
        ImageIO.write(image, "PNG", file.toFile());
    }

    @Override
    public void close() throws IOException {
        graphics.dispose();
    }
}
