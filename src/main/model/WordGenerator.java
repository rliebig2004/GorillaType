package model;

import java.util.*;

// Generates a random phrase from the 200 words declared.
public class WordGenerator {
    private List<String> wordList;

    public WordGenerator() {
        this.wordList = Arrays.asList("Yielding", "it", "you'll", "yielding", "they're", "to", "given", "there",
                "there", "face" ,".", "Dominion",".", "Stars", "god", "behold", "air", "lesser", "saw", "night",
                "green", "They're", "fifth", "saying", "their", "abundantly", "is", "you", "unto", "to", "firmament",
                "kind", "shall", "heaven",".", "All", "life", "also", "creeping", "his",".", "creepy", "be", "may",
                "evening", "which", "from", "deep", "multiply", "saw", "that", "be", "forth", "multiply", "forth",
                "herb",".", "Morning", "hath", "the", "upon", "together", "said",".", "Void", "in",".", "Fruitful",
                "their", "make", "earth", "unto", "saw", "dry", "great", "open", "light", "the", "under",".", "Tree",
                "gathered", "divided", "living", "us", "great", "i", "replenish", "may", "be", "divide", "signs", "two",
                "green", "fruitful", "it", "were", "land", "made", "given", "was", "said", "good", "their", "living",
                "own",".", "Sea", "fourth", "upon", "second", "every", "thing", "the", "firmament", "face", "replenish",
                "from", "greater", "moved", "a", "a", "midst", "them", "gathered", "upon",".", "Waters", "was", "our",
                "morning", "it", "face", "give", "firmament", "seas", "heaven", "spirit", "deep", "brought", "was", "a",
                "which",".", "Day", "you're", "one", "light", "meat",".", "Doesn't", "firmament", "made", "behold",
                "forth", "he", "is", "greater", "so", "signs", "third",".", "First", "move", "made", "thing", "to",
                "fill", "itself", "seasons", "darkness", "morning",".", "Greater", "It", "there", "open", "called",
                "gathering", "his", "made", "doesn't", "image", "without", "a", "seed", "together", "place", "moved",
                "you'll", "he", "moved", "Can't", "yielding", "male", "set", "can't", "and", "second", "winged",
                "great", "blessed",".", "Cattle", "was", "to", "Fish", "signs",".", "Whose", "isn't", "our", "man",
                "darkness", "deep", "abundantly",".");
    }

    // REQUIRES: Number of string in list > 0
    // MODIFIES: this
    // EFFECTS: Generates a list of string with 200 words.
    public List<String> getRandomWordList(int numWords) {
        List<String> result = new ArrayList<String>();
        Collections.shuffle(this.wordList);
        for (int i = 0; i <= numWords; i++) {
            result.add(this.wordList.get(i));
        }
        return result;
    }
}
