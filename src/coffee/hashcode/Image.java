package coffee.hashcode;

import java.util.Set;

/**
 * Represents one data record in the input file which itself represents an image
 */
public class Image implements Taggable {

    public enum Orientation{
        HORIZONTAL, VERTICAL;

        public static Orientation parseOrientation(String input){
            if (input.equalsIgnoreCase("V")) return VERTICAL;

            // TODO(Ryan): dangerous?
            else return HORIZONTAL;
        }
    }

    /**
     * The ID of the image in the input file (line number - 2)
     */
    private int id;
    /**
     * If the photo is vertical (V) or horizontal (H)
     */
    private Orientation orientation;
    /**
     * The number of tags as specified in the file
     */
    private int tagNumber;
    /**
     * All tags in the file
     */
    private Set<String> tags;

    public Image(int id, Orientation orientation, int tagNumber, Set<String> tags) {
        this.id = id;
        this.orientation = orientation;
        this.tagNumber = tagNumber;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getTagsNumber() {
        return tagNumber;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Image{" +
            "id=" + id +
            ", orientation=" + orientation +
            ", tagNumber=" + tagNumber +
            '}';
    }
}
