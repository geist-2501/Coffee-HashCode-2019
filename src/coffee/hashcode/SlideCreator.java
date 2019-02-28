package coffee.hashcode;

import coffee.hashcode.Image.Orientation;
import coffee.hashcode.parser.ImageParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

        for (int i = 0; i < Math.floor(vImages.size() / 2.0); i++) {
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
        List<Slide> output = new ArrayList<>();

        Slide current = reduced.get(0);
        reduced.remove(current);

        while (!reduced.isEmpty()) {
            Slide bestMatch = null;
            int bestScore = -1;
            for (int i = 0; i < Math.min(100, reduced.size()); i++) {
                int score = calculateScore(current, reduced.get(i));
                if (score > bestScore){
                    bestScore = score;
                    bestMatch = reduced.get(i);
                }
            }


//            for (Slide option : reduced) {
//                if (option.equals(current))
//                    continue;
//
//                double score = calculateSimilarityScore(current, option);
//                if (score > 0.14 / 3) {
//                    bestMatch = option;
//
//                    break;
//                }
//            }


            reduced.remove(bestMatch);
            output.add(bestMatch);
            current = bestMatch;

            if (reduced.size() % 1000 == 0)
                System.out.println(output.size() + " / " + reduced.size());
        }

        slideShow = output;
    }

    private int calculateScore(Slide first, Slide second) {
        HashSet<String> firstTags = new HashSet<>(first.getTags());
        firstTags.retainAll(second.getTags());

        int commonNumber = firstTags.size();
        int firstMissing = first.getTags().size() - commonNumber;
        int secondMissing = second.getTags().size() - commonNumber;

        return Math.min(commonNumber, Math.min(firstMissing, secondMissing));
    }

    private double calculateSimilarityScore(Slide first, Slide second) {

        HashSet<String> firstTags = new HashSet<>(first.getTags());
        firstTags.addAll(second.getTags());
        HashSet<String> firstTags2 = new HashSet<>(first.getTags());
        firstTags2.retainAll(second.getTags());

        double overlap = firstTags2.size();
        double different = firstTags.size() - overlap;
//        firstTags.retainAll(second.getTags());
//
//        int commonNumber = firstTags.size();
//        int firstMissing = first.getTags().size() - commonNumber;
//        int secondMissing = second.getTags().size() - commonNumber;

//        return commonNumber - ((firstMissing + secondMissing) / 2.0);
        return overlap / different;
    }


    public List<Slide> getSlideShow() {
        CreateSlideShow(preprocessVerticals());
        return slideShow;
    }
}