package coffee.hashcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Creates a slideshow.
     */
    private void CreateSlideShow()
    {
        //TODO: (Blair) like, the actual meat of the project goes here.


    }


    public List<Slide> getSlideShow()
    {
        CreateSlideShow();
        return slideShow;
    }
}