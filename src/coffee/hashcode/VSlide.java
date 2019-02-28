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
}