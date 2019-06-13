package javaocr;

import net.sourceforge.javaocr.ocrPlugins.CharacterExtractor;

import java.io.File;

public class CharacterExtractorDemo {
    public static void main(String[] args) throws Exception
    {
        
        CharacterExtractor slicer = new CharacterExtractor();
        File inputImage = new File("ocrTests/wechat.png");
        File outputDir = new File("outputDir");
        slicer.slice(inputImage, outputDir, 30, 30);
    }
}
