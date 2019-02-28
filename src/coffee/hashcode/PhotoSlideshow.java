package coffee.hashcode;

import coffee.hashcode.parser.ImageParser;
import java.io.File;
import java.io.IOException;

public class PhotoSlideshow {

    public static void main(String[] args) throws IOException {
        System.out.println(ImageParser.parseInputFile(FileUtilities.readFileFully(new File("datasets/a_example.txt"))));
    }

}
