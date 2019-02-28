package coffee.hashcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputWriter {

    private String outputFile;

    public OutputWriter(String outputFile) {
        this.outputFile = outputFile;
    }

    public void print(List<Slide> slides) throws IOException {

        PrintWriter output = new PrintWriter(outputFile);
        output.println(slides.size());

        for (Slide current : slides){
            if(current instanceof HSlide){
                output.println(((HSlide) current).getImage().getId());
            }
            else if (current instanceof VSlide){
                output.print(((VSlide) current).getLeft().getId());
                output.print(" ");
                output.print(((VSlide) current).getRight().getId());
                output.println();
            }
        }
        output.close();
    }
}
