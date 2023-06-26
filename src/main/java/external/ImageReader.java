package external;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class ImageReader extends Component {

    static Logger log = Logger.getLogger(ImageReader.class.getName());

    public BufferedImage readInputImage() throws IOException {
        /*
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG IMAGES", "png", "image");
        fileChooser.setFileFilter(filter);
        //fileChooser.setCurrentDirectory(new File("./src/main/resources/in/picture.png"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }else{
            System.out.println("Input Image not selected");
        }
        if(selectedFile!=null) {
            return ImageIO.read(selectedFile);
        }else{
            return null;
        }

         */
        //File in = new File("./src/main/resources/in/picture.png");
        //return ImageIO.read(in);
        File selectedFile = null;
        JFileChooser choice = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG IMAGES", "png", "image");
        choice.setFileFilter(filter);
        int option = choice.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            selectedFile = new File(choice.getSelectedFile().getAbsolutePath());
        }

        if (selectedFile != null) {
            return ImageIO.read(selectedFile);
        } else {
            return null;
        }
    }

    public void writeOutputImage(BufferedImage bufferedImage) throws IOException {
        /*
        // parent component of the dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            ImageIO.write(bufferedImage, "png", fileToSave);
        }

         */


        File outputfile = new File("./src/main/resources/out/final.png");
        ImageIO.write(bufferedImage, "png", outputfile);
    }
}
