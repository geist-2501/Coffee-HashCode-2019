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

    private HashMap<String, List<Slide>> preprocessVerticals(){
        HashMap<String, List<Slide>> output = new HashMap<>();

        // TODO: replace this with some magic

        return output;
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