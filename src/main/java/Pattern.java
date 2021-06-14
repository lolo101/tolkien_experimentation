import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;

public class Pattern {
    private final String[] partsOfSpeech;
    private final Head NeverMatchingHead = new Head(emptyList()) {
        @Override
        public boolean matches() {
            return false;
        }
    };

    public Pattern(String... partsOfSpeech) {
        this.partsOfSpeech = partsOfSpeech;
    }

    public Head head(List<Couple> couples) {
        if (couples.size() < partsOfSpeech.length) {
            return NeverMatchingHead;
        }
        return new Head(couples.subList(0, partsOfSpeech.length));
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
