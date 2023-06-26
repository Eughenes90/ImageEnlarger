package core;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {


    public static BufferedImage processX2(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        int newWidth = inputImage.getWidth() * 2;
        int newHeight = inputImage.getHeight() * 2;

        inputImage = enlargeImage(inputImage, width, height, newWidth, newHeight);
        /*
         * File outputfile = new File("enlarged.png"); ImageIO.write(enlarged, "png",
         * outputfile);
         */
        inputImage = fillEmptyGaps(inputImage);
        return inputImage;
    }

    private static BufferedImage fillEmptyGaps(BufferedImage inputImage) {
        BufferedImage filled = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), 3);

        for (int i = 0; i < inputImage.getHeight(); i++) {
            for (int j = 0; j < inputImage.getWidth(); j++) {
                if (inputImage.getRGB(j, i) != 0) {
                    filled.setRGB(j, i, inputImage.getRGB(j, i));
                } else {
                    filled.setRGB(j, i, extractColor(filled, j, i));
                }
            }
        }
        return filled;
    }

    private static int extractColor(BufferedImage inputImage, int j, int i) {
        int[] minusJ = null;
        int[] plusJ = null;
        int[] minusI = null;
        int[] plusI = null;

        int[] averageJ;
        int[] averageI;

        int[] fullAverage;

        if (j > 0) {
            if (inputImage.getRGB(j - 1, i) != 0) minusJ = getRGB(inputImage, j - 1, i);
        }
        if (j + 1 < inputImage.getWidth()) {
            if (inputImage.getRGB(j + 1, i) != 0) plusJ = getRGB(inputImage, j + 1, i);
        }

        if (i > 0) {
            if (inputImage.getRGB(j, i - 1) != 0) minusI = getRGB(inputImage, j, i - 1);
        }
        if (i + 1 < inputImage.getHeight()) {
            if (inputImage.getRGB(j, i + 1) != 0) plusI = getRGB(inputImage, j, i + 1);
        }

        averageJ = getAverage(minusJ, plusJ);

        averageI = getAverage(minusI, plusI);

        fullAverage = getAverage(averageJ, averageI);

        Color average = new Color(fullAverage[0], fullAverage[1], fullAverage[2]); // Average color

        return average.getRGB();
    }

    private static int[] getRGB(BufferedImage img, int j, int i) {
        int color = img.getRGB(j, i);
        Color c = new Color(color);
        // Components will be in the range of 0..255:
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        return new int[]{red, green, blue};
    }

    private static int[] getAverage(int[] first, int[] second) {
        int[] toReturn = null;
        if (first != null && second != null) {
            toReturn = calculateAverage(first, second);
        } else if (first != null) {
            toReturn = first;
        } else {
            toReturn = second;
        }
        return toReturn;
    }

    private static int[] calculateAverage(int[] first, int[] second) {
        int mulRed = (int) Math.sqrt(((Math.pow(first[0], 2) + Math.pow(second[0], 2)) / 2));
        int mulGreen = (int) Math.sqrt(((Math.pow(first[1], 2) + Math.pow(second[1], 2)) / 2));
        int mulBlue = (int) Math.sqrt(((Math.pow(first[2], 2) + Math.pow(second[2], 2)) / 2));
        return new int[]{mulRed, mulGreen, mulBlue};
    }

    private static BufferedImage enlargeImage(BufferedImage inputImage, int width, int height, int newWidth, int newHeight) {
        BufferedImage end = new BufferedImage(newWidth, newHeight, 3);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                end.setRGB(j * 2, i * 2, inputImage.getRGB(j, i));
            }
        }
        return end;
    }
}
