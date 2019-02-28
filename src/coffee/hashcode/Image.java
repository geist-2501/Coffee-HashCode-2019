package coffee.hashcode;

import java.util.Set;

/**
 * Represents one data record in the input file which itself represents an image
 */
public class Image {

    public enum Orientation{
        HORIZONTAL, VERTICAL
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

    public int getTagNumber() {
        return tagNumber;
    }

    public Set<String> getTags() {
        return tags;
    }

}
