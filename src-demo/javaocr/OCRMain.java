package javaocr;

import net.sourceforge.javaocr.gui.meanSquareOCR.TrainingImageSpec;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class OCRMain {

    public static ArrayList<TrainingImageSpec> getTraningImages () {
        
        ArrayList<TrainingImageSpec> ret = new ArrayList<TrainingImageSpec>(2);
        
        CharacterRange cr1 = new CharacterRange('0', '9');
        TrainingImageSpec spec1 = new TrainingImageSpec();
        spec1.setCharRange(cr1);
        spec1.setFileLocation("./ocrTests/digits.jpg");
        ret.add(spec1);
        return ret;
    }
    
    private static HashMap<Character, ArrayList<TrainingImage>> getTrainingImageHashMap(ArrayList<TrainingImageSpec> imgs) throws Exception
    {
        TrainingImageLoader loader = new TrainingImageLoader();
        HashMap<Character, ArrayList<TrainingImage>> trainingImages = new HashMap<Character, ArrayList<TrainingImage>>();
        Frame frame = new Frame();

        for (int i = 0; i < imgs.size(); i++)
        {
            loader.load(
                    frame,
                    imgs.get(i).getFileLocation(),
                    imgs.get(i).getCharRange(),
                    trainingImages);
        }

        return trainingImages;
    }
    public static void main(String[] args) throws Exception
    {
        
        OCRScanner scanner = new OCRScanner();
        ArrayList<TrainingImageSpec> specs = getTraningImages();
        HashMap<Character, ArrayList<TrainingImage>> images = getTrainingImageHashMap(specs);
        scanner.addTrainingImages(images);
        BufferedImage targetImage = ImageIO.read(new File("./ocrTests/shuffledDigits.jpg"));
        String scaned = scanner.scan(targetImage, 0,0,0,0, null);
        System.out.println("scaned: " + scaned);
    }
}
