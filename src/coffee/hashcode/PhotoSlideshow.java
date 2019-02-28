package coffee.hashcode;

import coffee.hashcode.parser.ImageParser;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PhotoSlideshow {

    public static void main(String[] args) throws IOException {
        File inputFile = new File("datasets/a_example.txt");
        String fileData = FileUtilities.readFileFully(inputFile);
        HashMap<String, List<Image>> categorisedImages = ImageParser.parseInputFile(fileData);
        SlideCreator creator = new SlideCreator(categorisedImages);
        List<Slide> slideShow = creator.getSlideShow();

        OutputWriter outputWriter = new OutputWriter();
        outputWriter.print(slideShow);
    }

}
