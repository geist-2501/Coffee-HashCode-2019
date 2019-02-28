package coffee.hashcode;

import java.util.HashSet;
import java.util.Set;

public class VSlide extends Slide
{
    //Just stores two images;
    Image left;
    Image right;

    Set<String> tags;

    public VSlide(Image left, Image right)
    {
        this.left = left;
        this.right = right;

        //cache all calculations.

        tags = new HashSet<String>();
        //Populate left set of tags.
        for (String tag : left.getTags()) 
        {
            tags.add(tag);    
        }

        //Populate right set of tags.
        for (String tag : left.getTags()) 
        {
            //No duplicates allowed.
            if ( ! tags.contains(tag)) 
            {
                tags.add(tag);    
            }    
        }



    }


    /**
     * Gets the tags of images in the slide.
     * @return  Images tags.
     */
    public Set<String> getTags()
    {
        return tags;
    }


    /**
     * Gets the number of tags in the slide.
     * @return  Amount of tags.
     */
    public int getTagsNumber()
    {
        return tags.size();
    }

    public Image getLeft() {
        return left;
    }

    public Image getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VSlide vSlide = (VSlide) o;

        if (left != null ? !left.equals(vSlide.left) : vSlide.left != null)
            return false;
        if (right != null ? !right.equals(vSlide.right) : vSlide.right != null)
            return false;
        return tags != null ? tags.equals(vSlide.tags) : vSlide.tags == null;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}