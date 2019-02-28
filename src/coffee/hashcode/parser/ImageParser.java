package coffee.hashcode.parser;

import coffee.hashcode.Image;
import coffee.hashcode.Image.Orientation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ImageParser {

    /**
     * Converts the input data files in the form
     * <pre>
     *     [number of images]
     *     [orientation] [tag number] [tags]...
     * </pre>
     *
     * @param inputFile the input data from the dataset
     * @return a HashMap with keys as tags and lists of images containing that key
     */
    public static HashMap<String, List<Image>> parseInputFile(String inputFile) {
        HashMap<String, List<Image>> output = new HashMap<>();

        String[] lines = inputFile.split("\n");
        long amount = Long.parseLong(lines[0].trim());
        for (int i = 1; i <= 1 + amount; i++) {
            String[] elements = lines[i].split(" ");
            Image image = new Image(i - 1, Orientation.parseOrientation(elements[0]), Integer.parseInt(elements[1]), loadTags(Arrays.copyOfRange(elements, 2, elements.length)));
            insertAtAllTagLocations(output, image);
        }

        return output;
    }

    /**
     * Inserts the given image into all lists of images associated with the keys that the image has. For any entry that does not already exist it
     * will create a new array list in the output hashmap
     *
     * @param output the hashmap to insert the image into
     * @param image  the image to insert into the hashmap
     */
    private static void insertAtAllTagLocations(HashMap<String, List<Image>> output, Image image) {
        for (String tag : image.getTags()) {
            if (output.containsKey(tag)) {
                output.get(tag).add(image);
            } else {
                List<Image> collection = new ArrayList<>();
                collection.add(image);
                output.put(tag, collection);
            }
        }
    }

    /**
     * Converts the given array into a hashset via a {@link Arrays#asList(Object[])} call
     *
     * @param data the array of tags to convert into a set
     * @return the set of tags
     * @// TODO(Ryan < vitineth >): 28/02/19 Profile this to see if the additional call to asList is inefficient
     */
    private static Set<String> loadTags(String[] data) {
        return new HashSet<>(Arrays.asList(data));
    }

}
