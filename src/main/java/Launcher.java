import core.ImageProcessor;
import external.ImageReader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        ImageReader imageReader = new ImageReader();
        BufferedImage inputImage = imageReader.readInputImage();
        if(inputImage!=null) {
            inputImage = new ImageProcessor().processX2(inputImage);
            imageReader.writeOutputImage(inputImage);
        }
    }


}