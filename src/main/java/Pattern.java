import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

public class Pattern {
    private final String[] partsOfSpeech;

    public Pattern(String... partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public Head head(List<Word> words) {
        return new Head(words.subList(0, min(partsOfSpeech.length, words.size())));
    }

    public class Head {
        private final List<Word> words;

        public Head(List<Word> words) {
            this.words = words;
        }

        public boolean matches() {
            Object[] otherPartsOfSpeech = words.stream()
                    .map(couple -> couple.partOfSpeech)
                    .toArray();
            return Arrays.equals(partsOfSpeech, otherPartsOfSpeech);
        }

        public String tokens() {
            return words.stream()
                    .map(couple -> couple.token)
                    .collect(joining(" "));
        }
    }
}
