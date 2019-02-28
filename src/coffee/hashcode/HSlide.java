package coffee.hashcode;

import java.util.Set;

public class HSlide extends Slide
{
    Image image;

    public HSlide(Image image)
    {
        this.image = image;
    }

    public Image getImage() {
        return image;
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
        return image.getTagsNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        HSlide hSlide = (HSlide) o;

        return image != null ? image.equals(hSlide.image) : hSlide.image == null;
    }

    @Override
    public int hashCode() {
        return image != null ? image.hashCode() : 0;
    }
}