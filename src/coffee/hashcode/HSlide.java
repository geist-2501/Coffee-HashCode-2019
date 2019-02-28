package coffee.hashcode;

import java.util.Set;

public class HSlide extends Slide
{
    Image image;

    public HSlide(Image image)
    {
        this.image = image;
    }


    /**
     * Gets the tags of images in the slide.
     * @return  Images tags.
     */
    public Set<String> getTags()
    {
        return image.getTags();
    }




    /**
     * Gets the number of tags in the slide.
     * @return  Amount of tags.
     */
    public int getTagsNumber()
    {
        return image.getTagNumber();
    }
}