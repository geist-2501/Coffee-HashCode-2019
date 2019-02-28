package coffee.hashcode;

import coffee.hashcode.Image.Orientation;
import coffee.hashcode.parser.ImageParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SlideCreator {

    HashMap<String, List<Image>> slides;

    List<Slide> slideShow;

    /**
     * Create a new slide creator.
     *
     * @param slides Hashmap of tags and their associated images.
     */
    public SlideCreator(HashMap<String, List<Image>> slides) {
        this.slides = slides;
        slideShow = new ArrayList<>();
    }

    private HashMap<String, List<Slide>> preprocessVerticals() {
        HashMap<String, List<Slide>> output = new HashMap<>();

        List<Image> images = slides.values().stream().flatMap(Collection::stream).distinct().sorted(Comparator.comparing(Image::getTagsNumber)).collect(Collectors.toList());
        List<Image> vImages = images.stream().filter(i -> i.getOrientation() == Orientation.VERTICAL).collect(Collectors.toList());
        List<Image> hImages = images.stream().filter(i -> i.getOrientation() == Orientation.HORIZONTAL).collect(Collectors.toList());

        for (int i = 0; i < Math.floor(vImages.size()); i++) {
            ImageParser.insertAtAllTagLocations(output, new VSlide(vImages.get(i), vImages.get(vImages.size() - 1 - i)));
        }
        hImages.stream().map(HSlide::new).forEach(i -> ImageParser.insertAtAllTagLocations(output, i));

        // TODO: replace this with some magic

        return output;
    }

    /**
     * Creates a slideshow.
     */
    private void CreateSlideShow(HashMap<String, List<Slide>> slides) {
        //TODO: (Blair) like, the actual meat of the project goes here.

        List<Slide> reduced = slides.values().stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());

        Collections.shuffle(reduced);
//        Slide current = reduced.get(0);
//        reduced.remove(current);
//
//        while (!reduced.isEmpty()){
//            Slide bestMatch = null;
//            int bestScore = Integer.MIN_VALUE;
//            for (Slide option : reduced) {
//                if (option.equals(current))
//                    continue;
//
//                int score = calculateSlideScore(current, option);
//                if (score > bestScore) {
//                    bestMatch = option;
//                    bestScore = score;
//                }
//            }
//
//            reduced.remove(bestMatch);
//            output.add(bestMatch);
//
//            System.out.println(output.size() + " / " + reduced.size());
//        }

        slideShow = reduced;
    }

    private int calculateSlideScore(Slide first, Slide second) {
        HashSet<String> firstTags = new HashSet<>(first.getTags());
        firstTags.retainAll(second.getTags());

        int commonNumber = firstTags.size();
        int firstMissing = first.getTags().size() - commonNumber;
        int secondMissing = second.getTags().size() - commonNumber;

        return Math.min(Math.min(commonNumber, firstMissing), secondMissing);
    }


    public List<Slide> getSlideShow() {
        CreateSlideShow(preprocessVerticals());
        return slideShow;
    }
}