package com.epam.jmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import javax.imageio.ImageIO;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class ImageChanger {

    private static final String IMAGE_NAME = "bear.jpg";
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageChanger.class);

    private ImageChanger() {}

    public static void blur() throws IOException, URISyntaxException {
        File srcFile = new File(Objects.requireNonNull(Main.class.getResource(IMAGE_NAME)).toURI());
        BufferedImage image = ImageIO.read(srcFile);

        LOGGER.info("Source image: {}", IMAGE_NAME);

        BufferedImage blurredImage = blur(image);

        String dstName = "blurred.jpg";
        File dstFile = new File(dstName);

        if (dstFile.createNewFile()) {
            LOGGER.info("Created: {}", dstFile);
        } else {
            LOGGER.info("File {} exists", dstFile);
        }

        ImageIO.write(blurredImage, "jpg", dstFile);
        LOGGER.info("Output image: {}", dstName);
    }

    private static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        LOGGER.info("Array size is {}", src.length);
        LOGGER.info("Threshold is " + 10000);

        int processors = Runtime.getRuntime().availableProcessors();
        LOGGER.info("{} processor available", processors);

        ForkBlur fb = new ForkBlur(src, 0, src.length, dst);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        LOGGER.info("Image blur took {} milliseconds.", endTime - startTime);

        BufferedImage dstImage =
            new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
}
