import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;

public class Pattern {
    private final String[] partsOfSpeech;

    public Pattern(String... partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public Head head(List<Couple> couples) {
        return new Head(couples.subList(0, min(partsOfSpeech.length, couples.size())));
    }

    public class Head {
        private final List<Couple> couples;

        public Head(List<Couple> couples) {
            this.couples = couples;
        }

        public boolean matches() {
            Object[] otherPartsOfSpeech = couples.stream()
                    .map(couple -> couple.partOfSpeech)
                    .toArray();
            return Arrays.equals(partsOfSpeech, otherPartsOfSpeech);
        }

        public String tokens() {
            return couples.stream()
                    .map(couple -> couple.token)
                    .collect(joining(" "));

        }
    }
}
