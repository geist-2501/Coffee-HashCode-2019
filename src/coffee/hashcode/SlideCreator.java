package coffee.hashcode;

import coffee.hashcode.Image.Orientation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SlideCreator
{
    HashMap<String, List<Image>> slides;

    List<Slide> slideShow;

    /**
     * Create a new slide creator.
     * @param slides    Hashmap of tags and their associated images.
     */
    public SlideCreator(HashMap<String, List<Image>> slides)
    {
        this.slides = slides;
        slideShow = new ArrayList<Slide>();
    }

    private HashMap<String, List<Slide>> preprocessVerticals(){
        HashMap<String, List<Slide>> output = new HashMap<>();

        List<Image> images = slides.values().stream().flatMap(Collection::stream).sorted(Comparator.comparing(Image::getTagNumber)).collect(Collectors.toList());
        List<Image> vImages = images.stream().filter(i -> i.getOrientation() == Orientation.VERTICAL).collect(Collectors.toList());
        List<Image> hImages = images.stream().filter(i -> i.getOrientation() == Orientation.HORIZONTAL).collect(Collectors.toList());

        for (int i = 0; i < Math.floor(vImages.size()); i++) {
            insertAtAllTagLocations(output, new VSlide(vImages.get(i), vImages.get(vImages.size() - 1 - i)));
        }
        hImages.stream().map(HSlide::new).forEach(i -> insertAtAllTagLocations(output, i));

        // TODO: replace this with some magic

        return output;
    }

    /**
     * Inserts the given image into all lists of images associated with the keys that the image has. For any entry that does not already exist it
     * will create a new array list in the output hashmap
     *
     * @param output the hashmap to insert the image into
     * @param image  the image to insert into the hashmap
     */
    private static void insertAtAllTagLocations(HashMap<String, List<Slide>> output, Slide image) {
        for (String tag : image.getTags()) {
            if (output.containsKey(tag)) {
                output.get(tag).add(image);
            } else {
                List<Slide> collection = new ArrayList<>();
                collection.add(image);
                output.put(tag, collection);
            }
        }
    }

    /**
     * Creates a slideshow.
     */
    private void CreateSlideShow(HashMap<String, List<Slide>> slides){}
    {
        //TODO: (Blair) like, the actual meat of the project goes here.


    }


    public List<Slide> getSlideShow()
    {
        CreateSlideShow(preprocessVerticals());
        return slideShow;
    }
}