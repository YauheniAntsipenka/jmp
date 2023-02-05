package task7;

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
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        String srcName = "bear.jpg";
        File srcFile = new File(Objects.requireNonNull(Main.class.getResource(srcName)).toURI());
        BufferedImage image = ImageIO.read(srcFile);

        LOGGER.info("Source image: {}", srcName);

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

    public static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + 10000);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors + " processor"
            + (processors != 1 ? "s are " : " is ")
            + "available");

        ForkBlur fb = new ForkBlur(src, 0, src.length, dst);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
            " milliseconds.");

        BufferedImage dstImage =
            new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
}
